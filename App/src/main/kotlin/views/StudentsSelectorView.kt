package views

import components.*
import getter.StudentGetter
import getter.TeacherGetter
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

class StudentsSelectorView : Initializable {

    @FXML
    lateinit var listSelectable : VBox

    @FXML
    lateinit var alert : Label

    @FXML
    lateinit var txtQuery : TextField

    private var students : ArrayList<Student> = ArrayList()

    fun back(event: ActionEvent) {
        Window.getWindow().userData = students
        (event.source as Node).scene.window.hide()
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        val listOfStudents = StudentGetter.get("")
        students = Window.getWindow().userData as ArrayList<Student>
        queryStudents(listOfStudents)
    }


    fun query(key: KeyEvent) {
        if (key.code == KeyCode.ENTER) {
            val listOfStudents = StudentGetter.get(txtQuery.text)
            queryStudents(listOfStudents)
        }
    }

    private fun queryStudents(listOfStudents: ArrayList<Student>) {

        if (listOfStudents.isEmpty()) {
            showEmptyStudentList()
            return
        }

        showStudentList()
        listSelectable.children.removeAll(listSelectable.children)

        for (student in listOfStudents) {

            val studentButton = SelectorStudentButton(student, false)

            for (studentS in students) {
                if (student.id == studentS.id)
                    studentButton.isDisable = true
            }

            VBox.setMargin(studentButton, Insets(10.0, 10.0, 0.0, 10.0))
            studentButton.setOnAction {
                studentButton.isDisable = true
                println("Se presiono")
                students.add(student)
            }

            listSelectable.children.add(studentButton)
        }
    }

    private fun showEmptyStudentList() {
        listSelectable.isVisible = false
        alert.isVisible = true
    }

    private fun showStudentList() {
        listSelectable.isVisible = true
        alert.isVisible = false
    }
}