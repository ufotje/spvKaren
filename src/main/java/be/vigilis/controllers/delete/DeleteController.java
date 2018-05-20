package be.vigilis.controllers.delete;

import be.vigilis.controllers.MenuController;
import be.vigilis.repositories.GeneralRepo;
import be.vigilis.utility.validation.Warning;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeleteController {

    @FXML
    private TextField kbo;
    @FXML
    private TextField name;

    @Autowired
    private GeneralRepo repo;

    @FXML
    private void deleteByKbo() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Weet u zeker dat u dit record wilt verwijderen?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            MenuController.stage.close();
            int r = repo.deleteByKboNumber(Long.valueOf(kbo.getText()));
            if (r > 0) {
                Warning.alert("Record deleted!", "het record met kbonummer: " + kbo.getText() + " werd succesvol verwijderd.");
            } else{
                Warning.alert("Record Not found!", "Het record met kbonummer: " + kbo.getText() + " werd niet terug gevonden.");
            }
        } else {
            MenuController.stage.close();
        }
    }

    @FXML
    private void deleteByName(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Weet u zeker dat u dit record wilt verwijderen?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            MenuController.stage.close();
            int r = repo.deleteByNameKbo(name.getText());
            if (r > 0) {
                Warning.alert("Record deleted!", "Het bedrijf met naam: '" + name.getText() + "' werd succesvol verwijderd.");
            } else{
                Warning.alert("Record Not found!", "Het bedrijf met naam: '" + name.getText() + "' werd niet terug gevonden.");
            }
        } else {
            MenuController.stage.close();
        }
    }
}
