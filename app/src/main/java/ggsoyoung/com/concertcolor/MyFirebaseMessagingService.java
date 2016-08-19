package ggsoyoung.com.concertcolor;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by YoYo on
 * 16/8/19.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

	final String Tag = "Jonathan";
	public final static String COLOR = "color";
	public final static  String AUDIO = "audio";
	public final static  String IMAGE = "image";
	@Override
	public void onMessageReceived(RemoteMessage remoteMessage) {
		// ...

		// TODO(developer): Handle FCM messages here.
		// Not getting messages here? See why this may be: https://goo.gl/39bRNJ
		Log.d(Constant.TAG, "From: " + remoteMessage.getFrom());

		// Check if message contains a data payload.
		if (remoteMessage.getData().size() > 0) {
			Log.d(Constant.TAG, "Message data payload: " + remoteMessage.getData());
			Map<String, String> data = remoteMessage.getData();
			String function = data.get("function");
			String content = data.get("content");
			if (function.equals("color")) {
				broadcastUpdate(COLOR, content);
			}
			else if (function.equals("audio")){
				broadcastUpdate(AUDIO, content);
			}
			else if (function.equals("image")) {
				broadcastUpdate(IMAGE, content);
			}
			else {
				broadcastUpdate(COLOR, "white");
			}
		}

		// Check if message contains a notification payload.
		if (remoteMessage.getNotification() != null) {
			Log.d(Constant.TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
		}

		// Also if you intend on generating your own notifications as a result of a received FCM
		// message, here is where that should be initiated. See sendNotification method below.
	}
	private void broadcastUpdate(final String action, final String msg) {
		Log.i(Tag, "broadcastUpdate: " + action + " message : " + msg);
		final Intent intent = new Intent(action);
		intent.putExtra(action, msg);
		sendBroadcast(intent);
	}
}

