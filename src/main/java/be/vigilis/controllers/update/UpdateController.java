package be.vigilis.controllers.update;

import be.vigilis.controllers.MenuController;
import be.vigilis.entities.Additional;
import be.vigilis.entities.Address;
import be.vigilis.entities.General;
import be.vigilis.entities.Invoices;
import be.vigilis.repositories.GeneralRepo;
import be.vigilis.utility.FileSelector;
import be.vigilis.utility.sceneControl.ChangeScene;
import be.vigilis.utility.validation.Warning;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class UpdateController {
    @FXML
    private TextField kbo;

    @FXML
    private TextField name;

    //GeneralFields
    @FXML
    private ChoiceBox<String> language;
    @FXML
    private DatePicker applicationDate;
    @FXML
    private TextField kboName, kboNr, type;
    @FXML
    private TextArea notes;

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

    //AdditionFields
    @FXML
    private DatePicker receivedOn, experation, notification, requested, continuance, payed;
    private List<File> additionalFiles = new ArrayList<>();

    @Autowired
    private GeneralRepo repo;
    private List<File> files;
    private General g;
    private Address a;
    private Invoices inv;
    private Additional add;

    @FXML
    private void updateByKbo() {
        g = repo.findByKboNumber(Long.valueOf(kbo.getText()));
        a = g.getAddress();
        inv = g.getInvoice();
        add = g.getAdditional();
        ChangeScene.init("/fxml/update/update.fxml", "Update een record!");
        setFields(g, a, inv, add);
        MenuController.stage.close();
    }

    @FXML
    private void addDoc() {
        additionalFiles.add(FileSelector.chooseFile());
    }

    @FXML
    private void updateByName() {
        g = repo.findByNameKbo(name.getText());
        a = g.getAddress();
        inv = g.getInvoice();
        add = g.getAdditional();
        if (g != null) {
            ChangeScene.init("/fxml/update/update.fxml", "Update een record!");
            setFields(g, a, inv, add);
            MenuController.stage.close();
        } else {
            Warning.alert("Record not found!", "Het record werd niet terug gevonden.");
        }
    }

    @FXML
    private void update() {
        updateAdditional();
        updateAddress();
        updateInvoice();
        updateGeneral();
        if (repo.save(g) != null) {
            Warning.alert("Record Updated!", "Het record voor " + kboName.getText() + " met kbonummer " +
                    kboNr.getText() + " werd succesvol geupdate.");
            ChangeScene.init("/fxml/home.fxml", "Camera Vergunningen");
        } else {
            Warning.alert("Update failed", "Het record kon niet geupdate worden.");
        }
    }

    private void setFields(General general, Address address, Invoices invoices, Additional additional) {
        language.setValue(general.getLanguage());
        applicationDate.setValue(general.getApplication());
        kboName.setText(general.getNameKbo());
        kboNr.setText("" + general.getKboNumber());
        received.setValue(invoices.getReceived());
        nameOrg.setText(address.getNameOrg());
        notes.setText(general.getNotes());
        confirmation.setValue(invoices.getConfirmationDate());
        state.setText(invoices.getState());
        street.setText(address.getStreetAndNumber());
        zip.setText(address.getZip());
        city.setText(address.getCity());
        tel.setText(address.getTelephone());
        mail.setText(address.getEmail());
        type.setText(general.getApplicationType());
        receivedOn.setValue(additional.getReceiveDate());
        experation.setValue(additional.getExpiration());
        notification.setValue(additional.getNotification());
        requested.setValue(additional.getRequested());
        continuance.setValue(additional.getContinuity());
        payed.setValue(additional.getCharge());
    }

    private void updateAdditional(){
        add.setReceiveDate(receivedOn.getValue());
        add.setNotification(notification.getValue());
        add.setContinuity(continuance.getValue());
        add.setCharge(payed.getValue());
    }

    private void updateAddress() {
        a.setNameOrg(nameOrg.getText());
        a.setStreetAndNumber(street.getText());
        a.setZip(zip.getText());
        a.setCity(city.getText());
        a.setEmail(mail.getText());
        a.setTelephone(tel.getText());
    }

    private void updateInvoice() {
        inv.setConfirmationDate(confirmation.getValue());
        inv.setReceived(received.getValue());
        inv.setState(state.getText());
        inv.setFile(files);
    }

    private void updateGeneral() {
        g.setAddress(a);
        g.setKboNumber(Long.valueOf(kboNr.getText()));
        g.setApplication(applicationDate.getValue());
        g.setApplicationType(type.getText());
        g.setInvoice(inv);
        g.setLanguage(language.getValue());
        g.setNameKbo(kboName.getText());
        g.setNotes(notes.getText());
    }

    @FXML
    public void chooseFile() {
        files.add(FileSelector.chooseFile());
    }
}
