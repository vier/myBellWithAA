package net.monoboy.core;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import net.monoboy.constant.FlurryConstant;
import net.monoboy.model.FlurryActiveUser;

import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class FlurryHttpClient extends HttpClient {

	private static RequestParams mParamMap = new RequestParams();

	public static void getActiveUser(Date startDate, Date endDate) {
		mParamMap.put(FlurryConstant.ARG_READ_API_KEY, FlurryConstant.FLURRY_READ_API_KEY);
		mParamMap.put(FlurryConstant.ARG_LOGGING_API_KEY, FlurryConstant.FLURRY_LOGGING_API_KEY);
		mParamMap.put(FlurryConstant.ARG_START_DATE, "2013-03-25");
		mParamMap.put(FlurryConstant.ARG_END_DATE, "2013-03-26");

		httpClient.addHeader("Accept", "application/xml");
		httpClient.addHeader("Content-Type", "application/xml");

		HttpClient.get(FlurryConstant.URL_BASE + FlurryConstant.URL_ACTIVE_USERS, mParamMap, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				super.onSuccess(response);
				Log.d("vier", response);
				parsingActiveUser(response);
			}

			@Override
			protected void handleFailureMessage(Throwable t, String message) {
				super.handleFailureMessage(t, message);
				Log.d("vier", "message : " + message + "\nthrowable : " + t);
			}

		});
	}

	public static void parsingActiveUser(String response) {
		try {
			XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
			xmlPullParserFactory.setNamespaceAware(true);
			XmlPullParser xpp = xmlPullParserFactory.newPullParser();

			xpp.setInput(new StringReader(response));

			List<FlurryActiveUser> flurryActiveUsers = new ArrayList<FlurryActiveUser>();

			int eventType = xpp.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("day")) {
					flurryActiveUsers.add(new FlurryActiveUser(xpp.getAttributeValue(null, "value"), xpp.getAttributeValue(null, "date")));
				}

				eventType = xpp.next();
			}
			Log.d("vier", "result : " + flurryActiveUsers.size());
			Log.d("vier", "result : " + flurryActiveUsers.get(0));
		} catch (XmlPullParserException e) {
			Log.e("vier", "Error " + e);
		} catch (IOException e) {
			Log.e("vier", "Error " + e);
		}
	}
}
