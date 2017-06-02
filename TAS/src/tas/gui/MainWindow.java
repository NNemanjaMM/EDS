package tas.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import tas.actions.BrowseDiagramAction;
import tas.actions.BrowseReportAction;
import tas.actions.CancelAnalysisAction;
import tas.actions.StartAnalysisAction;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 6982195527732029083L;

	private static MainWindow instance = null;
	
	private JTextField contentDiagram;
	private JTextField contentReport;
	
	private MainWindow() {
		super();
	}
	
	private void initialize() {
		initializeWindow();
		initializeContent();
	}
	
	private void initializeWindow() {
		setSize(500,200);
		setTitle("Threat Analysis System");
		setResizable(false);
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			System.out.println("Windows look and feel unsupported.");
		}
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);		
	}
	
	private void initializeContent() {
		setLayout(new GridBagLayout());
		contentDiagram = new JTextField();
		contentReport = new JTextField();
		JLabel labelBrowse = new JLabel("Source diagram location: ");
		JLabel labelReport = new JLabel("Save report location: ");
		
		JButton buttonBrowse = new JButton(new BrowseDiagramAction("..."));
		JButton buttonReport = new JButton(new BrowseReportAction("..."));
		JButton buttonStart = new JButton(new StartAnalysisAction("Analyze diagram and Create report"));
		
		buttonBrowse.setPreferredSize(new Dimension(20, 25));
		contentDiagram.setPreferredSize(new Dimension(300, 25));		
		buttonReport.setPreferredSize(new Dimension(20, 25));
		contentReport.setPreferredSize(new Dimension(300, 25));
		buttonStart.setPreferredSize(new Dimension(200, 25));
				
		add(labelBrowse, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,0,0,0), 5, 5));
		add(contentDiagram, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(5,0,5,0), 5, 5));
		add(buttonBrowse, new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(5,5,5,0), 5, 5));
		add(labelReport, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,0,0,0), 5, 5));
		add(contentReport, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(5,0,5,0), 5, 5));
		add(buttonReport, new GridBagConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(5,5,5,0), 5, 5));
		add(buttonStart, new GridBagConstraints(1, 2, 2, 1, 0, 0, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(20,0,0,0), 5, 5));
	}
	
	public void setReportLocation(String location) {
		contentReport.setText(location);
	}
	
	public void setDiagramLocation(String location) {
		contentDiagram.setText(location);		
	}
	
	public void setReportDefaultLocation() {
		DateFormat dateFormat = new SimpleDateFormat("MMdd_HHmmss");
		Date date = new Date();		
		contentReport.setText("D:\\Master\\temp\\ThreatReport_" + dateFormat.format(date));
	}	
	
	
	public String getReportLocation() {
		return contentReport.getText();
	}
	
	public String getDiagramLocation() {
		return contentDiagram.getText();		
	}
	
	public static MainWindow getInstance() {
		if (instance == null) {
			instance = new MainWindow();
			instance.initialize();
		}
		return instance;
	}
	
	public static void main(String[] args) {
		MainWindow.getInstance().setVisible(true);
	}
}
