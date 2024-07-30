package views

import components.Course
import connection.SQLiteConnection
import getter.CourseGetter
import javafx.event.ActionEvent
import javafx.fxml.Initializable
import window.Window
import java.net.URL
import java.util.*

class CoursesView : Initializable{

    fun createCourse(event: ActionEvent) {
        Window.getWindow().changeToView("course_creator_view")
    }

    fun back(event: ActionEvent) {
        val w = Window.getWindow()
        w.changeToView("students_view")
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        SQLiteConnection.openConnection()
        val courses : ArrayList<Course> = CourseGetter.getAll()

        if(courses.isEmpty()) {
            println("No existen cursos")
            SQLiteConnection.closeConnection()
            return
        }

        courses.forEach { course ->
            println(course.name)
        }
        SQLiteConnection.closeConnection()
    }
}