package views

import components.Course
import components.CourseCard
import components.Teacher
import components.TeacherCard
import getter.CourseGetter
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

class TeachersView : Initializable{
    @FXML
    lateinit var list : VBox
    @FXML
    lateinit var txtQuery : TextField

    fun createTeacher(event: ActionEvent) {
        Window.getWindow().changeToView("teacher_creator_view")
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        val teachers = TeacherGetter.getAll()

        if(teachers.isEmpty()) {
            println("No existen maestros")
            return
        }

        queryTeachers(teachers)
    }

    fun query(key : KeyEvent) {
        if (key.code == KeyCode.ENTER)
            queryTeachers(TeacherGetter.get(txtQuery.text))
    }

    fun queryTeachers(teachers: ArrayList<Teacher> ) {
        list.children.removeAll(list.children)
        for (teacher in teachers) {
            val loader = Component<AnchorPane, TeacherCard>("teacher_card", TeacherCard(teacher, this))
            val teacherCard = loader.getComponent()
            VBox.setMargin(teacherCard, Insets(10.0, 10.0 , 0.0, 10.0))
            list.children.add(teacherCard)
        }
    }

    fun back() {
        Window.getWindow().changeToView("courses_view")
    }
}