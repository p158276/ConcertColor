package ggsoyoung.com.concertcolor;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

	final String Tag = "Jonathan";
    private String topic = "";
    Button section1B, section2B, section3B;
    Button disableB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        section1B = (Button) findViewById(R.id.section1_button);
        section2B = (Button) findViewById(R.id.section2_button);
        section3B = (Button) findViewById(R.id.section3_button);
        // section4B = (Button) findViewById(R.id.section4_button);
        disableB = (Button) findViewById(R.id.disable_button);

        section1B.setOnClickListener(this);
        section2B.setOnClickListener(this);
        section3B.setOnClickListener(this);
        // section4B.setOnClickListener(this);
        disableB.setOnClickListener(this);

        IntentFilter mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(MyFirebaseMessagingService.COLOR);
        mIntentFilter.addAction(MyFirebaseMessagingService.AUDIO);
        mIntentFilter.addAction(MyFirebaseMessagingService.IMAGE);
        registerReceiver(mReceiver, mIntentFilter);
	}

    @Override
    protected void onResume() {
        super.onResume();
        setButtonEnable(true);
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
            if(MyFirebaseMessagingService.COLOR.equals(action)){
                String hex_color = intent.getStringExtra(MyFirebaseMessagingService.COLOR);
                LinearLayout bgElement = (LinearLayout) findViewById(R.id.container);
                bgElement.setBackgroundColor(Color.parseColor(hex_color));
                /*AlphaAnimation alphaAnimation1 = new AlphaAnimation(0.1f, 1.0f);
                alphaAnimation1.setDuration(3000);
                alphaAnimation1.setRepeatCount(Animation.INFINITE);
                alphaAnimation1.setRepeatMode(Animation.REVERSE);
                bgElement.setAnimation(alphaAnimation1);
                alphaAnimation1.start();*/
            }
		}
	};

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.section1_button:
                topic = "section1";
                Log.i(Tag, "Firebase subscribe topic to " + topic);
                FirebaseMessaging.getInstance().subscribeToTopic(topic);
                setButtonEnable(false);
                break;
            case R.id.section2_button:
                topic = "section2";
                FirebaseMessaging.getInstance().subscribeToTopic(topic);
                Log.i(Tag, "Firebase subscribe topic to " + topic);
                setButtonEnable(false);
                break;
            case R.id.section3_button:
                topic = "section3";
                FirebaseMessaging.getInstance().subscribeToTopic(topic);
                Log.i(Tag, "Firebase subscribe topic to " + topic);
                setButtonEnable(false);
                break;
            /*case R.id.section4_button:
                topic = "section4";
                FirebaseMessaging.getInstance().subscribeToTopic(topic);
                Log.i(Tag, "Firebase subscribe topic to " + topic);
                setButtonEnable(false);
                break;*/
            case R.id.disable_button:
                FirebaseMessaging.getInstance().unsubscribeFromTopic(topic);
                Log.i(Tag, "Firebase unsubscribe topic " + topic);
                topic = "";
                setButtonEnable(true);
                break;
        }
    }

    public void setButtonEnable(boolean bool){
        if (bool) {
            section1B.setEnabled(true);
            section1B.setVisibility(View.VISIBLE);
            section2B.setEnabled(true);
            section2B.setVisibility(View.VISIBLE);
            section3B.setEnabled(true);
            section3B.setVisibility(View.VISIBLE);
            // section4B.setEnabled(true);
            // section4B.setVisibility(View.VISIBLE);
            disableB.setEnabled(false);
            disableB.setVisibility(View.GONE);
        }
        else {
            section1B.setEnabled(false);
            section1B.setVisibility(View.GONE);
            section2B.setEnabled(false);
            section2B.setVisibility(View.GONE);
            section3B.setEnabled(false);
            section3B.setVisibility(View.GONE);
            // section4B.setEnabled(false);
            // section4B.setVisibility(View.GONE);
            disableB.setEnabled(true);
            disableB.setVisibility(View.VISIBLE);
        }
    }
}
