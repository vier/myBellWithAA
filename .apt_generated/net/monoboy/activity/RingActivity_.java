//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations.
//


package net.monoboy.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.VideoView;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import net.monoboy.R.id;
import net.monoboy.R.layout;

public final class RingActivity_
    extends RingActivity
{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(layout.ring);
    }

    private void init_(Bundle savedInstanceState) {
        injectExtras_();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN, android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void afterSetContentView_() {
        myVideoView = ((VideoView) findViewById(id.call_video));
        incomingInfoTextView = ((TextView) findViewById(id.incoming_info));
        updateContents();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        afterSetContentView_();
    }

    @Override
    public void setContentView(View view, android.view.ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        afterSetContentView_();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        afterSetContentView_();
    }

    public static RingActivity_.IntentBuilder_ intent(Context context) {
        return new RingActivity_.IntentBuilder_(context);
    }

    @SuppressWarnings("unchecked")
    private<T >T cast_(Object object) {
        return ((T) object);
    }

    private void injectExtras_() {
        Intent intent_ = getIntent();
        Bundle extras_ = intent_.getExtras();
        if (extras_!= null) {
            if (extras_.containsKey("incoming")) {
                try {
                    incomingPhoneNumber = cast_(extras_.get("incoming"));
                } catch (ClassCastException e) {
                    Log.e("RingActivity_", "Could not cast extra to expected type, the field is left to its default value", e);
                }
            }
        }
    }

    @Override
    public void setIntent(Intent newIntent) {
        super.setIntent(newIntent);
        injectExtras_();
    }

    @Override
    public void setVideoOnBackground() {
        BackgroundExecutor.execute(new Runnable() {


            @Override
            public void run() {
                try {
                    RingActivity_.super.setVideoOnBackground();
                } catch (RuntimeException e) {
                    Log.e("RingActivity_", "A runtime exception was thrown while executing code in a runnable", e);
                }
            }

        }
        );
    }

    public static class IntentBuilder_ {

        private Context context_;
        private final Intent intent_;

        public IntentBuilder_(Context context) {
            context_ = context;
            intent_ = new Intent(context, RingActivity_.class);
        }

        public Intent get() {
            return intent_;
        }

        public RingActivity_.IntentBuilder_ flags(int flags) {
            intent_.setFlags(flags);
            return this;
        }

        public void start() {
            context_.startActivity(intent_);
        }

        public void startForResult(int requestCode) {
            if (context_ instanceof Activity) {
                ((Activity) context_).startActivityForResult(intent_, requestCode);
            } else {
                context_.startActivity(intent_);
            }
        }

        public RingActivity_.IntentBuilder_ incomingPhoneNumber(String incomingPhoneNumber) {
            intent_.putExtra("incoming", incomingPhoneNumber);
            return this;
        }

    }

}
