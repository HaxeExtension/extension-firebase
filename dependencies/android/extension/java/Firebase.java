package extension.java;


import android.app.Activity;
import android.app.Application;
import android.content.res.AssetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

import org.haxe.extension.Extension;


public class Firebase extends Extension {


	private static Map<String, String> getPayloadFromJson(String jsonString) {
		Type type = new TypeToken<Map<String, String>>(){}.getType();
		Map<String, String> payload = new Gson().fromJson(jsonString, type);
		return payload;
	}


	private static Bundle getFirebaseAnalyticsBundleFromJson(String jsonString) {
		Map<String, String> payloadMap = getPayloadFromJson(jsonString);

		Bundle payloadBundle = new Bundle();
		for (Map.Entry<String, String> entry : payloadMap.entrySet()) {
			payloadBundle.putString(entry.getKey(), entry.getValue());
		}

		return payloadBundle;
	}


	public static void sendFirebaseAnalyticsEvent(String eventName, String jsonPayload) {
		Log.i("trace","Firebase.java: sendFirebaseAnalyticsEvent name= " + eventName + ", payload= " + jsonPayload);

		Application mainApp = Extension.mainActivity.getApplication();
		FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(mainApp);

		Bundle payloadBundle = getFirebaseAnalyticsBundleFromJson(jsonPayload);
		firebaseAnalytics.logEvent(eventName, payloadBundle);
	}

	public static String getInstanceIDToken() {
		String idToken = FirebaseInstanceId.getInstance().getToken();
		Log.i("trace","Firebase.java: getInstanceIDToken= " + idToken);

		return idToken;
	}

}
