package chargen;

public interface Actor {
	//
	public String getName();

	public void setName(String name);

	public boolean isFemale();

	public Alignment getAlignment();

	public void setAlignment(Alignment alignment);

	public Background getBackground();

	public void setBackground(Background background);

	//
	public int getLevel();

	public void setLevel(int level);

	//
	public int getExperience();

	public void setExperience(int experience);

	//
	public int[] getAbilityScores();

	public void setAbilityScores(int[] abilityScores);

	public int[] getAbilityCeiling();

	//
	public Race getRace();

	public void setRace(Race race);

	public Class getJob();

	public void setJob(Class job);

	public Subclass getSubclass();

	public void setSubclass(Subclass subclass);

	/*
	 * DEFAULT METHODS
	 */
	public default int proficiency() {
		int bonus, level = getLevel();
		if (level > 16)
			bonus = 6;
		else if (level > 12)
			bonus = 5;
		else if (level > 8)
			bonus = 4;
		else if (level > 4)
			bonus = 3;
		else
			bonus = 2;

		return bonus;
	}

	public default int getStrength() {
		return getAbilityScores()[0];
	}

	public default int getDexterity() {
		return getAbilityScores()[1];
	}

	public default int getConstitution() {
		return getAbilityScores()[2];
	}

	public default int getIntelligence() {
		return getAbilityScores()[3];
	}

	public default int getWisdom() {
		return getAbilityScores()[4];
	}

	public default int getCharisma() {
		return getAbilityScores()[5];
	}

	public default boolean setAbilityScore(int index, int bonus) {
		boolean set = false;
		int ability = getAbilityScores()[index], ceiling = getAbilityCeiling()[index];

		if (ability <= ceiling) {
			getAbilityScores()[index] = bonus;
			set = true;
		}

		return set;
	}

	public default boolean setStrength(int bonus) {
		return setAbilityScore(0, bonus);
	}

	public default boolean setDexterity(int bonus) {
		return setAbilityScore(1, bonus);
	}

	public default boolean setConstitution(int bonus) {
		return setAbilityScore(2, bonus);
	}

	public default boolean setIntelligence(int bonus) {
		return setAbilityScore(3, bonus);
	}

	public default boolean setWisdom(int bonus) {
		return setAbilityScore(4, bonus);
	}

	public default boolean setCharisma(int bonus) {
		return setAbilityScore(5, bonus);
	}

	public default boolean raiseAbilityMaximum(int index, int bonus) {
		boolean increased = false;
		int hardCap = 30, ceiling = getAbilityCeiling()[index];

		if (ceiling + bonus <= hardCap) {
			getAbilityCeiling()[index] = (ceiling + bonus);
			increased = true;
		}

		return increased;
	}

	public default boolean raiseMaxStrength(int bonus) {
		return raiseAbilityMaximum(0, bonus);
	}

	public default boolean raiseMaxDexterity(int bonus) {
		return raiseAbilityMaximum(1, bonus);
	}

	public default boolean raiseMaxConstitution(int bonus) {
		return raiseAbilityMaximum(2, bonus);
	}

	public default boolean raiseMaxIntelligence(int bonus) {
		return raiseAbilityMaximum(3, bonus);
	}

	public default boolean raiseMaxWisdom(int bonus) {
		return raiseAbilityMaximum(4, bonus);
	}

	public default boolean raiseMaxCharisma(int bonus) {
		return raiseAbilityMaximum(5, bonus);
	}

	public default int getAbilityModifier(int index) {
		int ability = getAbilityScores()[index];
		return (ability > 9) ? (ability - 10) / 2 : (ability - 11) / 2;
	}

	public default int getStrengthModifier() {
		return getAbilityModifier(0);
	}

	public default int getDexterityModifier() {
		return getAbilityModifier(1);
	}

	public default int getConstitutionModifier() {
		return getAbilityModifier(2);
	}

	public default int getIntelligenceModifier() {
		return getAbilityModifier(3);
	}

	public default int getWisdomModifier() {
		return getAbilityModifier(4);
	}

	public default int getCharismaModifier() {
		return getAbilityModifier(5);
	}
}
