package be.vigilis.controllers;


import be.vigilis.utility.sceneControl.ChangeScene;
import be.vigilis.utility.sceneControl.NewStage;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class MenuController {
    public static Stage stage;

    @FXML
    private void add(){
        ChangeScene.init("/fxml/home.fxml", "Voeg een record toe!");
    }

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


    @FXML
    private void searchByKboName() {
        stage = NewStage.getStage("Zoek een record op naam!", "/fxml/search/dialogs/searchByKboNameDialog.fxml");
        stage.show();
    }

    @FXML
    private void searchByKboNr() {
        stage = NewStage.getStage("Zoek een record op KBO-Nr!", "/fxml/search/dialogs/searchByKboNrDialog.fxml");
        stage.show();
    }

    @FXML
    private void searchByCity() {
        stage = NewStage.getStage("Zoek een record op Stad!", "/fxml/search/dialogs/searchByCityDialog.fxml");
        stage.show();
    }

    @FXML
    private void searchByType() {
        stage = NewStage.getStage("Zoek een record op Type Aanvraag!", "/fxml/search/dialogs/searchByTypeDialog.fxml");
        stage.show();
    }

    @FXML
    private void searchByState() {
        stage = NewStage.getStage("Zoek een record op Status Aanvraag!", "/fxml/search/dialogs/searchByStateDialog.fxml");
        stage.show();
    }

    @FXML
    private void searchByLang() {
        stage = NewStage.getStage("Zoek een record op Taal Aanvraag!", "/fxml/search/dialogs/searchByLangDialog.fxml");
        stage.show();
    }
}
