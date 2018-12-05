package controller;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import chargen.*;
import chargen.Class;
import model.*;

@SuppressWarnings("serial")
public class Main extends JFrame {

	private static final String DB_URL = "jdbc:sqlite:C:/ProgramData/chargen.db";

	/*
	 * INSTANCE FIELDS
	 */
	private PlayerTableModel tableModel;
	private JTable playerTable;

	/*
	 * CONSTRUCTOR
	 */
	public Main() {
		super("Character Generator");

		setLayout(new BorderLayout(5, 5));
		tableModel = new PlayerTableModel();
		// TODO create "instances" of data
		for (int i = 0; i < 50; ++i) {
			tableModel.addInstance(new Player());
		}

		//
		playerTable = new JTable(tableModel);
		// playerTable.setDefaultEditor(columnClass, editor);

		add(new JScrollPane(playerTable), BorderLayout.CENTER);

		// components
		JPanel sortPanel = new JPanel();
		JButton sortIdButton = new JButton("Sort by ID");
		JButton sortFirstButton = new JButton("Sort by first");
		JButton sortLastButton = new JButton("Sort by last");
		JButton sortStatusButton = new JButton("Sort by status");

		sortPanel.add(sortIdButton);
		sortPanel.add(sortFirstButton);
		sortPanel.add(sortLastButton);
		sortPanel.add(sortStatusButton);

		add(sortPanel, BorderLayout.SOUTH);

		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/*
	 * METHODS
	 */
	public static void main(String... args) {
		Main main = new Main();
		main.setVisible(true);
	}

	private List<Player> loadPlayers() {
		List<Player> players = new ArrayList<Player>();

		String query = "SELECT name, Alignment.name, Background.name, Race.name, level, experience, Class.name, SubClass.subclassName, strength, dexterity, constitution, intelligence, wisdom, charisma\r\n"
				+ "  FROM Player\r\n" + "  JOIN Background ON backgroundType = Background.id\r\n"
				+ "  JOIN Alignment ON alignmentType = Alignment.id\r\n" + "  JOIN Class ON classType = Class.id\r\n"
				+ "  JOIN SubClass ON subclassType = SubClass.id\r\n" + "  JOIN Race ON raceType = Race.id;";

		try (Connection c = DriverManager.getConnection("DB_URL")) {
			Statement statement = c.createStatement();
			ResultSet results = statement.executeQuery(query);

			while (results.next()) {
				String name = results.getString("name");
				Alignment ali = Alignment.parseAlignment(results.getString("alignment"));
				Background bgd = Background.parseBackground(results.getString("background"));
				Race rac = Race.parseRace(results.getString("race"));
				int lvl = results.getInt("level");
				int exp = results.getInt("experience");
				Class job = Class.parseClass(results.getString("class"));
				Subclass subclass = Subclass.parseSubclass(results.getString("subclass"));
				int STR = results.getInt("strength");
				int DEX = results.getInt("dexterity");
				int CON = results.getInt("constitution");
				int INT = results.getInt("intelligence");
				int WIS = results.getInt("wisdom");
				int CHA = results.getInt("charisma");
				

			}

		} catch (SQLException e) {
			e.printStackTrace();
			// JOptionPane.showMessageDialog("Unable to connect to database.", arg1);
		}

		return null;
	}

}
