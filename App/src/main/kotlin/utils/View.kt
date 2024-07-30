package utils

import javafx.fxml.FXMLLoader
import javafx.scene.layout.AnchorPane

class View(viewName: String) {

    private val view : AnchorPane

    init {
        val loader = FXMLLoader(javaClass.getResource("/views/${viewName}.fxml"))
        view = loader.load()
    }

    fun getView() : AnchorPane {
        return view
    }
}