package com.zoup.android.game.guess.utils;

import java.util.Random;

public class GuessLogic {
	private int[] numbers;
	private final int N = 4;

	public GuessLogic() {
		numbers = new int[N];
		this.initNumbers();
	}

	private boolean isUnique(int[] array) {
		for (int i = 0; i < N; i++) {
			int temp = array[i];
			for (int j = i + 1; j < N; j++)
				if (temp == array[j]) {
					return false;
				}
		}
		return true;
	}

	private void initNumbers() {
		Random r = new Random();
		do {
			for (int i = 0; i < N; i++)
				numbers[i] = r.nextInt(10);
		} while (!isUnique(numbers));
	}

	public GuessResult guess(int[] array) {
		int correctNumber=0;
		int existingNumber=0;
		boolean isWon=false;
		String str="";
		for (int i = 0; i < N; i++) {
			str=str+array[i];
			if (numbers[i] == array[i])
				correctNumber++;
		}
		if (correctNumber == N)
			isWon=true;
		for (int i = 0; i < N; i++) {
			int temp = array[i];
			for (int j = 0; j < N; j++)
				if (numbers[j] == temp)
					existingNumber++;
		}
		existingNumber -= correctNumber;
		GuessResult guessResult=new GuessResult();
		guessResult.correctNumber=correctNumber;
		guessResult.existingNumber=existingNumber;
		guessResult.isWon=isWon;
		guessResult.guessStr=str;
		return guessResult;
	}

	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < N; i++)
			str = str + numbers[i] + " ";
		return str;
	}

	public static class GuessResult{
		public String guessStr;
		public int attemptCounts;
		public boolean isWon;
		public int correctNumber;
		public int existingNumber;
	}

}

