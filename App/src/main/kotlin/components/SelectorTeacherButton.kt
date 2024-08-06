package components

import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.text.Font
import javafx.scene.text.TextAlignment
import window.Window

class SelectorTeacherButton(private val element : Teacher) : Button() {
    init {
        text = "${element.id} | ${element.name} ${element.lastname}"
        font = Font("JetBrainsMono NF Regular", 16.0)
        textAlignment = TextAlignment.LEFT
        alignment = Pos.CENTER_LEFT

        style = "-fx-background-color: #ffaa00; -fx-text-fill: #fff; -fx-cursor: hand;"

        prefWidth = 500.0

        setOnAction {
            Window.getWindow().userData = element
            this.scene.window.hide()
        }
    }
}