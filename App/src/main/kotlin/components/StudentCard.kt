package components

import deleter.StudentDeleter
import getter.StudentGetter
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.ButtonType
import javafx.scene.control.Label
import utils.PopupMessage
import utils.PopupMessageType
import views.StudentsView
import java.net.URL
import java.util.*

class StudentCard(private var student : Student, private var studentsView: StudentsView) : Initializable {

    @FXML
    lateinit var txtStudent: Label
    @FXML
    lateinit var btnD : Button

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        txtStudent.text = "${student.name} ${student.lastname}"

        btnD.setOnAction { e ->
            delete()
        }
    }

    fun delete()  {
        val pop = PopupMessage("Advertencia", "Se va a borrar el profesor: ${student.name} ${student.lastname}", PopupMessageType.CONFIRMATION)
        pop.showAndWait()

        if (pop.result != ButtonType.OK) {
            return
        }

        println("Se va a borrar")
        StudentDeleter.delete(student.id)
        studentsView.queryStudents(StudentGetter.getAll())
    }
}