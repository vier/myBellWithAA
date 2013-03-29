package net.monoboy.core;

import java.util.Date;

import net.monoboy.constant.FlurryConstant;

import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class FlurryHttpClient extends HttpClient {
	
	private static RequestParams mParamMap = new RequestParams();

	public static void getActiveUser(Date startDate, Date endDate) {
		Log.d("vier", "getActiveUser start");
		
		mParamMap.put(FlurryConstant.ARG_READ_API_KEY, FlurryConstant.FLURRY_READ_API_KEY);
		mParamMap.put(FlurryConstant.ARG_LOGGING_API_KEY, FlurryConstant.FLURRY_LOGGING_API_KEY);
		mParamMap.put(FlurryConstant.ARG_START_DATE, "2013-03-25");
		mParamMap.put(FlurryConstant.ARG_END_DATE, "2013-03-26");
		
		httpClient.addHeader("Accept","application/xml");
		httpClient.addHeader("Content-Type","application/xml");
		
		HttpClient.get(FlurryConstant.URL_BASE + FlurryConstant.URL_ACTIVE_USERS, mParamMap, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				super.onSuccess(response);
				Log.d("vier",response);
			}
			
			@Override
			protected void handleFailureMessage(Throwable t, String message) {
				// TODO Auto-generated method stub
				super.handleFailureMessage(t, message);
				Log.d("vier", "message : "  + message + "\nthrowable : " + t);
			}
			
		});
		Log.d("vier", "getActiveUser end");
	}
}
