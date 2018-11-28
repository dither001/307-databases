package chargen;

import java.util.Random;

public abstract class Dice {
	// static fields
	private static final Random RAND = new Random();

	// static methods
	public static int roll(int faces) {
		return roll(1, faces);
	}

	public static int roll(int dice, int faces) {
		int result = 0;

		dice = (dice < 1) ? 1 : dice;
		faces = (faces < 1) ? 1 : faces;

		for (int i = 0; i < dice; ++i) {
			result += RAND.nextInt(faces) + 1;
		}

		return result;
	}

	public static int[] rollAbilities() {
		int[] array = new int[] { 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < array.length; ++i) {
			array[i] += roll(3, 6);
		}

		return array;
	}

	/*
	 * UTILITY
	 */
	public static <T> T randomFromArray(T[] array) {
		T choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static int sumFromArrayIndex(int start, int[] array) {
		int sum = 0;

		if (start >= array.length)
			start = array.length - 1;

		for (int i = start; i < array.length; ++i) {
			sum += array[i];
		}

		return sum;
	}

	public static int sumToArrayIndex(int stop, int[] array) {
		int sum = 0;

		if (stop >= array.length)
			stop = array.length - 1;

		for (int i = 0; i < stop; ++i) {
			sum += array[i];
		}

		return sum;
	}

	public static int sumArrayRange(int start, int stop, int[] array) {
		int sum = 0;

		if (start >= array.length)
			start = array.length - 1;

		if (stop >= array.length)
			stop = array.length - 1;

		for (int i = start; i < stop; ++i) {
			sum += array[i];
		}

		return sum;
	}

}
