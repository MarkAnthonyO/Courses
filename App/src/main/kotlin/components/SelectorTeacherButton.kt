package components

import javafx.scene.control.Button
import javafx.scene.text.Font
import window.Window

class SelectorTeacherButton(private val element : Teacher) : Button() {
    init {

        text = "${element.id} | ${element.name}"
        font = Font("JetBrainsMono NF Regular", 16.0)
        prefWidth = 500.0

        setOnAction {
            Window.getWindow().userData = element
        }
    }
}