package com.example.thread_messages;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	ImageView iv;
	Button b1, b2;
	TextView tv;
	Bitmap image;
	String text;
	Handler handler = new Handler();
	ProgressBar pb;

	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
		b1 = (Button) findViewById(R.id.b1);
		b2 = (Button) findViewById(R.id.b2);
		tv = (TextView) findViewById(R.id.tv);
		pb = (ProgressBar) findViewById(R.id.pb);
		pb.setVisibility(ProgressBar.INVISIBLE);
		text = new String("what was the question...???");

		b1.setOnClickListener(this);
		b2.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.b1) {
			imageLoader imageloader = new imageLoader();
			Thread t = new Thread(imageloader);
			t.start();
		}

		else if (v.getId() == R.id.b2) {
			Toast.makeText(MainActivity.this, "i m working", Toast.LENGTH_SHORT)
					.show();
		}
	}

	class imageLoader implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub

			Runnable r = new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Toast.makeText(MainActivity.this, "loading.......",
							Toast.LENGTH_SHORT).show();
					tv.setText("i m fine...!!!");
				}
			};
			handler.post(r);

			image = BitmapFactory
					.decodeResource(getResources(), R.drawable.qaz);

			Runnable r1 = new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					pb.setVisibility(ProgressBar.VISIBLE);
				}
			};
			handler.post(r1);

			for (int i = 0; i < 11; ++i) {
				try {
					Thread.sleep(500);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				final int x = i;
				Runnable r2 = new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						pb.setProgress(x * 10);
					}
				};
				handler.post(r2);

			}
			Runnable r3 = new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					iv.setImageBitmap(image);
					pb.setVisibility(ProgressBar.INVISIBLE);
					Toast.makeText(MainActivity.this,
							"loading complete.......", Toast.LENGTH_SHORT)
							.show();
				}
			};
			handler.post(r3);

		}
	}
}
