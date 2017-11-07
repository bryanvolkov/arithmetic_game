package com.example.sumgameproject1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ChooseDifficultyActivity extends Activity {
	
	Button btnEasy;
	Button btnMedium;
	Button btnHard;
	
	Intent intent;
	
	Typeface actionManBold;
	
	public static int EASY = 1;
	public static int MEDIUM = 2;
	public static int HARD = 3;
	
	public static int difficulty; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.copy_of_choose_mode_activity);
		
		actionManBold = Typeface.createFromAsset(getAssets(), "Action_Man_Bold.ttf");
		
		btnEasy = (Button) findViewById(R.id.btnEasy);
		btnMedium = (Button) findViewById(R.id.btnMedium);
		btnHard = (Button)findViewById(R.id.btnHard);
		
		btnEasy.setOnClickListener(l);
		btnEasy.setTypeface(actionManBold);
		btnMedium.setOnClickListener(l);
		btnMedium.setTypeface(actionManBold);
		btnHard.setOnClickListener(l);
		btnHard.setTypeface(actionManBold);
		
	}
	
	OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(btnEasy.isPressed()){
				difficulty = EASY;
			}
			else if (btnMedium.isPressed()){
				difficulty = MEDIUM;
			}
			else if (btnHard.isPressed()){
				difficulty = HARD;
			}
			intent = new Intent(ChooseDifficultyActivity.this, ChooseNumberQuestionsActivity.class);
			intent.putExtra("difficulty", difficulty);
			startActivity(intent);
			
			finish();
		}
	};
	
	public void onBackPressed() {
		startActivity(new Intent(ChooseDifficultyActivity.this, StartActivity.class));
		finish();
	};	
}