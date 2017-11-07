package com.example.sumgameproject1;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.SystemClock;

public class GameActivity extends Activity {
	
	Game newGame;
	int intUsersAnswer;
	
	private Dialog closingDialog;
	TextView lblTimer;
	Button btnNext;
	TextView lblQuestion;
	EditText txtUsersAnswer;
	TextView lblEvaluation;
	
	Typeface actionManBold;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_activity);
		
		actionManBold = Typeface.createFromAsset(getAssets(), "Action_Man_Bold.ttf");
		
		txtUsersAnswer = (EditText) findViewById(R.id.txtUsersAnswer);
		lblQuestion = (TextView) findViewById(R.id.lblQuestion);
		btnNext = (Button) findViewById(R.id.btnNext);
		lblEvaluation = (TextView) findViewById(R.id.lblEvaluation);
		lblTimer = (TextView) findViewById(R.id.textView4);
		
		btnNext.setOnClickListener(l);
		btnNext.setTypeface(actionManBold);
		
		txtUsersAnswer.setTypeface(actionManBold);
		
		lblEvaluation.setText("");
		
		lblTimer.setTypeface(actionManBold);
		
		lblQuestion.setTypeface(actionManBold);
		lblEvaluation.setTypeface(actionManBold);
		
		 Bundle bundle = getIntent().getExtras();
		 
		 newGame = new Game();
		 newGame.setDifficulty(bundle.getInt("difficulty"));
		 newGame.setQuestions(bundle.getInt("questions"));
		 
		 newGame.generateQuestion();
		 lblQuestion.setText(newGame.getQuestion());
		 
		 // Start timer
		 startTime = SystemClock.uptimeMillis();
		 customHandler.postDelayed(updateTimerThread, 0);
		 
	}
	
	OnClickListener l = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			try {
				intUsersAnswer = Integer.parseInt(txtUsersAnswer.getText().toString());
				newGame.evaluateQuestion(intUsersAnswer);
				lblEvaluation.setText(newGame.getEvaluation());
				
				if(newGame.isOver() == newGame.NO){
					newGame.generateQuestion();
					lblQuestion.setText(newGame.getQuestion());
					txtUsersAnswer.setText("");
				}
				else{
					Intent intent = new Intent(GameActivity.this, ResultsActivity.class);
					intent.putExtra("correct", newGame.getCorrect());
					intent.putExtra("incorrect", newGame.getIncorrect());
					intent.putExtra("time", strTime);
					
					startActivity(intent);
					customHandler.removeCallbacks(updateTimerThread);
					finish();
				}
			} catch (Exception e) {
				// TODO: handle exception
				Toast.makeText(getBaseContext(), "Enter an answer", Toast.LENGTH_SHORT).show();
			}
		}
	};
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
		closingDialog = new Dialog(this);
		closingDialog.setContentView(R.layout.closing_dialog_window);
		closingDialog.setCancelable(true);
		
		final Button btnYes = (Button) closingDialog.findViewById(R.id.btnYes);
		final Button btnNo = (Button) closingDialog.findViewById(R.id.btnNo);
		
		btnYes.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				customHandler.removeCallbacks(updateTimerThread);
				startActivity(new Intent(GameActivity.this, StartActivity.class));
				finish();
			}
		});
		
		btnNo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				closingDialog.dismiss();
			}
		});
		
		closingDialog.show();
	}
	
	//*******    TIMER    ***********//
	long startTime = 0L;
	private Handler customHandler = new Handler();
	
	long timeInMilliseconds = 0L;
	long timeSwapBuff = 0L;
	long updatedTime = 0L;
	
	String strTime;
	
	int secs;
	int mins;
	int milliseconds;
	
	private Runnable updateTimerThread = new Runnable(){
		public void run() {
			timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
			updatedTime = timeSwapBuff + timeInMilliseconds;
			
			secs = (int) (updatedTime / 1000);
			mins = secs / 60;
			secs = secs % 60;
			milliseconds = (int) (updatedTime % 1000);
			strTime = "" + mins + ":" + String.format("%02d", secs) + ":" + String.format("%03d", milliseconds);
			lblTimer.setText(strTime);
			customHandler.postDelayed(this, 0);
		}
	};
}