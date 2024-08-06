package views

import components.Classroom
import components.Course
import components.SelectedStudent
import components.Student
import components.Teacher
import generator.CourseGenerator
import generator.StudentListGenerator
import getter.CourseGetter
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
    @FXML
    lateinit var txtClassroom : TextField

    private var teacher : Teacher? = null
    private var students : ArrayList<Student> = ArrayList()
    private var classroom : Classroom? = null

    fun addCourse(event: ActionEvent) {
        if(txtName.text.equals("") || teacher == null || students.isEmpty() || classroom == null){
            showMissingDataMessage()
            return
        }

        try {
            val id = if(CourseGetter.getAll().isEmpty()) {
                0
            } else {
                CourseGetter.getAll().last().id + 1
            }

            val course = Course(id, txtName.text, teacher, students, classroom)
            CourseGenerator.generate(course)
            println("Id de curso: ${CourseGetter.getAll().last().id}")
            StudentListGenerator.generate(CourseGetter.getAll().last(), students)
        } catch (ex : Exception) {
            showErrorMessage("${ex.message} ${ex.localizedMessage}")
            return
        }

        showConfirmation()
        returnToCoursesView()
    }

    fun selectTeacher(event: ActionEvent) {
        val popWindow = Stage()
        Window.getWindow().userData = null
        popWindow.title = "Profesores"
        popWindow.scene = Scene(View("teacher_selector_view").getView())
        popWindow.showAndWait()

        if (Window.getWindow().userData == null) {
            println("No se especifico ningun profesor")
            return
        }

        teacher = (Window.getWindow().userData as Teacher)
        Window.getWindow().userData = null
        txtTeacher.text = teacher!!.name
    }

    fun selectStudents(event: ActionEvent) {
        listStudent.children.removeAll(listStudent.children)
        Window.getWindow().userData = students

        val popWindow = Stage()
        popWindow.title = "Estudiantes"
        popWindow.scene = Scene(View("students_selector_view").getView())
        popWindow.showAndWait()

        students =  Window.getWindow().userData as ArrayList<Student>
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

    fun selectClassroom(event: ActionEvent) {
        val popWindow = Stage()
        Window.getWindow().userData = null
        popWindow.title = "Salon"
        popWindow.scene = Scene(View("classroom_selector_view").getView())
        popWindow.showAndWait()

        if (Window.getWindow().userData == null) {
            println("No se especifico ningun salon")
            return
        }

        classroom = (Window.getWindow().userData as Classroom)
        Window.getWindow().userData = null
        txtClassroom.text = classroom!!.name
    }

    private fun showMissingDataMessage() {
        val alert = Alert(Alert.AlertType.WARNING)
        alert.title = "Adevertencia"
        alert.contentText = "Faltan datos en el formulario"
        alert.showAndWait()
    }

    private fun showErrorMessage(message : String?) {
        val alert = Alert(Alert.AlertType.WARNING)
        alert.title = "Advertencia"
        alert.contentText = "Se ha producido un error: ${message}"
        alert.showAndWait()
    }

    private fun showConfirmation() {
        val alert = Alert(Alert.AlertType.INFORMATION)
        alert.title = "Listo"
        alert.contentText = "Se ha registrado el curso, regresando a la vista de cursos"
        alert.showAndWait()
    }

    private fun returnToCoursesView() {
        Window.getWindow().changeToView("courses_view")
    }

    fun back(event: ActionEvent) {
        returnToCoursesView()
    }
}