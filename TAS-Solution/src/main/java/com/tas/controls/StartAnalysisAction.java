package com.tas.controls;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import com.tas.gui.MainWindow;
import com.tas.gui.WorkingDialog;
import com.tas.worker.ThreatWorker;

public class StartAnalysisAction extends AbstractAction {

	private static final long serialVersionUID = 3351176738051001159L;

	public StartAnalysisAction(String title) {
        putValue(NAME, title);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		String diagramPath = MainWindow.getInstance().getDiagramLocation();
		String assetsPath = MainWindow.getInstance().getAssetLocation();
		String exploitsPath = "D:\\Master\\work\\TAS\\TAS-Solution\\src\\main\\resources\\com\\tas\\xml\\exploits\\exploits_definitions.xml"; // TODO AFTER relocate variable
		String reportPath = MainWindow.getInstance().getReportLocation();
		boolean analyseComponents = MainWindow.getInstance().getComponentsThreatsSelected();
		
		if (diagramPath.equals("")) {
			String message = "Diagram file name can not be empty!\nPlease locate the diagram file.";
			JOptionPane.showMessageDialog(MainWindow.getInstance(), message, "Wrong Diagram File", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (assetsPath.equals("")) {
			String message = "Asset definitions file name can not be empty!\nPlease locate the asset definitions file.";
			JOptionPane.showMessageDialog(MainWindow.getInstance(), message, "Wrong Asset Definitios File", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (reportPath.equals("")) {	// TODO AFTER Remove or change report default location
			MainWindow.getInstance().setReportDefaultLocation();
			reportPath = MainWindow.getInstance().getReportLocation();
		}

		File diagram = new File(diagramPath);	
		File assets = new File(assetsPath);	
		File exploits = new File(exploitsPath);
		File report = new File(reportPath);
				
		ThreatWorker analyzer = new ThreatWorker(diagram, assets, exploits, report, analyseComponents);		
		WorkingDialog dialog = new WorkingDialog(analyzer, diagram.getName(), report.getName(), reportPath);
		analyzer.setDialog(dialog);
		analyzer.addPropertyChangeListener(new WorkerChangeListener(dialog));
		dialog.setVisible(true);
		
		analyzer.execute();
	}

}
