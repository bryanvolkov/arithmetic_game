package com.example.sumgameproject1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChooseNumberQuestionsActivity extends Activity {
	
	Button btnStart; 
	EditText inputNumQuestions;
	
	Intent intent; 
	Bundle bundle;
	
	public static int questions;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_number_questions);
		
        intent= new Intent(ChooseNumberQuestionsActivity.this, GameActivity.class);
        bundle = getIntent().getExtras();
        
        btnStart = (Button) findViewById(R.id.btnStart);
        inputNumQuestions = (EditText) findViewById(R.id.inputNumQuestions);
        

        inputNumQuestions.setText("10");
        inputNumQuestions.selectAll();
        
        btnStart.setOnClickListener(l);
	}
	
	OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			try {
				questions = Integer.parseInt(inputNumQuestions.getText().toString());
				if(questions != 0){
					intent.putExtra("questions", questions);
					intent.putExtra("difficulty", bundle.getInt("difficulty"));
					startActivity(intent);
					finish();
				}
				else{
					Toast.makeText(getBaseContext(), "Enter a whole number!", Toast.LENGTH_SHORT).show();
					inputNumQuestions.selectAll();
				}
			} catch (Exception e) {
				// TODO: handle exception
				Toast.makeText(getBaseContext(), "Enter the number of questions", Toast.LENGTH_SHORT).show();
			}
		}
	};
	
	public void onBackPressed() {
		startActivity(new Intent(ChooseNumberQuestionsActivity.this, ChooseDifficultyActivity.class));
		finish();
	};
}
