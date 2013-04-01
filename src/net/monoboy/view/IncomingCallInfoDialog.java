package net.monoboy.view;

import net.monoboy.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class IncomingCallInfoDialog extends CustomDialogFragment {

	@Override
	public View onCreateContents(LayoutInflater inflater, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView textView = (TextView)inflater.inflate(R.layout.custom_dialog_alert_message, parent, false);
		textView.setText("test");
		return textView;
	}

}
