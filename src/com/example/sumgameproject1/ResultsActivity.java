package com.example.sumgameproject1;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends Activity{
	
	int correct;
	int incorrect;
	double score;
	String strTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results_activity);
		
		final TextView lblCorrect = (TextView) findViewById(R.id.lblCorrect);
		final TextView lblIncorrect = (TextView) findViewById(R.id.lblIncorrect);
		final TextView lblScore = (TextView) findViewById(R.id.lblScore);
		final TextView lblTime = (TextView) findViewById(R.id.lblTime);
		final Button btnOk = (Button) findViewById(R.id.btnOk);
		
		Bundle bundle = getIntent().getExtras();
		
		DecimalFormat tenth = new DecimalFormat("#.#%");
	
		correct = bundle.getInt("correct");
		incorrect = bundle.getInt("incorrect");
		strTime = bundle.getString("time");
		
		score = (double)correct / (double)(correct + incorrect);
		lblCorrect.setText(Integer.toString(correct));
		lblIncorrect.setText(Integer.toString(incorrect));
		lblScore.setText(tenth.format(score));
		lblTime.setText(strTime);
		
		
		btnOk.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ResultsActivity.this, StartActivity.class));
				finish();
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		startActivity(new Intent(ResultsActivity.this, StartActivity.class));
		finish();
	}
}
