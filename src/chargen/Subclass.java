package chargen;

public enum Subclass {
	BERSERKER, BEAR_TOTEM, EAGLE_TOTEM, WOLF_TOTEM, // barbarians
	LORE_COLLEGE, VALOR_COLLEGE, // bards
	DEATH, KNOWLEDGE, LIFE, LIGHT, NATURE, TEMPEST, TRICKERY, WAR, // clerics
	LAND_CIRCLE, MOON_CIRCLE, // druids
	CHAMPION, BATTLE_MASTER, ELDRITCH_KNIGHT, // fighters
	OPEN_HAND, SHADOW_WAY, FOUR_ELEMENTS, // monks
	DEVOTION_OATH, ANCIENTS_OATH, VENGEANCE_OATH, OATHBREAKER, // paladins
	HUNTER, BEAST_MASTER, // rangers
	THIEF, ASSASSIN, ARCANE_TRICKSTER, // rogues
	DRAGON_ORIGIN, CHAOS_ORIGIN, // sorcerers
	FEY_PACT, FIEND_PACT, STAR_PACT, // warlocks
	ABJURER, CONJUROR, DIVINER, ENCHANTER, EVOKER, ILLUSIONIST, NECROMANCER, TRANSMUTER; // wizards

	private static final Subclass[] ALL_SUBCLASSES = { BERSERKER, BEAR_TOTEM, EAGLE_TOTEM, WOLF_TOTEM, LORE_COLLEGE,
			VALOR_COLLEGE, DEATH, KNOWLEDGE, LIFE, LIGHT, NATURE, TEMPEST, TRICKERY, WAR, LAND_CIRCLE, MOON_CIRCLE,
			CHAMPION, BATTLE_MASTER, ELDRITCH_KNIGHT, OPEN_HAND, SHADOW_WAY, FOUR_ELEMENTS, DEVOTION_OATH,
			ANCIENTS_OATH, VENGEANCE_OATH, OATHBREAKER, HUNTER, BEAST_MASTER, THIEF, ASSASSIN, ARCANE_TRICKSTER,
			DRAGON_ORIGIN, CHAOS_ORIGIN, FEY_PACT, FIEND_PACT, STAR_PACT, ABJURER, CONJUROR, DIVINER, ENCHANTER, EVOKER,
			ILLUSIONIST, NECROMANCER, TRANSMUTER };

	public int indexOf() {
		return Util.indexOfEnum(this.toString(), ALL_SUBCLASSES);
	}

	/*
	 * STATIC FIELDS
	 */
	private static final Subclass[] BARBARIAN_TYPES = { BERSERKER, BEAR_TOTEM, EAGLE_TOTEM, WOLF_TOTEM };
	private static final Subclass[] BARD_TYPES = { LORE_COLLEGE, VALOR_COLLEGE };
	private static final Subclass[] CLERIC_TYPES = { DEATH, KNOWLEDGE, LIFE, LIGHT, NATURE, TEMPEST, TRICKERY, WAR };
	private static final Subclass[] DRUID_TYPES = { LAND_CIRCLE, MOON_CIRCLE };
	private static final Subclass[] FIGHTER_TYPES = { CHAMPION, BATTLE_MASTER, ELDRITCH_KNIGHT };
	private static final Subclass[] MONK_TYPES = { OPEN_HAND, SHADOW_WAY, FOUR_ELEMENTS };
	private static final Subclass[] PALADIN_TYPES = { DEVOTION_OATH, ANCIENTS_OATH, VENGEANCE_OATH, OATHBREAKER };
	private static final Subclass[] RANGER_TYPES = { HUNTER, BEAST_MASTER };
	private static final Subclass[] ROGUE_TYPES = { THIEF, ASSASSIN, ARCANE_TRICKSTER };
	private static final Subclass[] SORCERER_TYPES = { DRAGON_ORIGIN, CHAOS_ORIGIN };
	private static final Subclass[] WARLOCK_TYPES = { FEY_PACT, FIEND_PACT, STAR_PACT };
	private static final Subclass[] WIZARD_TYPES = { ABJURER, CONJUROR, DIVINER, ENCHANTER, EVOKER, ILLUSIONIST,
			NECROMANCER, TRANSMUTER };

