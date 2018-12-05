package chargen;

public enum Alignment {
	LAWFUL, GOOD, NEUTRAL, CHAOTIC, EVIL;

	/*
	 * STATIC FIELDS
	 */
	private static final Alignment[] ALL_ALIGNMENTS = { LAWFUL, GOOD, NEUTRAL, CHAOTIC, EVIL };

	// lawful (15%), good (15%), neutral (15%), evil (40%), chaotic (15%)
	private static final int[] ALIGNMENT_SKEW = new int[] { 15, 15, 15, 40, 15 };
	private static final int[] ALIGNMENT_TABLE = new int[] { //
			1 + ALIGNMENT_SKEW[0], //
			1 + Dice.sumToArrayIndex(1, ALIGNMENT_SKEW), //
			1 + Dice.sumToArrayIndex(2, ALIGNMENT_SKEW), //
			1 + Dice.sumToArrayIndex(3, ALIGNMENT_SKEW) //
	};

	/*
	 * STATIC METHODS
	 */
	public static Alignment random() {
		Alignment choice;

		int dice = Dice.roll(100);
		if (dice < ALIGNMENT_TABLE[0])
			choice = LAWFUL;
		else if (dice < ALIGNMENT_TABLE[1])
			choice = GOOD;
		else if (dice < ALIGNMENT_TABLE[2])
			choice = NEUTRAL;
		else if (dice < ALIGNMENT_TABLE[3])
			choice = EVIL;
		else
			choice = CHAOTIC;

		return choice;
	}

	public static Alignment parseAlignment(String string) {
		Alignment type = null;

		for (Alignment el : ALL_ALIGNMENTS) {
			if (el.toString().compareToIgnoreCase(string) == 0) {
				type = el;
				break;
			}
		}

		return type;
	}

}
