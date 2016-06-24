package co.uk.taurasystems.application.ui.tabpanes;

import javafx.scene.control.Tab;

public class CustomTab extends Tab {

	protected boolean unsaved = false;
	
	protected void load() {}
	
	public void setUnsaved(boolean unsaved) {
		this.unsaved = unsaved;
	}
	
	public boolean getUnsaved() {
		return unsaved;
	}
}
