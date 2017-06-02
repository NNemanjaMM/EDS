package tas.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import tas.actions.CancelAnalysisAction;
import tas.actions.CloseAnalysisAction;
import tas.actions.OpenAnalysisAction;
import tas.analyzer.ThreatAnalyzer;

public class WorkingDialog extends JDialog {

	private static final long serialVersionUID = -483201866634973300L;
	
	private ThreatAnalyzer backgroundWork;
	private String diagramFile;
	private String reportFile;
	private String reportPath;
	private JLabel labelPhase;
	private JLabel labelNumber;
	private JProgressBar progressBar;
	private JButton buttonCancel;
	private JButton buttonClose;
	private JButton buttonOpen;
	
	public WorkingDialog(ThreatAnalyzer backgroundWork, String diagram, String report, String reportPath) {
		super();
		this.backgroundWork = backgroundWork;
		this.diagramFile = diagram;
		this.reportFile = report;
		this.reportPath = reportPath;
		initializeDialog();
		initializeComponents();
	}

	private void initializeDialog() {
		setSize(350,220);
		setTitle("Analyzing Threats...");
		setResizable(false);
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			System.out.println("Windows look and feel unsupported.");
		}
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(MainWindow.getInstance());		
	}

	private void initializeComponents() {
		setLayout(new GridBagLayout());
		JLabel labelDiagram = new JLabel("Diagram file: " + diagramFile);
		JLabel labelReport = new JLabel("Report file: " + reportFile);
		labelNumber = new JLabel("Phase: 1 of 6");
		labelPhase = new JLabel("Validating diagram");
		progressBar = new JProgressBar();
		buttonCancel = new JButton(new CancelAnalysisAction("Cancel", backgroundWork, this));
		buttonClose = new JButton(new CloseAnalysisAction("Close", this));
		buttonOpen = new JButton(new OpenAnalysisAction("Locate Report", this, reportPath));

		UIManager.put("ProgressBar.repaintInterval", new Integer(15));
		UIManager.put("ProgressBar.cycleTime", new Integer(4000));
        progressBar.setBorderPainted(true); 
		buttonCancel.setPreferredSize(new Dimension(100, 25));
		buttonClose.setPreferredSize(new Dimension(100, 25));
		progressBar.setPreferredSize(new Dimension(300, 25));
		buttonOpen.setPreferredSize(new Dimension(100, 25));

		buttonOpen.setVisible(false);
		buttonClose.setVisible(false);
		setPhase(0);
		
		add(labelNumber, new GridBagConstraints(0, 0, 2, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10,0,0,0), 5, 5));		
		add(labelPhase, new GridBagConstraints(0, 1, 2, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 5, 5));		
		add(progressBar, new GridBagConstraints(0, 2, 2, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5,0,0,0), 5, 5));
		
		add(labelDiagram, new GridBagConstraints(0, 3, 2, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(10,0,0,0), 5, 5));		
		add(labelReport, new GridBagConstraints(0, 4, 2, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,0,0,0), 5, 5));
		
		add(buttonCancel, new GridBagConstraints(0, 5, 2, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10,0,10,0), 5, 5));
		add(buttonClose, new GridBagConstraints(0, 5, 2, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(10,40,10,20), 5, 5));
		add(buttonOpen, new GridBagConstraints(0, 5, 2, 1, 0, 0, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(10,20,10,40), 5, 5));
	}

	private void setPhase(int progressValue) {
		progressBar.setIndeterminate(false);
		switch (progressValue) {
			case 0: 
				progressBar.setIndeterminate(true);
				labelNumber.setText("Initializing");
				labelPhase.setText("Waiting for process to start");
				break;
			case 1: 
				progressBar.setIndeterminate(false);
				labelNumber.setText("Phase: 1 of 6");
				labelPhase.setText("Validating diagram");
				break;
			case 10: 
				labelNumber.setText("Phase: 2 of 6");
				labelPhase.setText("Importing diagram");
				break;
			case 20: 
				labelNumber.setText("Phase: 3 of 6");
				labelPhase.setText("Decomposing diagram");
				break;
			case 45: 
				labelNumber.setText("Phase: 4 of 6");
				labelPhase.setText("Analyzing components");
				break;
			case 70: 
				labelNumber.setText("Phase: 5 of 6");
				labelPhase.setText("Creating report");
				break;
			case 90: 
				labelNumber.setText("Phase: 6 of 6");
				labelPhase.setText("Saving report");
				break;
			case 100: 
				progressBar.setIndeterminate(false);
				buttonCancel.setVisible(false);
				break;
			case 111:
				progressBar.setIndeterminate(true);
				buttonCancel.setEnabled(false);
				labelNumber.setText("Canceling");
				labelPhase.setText("Waiting for process to stop");
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				break;
		}		
	}
	
	public void setProgressBarValue(int value) {
		progressBar.setValue(value);
		setPhase(value);
	}
	
	public void setResultLabel(boolean successful) {
		Font font = labelNumber.getFont();
		Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
		buttonOpen.setVisible(true);
		buttonClose.setVisible(true);
		setCursor(Cursor.getDefaultCursor());
		if (successful) {
			labelNumber.setText("DONE");
			labelPhase.setText("Threat analysis is finished");
			labelNumber.setFont(boldFont);
			buttonOpen.setEnabled(true);
		} else {
			labelNumber.setText("NOT COMPLETED");
			labelPhase.setText("Threat analysis was unable to finish");
			labelNumber.setForeground(Color.RED);
			labelNumber.setFont(boldFont);
			buttonOpen.setEnabled(false);
		}
	}
}
