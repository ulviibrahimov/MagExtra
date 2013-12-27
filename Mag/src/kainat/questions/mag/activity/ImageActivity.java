package kainat.questions.mag.activity;

import kainat.questions.mag.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class ImageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_image);
		Intent mIntent = getIntent();
		int id = mIntent.getIntExtra("imageId", 0);
		ImageView img= (ImageView) findViewById(R.id.imageView1);
		img.setImageResource(id);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		
	    getMenuInflater().inflate(R.menu.image, menu);
		
		return true;
	}

}
