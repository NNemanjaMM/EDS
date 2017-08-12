package com.tas.controls;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.tas.gui.MainWindow;
import com.tas.utils.ResourcesLocation;

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
		
		String folderLocation = ResourcesLocation.getInstance().getAssetLocationFolder();
		
		JFileChooser fileChooser = new JFileChooser(folderLocation);
		FileFilter filter = new FileNameExtensionFilter("XML files (*.xml)", "xml");
		fileChooser.setFileFilter(filter);
		fileChooser.setDialogTitle("Choose Asset Definitions File");
		fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
		
		int result = fileChooser.showOpenDialog(MainWindow.getInstance());		
		
		if (result == JFileChooser.APPROVE_OPTION) {
			String fileLocation = fileChooser.getSelectedFile().getAbsolutePath();
			MainWindow.getInstance().setAssetsLocation(fileLocation);

		    int index = fileLocation.lastIndexOf('\\');		
		    if (index == -1) {
			    index = fileLocation.lastIndexOf('/');	
		    }
			ResourcesLocation.getInstance().setAssetLocationFolder(fileLocation.substring(0,index));
		}		
	}
}

