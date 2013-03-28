package net.monoboy.test;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.MediaController;
import android.widget.VideoView;

@EActivity(R.layout.main)
public class MainActivity extends BaseActivity {
	
	@ViewById(R.id.init_video)
	VideoView introVideoView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

