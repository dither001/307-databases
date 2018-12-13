package chargen;

public enum Class {
	BARBARIAN, BARD, CLERIC, DRUID, FIGHTER, MONK, PALADIN, RANGER, ROGUE, SORCERER, WARLOCK, WIZARD;

	/*
	 * STATIC FIELDS
	 */
	private static final Class[] ALL_CLASSES = { BARBARIAN, BARD, CLERIC, DRUID, FIGHTER, MONK, PALADIN, RANGER, ROGUE,
			SORCERER, WARLOCK, WIZARD };

	public int indexOf() {
		return Util.indexOfEnum(this.toString(), ALL_CLASSES);
	}


	/*
	 * STATIC FIELDS
	 */
	public static Class random() {
		return Dice.randomFromArray(ALL_CLASSES);
	}
	
	public static Class selectClass(Player actor) {
		Class job;
		Alignment ali = actor.getAlignment();

		int dexterity, intelligence, wisdom, charisma;
		dexterity = actor.getDexterity();
		intelligence = actor.getIntelligence();
		wisdom = actor.getWisdom();
		charisma = actor.getCharisma();

		int dice = Dice.roll(4);
		if (charisma > 11 && dice == 1)
			job = BARD;
		else if (charisma > 11 && dice == 2)
			job = SORCERER;
		else if (charisma > 11 && dice == 3)
			job = WARLOCK;
		else if (wisdom > 11 && dice == 1)
			job = CLERIC;
		else if (wisdom > 11 && dice == 2)
			job = DRUID;
		else if (wisdom > 11 && dice == 3)
			job = MONK;
		else if (intelligence > 11 && dice < 3)
			job = WIZARD;
		else if (dexterity > 11 && dice < 3)
			job = RANGER;
		else if (ali.equals(Alignment.GOOD))
			job = PALADIN;
		else if (ali.equals(Alignment.CHAOTIC))
			job = BARBARIAN;
		else if (ali.equals(Alignment.LAWFUL) || dice < 4)
			job = FIGHTER;
		else
			job = ROGUE;

		return job;
	}

	public static int getHitDie(Class job) {
		int hitDie = 8;

		switch (job) {
		case BARBARIAN:
			hitDie = 12;
			break;
		case BARD:
		case CLERIC:
		case DRUID:
		case MONK:
		case ROGUE:
		case WARLOCK:
			hitDie = 8;
			break;
		case FIGHTER:
		case PALADIN:
		case RANGER:
			hitDie = 10;
			break;
		case SORCERER:
		case WIZARD:
			hitDie = 6;
			break;
		default:
			hitDie = 8;
			break;
		}

		return hitDie;
	}

	public static int getNumberOfSkills(Class job) {
		int jobSkills = 2;

		switch (job) {
		case BARBARIAN:
		case CLERIC:
		case DRUID:
		case FIGHTER:
		case MONK:
		case PALADIN:
		case SORCERER:
		case WARLOCK:
		case WIZARD:
			jobSkills = 2;
			break;
		case BARD:
		case RANGER:
			jobSkills = 3;
			break;
		case ROGUE:
			jobSkills = 4;
			break;
		default:
			jobSkills = 2;
			break;
		}

		return jobSkills;
	}

	public static Class[] getClasses() {
		return ALL_CLASSES;
	}

	public static Class parseClass(String string) {
		Class type = null;

		for (Class el : ALL_CLASSES) {
			if (el.toString().compareToIgnoreCase(string) == 0) {
				type = el;
				break;
			}
		}

		return type;
	}

}
