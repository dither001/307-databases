package chargen;

public class Player implements Actor {

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

	public Player() {
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

}
