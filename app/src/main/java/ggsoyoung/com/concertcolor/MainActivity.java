package ggsoyoung.com.concertcolor;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

	final String Tag = "Jonathan";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        IntentFilter mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(MyFirebaseMessagingService.MESSAGE_RECEIVE);
        registerReceiver(mReceiver, mIntentFilter);
	}

//	@Override
//	protected void onStart() {
//		super.onStart();
//		Intent intent = getIntent();
//		String msg = intent.getStringExtra("msg");
//		if (msg!=null)
//			Log.d("FCM", "msg:"+msg);
//	}

	// public void onMsgGet(){Log.d("TAG","GETIT");}

	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			final String action = intent.getAction();
			Log.i(Tag, "broadcastReceiver: " + action);
            if(MyFirebaseMessagingService.MESSAGE_RECEIVE.equals(action)){
                LinearLayout bgElement = (LinearLayout) findViewById(R.id.container);
                bgElement.setBackgroundColor(getResources().getColor(R.color.background_Blue));
            }
		}
	};

}
