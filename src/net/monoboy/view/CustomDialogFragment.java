package net.monoboy.view;

import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.SystemService;

import net.monoboy.R;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

@SuppressLint("NewApi")
@EFragment
public abstract class CustomDialogFragment extends DialogFragment {
	public abstract View onCreateContents(LayoutInflater inflater, ViewGroup parent);
	
	@SystemService
	LayoutInflater layoutInflater;

	LayoutInflater inflater;
	LinearLayout layout;
	
	public CustomDialogFragment() {
		layout = (LinearLayout)layoutInflater.inflate(R.layout.custom_dialog_fragment, null, true);
		layout.setPadding(0, 0, 0, 0);
		setStyle(DialogFragment.STYLE_NO_TITLE, 0);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (layout != null) {
			FrameLayout body = (FrameLayout)layout.findViewById(R.id.custom_dialog_fragment_body);
			body.addView(onCreateContents(inflater, body));
		}
		Dialog dialog = getDialog();
		if (dialog != null && dialog.getWindow() != null) {
			dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		}
		return layout;
	}

	@Override
	public void dismiss() {
		getDialog().dismiss();
	}

}
