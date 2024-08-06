package views

import components.Course
import components.CourseCard
import getter.CourseGetter
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

class CoursesView : Initializable{
    @FXML
    lateinit var list : VBox
    @FXML
    lateinit var txtQuery : TextField

    fun createCourse(event: ActionEvent) {
        Window.getWindow().changeToView("course_creator_view")
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        val courses = CourseGetter.getAll()

        if(courses.isEmpty()) {
            println("No existen cursos")
            return
        }

        queryCourses(courses)
    }

    fun query(key : KeyEvent) {
        if (key.code == KeyCode.ENTER)
            queryCourses(CourseGetter.get(txtQuery.text))
    }

    fun queryCourses(courses: ArrayList<Course> ) {
        list.children.removeAll(list.children)
        for (course in courses) {
            val loader = Component<AnchorPane, CourseCard>("course_card", CourseCard(course, this))
            val courseCard = loader.getComponent()
            VBox.setMargin(courseCard, Insets(10.0, 10.0 , 0.0, 10.0))
            list.children.add(courseCard)
        }
    }

    fun showStudentsView(event: ActionEvent) {
        Window.getWindow().changeToView("students_view")
    }

    fun showTeachersView(event: ActionEvent) {
        Window.getWindow().changeToView("teachers_view")
    }

    fun showClassroomsView(event: ActionEvent) {
        Window.getWindow().changeToView("classrooms_view")
    }
}