package views

import components.Teacher
import generator.TeacherGenerator
import getter.TeacherGetter
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Alert
import javafx.scene.control.TextField
import window.Window
import java.net.URL
import java.util.*

class TeacherCreatorView {
    @FXML
    lateinit var txtName : TextField
    @FXML
    lateinit var txtLastname : TextField
    @FXML
    lateinit var txtAge : TextField
    @FXML
    lateinit var txtTelephone : TextField
    @FXML
    lateinit var txtAddress : TextField

    fun addTeacher(event: ActionEvent) {
        if (txtName.text.equals("") || txtLastname.text.equals("") || txtAge.text.equals("") || txtTelephone.text.equals("") || txtAddress.text.equals("")) {
            showMessage()
            return
        }

        if (txtTelephone.text.length > 10) {
            showAlertTelephone()
            return
        }

        println("Datos completos")

        try {
            val teacher = Teacher(1, txtName.text, txtLastname.text, Integer.parseInt(txtAge.text), txtTelephone.text, txtAddress.text)
            TeacherGenerator.generate(teacher)
        } catch (ex : Exception) {
            showError(ex.message)
            println(ex.message)
            return
        }

        showConfirmation()
        returnToTeacherView()
    }

    private fun showMessage() {
        val alert = Alert(Alert.AlertType.WARNING)
        alert.title = "Adevertencia"
        alert.contentText = "Faltan datos en el formulario"
        alert.showAndWait()
    }

    private fun showError(message: String?) {
        val alert = Alert(Alert.AlertType.WARNING)
        alert.title = "Advertencia"
        alert.contentText = "Se ha producido un error: ${message}"
        alert.showAndWait()
    }

    private fun showConfirmation() {
        val alert = Alert(Alert.AlertType.INFORMATION)
        alert.title = "Listo"
        alert.contentText = "Se ha registrado al maestro, regresando a la vista de maestros"
        alert.showAndWait()
    }

    private fun showAlertTelephone() {
        val alert = Alert(Alert.AlertType.WARNING)
        alert.title = "Adevertencia"
        alert.contentText = "El numero telefonico tiene mas de 10 digitos"
        alert.showAndWait()
    }

    private fun returnToTeacherView() {
        Window.getWindow().changeToView("teachers_view")
    }

    fun back(event: ActionEvent) {
        returnToTeacherView()
    }
}