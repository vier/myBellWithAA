package net.monoboy.activity;

import net.monoboy.R;
import net.monoboy.core.GlobalHolder;
import net.monoboy.view.IncomingCallInfoDialog;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.Fullscreen;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.googlecode.androidannotations.annotations.SystemService;
import com.googlecode.androidannotations.annotations.ViewById;

import android.annotation.SuppressLint;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

@EActivity(R.layout.ring)
@NoTitle
@Fullscreen
public class RingActivity extends BaseActivity {
	
	@SystemService
	WindowManager windowManager;
	
	@ViewById(R.id.incoming_info)
	TextView incomingInfoTextView;
	
	@ViewById(R.id.call_video)
	VideoView myVideoView;
	
	@Extra("incoming")
	String incomingPhoneNumber;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED 
				| WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
				| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON 
				| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
		getWindow().setFormat(PixelFormat.TRANSPARENT);
    }
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		showDailog();
		return true;
	}
	
	@AfterViews
	void updateContents() {
		setVideoOnBackground();
		
		incomingInfoTextView.setText(incomingPhoneNumber);
		
	    myVideoView.setMediaController(new MediaController(this));

		myVideoView.setOnPreparedListener(new OnPreparedListener() {
		    @Override
		    public void onPrepared(MediaPlayer mp) {
		    	mp.setLooping(true);
		    }
		});
		
		myVideoView.requestFocus();
		myVideoView.start();
		
		windowManager.removeView(GlobalHolder.getCallReceiverImageView());
	}
	
	@Background
	void setVideoOnBackground() {
		Uri defaultVideofile = Uri.parse("android.resource://" + this.getPackageName() + "/raw/a");
	    myVideoView.setVideoURI(defaultVideofile);
	}
	
	@SuppressLint("NewApi")
	private void showDailog() {
		final IncomingCallInfoDialog incomingCallInfoDialog = new IncomingCallInfoDialog();
		
		incomingCallInfoDialog.show(this.getFragmentManager(), null);
	}
}
