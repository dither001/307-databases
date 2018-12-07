package controller;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import chargen.*;
import model.*;

@SuppressWarnings("serial")
public class Main extends JFrame {

	private static final String DB_URL = "jdbc:sqlite:C:/ProgramData/chargen.db";

	/*
	 * INSTANCE FIELDS
	 */
	private Connection connection;
	private PlayerTableModel tableModel;
	private JTable playerTable;

	/*
	 * CONSTRUCTOR
	 */
	public Main() {
		super("Character Generator");

		setLayout(new BorderLayout(5, 5));
		tableModel = new PlayerTableModel();

		List<Player> results = loadPlayers();
		if (results.size() > 0) {
			tableModel.addAll(results);
		} else {
			System.out.println("No data to load.");
			for (int i = 0; i < 50; ++i)
				tableModel.addInstance(new Player());

			int count = insertPlayers();
			System.out.println("Inserted " + count + " characters.");
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

		// sortPanel.add(sortIdButton);
		// sortPanel.add(sortFirstButton);
		// sortPanel.add(sortLastButton);
		// sortPanel.add(sortStatusButton);

		// add(sortPanel, BorderLayout.SOUTH);

		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/*
	 * METHODS
	 */
	public static void main(String... args) {

		// Object[] array = Subclass.getSubclasses();
		// for (int i = 0; i < array.length; ++i)
		// System.out.println("(" + array[i].toString() + "),");

		Main main = new Main();
		main.setVisible(true);
	}

	private void connect() {
		try {
			java.lang.Class.forName("org.sqlite.JDBC");

			connection = DriverManager.getConnection(DB_URL);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private int lastPlayerIndex() {
		int lastPlayerIndex = 0;
		String query = "SELECT id FROM Player DESC LIMIT 1;";

		ResultSet results;
		try {
			results = connection.createStatement().executeQuery(query);
			lastPlayerIndex = results.getInt("id");
			System.out.println(lastPlayerIndex);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lastPlayerIndex;
	}

	private int insertPlayers() {
		int count = 0;
		if (connection == null)
			connect();

		int playerIndex = 1 + lastPlayerIndex();
		PreparedStatement statement = null;
		String string = String.format("INSERT INTO Player VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );");

		try {
			statement = connection.prepareStatement(string);

			List<Player> list = tableModel.getInstances();
			for (Iterator<Player> it = list.iterator(); it.hasNext();) {
				Player player = it.next();

				if (!player.isPersistent()) {
					int col = 1;
					statement.setString(col++, String.valueOf(playerIndex++));
					statement.setString(col++, String.valueOf(player.getName()));
					statement.setString(col++, String.valueOf(player.getAlignment().indexOf()));
					statement.setString(col++, String.valueOf(player.getBackground().indexOf()));
					statement.setString(col++, String.valueOf(player.getRace().indexOf()));
					statement.setString(col++, String.valueOf(player.getLevel()));
					statement.setString(col++, String.valueOf(player.getExperience()));
					statement.setString(col++, String.valueOf(player.getJob().indexOf()));
					statement.setString(col++, String.valueOf(player.getSubclass().indexOf()));
					statement.setString(col++, String.valueOf(player.getStrength()));
					statement.setString(col++, String.valueOf(player.getDexterity()));
					statement.setString(col++, String.valueOf(player.getConstitution()));
					statement.setString(col++, String.valueOf(player.getIntelligence()));
					statement.setString(col++, String.valueOf(player.getWisdom()));
					statement.setString(col++, String.valueOf(player.getCharisma()));

					statement.execute();
					++count;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}

	private List<Player> loadPlayers() {
		List<Player> players = new ArrayList<Player>();

		if (connection == null)
			connect();

		String query = "SELECT Player.id, Player.name, Alignment.name, Background.name, Race.name, level, experience, Class.name, Subclass.name, strength, dexterity, constitution, intelligence, wisdom, charisma\r\n"
				+ "    FROM Player\r\n" + "    JOIN Alignment ON alignment = Alignment.id\r\n"
				+ "    JOIN Background ON background = Background.id\r\n" + "    JOIN Class ON class = Class.id\r\n"
				+ "    JOIN Subclass ON subclass = Subclass.id\r\n" + "    JOIN Race ON race = Race.id;";

		try {
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery(query);

			int id;
			String name;
			Alignment ali;
			Background bgd;
			Race rac;
			int lvl, exp, STR, DEX, CON, INT, WIS, CHA;
			chargen.Class job;
			Subclass sub;
			while (results.next()) {
				int i = 1;
				id = results.getInt(i++);
				name = results.getString(i++);
				ali = Alignment.parseAlignment(results.getString(i++));
				bgd = Background.parseBackground(results.getString(i++));
				rac = Race.parseRace(results.getString(i++));
				lvl = results.getInt(i++);
				exp = results.getInt(i++);
				job = chargen.Class.parseClass(results.getString(i++));
				sub = Subclass.parseSubclass(results.getString(i++));
				STR = results.getInt(i++);
				DEX = results.getInt(i++);
				CON = results.getInt(i++);
				INT = results.getInt(i++);
				WIS = results.getInt(i++);
				CHA = results.getInt(i++);

				// System.out.println(String.format("%d %d %d %d %d %d", STR, DEX, CON, INT,
				// WIS, CHA));

				players.add(new Player(id, name, ali, bgd, rac, lvl, exp, job, sub, STR, DEX, CON, INT, WIS, CHA));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return players;
	}

}
