package chargen;

public enum Background {
	ACOLYTE, CHARLATAN, CRIMINAL, ENTERTAINER, FOLK_HERO, GUILD_ARTISAN, HERMIT, NOBLE, OUTLANDER, SAGE, SAILOR, SOLDIER, URCHIN;

	/*
	 * STATIC FIELDS
	 */
	private static final Background[] ALL_BACKGROUNDS = { ACOLYTE, CHARLATAN, CRIMINAL, ENTERTAINER, FOLK_HERO,
			GUILD_ARTISAN, HERMIT, NOBLE, OUTLANDER, SAGE, SAILOR, SOLDIER, URCHIN };

	/*
	 * STATIC METHODS
	 */
	public static Background random() {
		return Dice.randomFromArray(ALL_BACKGROUNDS);
	}

	public static Background parseBackground(String string) {
		Background type = null;

		for (Background el : ALL_BACKGROUNDS) {
			if (el.toString().compareToIgnoreCase(string) == 0) {
				type = el;
				break;
			}
		}

		return type;
	}
}
