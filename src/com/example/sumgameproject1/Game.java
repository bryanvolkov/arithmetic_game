package com.example.sumgameproject1;

import java.util.Random;

public class Game {
	
	public final boolean YES = true;
	public final boolean NO = false;
	
	private int questions;
	private int count;
	private boolean proceed;
	private static Random gen;
	
	private int correct;
	private int incorrect;
	private int rightAnswer;
	
	private int randomNumber1;
	private int randomNumber2;
	
	private String strQuestion;
	private String evaluation;
	
	private int maxNumber;
	
	public Game(){
		proceed = YES;
		count = 1;
		gen = new Random();
	}
	
	public void setQuestions(int questions){
		this.questions = questions;
	}
	public void setDifficulty(int difficulty){
		switch(difficulty){
		case 1:
			maxNumber = 10;
			break;
		case 2:
			maxNumber = 50; 
			break;
		case 3:
			maxNumber = 100;
			break;
		}
	}
	public void setRandomNumberRange(int maxNumber){
		this.maxNumber = maxNumber;
	}
	public String getQuestion(){
		return strQuestion;
	}
	public int getCorrect(){
		return correct;
	}
	public int getIncorrect(){
		return incorrect;
	}
	public String getEvaluation(){
		return evaluation;
	}
	
	public void evaluateQuestion(int playerAnswer){
		
		if ( playerAnswer != rightAnswer){
			evaluation = "Incorrect";
			incorrect++;
		}
		else {
			evaluation = "Correct";
			correct++;
		}
		count++;
	}
	
	public boolean isOver(){
		if(count!=(questions + 1)){
			proceed = YES;
		}
		else
			proceed = NO;
		return !(proceed); 
	}
	
	public void generateQuestion(){
		randomNumber1 = gen.nextInt(maxNumber) + 1;
		randomNumber2 = gen.nextInt(maxNumber) + 1;
		rightAnswer = randomNumber1 + randomNumber2;
		strQuestion = Integer.toString(randomNumber2) + " + " + Integer.toString(randomNumber1);
	}
}
