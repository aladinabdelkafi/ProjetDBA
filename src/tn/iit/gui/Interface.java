package tn.iit.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import tn.iit.connection.DBConnection;
import tn.iit.diagram.MainWindow;

import javax.swing.JTree;
import javax.swing.RootPaneContainer;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

public class Interface extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static DBConnection cnxx;
	private JScrollPane scrollPane;
	private JTabbedPane tabbedPane;
	private JPanel panel;
	private JPanel requette;
	//private JPanel Digramme;
	private JTree tree;
	private JTextPane textPane;
	private JTextPane textPane_1;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws BadLocationException 
	 */
	public Interface(DBConnection cnx) throws BadLocationException, IOException {
		cnxx = cnx;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1116, 697);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		Tree jt = new Tree(cnx);
		jt.setEditable(false);
		jt.setForeground(Color.ORANGE);
		jt.setFont(jt.getFont().deriveFont(jt.getFont().getSize() + 1f));
		jt.setBorder(new LineBorder(new Color(0, 0, 153), 3, true));
		jt.setBackground(Color.LIGHT_GRAY);
		
		tree = new JTree();
		tree = jt;

		tree.setCellRenderer(new DefaultTreeCellRenderer() {

			private static final long serialVersionUID = 1L;
			private Icon db = new ImageIcon("res/db.png");
			private Icon table = new ImageIcon("res/table.png");
			private Icon view = new ImageIcon("res/ve.png");
			private Icon pro = new ImageIcon("res/pro.png");
			private Icon index = new ImageIcon("res/index.png");
			private Icon trig = new ImageIcon("res/trig.png");
			private Icon user = new ImageIcon("res/user.png");
			private Icon sec = new ImageIcon("res/sec.png");
			 @Override
			    public Color getBackgroundNonSelectionColor() {
			        return (null);
			    }
			@Override
		    public Color getBackgroundSelectionColor() {
		        return Color.GREEN;
		    }
			 @Override
			    public Color getBackground() {
			        return (null);
			    }
			@Override
			public Component getTreeCellRendererComponent(JTree tree,
			    Object value, boolean selected, boolean expanded,
			    boolean leaf, int row, boolean hasFocus) {
			        super.getTreeCellRendererComponent(tree, value, selected,expanded, leaf, row, hasFocus);
			        
			        DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) value;
			        TreeModel tmodel = tree.getModel();
			        Object root = tmodel.getRoot();
			        int nchildren = tmodel.getChildCount(root);
			        String s= value.toString();
			        if (row == 0) {
						setIcon(db);
					}
					if (s.equals("TABLES")) {
						setIcon(table);
					}
					if (s.equals("VIEWS")) {
						setIcon(view);
					}
					if (s.equals("INDEXES")) {
						setIcon(index);
					}
					if (s.equals("SEQUENCES")) {
						setIcon(sec);
					}
					if (s.equals("PROCEDURES")) {
						setIcon(pro);
					}
					if (s.equals("TRIGGERS")) {
						setIcon(trig);
					}
					if (s.equals("OTHER USERS")) {
						setIcon(user);
					}      
			        return this;
			}
		});
		
		
		


		contentPane.add(tree, BorderLayout.WEST);
		
		MouseListener ml = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {

				TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
				int selRow = tree.getRowForPath(selPath);
				if (selRow != -1) {
					if (e.getClickCount() == 1) {

						try {
							textPane.setText(cnxx.detail(selPath));
							// System.out.println(cnxx.detail(selPath));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						// System.out.println("selPath= "+selPath);

					}

				}
			}
		};
		tree.addMouseListener(ml);

		scrollPane = new JScrollPane(tree);
		getContentPane().add(scrollPane, BorderLayout.WEST);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("");
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		scrollPane.setPreferredSize(new Dimension(200, 50));
		panel = new JPanel(); 
		tabbedPane.addTab("information", new ImageIcon("res\\inf.png"), panel, null);
		tabbedPane.setBackgroundAt(0, Color.LIGHT_GRAY);
		tabbedPane.setForegroundAt(0, new Color(0, 0, 153));
		tabbedPane.setEnabledAt(0, true);
		panel.setLayout(null);

		textPane = new JTextPane();
		textPane.setForeground(Color.BLACK);
		textPane.setBackground(Color.WHITE);
		textPane.setBounds(12, 57, 244, 483);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		textPane.setBorder(BorderFactory.createCompoundBorder(border,
	            BorderFactory.createEmptyBorder(20, 20, 20, 20)));
		panel.add(textPane);
		
		textPane_1 = new JTextPane();
		textPane_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		textPane_1.setBounds(266, 57, 609, 483);
		
		textPane_1.setBorder(BorderFactory.createCompoundBorder(border,
	            BorderFactory.createEmptyBorder(20, 20, 20, 20)));
		textPane_1.setContentType( "text/html" );
		textPane_1.setEditable(false);
		cnxx.RepText(textPane_1);
		//textPane_1.setText(cnxx.RepText());
		panel.add(textPane_1);
		
		JLabel lblNewLabel = new JLabel("Repr\u00E9sentation Textuelle");
		lblNewLabel.setForeground(new Color(0, 0, 204));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(266, 13, 244, 33);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Schema Diagram");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setForeground(new Color(0, 0, 204));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						 MainWindow window = null;
						window = new MainWindow();
						    window.setVisible(true);
						
					}
				});	
			}
		});
		btnNewButton.setBounds(509, 13, 165, 33);
		panel.add(btnNewButton);

		
		requette = new JPanel();
		tabbedPane.addTab("Requette", new ImageIcon("res\\edit.png"), requette, null);
		tabbedPane.setBackgroundAt(1, Color.LIGHT_GRAY);
		tabbedPane.setForegroundAt(1, new Color(0, 0, 153));
		tabbedPane.setEnabledAt(1, true);
		requette.setLayout(null);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.BOLD, 19));
		textArea.setBounds(12, 13, 800, 188);
		requette.add(textArea);

		JButton btnExecuter = new JButton("Executer");
		btnExecuter.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExecuter.setForeground(new Color(0, 0, 204));
		btnExecuter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[][] obj;
				
				List<String> columns = null;
				
				//
				obj = cnxx.ExecQuery(textArea.getText());
				 String[] arrOfStr = textArea.getText().split(" ");
				 String query_col = "SELECT COLUMN_NAME FROM USER_TAB_COLUMNS WHERE TABLE_NAME ='" + arrOfStr[3] + "'";
				 columns = cnxx.getTableColumns(query_col);
				 String []names=new String[columns.size()];
				 for(int i=0;i<columns.size();i++) {
					 names[i]=columns.get(i);
				 }
				 
				 System.out.println();

			TableRequette st = new TableRequette(obj,names);
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						st.createAndShowGUI(obj,names);
					}
				});
			}
		});
		btnExecuter.setBounds(295, 244, 120, 43);
		requette.add(btnExecuter);

	}

	
}
