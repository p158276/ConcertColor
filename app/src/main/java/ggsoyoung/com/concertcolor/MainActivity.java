package ggsoyoung.com.concertcolor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

//	@Override
//	protected void onStart() {
//		super.onStart();
//		Intent intent = getIntent();
//		String msg = intent.getStringExtra("msg");
//		if (msg!=null)
//			Log.d("FCM", "msg:"+msg);
//	}

	public void onMsgGet(){
		Log.d("TAG","GETIT");
	}

}