	/*
	 * STATIC METHODS
	 */
	public static Subclass random(Player actor) {
		Class job = actor.getJob();

		Subclass Subclass = null;
		int dice;

		switch (job) {
		case BARBARIAN:
			dice = Dice.roll(BARBARIAN_TYPES.length) - 1;
			Subclass = BARBARIAN_TYPES[dice];
			break;
		case BARD:
			dice = Dice.roll(BARD_TYPES.length) - 1;
			Subclass = BARD_TYPES[dice];
			break;
		case CLERIC:
			dice = Dice.roll(CLERIC_TYPES.length) - 1;
			Subclass = CLERIC_TYPES[dice];
			break;
		case DRUID:
			dice = Dice.roll(DRUID_TYPES.length) - 1;
			Subclass = DRUID_TYPES[dice];
			break;
		case FIGHTER:
			dice = Dice.roll(100);

			if (dice < 21 && actor.getIntelligence() > 9) {
				Subclass = FIGHTER_TYPES[2]; // 20% of fighters are eldritch knights
			} else if (dice < 61) {
				/*
				 * 40% of fighters are champions, plus whichever fighters failed to qualify for
				 * Eldritch Knight with the Intelligence requirement
				 */
				Subclass = FIGHTER_TYPES[0];
			} else {
				Subclass = FIGHTER_TYPES[1]; // 40% of fighters are battle masters
			}
			break;
		case MONK:
			dice = Dice.roll(MONK_TYPES.length) - 1;
			Subclass = MONK_TYPES[dice];
			break;
		case PALADIN:
			dice = Dice.roll(PALADIN_TYPES.length) - 1;
			Subclass = PALADIN_TYPES[dice];
			break;
		case RANGER:
			dice = Dice.roll(RANGER_TYPES.length) - 1;
			Subclass = RANGER_TYPES[dice];
			break;
		case ROGUE:
			dice = Dice.roll(100);

			if (dice < 21 && actor.getIntelligence() > 9) {
				Subclass = ROGUE_TYPES[2]; // 20% of rogues are arcane tricksters
			} else if (dice < 61) {
				/*
				 * 40% of rogues are thieves, plus whichever rogues failed to qualify for Arcane
				 * Trickster with the Intelligence requirement
				 */
				Subclass = ROGUE_TYPES[0];
			} else {
				Subclass = ROGUE_TYPES[1]; // 40% of rogues are assassins
			}
			break;
		case SORCERER:
			dice = Dice.roll(SORCERER_TYPES.length) - 1;
			Subclass = SORCERER_TYPES[dice];
			break;
		case WARLOCK:
			dice = Dice.roll(100);

			if (dice < 41) {
				Subclass = WARLOCK_TYPES[1]; // 40% of warlocks have fiend pacts
			} else if (dice < 71) {
				Subclass = WARLOCK_TYPES[0]; // 30% of warlocks have fey pacts
			} else {
				Subclass = WARLOCK_TYPES[2]; // 30% of warlocks have star pacts
			}
			break;
		case WIZARD:
			dice = Dice.roll(100);

			if (dice < 41) {
				Subclass = WIZARD_TYPES[2]; // 40% of wizards are diviners
			} else if (dice < 56) {
				Subclass = WIZARD_TYPES[4]; // 15% of wizards are evokers
			} else if (dice < 71) {
				Subclass = WIZARD_TYPES[5]; // 15% of wizards are illusionists
			} else if (dice < 81) {
				Subclass = WIZARD_TYPES[1]; // 10% of wizards are conjurors
			} else if (dice < 86) {
				Subclass = WIZARD_TYPES[0]; // 5% of wizards are abjurers
			} else if (dice < 91) {
				Subclass = WIZARD_TYPES[3]; // 5% of wizards are enchanters
			} else if (dice < 96) {
				Subclass = WIZARD_TYPES[6]; // 5% of wizards are necromancers
			} else {
				Subclass = WIZARD_TYPES[7]; // 5% of wizards are transmuters
			}
			break;
		default:
			break;
		}

		return Subclass;
	}

	public static Subclass[] getSubclasses() {
		return ALL_SUBCLASSES;
	}

	public static Subclass parseSubclass(String string) {
		Subclass type = null;

		for (Subclass el : ALL_SUBCLASSES) {
			if (el.toString().compareToIgnoreCase(string) == 0) {
				type = el;
				break;
			}
		}

		return type;
	}

}
