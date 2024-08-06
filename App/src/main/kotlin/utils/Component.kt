package utils

import javafx.fxml.FXMLLoader

class Component<Type, Controller>(componentName: String, controller : Controller) {

    private var loader : FXMLLoader = FXMLLoader(javaClass.getResource("/components/${componentName}.fxml"))

    init {
        loader.setController(controller)
    }

    fun getController() : Controller {
        return loader.getController()
    }

    fun getComponent() : Type {
        return loader.load()
    }
}