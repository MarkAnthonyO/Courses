package views

import components.Teacher
import connection.SQLiteConnection
import getter.TeacherGetter
import javafx.collections.ObservableList
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.Node
import javafx.scene.control.Label
import javafx.scene.control.ListView
import window.Window
import java.net.URL
import java.util.*

class TeacherSelector : Initializable {

    @FXML
    lateinit var list : ListView<Teacher>

    @FXML
    lateinit var alert : Label

    private var teacher : Teacher? = null

    fun back(event: ActionEvent) {
        Window.getWindow().userData = teacher
        (event.source as Node).scene.window.hide()
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        val listOfTeacher = TeacherGetter.getAll()

        if (listOfTeacher.isEmpty()) {
            showEmptyTeacherList()
            return
        }

        println("Listando")
        list.items.addAll(listOfTeacher)
    }

    private fun showEmptyTeacherList() {
        list.isVisible = false
        alert.isVisible = true
    }
}