package chargen;

public enum Race {
	HUMAN, DRAGONBORN, HALF_ELF, HALF_ORC, TIEFLING,
	// two dwarf subraces
	HILL_DWARF, MOUNTAIN_DWARF,
	// three elf subraces
	HIGH_ELF, WOOD_ELF, DARK_ELF,
	// two gnome subraces
	FOREST_GNOME, TINKER_GNOME,
	// two halfling subraces
	LIGHTFOOT_HALFLING, STOUTHEART_HALFLING;

	private static final Race[] ALL_RACES = { HUMAN, DRAGONBORN, HALF_ELF, HALF_ORC, TIEFLING, HILL_DWARF,
			MOUNTAIN_DWARF, HIGH_ELF, WOOD_ELF, DARK_ELF, FOREST_GNOME, TINKER_GNOME, LIGHTFOOT_HALFLING,
			STOUTHEART_HALFLING };

	public int indexOf() {
		return Util.indexOfEnum(this.toString(), ALL_RACES);
	}


	/*
	 * STATIC METHODS
	 */
	public static Race random() {
		Race race;
		int dice = Dice.roll(100);

		if (dice < 41) {
			race = HUMAN;
		} else if (dice < 41 + 15) {
			// DWARF
			dice = Dice.roll(3);
			if (dice < 3)
				race = Race.HILL_DWARF;
			else
				race = Race.MOUNTAIN_DWARF;

		} else if (dice < 41 + 30) {
			// ELF
			dice = Dice.roll(6);
			if (dice < 4)
				race = Race.WOOD_ELF;
			else if (dice < 6)
				race = Race.HIGH_ELF;
			else
				race = Race.DARK_ELF;

		} else if (dice < 41 + 45) {
			// HALFLING
			dice = Dice.roll(3);
			if (dice < 3)
				race = Race.LIGHTFOOT_HALFLING;
			else
				race = Race.STOUTHEART_HALFLING;

		} else {
			// other races
			dice = Dice.roll(10);
			if (dice == 1 || dice == 2 || dice == 3)
				race = Race.HALF_ELF;
			else if (dice == 4 || dice == 5)
				race = Race.DRAGONBORN;
			else if (dice == 6 || dice == 7)
				race = Race.TIEFLING;
			else if (dice == 8)
				race = Race.FOREST_GNOME;
			else if (dice == 9)
				race = Race.TINKER_GNOME;
			else
				race = Race.HALF_ORC;

		}

		return race;
	}

	public static void applyRacialAbilityBonuses(Actor actor) {
		Race race = actor.getRace();

		switch (race) {
		case DARK_ELF:
			actor.setDexterity(actor.getDexterity() + 2);
			actor.setCharisma(actor.getCharisma() + 1);
			break;
		case DRAGONBORN:
			actor.setStrength(actor.getStrength() + 2);
			actor.setCharisma(actor.getCharisma() + 1);
			break;
		case FOREST_GNOME:
			actor.setIntelligence(actor.getIntelligence() + 2);
			actor.setDexterity(actor.getDexterity() + 1);
			break;
		case HALF_ELF:
			actor.setCharisma(actor.getCharisma() + 2);
			actor.setDexterity(actor.getDexterity() + 1);
			actor.setConstitution(actor.getConstitution() + 1);
			break;
		case HALF_ORC:
			actor.setStrength(actor.getStrength() + 2);
			actor.setConstitution(actor.getConstitution() + 1);
			break;
		case HIGH_ELF:
			actor.setDexterity(actor.getDexterity() + 2);
			actor.setIntelligence(actor.getIntelligence() + 1);
			break;
		case HILL_DWARF:
			actor.setConstitution(actor.getConstitution() + 2);
			actor.setWisdom(actor.getWisdom() + 1);
			break;
		case HUMAN:
			actor.setStrength(actor.getStrength() + 1);
			actor.setDexterity(actor.getDexterity() + 1);
			actor.setConstitution(actor.getConstitution() + 1);
			actor.setIntelligence(actor.getIntelligence() + 1);
			actor.setWisdom(actor.getWisdom() + 1);
			actor.setCharisma(actor.getCharisma() + 1);
			break;
		case LIGHTFOOT_HALFLING:
			actor.setDexterity(actor.getDexterity() + 2);
			actor.setCharisma(actor.getCharisma() + 1);
			break;
		case MOUNTAIN_DWARF:
			actor.setConstitution(actor.getConstitution() + 2);
			actor.setStrength(actor.getStrength() + 2);
			break;
		case STOUTHEART_HALFLING:
			actor.setDexterity(actor.getDexterity() + 2);
			actor.setConstitution(actor.getConstitution() + 1);
			break;
		case TIEFLING:
			actor.setCharisma(actor.getCharisma() + 2);
			actor.setIntelligence(actor.getIntelligence() + 1);
			break;
		case TINKER_GNOME:
			actor.setIntelligence(actor.getIntelligence() + 2);
			actor.setConstitution(actor.getConstitution() + 1);
			break;
		case WOOD_ELF:
			actor.setDexterity(actor.getDexterity() + 2);
			actor.setWisdom(actor.getWisdom() + 1);
			break;
		default:
			break;
		}
	}

	public static Race[] getRaces() {
		return ALL_RACES;
	}

	public static Race parseRace(String string) {
		Race type = null;

		for (Race el : ALL_RACES) {
			if (el.toString().compareToIgnoreCase(string) == 0) {
				type = el;
				break;
			}
		}

		return type;
	}
}
