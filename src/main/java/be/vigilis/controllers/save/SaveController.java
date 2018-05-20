package be.vigilis.controllers.save;

import be.vigilis.entities.Address;
import be.vigilis.entities.General;
import be.vigilis.entities.Invoices;
import be.vigilis.repositories.AddressRepo;
import be.vigilis.repositories.GeneralRepo;
import be.vigilis.utility.FileSelector;
import be.vigilis.utility.sceneControl.ChangeScene;
import be.vigilis.utility.validation.Warning;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;

import static javafx.collections.FXCollections.observableArrayList;

@Component
public class SaveController {
    //GeneralFields
    @FXML
    private ChoiceBox<String> language;
    @FXML
    private DatePicker applicationDate;
    @FXML
    private TextField kboName, kboNr, type;
    @FXML
    private TextArea notes;
    private File file;

    //AddressFields
    @FXML
    private TextField nameOrg, street, zip, city, tel, mail;

    //InvoiceFields
    @FXML
    private ChoiceBox<String> received;
    @FXML
    private DatePicker confirmation;
    @FXML
    private TextField state;

    //Repo's
    @Autowired
    @Qualifier("generalRepo")
    private GeneralRepo generalRepo;

    @FXML
    public void initialize(){
        ObservableList<String> languages = observableArrayList("NL", "FR", "EN", "DE");
        language.setItems(languages);
        language.setValue(languages.get(0));

        ObservableList<String> r = observableArrayList("OK", "NOK", "Niet aanwezig", "Aanwezig");
        received.setItems(r);
        received.setValue(r.get(0));
    }

    @FXML
    private void save() {
        Invoices invoice = new Invoices(received.getValue(), confirmation.getValue(), state.getText(), file);
        Address address = new Address(nameOrg.getText(), street.getText(), zip.getText(), city.getText(), tel.getText(), mail.getText());
        General general = new General(language.getValue(), applicationDate.getValue(), kboName.getText(), Long.valueOf(kboNr.getText()), address, type.getText(), notes.getText(), invoice);
        if(generalRepo.save(general) != null){
            Warning.alert("Save succeed", "Het record van " + kboName.getText() + " werd succesvol opgeslagen.");
            clearFields();
            ChangeScene.init("/fxml/home.fxml", "Vergunningen Camerabewaking");
        } else{
            Warning.alert("Save Failed!", "Er ging iets fout tijdens het opslaan van het record.");
        }
    }

    @FXML
    public void chooseFile() {
        file = FileSelector.chooseFile();
    }

    private void clearFields(){
        applicationDate.setValue(null);
        kboName.clear();
        kboNr.clear();
        received.setValue(null);
        nameOrg.clear();
        notes.clear();
        confirmation.setValue(null);
        state.clear();
        street.clear();
        zip.clear();
        city.clear();
        tel.clear();
        mail.clear();
        type.clear();
    }
}
