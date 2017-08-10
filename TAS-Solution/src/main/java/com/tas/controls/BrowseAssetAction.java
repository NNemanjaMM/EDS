package com.tas.controls;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.tas.gui.MainWindow;

public class BrowseAssetAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5609881124089381726L;

	public BrowseAssetAction(String title) {
        putValue(NAME, title);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//JFileChooser fileChooser = new JFileChooser("D:\\Master\\work\\TAS\\TAS-Solution\\src\\main\\resources\\com\\tas\\xml\\assets");	// TODO AFTER Change or remove default location
		JFileChooser fileChooser = new JFileChooser("E:\\Users\\NemanjaM\\Documents\\Practice\\Master\\TAS\\TAS-Solution\\src\\main\\resources\\com\\tas\\xml\\assets");	// TODO AFTER Change or remove default location
		FileFilter filter = new FileNameExtensionFilter("XML files (*.xml)", "xml");
		fileChooser.setFileFilter(filter);
		fileChooser.setDialogTitle("Choose Asset Definitions File");
		fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
		
		int result = fileChooser.showOpenDialog(MainWindow.getInstance());		
		
		if (result == JFileChooser.APPROVE_OPTION) {
			String fileLocation = fileChooser.getSelectedFile().getAbsolutePath();
			MainWindow.getInstance().setAssetsLocation(fileLocation);
		}		
	}
}

