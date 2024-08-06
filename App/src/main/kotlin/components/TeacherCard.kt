package components

import deleter.TeacherDeleter
import getter.TeacherGetter
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.ButtonType
import javafx.scene.control.Label
import utils.PopupMessage
import utils.PopupMessageType
import views.TeachersView
import java.net.URL
import java.util.*

class TeacherCard(private var teacher : Teacher, private var teachersView: TeachersView) : Initializable {

    @FXML
    lateinit var txtTeacher : Label
    @FXML
    lateinit var btnD : Button

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        txtTeacher.text = "${teacher.name} ${teacher.lastname}"

        btnD.setOnAction { e ->
            delete()
        }
    }

    fun delete()  {
        val pop = PopupMessage("Advertencia", "Se va a borrar el profesor: ${teacher.name} ${teacher.lastname}", PopupMessageType.CONFIRMATION)
        pop.showAndWait()

        if (pop.result != ButtonType.OK) {
            return
        }

        println("Se va a borrar")
        TeacherDeleter.delete(teacher.id)
        teachersView.queryTeachers(TeacherGetter.getAll())
    }
}