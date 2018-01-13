package com.nam.mythread.wtf;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nam.mythread.R;

public class AsyncTaskActivity extends AppCompatActivity {
	
	private final static String TAG = "ThreadingAsyncTask";
	
	private ImageView mImageView;
	private ProgressBar mProgressBar;
	private int mDelay = 500;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);

		mImageView = (ImageView) findViewById(R.id.imageView);;
		mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
		
		final Button button = (Button) findViewById(R.id.loadButton);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				new LoadIconTask().execute(R.drawable.painter);
			}
		});
		
		final Button otherButton = (Button) findViewById(R.id.otherButton);
		otherButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(AsyncTaskActivity.this, "I'm Working",
						Toast.LENGTH_SHORT).show();
			}
		});

	}

	class LoadIconTask extends AsyncTask<Integer, Integer, Bitmap> {


		@Override
		protected void onPreExecute() {
			mProgressBar.setVisibility(ProgressBar.VISIBLE);
		}

		@Override
		protected Bitmap doInBackground(Integer... resId) {
			Bitmap tmp = BitmapFactory.decodeResource(getResources(), resId[0]);
			// simulating long-running operation 
			for (int i = 1; i < 11; i++) {
				sleep();
				publishProgress(i * 10);
			}
			return tmp;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			mProgressBar.setProgress(values[0]);
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			mProgressBar.setVisibility(ProgressBar.INVISIBLE);
			mImageView.setImageBitmap(result);
		}

		private void sleep() {
			try {
				Thread.sleep(mDelay);
			} catch (InterruptedException e) {
				Log.e(TAG, e.toString());
			}
		}
	}
}