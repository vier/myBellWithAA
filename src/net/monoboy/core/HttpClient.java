package net.monoboy.core;

import android.util.Log;

import com.loopj.android.http.*;

/**
 * 
 * @see https://github.com/AsyncHttpClient/async-http-client
 *
 */
public class HttpClient {
	
	protected static AsyncHttpClient httpClient = new AsyncHttpClient();
	
	public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		httpClient.get(url, params, responseHandler);
	}

	public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		httpClient.post(url, params, responseHandler);
	}
	
	public static void getDummyHttpResponse() {
		Log.d("vier", "getDummyHttpResponse start");
		
		HttpClient.get("http://naver.com", null, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				Log.d("vier",response);
				
			}
		});
		Log.d("vier", "getDummyHttpResponse end");
	}
}
