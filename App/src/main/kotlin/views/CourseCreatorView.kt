package views

import components.Course
import components.SelectedStudent
import components.Student
import components.Teacher
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.TextField
import javafx.scene.layout.VBox
import javafx.stage.Stage
import utils.View
import window.Window

class CourseCreatorView {

    @FXML
    lateinit var txtName : TextField
    @FXML
    lateinit var txtTeacher: TextField
    @FXML
    lateinit var listStudent : VBox

    private val students : ArrayList<Student> = ArrayList()

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
        Window.getWindow().userData = null
        txtTeacher.text = teacher.name
    }

    fun back(event: ActionEvent) {
        Window.getWindow().changeToView("courses_view")
    }

    fun selectStudents(event: ActionEvent) {
        listStudent.children.removeAll(listStudent.children)
        Window.getWindow().userData = students

        val popWindow = Stage()
        popWindow.scene = Scene(View("students_selector_view").getView())
        popWindow.showAndWait()

        val students : ArrayList<Student> =  Window.getWindow().userData as ArrayList<Student>
        Window.getWindow().userData = null

        if (students.isEmpty()) {
            println("No se especifico ningun alumno")
            return
        }

        for (student in students) {
            val btnStudent = SelectedStudent(student)
            VBox.setMargin(btnStudent, Insets(10.0,10.0,0.0,10.0))

            btnStudent.btnDel.setOnAction {
                students.remove(student)
                listStudent.children.remove(btnStudent)
                println("elemento: ${student.name} removido")
            }

            listStudent.children.add(btnStudent)
        }
    }

    private fun showMessage() {
        val alert = Alert(Alert.AlertType.WARNING)
        alert.title = "Adevertencia"
        alert.contentText = "Faltan datos en el formulario"
        alert.showAndWait()
    }
}