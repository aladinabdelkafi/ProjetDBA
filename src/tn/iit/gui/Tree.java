package tn.iit.gui;

import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import tn.iit.connection.DBConnection;

public class Tree extends JTree {

	private static final long serialVersionUID = 1L;
	private DBConnection connection;
	private String query;
	public Tree(DBConnection connection) {
		this.connection = connection;
		constructTree();
	}

	private void constructTree() {

		DefaultMutableTreeNode root = new DefaultMutableTreeNode(connection.getLogin());
		

		DefaultMutableTreeNode tables = new DefaultMutableTreeNode("TABLES");
		tables = tables(tables);
		
		DefaultMutableTreeNode views = new DefaultMutableTreeNode("VIEWS");
		views = views(views);
		DefaultMutableTreeNode indexes = new DefaultMutableTreeNode("INDEXES");
		indexes = Indexes(indexes);
		DefaultMutableTreeNode sequences = new DefaultMutableTreeNode("SEQUENCES");
		sequences = Sequences(sequences);
		DefaultMutableTreeNode procedures = new DefaultMutableTreeNode("PROCEDURES");
		procedures = Procedures(procedures);
		DefaultMutableTreeNode triggers = new DefaultMutableTreeNode("TRIGGERS");
		triggers = Triggers(triggers);
		DefaultMutableTreeNode otherUsers = new DefaultMutableTreeNode("OTHER USERS");
		otherUsers = Users(otherUsers);

		root.add(tables);
		root.add(views);
		root.add(indexes);
		root.add(sequences);
		root.add(procedures);
		root.add(triggers);
		root.add(otherUsers);

		DefaultTreeModel model = (DefaultTreeModel) getModel();

		model.setRoot(root);
		
		

	}
	
	//ajouter les tables avec et les  colonnes  dans tree
	private DefaultMutableTreeNode tables(DefaultMutableTreeNode tables) {
		query = "SELECT TABLE_NAME FROM USER_TABLES";
		List<String> listTable = connection.getTables(query);
		

		
		DefaultMutableTreeNode node = null;
		List<String> columns = null;
		for (String s : listTable) {
			node = new DefaultMutableTreeNode(s);
			String query_col = "SELECT COLUMN_NAME FROM USER_TAB_COLUMNS WHERE TABLE_NAME ='" + s + "'";
			columns = connection.getTableColumns(query_col);
			for (String col : columns) {
				node.add(new DefaultMutableTreeNode(col));
			}
			tables.add(node);
		}

		return tables;
	}
	//ajouter views  dans tree

	private DefaultMutableTreeNode views(DefaultMutableTreeNode views) {
		query = "SELECT VIEW_NAME FROM USER_VIEWS";
		
		List<String> viewsList = connection.getViews(query);

		DefaultMutableTreeNode node = null;
		List<String> columns = null;

		for (String s : viewsList) {
			node = new DefaultMutableTreeNode(s);
			String query_col = "SELECT COLUMN_NAME FROM USER_TAB_COLUMNS WHERE TABLE_NAME ='" + s + "'";
			columns = connection.getTableColumns(query_col);
			for (String col : columns) {
				node.add(new DefaultMutableTreeNode(col));
			}
			views.add(node);
		}

		return views;
	}
	//ajouter Indexes  dans tree

	private DefaultMutableTreeNode Indexes(DefaultMutableTreeNode indexes) {
		query = "SELECT INDEX_NAME FROM USER_INDEXES";
		List<String> indexesList = connection.getIndexes(query);

		DefaultMutableTreeNode node = null;

		for (String s : indexesList) {
			node = new DefaultMutableTreeNode(s);
			indexes.add(node);
		}

		return indexes;
	}
	
	//ajouter Triggers  dans tree
	private DefaultMutableTreeNode Triggers(DefaultMutableTreeNode triggers) {
		query = "SELECT TRIGGER_NAME FROM USER_TRIGGERS";
		List<String> triggersList = connection.getTriggers(query);

		DefaultMutableTreeNode node = null;

		for (String s : triggersList) {
			node = new DefaultMutableTreeNode(s);
			triggers.add(node);
		}

		return triggers;
	}
	//ajouter Users  dans tree
	private DefaultMutableTreeNode Users(DefaultMutableTreeNode otherUsers) {
		query = "SELECT USERNAME FROM ALL_USERS";
		List<String> otherUsersList = connection.getUsers(query);

		DefaultMutableTreeNode node = null;

		for (String s : otherUsersList) {
			node = new DefaultMutableTreeNode(s);
			otherUsers.add(node);
		}

		return otherUsers;
	}
	
	//ajouter Sequences  dans tree
		private DefaultMutableTreeNode Sequences(DefaultMutableTreeNode sequences) {
			query = "SELECT SEQUENCE_NAME FROM USER_SEQUENCES";
			List<String> sequencesList = connection.getSequences(query);

			DefaultMutableTreeNode node = null;

			for (String s : sequencesList) {
				node = new DefaultMutableTreeNode(s);
				sequences.add(node);
			}

			return sequences;
		}
		//ajouter Procedures  dans tree
		private DefaultMutableTreeNode Procedures(DefaultMutableTreeNode procedures) {
			query = "SELECT PROCEDURE_NAME FROM USER_PROCEDURES";
			List<String> proceduresList = connection.getProcedures(query);

			DefaultMutableTreeNode node = null;

			for (String s : proceduresList) {
				node = new DefaultMutableTreeNode(s);
				procedures.add(node);
			}

			return procedures;
		}
}
