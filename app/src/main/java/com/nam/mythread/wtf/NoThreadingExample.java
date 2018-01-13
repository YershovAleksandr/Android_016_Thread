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

public class NoThreadingExample extends AppCompatActivity {
	private Bitmap mBitmap;
	private ImageView mIView;
	private int mDelay = 5000;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mIView = (ImageView) findViewById(R.id.imageView);

		final Button loadButton = (Button) findViewById(R.id.loadButton);
		loadButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				loadIcon();
				if (null != mBitmap) {
					mIView.setImageBitmap(mBitmap);
				}
			}
		});

		final Button otherButton = (Button) findViewById(R.id.otherButton);
		otherButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(NoThreadingExample.this, "I'm Working",
						Toast.LENGTH_SHORT).show();
			}
		});

		String str = getIntent().getExtras().getString("FU2");

		Log.i(FullscreenActivity.LOGTAG, "FU2 = " + str);

	}

	private void loadIcon() {
		try {
			Thread.sleep(mDelay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.painter);
	}
}