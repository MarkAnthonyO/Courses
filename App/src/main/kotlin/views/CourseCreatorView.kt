package views

import components.Course
import components.Student
import getter.TeacherGetter
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.TextField
import window.Window
import java.util.ArrayList

class CourseCreatorView {

    @FXML
    lateinit var txtName : TextField

    fun addCourse(event: ActionEvent) {
        val course = Course()

        if(txtName.text.equals("")){
            showMessage()
            return
        }

        course.name = txtName.text
        course.teacher = TeacherGetter.get(1)
    }

    fun back(event: ActionEvent) {
        Window.getWindow().changeToView("courses_view")
    }

    private fun showMessage() {
        val alert = Alert(Alert.AlertType.WARNING)
        alert.title = "Adevertencia"
        alert.contentText = "Faltan datos en el formulario"
        alert.showAndWait()
    }
}