package extension.haxe;


import lime.system.CFFI;
import lime.system.JNI;


/**
* https://firebase.google.com/
* Firebase is a suite of google libraries.
*
* We use Firebase for analytics, push notifications, and (TODO: deep links)
**/
class Firebase {

	public static function sendFirebaseAnalyticsEvent (eventName:String, payload:String):Void {

		#if (ios || android)
			extension_firebase_send_analytics_event(eventName, payload);
		#else
			trace("sendFirebaseAnalyticsEvent not implemented on this platform.");
		#end
	}


	#if (ios)
	private static var extension_firebase_send_analytics_event = CFFI.load ("firebase", "sendFirebaseAnalyticsEvent", 2);
	#end

	#if (android)
	private static var extension_firebase_send_analytics_event = JNI.createStaticMethod("extension.java.Firebase", "sendFirebaseAnalyticsEvent", "(Ljava/lang/String;Ljava/lang/String;)V");
	#end
}