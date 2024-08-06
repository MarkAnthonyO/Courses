package views

import components.Classroom
import generator.ClassroomGenerator
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.TextField
import window.Window

class ClassroomCreatorView {
    @FXML
    lateinit var txtName : TextField
    @FXML
    lateinit var txtCapacity : TextField

    fun addClassroom(action: ActionEvent) {
        if (txtName.text.equals("") || txtCapacity.text.equals("")) {
            showMissingDataMessage()
            return
        }

        try {
            val classroom = Classroom(1, txtName.text, Integer.parseInt(txtCapacity.text))
            ClassroomGenerator.generate(classroom)
        } catch (ex : Exception) {
            showErrorMessage(ex.message)
            return
        }

        showConfirmation()
        returnToClassroomView()
    }

    private fun showMissingDataMessage() {
        val alert = Alert(Alert.AlertType.WARNING)
        alert.title = "Advertencia"
        alert.contentText = "Faltan datos en el formulario"
        alert.showAndWait()
    }

    private fun showErrorMessage(message : String?) {
        val alert = Alert(Alert.AlertType.WARNING)
        alert.title = "Advertencia"
        alert.contentText = "Se ha producido un error: ${message}"
        alert.showAndWait()
    }

    private fun showConfirmation() {
        val alert = Alert(Alert.AlertType.INFORMATION)
        alert.title = "Listo"
        alert.contentText = "Se ha registrado el salon, regresando a la vista de salones"
        alert.showAndWait()
    }

    fun back(action: ActionEvent) {
        returnToClassroomView()
    }

    private fun returnToClassroomView() {
        Window.getWindow().changeToView("classrooms_view")
    }
}