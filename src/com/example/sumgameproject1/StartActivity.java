package com.example.sumgameproject1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartActivity extends Activity {
	
	Button btnPlay;
	Typeface actionManBold;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        
        actionManBold = Typeface.createFromAsset(getAssets(), "Action_Man_Bold.ttf");
        
        btnPlay = (Button) findViewById(R.id.btnPlay);
        
        btnPlay.setOnClickListener(l);
        btnPlay.setTypeface(actionManBold);
    }    
    
    OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			startActivity(new Intent(StartActivity.this, ChooseDifficultyActivity.class));
			finish();
		}
	};
}
