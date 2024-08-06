package components

import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.AnchorPane
import javafx.scene.text.Font
import javafx.scene.text.TextAlignment
import window.Window

class SelectedStudent(element : Student) : AnchorPane() {

    val txtName = Label()
    val btnDel = Button()

    init {
        txtName.text = "${element.id} | ${element.name} ${element.lastname}"
        txtName.font = Font("JetBrainsMono NF Regular", 16.0)
        txtName.textAlignment = TextAlignment.LEFT
        txtName.alignment = Pos.CENTER_LEFT
        txtName.style = "-fx-text-fill: #fff;"

        style = "-fx-background-color: #ffaa00;"

        btnDel.text = "\uF2ED"
        btnDel.font = Font("Font Awesome 6 Free Regular", 16.0)
        btnDel.textAlignment = TextAlignment.LEFT
        btnDel.alignment = Pos.CENTER
        btnDel.style = "-fx-text-fill: #ffaa00; -fx-background-color: #fff;"

        AnchorPane.setTopAnchor(txtName, 10.0)
        AnchorPane.setLeftAnchor(txtName, 10.0)
        AnchorPane.setRightAnchor(txtName, 50.0)
        AnchorPane.setBottomAnchor(txtName, 10.0)

        AnchorPane.setTopAnchor(btnDel, 10.0)
        AnchorPane.setRightAnchor(btnDel, 10.0)
        AnchorPane.setBottomAnchor(btnDel, 10.0)

        children.add(txtName)
        children.add(btnDel)

        prefWidth = 500.0
    }
}