package views

import components.Course
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.TextField
import window.Window

class CourseCreatorView {

    @FXML
    lateinit var txtName : TextField

    fun addCourse(event: ActionEvent) {
        val course = Course()
        course.name = txtName.text

        println(course.name)
    }

    fun back(event: ActionEvent) {
        Window.getWindow().changeToView("courses_view")
    }
}