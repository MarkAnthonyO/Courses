package window;

import javafx.animation.KeyFrame
import javafx.animation.KeyValue
import javafx.animation.Timeline
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.util.Duration
import utils.View

class Window : Stage() {

    init {
        title = "Courses"

        minWidth = 854.0
        minHeight = 480.0

        width = 1280.0
        height = 720.0

        scene = Scene(View("courses_view").getView())
    }

    fun changeToView(viewName : String) {
        val timeline = Timeline()
        val key = KeyFrame(Duration.millis(400.0), KeyValue(scene.root.opacityProperty(),0))
        timeline.getKeyFrames().add(key)
        timeline.setOnFinished {
            getWindow().scene = Scene(View(viewName).getView())
        }
        timeline.play();
    }

    companion object {
        private var window : Window = Window()

        fun getWindow() : Window {
            return window
        }
    }
}
