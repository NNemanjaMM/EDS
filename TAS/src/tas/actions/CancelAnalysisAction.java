package tas.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import tas.analyzer.ThreatAnalyzer;
import tas.gui.WorkingDialog;

public class CancelAnalysisAction extends AbstractAction {

	private static final long serialVersionUID = 8742157777461519490L;
	
	private ThreatAnalyzer backgroundProcess;
	private WorkingDialog dialog;

	public CancelAnalysisAction(String title, ThreatAnalyzer backgroundProcess, WorkingDialog dialog) {
        putValue(NAME, title);
        this.backgroundProcess = backgroundProcess;
        this.dialog = dialog;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		dialog.setProgressBarValue(111);
		backgroundProcess.cancel(true);
	}

}
