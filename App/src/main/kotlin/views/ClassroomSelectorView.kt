package views

import components.Classroom
import components.SelectorClassroomButton
import components.SelectorStudentButton
import components.Student
import getter.ClassroomGetter
import getter.StudentGetter
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.layout.VBox
import window.Window
import java.net.URL
import java.util.*

class ClassroomSelectorView : Initializable {
    @FXML
    lateinit var list : VBox

    @FXML
    lateinit var alert : Label

    @FXML
    lateinit var txtQuery : TextField

    fun back(event: ActionEvent) {
        (event.source as Node).scene.window.hide()
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        val listOfClassroom = ClassroomGetter.getAll()
        queryClassroom(listOfClassroom)
    }


    fun query(key: KeyEvent) {
        if (key.code == KeyCode.ENTER) {
            val listOfClassroom = ClassroomGetter.get(txtQuery.text)
            queryClassroom(listOfClassroom)
        }
    }

    private fun queryClassroom(listOfClassroom: ArrayList<Classroom>) {

        if (listOfClassroom.isEmpty()) {
            showEmptyClassroomList()
            return
        }

        showClassroomList()

        for (classroom in listOfClassroom) {
            list.children.add(SelectorClassroomButton(classroom))
        }
    }

    private fun showEmptyClassroomList() {
        list.isVisible = false
        alert.isVisible = true
    }

    private fun showClassroomList() {
        list.isVisible = true
        alert.isVisible = false
    }

}