package app

import javafx.application.Application
import javafx.stage.Stage
import window.Window

class App : Application() {
    override fun start(primaryStage: Stage?) {
        val w = Window.getWindow()
        w.show()
        w.centerOnScreen()
    }

    fun startApp() {
        launch()
    }
}