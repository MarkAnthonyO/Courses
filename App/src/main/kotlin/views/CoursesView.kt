package views

import javafx.event.ActionEvent
import window.Window

class CoursesView {
    fun back(event: ActionEvent) {
        var w = Window.getWindow()
        w.changeToView("students_view")
    }
}