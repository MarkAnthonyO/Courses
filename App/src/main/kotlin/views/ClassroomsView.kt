package views

import components.Classroom
import components.ClassroomCard
import getter.ClassroomGetter
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.geometry.Insets
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.VBox
import utils.Component
import window.Window
import java.net.URL
import java.util.*

class ClassroomsView : Initializable {
    @FXML
    lateinit var list : VBox
    @FXML
    lateinit var txtQuery : TextField

    fun createClassroom(event: ActionEvent) {
        Window.getWindow().changeToView("classroom_creator_view")
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        val classrooms = ClassroomGetter.getAll()

        if(classrooms.isEmpty()) {
            println("No existen salones")
            return
        }

        queryClassrooms(classrooms)
    }

    fun query(key : KeyEvent) {
        if (key.code == KeyCode.ENTER)
            queryClassrooms(ClassroomGetter.get(txtQuery.text))
    }

    fun queryClassrooms(classrooms : ArrayList<Classroom>) {
        list.children.removeAll(list.children)
        for (classroom in classrooms) {
            val loader = Component<AnchorPane, ClassroomCard>("classroom_card", ClassroomCard(classroom, this))
            val teacherCard = loader.getComponent()
            VBox.setMargin(teacherCard, Insets(10.0, 10.0 , 0.0, 10.0))
            list.children.add(teacherCard)
        }
    }

    fun back() {
        Window.getWindow().changeToView("courses_view")
    }
}