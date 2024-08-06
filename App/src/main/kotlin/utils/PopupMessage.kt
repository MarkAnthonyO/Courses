package utils

import javafx.scene.control.Alert

class PopupMessage(title: String, message : String, type: PopupMessageType) : Alert(AlertType.NONE) {
    init {
        this.title = title

        when(type) {
            PopupMessageType.ERROR -> {
                alertType = AlertType.ERROR
                this.contentText = message
            }

            PopupMessageType.INFO -> {
                alertType = AlertType.INFORMATION
                this.contentText = message
            }

            PopupMessageType.CONFIRMATION -> {
                alertType = AlertType.CONFIRMATION
                this.contentText = message
            }
        }
    }
}