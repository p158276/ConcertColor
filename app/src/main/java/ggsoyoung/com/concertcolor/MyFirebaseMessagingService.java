package ggsoyoung.com.concertcolor;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by YoYo on
 * 16/8/19.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

	final String Tag = "Jonathan";
	public final static String MESSAGE_RECEIVE = "MsgReceive";
	@Override
	public void onMessageReceived(RemoteMessage remoteMessage) {
		// ...

		// TODO(developer): Handle FCM messages here.
		// Not getting messages here? See why this may be: https://goo.gl/39bRNJ
		Log.d(Constant.TAG, "From: " + remoteMessage.getFrom());

		// Check if message contains a data payload.
		if (remoteMessage.getData().size() > 0) {
			Log.d(Constant.TAG, "Message data payload: " + remoteMessage.getData());
		}

		// Check if message contains a notification payload.
		if (remoteMessage.getNotification() != null) {
			Log.d(Constant.TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
			broadcastUpdate(MESSAGE_RECEIVE,remoteMessage.getNotification().getBody());
		}

		// Also if you intend on generating your own notifications as a result of a received FCM
		// message, here is where that should be initiated. See sendNotification method below.
	}
	private void broadcastUpdate(final String action, final String msg) {
		Log.i(Tag, "broadcastUpdate: " + action + " message : " + msg);
		final Intent intent = new Intent(action);
		intent.putExtra(MESSAGE_RECEIVE, msg);
		sendBroadcast(intent);
	}
}

