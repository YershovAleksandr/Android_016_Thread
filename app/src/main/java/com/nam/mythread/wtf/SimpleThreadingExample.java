/*
 * When "Load Icon" Button is pressed throws 
 * android.view.ViewRootImpl$CalledFromWrongThreadException: 
 * Only the original thread that created a view hierarchy can touch its views.
 */

package com.nam.mythread.wtf;

import android.content.Intent;
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

public class SimpleThreadingExample extends AppCompatActivity {

	private static final String TAG = "SimpleThreadingExample";

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
			}
		});

		final Button otherButton = (Button) findViewById(R.id.otherButton);
		otherButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(SimpleThreadingExample.this, "I'm Working",
						Toast.LENGTH_SHORT).show();
			}
		});


		Intent intent = getIntent();

		if (intent != null)
		{
			Bundle bundle = intent.getExtras();
			if (bundle != null)
			{
				String string = bundle.getString("FU");

			 	if (string != null)
			 	{
			 		Log.i(FullscreenActivity.LOGTAG, "Wtf = " + string);
				}
				else
				{
					Log.i(FullscreenActivity.LOGTAG, "String = null!!!! ");
				}
			}
			else
			{
				Log.i(FullscreenActivity.LOGTAG, "Bundle = null!!!! ");
			}
		}
		else
		{
			Log.i(FullscreenActivity.LOGTAG, "Intent = null!!!! ");
		}
	}

	private void loadIcon() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(mDelay);
				} catch (InterruptedException e) {
					Log.e(TAG, e.toString());
				}
				mBitmap = BitmapFactory.decodeResource(getResources(),
						R.drawable.painter);

				// This doesn't work in Android
				//mIView.setImageBitmap(mBitmap);
			}
		}).start();
	}
}