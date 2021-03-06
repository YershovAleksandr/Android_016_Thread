package com.nam.mythread.wtf;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.nam.mythread.FullscreenActivity;
import com.nam.mythread.R;

public class SimpleThreadingExampleFix extends AppCompatActivity {
	private Bitmap mBitmap;
	private ImageView mImageView;
	private int mDelay = 5000;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mImageView = (ImageView) findViewById(R.id.imageView);

		final Button button = (Button) findViewById(R.id.loadButton);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				loadIcon();
			}
		});
		
		final Button otherButton = (Button) findViewById(R.id.otherButton);
		otherButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(SimpleThreadingExampleFix.this, "I'm Working",
						Toast.LENGTH_SHORT).show();
			}
		});

		String str = getIntent().getExtras().getString("FU4");

		Log.i(FullscreenActivity.LOGTAG, "FU4 = " + str);
	}

	private void loadIcon() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(mDelay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.painter);

				SimpleThreadingExampleFix.this.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						mImageView.setImageBitmap(mBitmap);
					}
				});
			}
		}).start();
	}
}