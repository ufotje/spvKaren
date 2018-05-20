package be.vigilis.controllers;


import be.vigilis.utility.sceneControl.NewStage;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class MenuController {
    public static Stage stage;

    @FXML
    private void updateByName() {
        stage = NewStage.getStage("Update een record!", "/fxml/update/updateByNameDialog.fxml");
        stage.show();
    }

    @FXML
    private void updateByKbo() {
        stage = NewStage.getStage("Update een record!", "/fxml/update/updateByKboDialog.fxml");
        stage.show();
    }

    @FXML
    private void deleteByName() {
        stage = NewStage.getStage("Verwijder een record!", "/fxm/delete/deleteByNameDialog.fxml");
        stage.show();
    }

    @FXML
    private void deleteByKbo() {
        stage = NewStage.getStage("Verwijder een record!", "/fxml/delete/deleteByKboDialog.fxml");
        stage.show();
    }
}
