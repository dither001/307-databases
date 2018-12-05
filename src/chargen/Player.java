package chargen;

import model.Persistent;

public class Player implements Actor, Persistent {

	/*
	 * PERSISTENT FIELDS
	 */
	private boolean isPersistent;
	private boolean hasChanged;
	private int id;

	/*
	 * INSTANCE FIELDS
	 */
	private int[] abilityScores;
	private int[] abilityCeiling;
	private boolean isFemale;

	private Alignment alignment;
	private Class job;
	private Subclass subclass;
	private Race race;
	private Background background;

	// basic
	private String name;
	private int experience;
	private int level;

	/*
	 * CONSTRUCTORS
	 */
	public Player() {
		isPersistent = false;
		hasChanged = false;
		id = 0;

		abilityScores = Dice.rollAbilities();
		abilityCeiling = new int[] { 20, 20, 20, 20, 20, 20 };
		// if Constitution greater than Strength, then female
		isFemale = (abilityScores[2] > abilityScores[0]) ? true : false;

		alignment = Alignment.random();
		job = Class.random();
		subclass = Subclass.random(this);
		race = Race.random();
		background = Background.random();

		// initialize new character
		name = CharacterName.randomName(isFemale, race);
		experience = 0;
		level = 1;

	}

	public Player(String name, Alignment alignment, Background background, Race race, int level, int experience, Class job, Subclass subclass, int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma) {
		// TODO
		this.name = name;
	}
	
	
	/*
	 * INSTANCE METHODS
	 */
	public String toStringVerbose() {
		String s = "";

		s += name + " " + alignment.toString() + " " + race.toString() + " " + job.toString() + " "
				+ subclass.toString() + " " + background.toString();
		s += String.format("%n[%2d,%2d,%2d,%2d,%2d,%2d]", abilityScores[0], abilityScores[1], abilityScores[2],
				abilityScores[3], abilityScores[4], abilityScores[5]);

		return s;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean isFemale() {
		return isFemale;
	}

	@Override
	public Alignment getAlignment() {
		return alignment;
	}

	@Override
	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}

	@Override
	public Background getBackground() {
		return background;
	}

	@Override
	public void setBackground(Background background) {
		this.background = background;
	}

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public int getExperience() {
		return experience;
	}

	@Override
	public void setExperience(int experience) {
		this.experience = experience;
	}

	@Override
	public int[] getAbilityScores() {
		return abilityScores;
	}

	@Override
	public void setAbilityScores(int[] abilityScores) {
		int length = this.abilityScores.length;
		for (int i = 0; i < length; ++i) {
			this.abilityScores[i] = abilityScores[i];
		}
	}

	@Override
	public int[] getAbilityCeiling() {
		return abilityCeiling;
	}

	@Override
	public Race getRace() {
		return race;
	}

	@Override
	public void setRace(Race race) {
		this.race = race;
	}

	@Override
	public Class getJob() {
		return job;
	}

	@Override
	public void setJob(Class job) {
		this.job = job;
	}

	@Override
	public Subclass getSubclass() {
		return subclass;
	}

	@Override
	public void setSubclass(Subclass subclass) {
		this.subclass = subclass;
	}

	@Override
	public boolean isPersistent() {
		return isPersistent;
	}

	@Override
	public boolean hasChanged() {
		return hasChanged;
	}

	@Override
	public int getId() {
		if (isPersistent)
			return id;
		else
			return 0;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

}
