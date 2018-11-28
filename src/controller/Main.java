package controller;

import chargen.Player;

public class Main {
	public static void main(String... strings) {

		for (int i = 0; i < 50; ++i) {
			System.out.println(new Player().toStringVerbose());
			System.out.println();

		}

	}

}
