package views

import javafx.event.ActionEvent
import window.Window

class StudentsView {
    fun changeToCourses(event: ActionEvent) {
        val w = Window.getWindow()
        w.changeToView("courses_view")
    }
}