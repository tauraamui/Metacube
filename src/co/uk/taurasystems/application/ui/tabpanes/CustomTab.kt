package co.uk.taurasystems.application.ui.tabpanes

import javafx.scene.control.Tab

class CustomTab : Tab() {

    var unsaved = false

    open fun load() {}
}
