package components

import deleter.CourseDeleter
import getter.CourseGetter
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.ButtonType
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import utils.PopupMessage
import utils.PopupMessageType
import views.CoursesView
import java.net.URL
import java.util.*

class CourseCard(private var course: Course, private var coursesView: CoursesView) : Initializable {

    @FXML
    lateinit var txtCourse : Label
    @FXML
    lateinit var txtTeacher : Label
    @FXML
    lateinit var txtNumStudents : Label
    @FXML
    lateinit var txtClassroom : Label
    @FXML
    lateinit var btnD : Button

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        txtCourse.text = course.name
        txtTeacher.text = "Impartido por: ${course.teacher.name} ${course.teacher.lastname}"
        txtNumStudents.text = "Numero de estudiantes inscritos: ${course.students.size} personas"
        txtClassroom.text = "Salon: ${course.classroom.name}"

        btnD.setOnAction { e ->
            delete()
        }
    }

    fun delete()  {
        val pop = PopupMessage("Advertencia", "Se va a borrar el curso: ${course.name}", PopupMessageType.CONFIRMATION)
        pop.showAndWait()

        if (pop.result != ButtonType.OK) {
            return
        }

        println("Se va a borrar")
        CourseDeleter.delete(course.id)
        coursesView.queryCourses(CourseGetter.getAll())
    }
}