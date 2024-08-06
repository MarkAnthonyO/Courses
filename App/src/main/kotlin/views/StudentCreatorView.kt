package views

import components.Student
import generator.StudentGenerator
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.TextField
import window.Window

class StudentCreatorView {
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

    fun addStudent(event: ActionEvent) {
        if (txtName.text.equals("") || txtLastname.text.equals("") || txtAge.text.equals("") || txtTelephone.text.equals("") || txtAddress.text.equals("")) {
            showMissingDataMessage()
            return
        }

        if (txtTelephone.text.length > 10) {
            showAlertTelephone()
            return
        }

        println("Datos completos")

        try {
            val student = Student(1, txtName.text, txtLastname.text, Integer.parseInt(txtAge.text), txtTelephone.text, txtAddress.text)
            StudentGenerator.generate(student)
        } catch (ex : Exception) {
            showErrorMessage(ex.message)
            println(ex.message)
            return
        }

        showConfirmation()
        returnToStudentsView()
    }

    private fun showMissingDataMessage() {
        val alert = Alert(Alert.AlertType.WARNING)
        alert.title = "Adevertencia"
        alert.contentText = "Faltan datos en el formulario"
        alert.showAndWait()
    }

    private fun showErrorMessage(message: String?) {
        val alert = Alert(Alert.AlertType.WARNING)
        alert.title = "Adevertencia"
        alert.contentText = "Se ha producido un error: ${message}"
        alert.showAndWait()
    }

    private fun showConfirmation() {
        val alert = Alert(Alert.AlertType.INFORMATION)
        alert.title = "Listo"
        alert.contentText = "Se ha registrado al alumno, regresando a la vista de alumnos"
        alert.showAndWait()
    }

    private fun showAlertTelephone() {
        val alert = Alert(Alert.AlertType.WARNING)
        alert.title = "Adevertencia"
        alert.contentText = "El numero telefonico tiene mas de 10 digitos"
        alert.showAndWait()
    }

    private fun returnToStudentsView() {
        Window.getWindow().changeToView("students_view")
    }

    fun back(event: ActionEvent) {
        returnToStudentsView()
    }
}