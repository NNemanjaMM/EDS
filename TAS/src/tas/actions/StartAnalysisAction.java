package tas.actions;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import tas.analyzer.ThreatAnalyzer;
import tas.gui.MainWindow;
import tas.gui.WorkingDialog;

public class StartAnalysisAction extends AbstractAction {

	private static final long serialVersionUID = 3351176738051001159L;

	public StartAnalysisAction(String title) {
        putValue(NAME, title);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		String diagramPath = MainWindow.getInstance().getDiagramLocation();
		String reportPath = MainWindow.getInstance().getReportLocation();
		
		if (diagramPath.equals("")) {
			String message = "Diagram file name can not be empty!\nPlease locate the diagram file.";
			JOptionPane.showMessageDialog(MainWindow.getInstance(), message, "Wrong Diagram File", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (reportPath.equals("")) {	// TODO AFTER Remove or change report default location
			MainWindow.getInstance().setReportDefaultLocation();
			reportPath = MainWindow.getInstance().getReportLocation();
		}

		File diagram = new File(diagramPath);	
		File report = new File(reportPath);	
				
		ThreatAnalyzer analyzer = new ThreatAnalyzer(diagram, report);		
		WorkingDialog dialog = new WorkingDialog(analyzer, diagram.getName(), report.getName(), reportPath);
		analyzer.setDialog(dialog);
		analyzer.addPropertyChangeListener(new WorkerChangeListener(dialog));
		dialog.setVisible(true);
		
		analyzer.execute();
	}

}
