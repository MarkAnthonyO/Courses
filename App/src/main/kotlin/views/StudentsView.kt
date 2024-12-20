package views

import components.Student
import components.StudentCard
import components.Teacher
import components.TeacherCard
import getter.StudentGetter
import getter.TeacherGetter
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.geometry.Insets
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.VBox
import utils.Component
import window.Window
import java.net.URL
import java.util.*

class StudentsView : Initializable {
    @FXML
    lateinit var list : VBox
    @FXML
    lateinit var txtQuery : TextField

    fun createStudent(event: ActionEvent) {
        Window.getWindow().changeToView("student_creator_view")
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        val students = StudentGetter.getAll()

        if(students.isEmpty()) {
            println("No existen estudiantes")
            return
        }

        queryStudents(students)
    }

    fun query(key : KeyEvent) {
        if (key.code == KeyCode.ENTER)
            queryStudents(StudentGetter.get(txtQuery.text))
    }

    fun queryStudents(students: ArrayList<Student>) {
        list.children.removeAll(list.children)
        for (student in students) {
            val loader = Component<AnchorPane, StudentCard>("student_card", StudentCard(student, this))
            val teacherCard = loader.getComponent()
            VBox.setMargin(teacherCard, Insets(10.0, 10.0 , 0.0, 10.0))
            list.children.add(teacherCard)
        }
    }

    fun back() {
        Window.getWindow().changeToView("courses_view")
    }
}