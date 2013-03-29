package net.monoboy.activity;

import net.monoboy.R;
import net.monoboy.constant.FlurryConstant;
import net.monoboy.core.FlurryHttpClient;
import net.monoboy.core.HttpClient;

import com.flurry.android.FlurryAgent;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.MediaController;
import android.widget.VideoView;

@EActivity(R.layout.main)
public class MainActivity extends BaseActivity {
	
	@ViewById(R.id.init_video)
	VideoView introVideoView;
	
	@Override
	protected void onStart() {
		super.onStart();

		FlurryAgent.setUseHttps(true);
		FlurryAgent.setReportLocation(false);
		FlurryAgent.onStartSession(this, FlurryConstant.FLURRY_LOGGING_API_KEY);
	}

	@Override
	protected void onStop() {
		super.onStop();
		FlurryAgent.onEndSession(this);
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Log.d("vier", "onCreate");
        FlurryHttpClient.getActiveUser(null, null);
    }
    
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		this.finish();
		  
		System.exit(0);
		return true;
	}
    
    @AfterViews
	void updateContents() {
		setVideoOnBackground();
		
		introVideoView.setMediaController(new MediaController(this));
		introVideoView.setOnPreparedListener(new MainPreparedListner());
		
		introVideoView.requestFocus();
		introVideoView.start();
	}
	
	@Background
	void setVideoOnBackground() {
		Uri defaultVideofile = Uri.parse("android.resource://" + this.getPackageName() + "/raw/a");
		introVideoView.setVideoURI(defaultVideofile);
	}
	
	private class MainPreparedListner implements OnPreparedListener {
		@Override
		public void onPrepared(MediaPlayer mp) {
			mp.setLooping(true);
		}
	}
}

