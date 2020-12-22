package tn.iit.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class DBConnection {
	public static String login;
	private static String mdp;
	private static String orcl;
	public static Connection cnx;

	private final String[] INDEX_INFO = { "INDEX_NAME", "INDEX_TYPE", "TABLE_OWNER", "TABLE_NAME", "TABLE_TYPE",
			"PREFIX_LENGTH", "TABLESPACE_NAME", "DEGREE", "INSTANCES" };
	private final String[] SEQUENCE_INFO = { "SEQUENCE_NAME", "MIN_VALUE", "MAX_VALUE", "INCREMENT_BY" };

	private final String[] PROCEDURE_INFO = { "OBJECT_NAME", "PROCEDURE_NAME", "OVERLOAD", "OBJECT_TYPE",
			"IMPLTYPENAME" };

	private final String[] TRIGGER_INFO = { "TRIGGER_NAME", "TRIGGER_TYPE", "TRIGGERING_EVENT", "TABLE_OWNER",
			"BASE_OBJECT_TYPE", "REFERENCING_NAMES", "ACTION_TYPE" };

	private final String[] COLUMN_INFO = { "TABLE_NAME", "COLUMN_NAME", "DATA_TYPE", "LAST_ANA" };

	private final String[] TABLE_VIEW_INFO = { "TABLE_NAME", "TABLESPACE_NAME", "STATUS", "NUM_ROWS", "BLOCKS" };
	private final String[] USER_INFO = { "USERNAME", "USER_ID", "CREATED" };

	public DBConnection(String login, String mdp, String orcl) {
		this.login = login;
		this.mdp = mdp;
		this.orcl = orcl;

	}

	public void connection(JPanel jf) throws SQLException, ClassNotFoundException {
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			cnx = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:" + orcl, login, mdp);

		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(jf, "Internal Error!", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(jf, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

		if (cnx == null) {

			JOptionPane.showMessageDialog(jf, "Failed to connect to database!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void deconnection(JFrame jf) {
		try {
			cnx.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(jf, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public String getLogin() {
		return login;
	}
	
	public String getMdp() {
		return mdp;
	}

	public Connection getCnx() {
		return cnx;
	}

	//***** méthode retourne les noms des tables ********
	public List<String> getTables(String query) {

		List<String> tables = new ArrayList<>();

		try {
			Statement s = cnx.createStatement();
			ResultSet result = s.executeQuery(query);
			while (result.next()) {
				String tableName = result.getString("TABLE_NAME");
				if (tableName != null)
					tables.add(tableName);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

		return tables;
	}
	// ******* méthode retourne les noms des colonnes d'un table *********
	public List<String> getTableColumns(String query) {

		List<String> tableColumns = new ArrayList<>();

		try {

			Statement s = cnx.createStatement();
			ResultSet result = s.executeQuery(query);

			while (result.next()) {
				String columnName = result.getString("COLUMN_NAME");
				if (columnName != null)
					tableColumns.add(columnName);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

		return tableColumns;
	}
	// ******* méthode retourne les noms des Views *********
	public List<String> getViews(String query) {

		List<String> views = new ArrayList<>();

		try {

			Statement s = cnx.createStatement();
			ResultSet result = s.executeQuery(query);

			while (result.next()) {
				String viewName = result.getString("VIEW_NAME");
				if (viewName != null)
					views.add(viewName);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

		return views;
	}
	// ******* méthode retourne les noms des Indexes *********
	public List<String> getIndexes(String query) {

		List<String> indexes = new ArrayList<>();

		try {

			Statement s = cnx.createStatement();
			ResultSet result = s.executeQuery(query);

			while (result.next()) {
				String indexName = result.getString("INDEX_NAME");
				if (indexName != null)
					indexes.add(indexName);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

		return indexes;
	}
	// ******* méthode retourne les noms des Sequences *********
	public List<String> getSequences(String query) {

		List<String> sequences = new ArrayList<>();

		try {
			Statement s = cnx.createStatement();
			ResultSet result = s.executeQuery(query);

			while (result.next()) {
				String sequenceName = result.getString("SEQUENCE_NAME");
				if (sequenceName != null)
					sequences.add(sequenceName);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

		return sequences;
	}
	// ******* méthode retourne les noms des Procedures *********
	public List<String> getProcedures(String query) {

		List<String> procedures = new ArrayList<>();

		try {

			Statement s = cnx.createStatement();
			ResultSet result = s.executeQuery(query);

			while (result.next()) {
				String procedureName = result.getString("PROCEDURE_NAME");
				if (procedureName != null)
					procedures.add(procedureName);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

		return procedures;
	}
	// ******* méthode retourne les noms des Triggers *********
	public List<String> getTriggers(String query) {

		List<String> triggers = new ArrayList<>();

		try {
			Statement s = cnx.createStatement();
			ResultSet result = s.executeQuery(query);

			while (result.next()) {
				String triggerName = result.getString("TRIGGER_NAME");
				if (triggerName != null)
					triggers.add(triggerName);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

		return triggers;
	}
	// ******* méthode retourne les noms des Users *********
	public List<String> getUsers(String query) {

		List<String> otherUsers = new ArrayList<>();

		try {

			Statement s = cnx.createStatement();
			ResultSet result = s.executeQuery(query);

			while (result.next()) {
				String userName = result.getString("USERNAME");
				if (userName != null)
					otherUsers.add(userName);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

		return otherUsers;
	}
//********* méthode retourne l'execution d'une requette ********** 
	public String[][] ExecQuery(String query) {
		String[][] data, data2;
		data = new String[500][500];
		int i = 0;
		int x = 0, y = 0;
		try {
			Statement s = cnx.createStatement();
			ResultSet result = s.executeQuery(query);
			while (result.next()) {
				i = 0;
				y = 0;
				while (true) {
					i++;
					try {
						data[x][y] = result.getString(i);
					} catch (SQLException e) {
						break;
					}
					y++;

				}

				x++;
				i = 0;
			}
		} catch (SQLException e) {
			return null;
		}

		data2 = new String[x][y];
		for (int k = 0; k < x; k++) {
			for (int l = 0; l < y; l++) {

				data2[k][l] = data[k][l];
			}

		}

		return data2;
	}
	//*********** méthode retourne la détail de  TABLES ou SEQUENCES PROCEDURES  etc...
	public String detail(TreePath selPath) throws SQLException {
		String query = "";
		Statement s = cnx.createStatement();
		String ch = "";
		ResultSet result;
		int i = 0;

		if (selPath.getPathCount() == 3) {
			String val = selPath.getPathComponent(2).toString();
			switch (selPath.getPathComponent(1).toString()) {
			case "TABLES":
				query = "SELECT TABLE_NAME,TABLESPACE_NAME,STATUS,NUM_ROWS,BLOCKS FROM USER_TABLES WHERE TABLE_NAME='"
						+ val + "'";
				result = s.executeQuery(query);
				i = 0;
				while (result.next()) {
					while (i < TABLE_VIEW_INFO.length) {
						ch += TABLE_VIEW_INFO[i] + " : " + result.getString(i + 1) + "\n";
						i++;
					}
				}
				break;
			case "SEQUENCES":
				query = "SELECT SEQUENCE_NAME, MIN_VALUE, MAX_VALUE, INCREMENT_BY FROM USER_SEQUENCES WHERE SEQUENCE_NAME='"
						+ val + "'";
				result = s.executeQuery(query);
				i = 0;
				while (result.next()) {
					while (i < SEQUENCE_INFO.length) {
						ch += SEQUENCE_INFO[i] + " : " + result.getString(i + 1) + "\n";
						i++;
					}
				}
				break;
			case "PROCEDURES":
				query = "SELECT OBJECT_NAME,PROCEDURE_NAME,OVERLOAD,OBJECT_TYPE,IMPLTYPENAME FROM USER_PROCEDURES WHERE PROCEDURE_NAME='"
						+ val + "'";
				result = s.executeQuery(query);
				i = 0;
				while (result.next()) {
					while (i < PROCEDURE_INFO.length) {
						ch += PROCEDURE_INFO[i] + " : " + result.getString(i + 1) + "\n";
						i++;
					}
				}
				break;
			case "INDEXES":

				query = "SELECT INDEX_NAME, INDEX_TYPE, TABLE_OWNER, TABLE_NAME, TABLE_TYPE, PREFIX_LENGTH, TABLESPACE_NAME,DEGREE,INSTANCES FROM USER_INDEXES WHERE INDEX_NAME='"
						+ val + "'";
				i = 0;
				result = s.executeQuery(query);
				while (result.next()) {
					while (i < INDEX_INFO.length) {
						ch += INDEX_INFO[i] + " : " + result.getString(i + 1) + "\n";
						i++;
					}
				}
				break;

			case "VIEWS":
				query = "SELECT TABLE_NAME,TABLESPACE_NAME,STATUS,NUM_ROWS,BLOCKS FROM USER_VIEWS WHERE VIEW_NAME='"
						+ val + "'";
				i = 0;
				result = s.executeQuery(query);
				while (result.next()) {
					while (i < TABLE_VIEW_INFO.length) {
						ch += TABLE_VIEW_INFO[i] + " : " + result.getString(i + 1) + "\n";
						i++;
					}
				}
				break;
			case "TRIGGERS":
				query = "SELECT * FROM USER_TRIGGERS WHERE TRIGGER_NAME='" + val + "'";
				i = 0;
				result = s.executeQuery(query);
				while (result.next()) {
					while (i < TRIGGER_INFO.length) {
						ch += TRIGGER_INFO[i] + " : " + result.getString(i + 1) + "\n";
						i++;
					}
				}
				break;

			case "OTHER USERS":
				query = "SELECT * FROM ALL_USERS WHERE USERNAME='" + val + "'";
				i = 0;
				result = s.executeQuery(query);
				while (result.next()) {
					while (i < USER_INFO.length) {
						ch += USER_INFO[i] + " : " + result.getString(i + 1) + "\n";
						i++;
					}
				}

				break;

			}
		}
		if (selPath.getPathCount() == 4) {
			String val = selPath.getPathComponent(2).toString();
			query = "SELECT * FROM USER_TAB_COLUMNS WHERE COLUMN_NAME='" + selPath.getPathComponent(3).toString()
					+ "' AND TABLE_NAME='" + val + "'";
			i = 0;
			result = s.executeQuery(query);
			while (result.next()) {
				while (i < COLUMN_INFO.length) {
					ch += COLUMN_INFO[i] + " : " + result.getString(i + 1) + "\n";
					i++;
				}
			}
		}

		return ch;
	}
//*********** méthode retourne la représentation textuelle *************************
	public void RepText(JTextPane textpan) throws BadLocationException, IOException {
		HTMLDocument doc = (HTMLDocument) textpan.getDocument();
		HTMLEditorKit editorKit = (HTMLEditorKit) textpan.getEditorKit();

		String query = "SELECT TABLE_NAME FROM USER_TABLES";
		List<String> listTable = getTables(query);
		String rep = "";
		List<String> columns = null;
		int i = 0;
		for (String s : listTable) {
			i = 1;

			rep += "<b>" + s + "</b>" + "(";
			String query_col = "SELECT COLUMN_NAME FROM USER_TAB_COLUMNS WHERE TABLE_NAME ='" + s + "'";
			columns = getTableColumns(query_col);
			for (String col : columns) {
				if (i == 1 || col.equals("START_DATE")) {
					rep += "<font color='#ff0000'><u>" + col + "</u></font>" + " , ";
					i = 0;
				} else if (col.indexOf("_ID") != -1) {
					rep += "<font color='#0000ff'>#" + col + "</font> , ";
				} else {
					rep += col + " , ";
				}
			}
			rep += ");" + "<br>";

		}
		editorKit.insertHTML(doc, doc.getLength(), rep, 0, 0, null);

	}

	
}
