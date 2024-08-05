package views

import components.Course
import components.Teacher
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.TextField
import javafx.stage.Stage
import utils.View
import window.Window

class CourseCreatorView {

    @FXML
    lateinit var txtName : TextField
    @FXML
    lateinit var txtTeacher: TextField

    fun addCourse(event: ActionEvent) {
        val course = Course()

        if(txtName.text.equals("")){
            showMessage()
            return
        }

        if(txtName.text.equals("")){
            showMessage()
            return
        }

        course.name = txtName.text
        course.teacher = Window.getWindow().userData as Teacher
    }

    fun selectTeacher(event: ActionEvent) {
        val popWindow = Stage()
        popWindow.scene = Scene(View("teacher_selector_view").getView())
        popWindow.showAndWait()

        if (Window.getWindow().userData == null) {
            println("No se especifico ningun profesor")
            return
        }

        val teacher = (Window.getWindow().userData as Teacher)
        txtTeacher.text = teacher.name
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