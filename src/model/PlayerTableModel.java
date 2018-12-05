package model;

import chargen.*;
import chargen.Class;

@SuppressWarnings("serial")
public class PlayerTableModel extends EntityTableModel<Player> {
	
	/*
	 * ATTRIBUTES
	 */
//	public final Attribute<Integer> ID = new MutableAttribute<>("ID", Integer.class, Player::getId, Player::setId);
	public final Attribute<String> NAME = new MutableAttribute<>("NAME", String.class, Player::getName,
			Player::setName);

	public final Attribute<Alignment> ALI = new MutableAttribute<>("ALI", Alignment.class, Player::getAlignment,
			Player::setAlignment);
	public final Attribute<Background> BGD = new MutableAttribute<>("BACKGROUND", Background.class,
			Player::getBackground, Player::setBackground);
	public final Attribute<Race> RACE = new MutableAttribute<>("RACE", Race.class, Player::getRace, Player::setRace);

	public final Attribute<Integer> LVL = new MutableAttribute<>("LVL", Integer.class, Player::getLevel,
			Player::setLevel);
	public final Attribute<Integer> EXP = new MutableAttribute<>("EXP", Integer.class, Player::getExperience,
			Player::setExperience);
	public final Attribute<Class> CLASS = new MutableAttribute<>("CLASS", Class.class, Player::getJob, Player::setJob);
	public final Attribute<Subclass> SUBCLASS = new MutableAttribute<>("SUBCLASS", Subclass.class, Player::getSubclass,
			Player::setSubclass);

	public final Attribute<Integer> STR = new MutableAttribute<>("STR", Integer.class, Player::getStrength,
			Player::setStrength);
	public final Attribute<Integer> DEX = new MutableAttribute<>("DEX", Integer.class, Player::getDexterity,
			Player::setDexterity);
	public final Attribute<Integer> CON = new MutableAttribute<>("CON", Integer.class, Player::getConstitution,
			Player::setConstitution);
	public final Attribute<Integer> INT = new MutableAttribute<>("INT", Integer.class, Player::getIntelligence,
			Player::setIntelligence);
	public final Attribute<Integer> WIS = new MutableAttribute<>("WIS", Integer.class, Player::getWisdom,
			Player::setWisdom);
	public final Attribute<Integer> CHA = new MutableAttribute<>("CHA", Integer.class, Player::getCharisma,
			Player::setCharisma);

	/*
	 * CONSTRUCTORS
	 */
	public PlayerTableModel() {
		setColumns(NAME, ALI, BGD, RACE, LVL, EXP, CLASS, SUBCLASS, STR, DEX, CON, INT, WIS, CHA);
	}
}
