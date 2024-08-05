package views

import components.SelectorTeacherButton
import components.Teacher
import getter.TeacherGetter
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.control.Label
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.layout.VBox
import java.net.URL
import java.util.*

class TeacherSelectorView : Initializable {

    @FXML
    lateinit var list : VBox

    @FXML
    lateinit var alert : Label

    fun back(event: ActionEvent) {
        (event.source as Node).scene.window.hide()
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        val listOfTeacher = TeacherGetter.getAll()

        if (listOfTeacher.isEmpty()) {
            showEmptyTeacherList()
            return
        }

        println("Listando")

        for ( teacher : Teacher in listOfTeacher) {
            val teacherButton = SelectorTeacherButton(teacher)
            VBox.setMargin(teacherButton, Insets(10.0, 10.0, 10.0, 10.0))
            list.children.add(teacherButton)
        }
    }

    fun query(key: KeyEvent) {
        if (key.code == KeyCode.ENTER) {
            println("Enter presionado")
        }
    }

    private fun showEmptyTeacherList() {
        list.isVisible = false
        alert.isVisible = true
    }
}