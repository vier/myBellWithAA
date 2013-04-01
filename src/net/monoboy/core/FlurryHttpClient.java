package net.monoboy.core;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import net.monoboy.activity.ChartActivity_;
import net.monoboy.constant.FlurryConstant;
import net.monoboy.model.FlurryActiveUser;

import android.content.Intent;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class FlurryHttpClient extends HttpClient {

	private static RequestParams mParamMap = new RequestParams();
	

	public static void getActiveUser(Date startDate, Date endDate) {
		mParamMap.put(FlurryConstant.ARG_READ_API_KEY, FlurryConstant.FLURRY_READ_API_KEY);
		mParamMap.put(FlurryConstant.ARG_LOGGING_API_KEY, FlurryConstant.FLURRY_LOGGING_API_KEY);
		mParamMap.put(FlurryConstant.ARG_START_DATE, "2013-03-25");
		mParamMap.put(FlurryConstant.ARG_END_DATE, "2013-03-31");

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
		List<Integer> flurryActiveUserCount = new ArrayList<Integer>();
		List<String> flurryActiveUserDate = new ArrayList<String>();
		
		try {
			XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
			xmlPullParserFactory.setNamespaceAware(true);
			XmlPullParser xpp = xmlPullParserFactory.newPullParser();

			xpp.setInput(new StringReader(response));

			int eventType = xpp.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("day")) {
					FlurryActiveUser fau = new FlurryActiveUser(xpp.getAttributeValue(null, "value"), xpp.getAttributeValue(null, "date"));
					flurryActiveUserCount.add(fau.getUserCount());
					flurryActiveUserDate.add(fau.getDate());
				}

				eventType = xpp.next();
			}
		} catch (XmlPullParserException e) {
			Log.e("vier", "Error " + e);
		} catch (IOException e) {
			Log.e("vier", "Error " + e);
		}
		
		Intent newChartIntent = new Intent(GlobalHolder.getApplicationContext(), ChartActivity_.class);
		newChartIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
		newChartIntent.putIntegerArrayListExtra("userCount", (ArrayList<Integer>) flurryActiveUserCount);
		newChartIntent.putStringArrayListExtra("date", (ArrayList<String>) flurryActiveUserDate);
		GlobalHolder.getApplicationContext().startActivity(newChartIntent);
	}
}
