package components

import deleter.ClassroomDeleter
import getter.ClassroomGetter
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.ButtonType
import javafx.scene.control.Label
import utils.PopupMessage
import utils.PopupMessageType
import views.ClassroomsView
import java.net.URL
import java.util.*

class ClassroomCard(private var classroom: Classroom, private var classroomsView: ClassroomsView) : Initializable {

    @FXML
    lateinit var txtClassroom : Label
    @FXML
    lateinit var btnD : Button

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        txtClassroom.text = "Nombre: ${classroom.name} | capacidad: ${classroom.capacity}"

        btnD.setOnAction { e ->
            delete()
        }
    }

    fun delete()  {
        val pop = PopupMessage("Advertencia", "Se va a borrar el salon: ${classroom.name}", PopupMessageType.CONFIRMATION)
        pop.showAndWait()

        if (pop.result != ButtonType.OK) {
            return
        }

        println("Se va a borrar")
        ClassroomDeleter.delete(classroom.id)
        classroomsView.queryClassrooms(ClassroomGetter.getAll())
    }
}