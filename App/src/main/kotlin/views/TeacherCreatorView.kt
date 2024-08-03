package views

import javafx.fxml.FXML
import javafx.scene.control.TextField

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
}