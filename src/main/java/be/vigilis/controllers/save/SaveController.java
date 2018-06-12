package be.vigilis.controllers.save;

import be.vigilis.entities.*;
import be.vigilis.repositories.GeneralRepo;
import be.vigilis.utility.FileSelector;
import be.vigilis.utility.Mail;
import be.vigilis.utility.sceneControl.ChangeScene;
import be.vigilis.utility.sceneControl.NewStage;
import be.vigilis.utility.validation.Warning;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static javafx.collections.FXCollections.observableArrayList;

@Component
public class SaveController {
    //GeneralFields
    @FXML
    private ChoiceBox<String> language, type;
    @FXML
    private DatePicker applicationDate;
    @FXML
    private TextField kboName, kboNr;
    @FXML
    private TextArea notes;
    private List<File> files = new ArrayList<>();
    private List<File> filesNow = new ArrayList<>();
    private File appendix1;

    //AddressFields
    @FXML
    private TextField nameOrg, nameContact, roleContact, street, zip, city, tel, mail;


    //InvoiceFields
    @FXML
    private ChoiceBox<String> invReceived, invState;
    @FXML
    private DatePicker confirmation;

    //AdditionFields
    @FXML
    private DatePicker receivedOn, experation, notification, requested, continuance, payed;
    private List<File> additionalFiles = new ArrayList<>();

    //EmployeeFields
    @FXML
    private TextField nameEmp, rr;
    @FXML
    private DatePicker fieEnd, fieBegin, educ;
    @FXML
    private ChoiceBox<String> ifk;
    private File sr, vo;

    //ANG-CheckFields
    @FXML
    private DatePicker appendix3Date, checkRequested, checkRecieved, advice;
    @FXML
    private CheckBox positive;
    private File appendix3;

    //Repo's
    @Autowired
    @Qualifier("generalRepo")
    private GeneralRepo generalRepo;
    private Invoices invoice;
    private Stage window;
    List<Employee> employees = new ArrayList<>();

    @FXML
    public void initialize() {
        language.setItems(observableArrayList("NL", "FR", "EN", "DE"));
        language.setValue("NL");

//        invReceived.setItems(observableArrayList("OK", "NOK", "Niet aanwezig", "Aanwezig"));
        //      invReceived.setValue("OK");

        type.setItems(observableArrayList("In Aanvraag", "Vernieuwing", "Wijziging", "Vrijwillige Intrekking",
                "Faillissement", "Voorlopige Voortzetting Activiteiten", "InformatieAanvraag", "Andere"));

        //    invState.setItems(observableArrayList("In Behandeling", "GoedGekeurd", "Geweigerd", "Ingetrokken","MoraliteitsOnderzoek"));
    }

    @FXML
    public void save() {
        invoice = new Invoices(invReceived.getValue(), confirmation.getValue(), invState.getValue(), files, filesNow);
        Address address = new Address(nameOrg.getText(), street.getText(), zip.getText(), city.getText(), tel.getText(),
                mail.getText(), nameContact.getText(), roleContact.getText());
        Additional additional = new Additional(requested.getValue(), experation.getValue(), receivedOn.getValue(),
                continuance.getValue(), notification.getValue(), additionalFiles, payed.getValue());
         General general = new General(language.getValue(), applicationDate.getValue(), kboName.getText(),
                Long.valueOf(kboNr.getText()), address, type.getValue(), notes.getText(), invoice, additional, employees
                 , appendix1);
        if (generalRepo.save(general) != null) {
            Warning.alert("Save succeed", "Het record van " + kboName.getText() + " werd succesvol opgeslagen.");
            clearFields();
            ChangeScene.init("/fxml/home.fxml", "Vergunningen Camerabewaking");
        } else {
            Warning.alert("Save Failed!", "Er ging iets fout tijdens het opslaan van het record.");
        }
    }

    @FXML
    public void chooseFile() {
        files.add(FileSelector.chooseFile());
    }

    @FXML
    public void chooseFileNow() {
        filesNow.add(FileSelector.chooseFile());
    }

    @FXML
    private void addDoc() {
        additionalFiles.add(FileSelector.chooseFile());
    }

    @FXML
    private void addSr(){
        sr = FileSelector.chooseFile();
    }

    @FXML
    private void addVo(){
        vo = FileSelector.chooseFile();
    }

    @FXML
    private void addDocument() {
    }

    @FXML
    private void addAppendix1() {
        appendix1 = FileSelector.chooseFile();
    }

    @FXML
    private void addAppendix3() {
        appendix3 = FileSelector.chooseFile();
    }

    @FXML
    private void angCheck() {
        window = NewStage.getStage("Vul de ANG-Checkdetails in!", "/fxml/save/ang.fxml");
        window.show();
    }

    @FXML
    private void saveAngCheck() {
        AngCheck angCheck = new AngCheck(positive.isSelected(), appendix3Date.getValue(), appendix3,
                checkRequested.getValue(), checkRecieved.getValue());
        Employee employee = new Employee(nameEmp.getText(), rr.getText(), roleContact.getText(), null,
                sr, vo, educ.getValue(), fieBegin.getValue(), fieEnd.getValue(), ifk.getValue(), angCheck);
        employees.add(employee);
        window.close();
    }

    private void clearEmployee(){
        positive.setSelected(false);
        nameEmp.clear();
        appendix3 = null;
        appendix3Date.setValue(null);
        checkRequested.setValue(null);
        checkRecieved.setValue(null);
        rr.clear();
        sr = null;
        vo = null;
        educ.setValue(null);
        fieEnd.setValue(null);
        fieBegin.setValue(null);
        ifk.setValue(null);
    }

    private void clearFields() {
        applicationDate.setValue(null);
        kboName.clear();
        kboNr.clear();
        invReceived.setValue(null);
        nameOrg.clear();
        notes.clear();
        confirmation.setValue(null);
        invState.setValue(null);
        street.clear();
        zip.clear();
        city.clear();
        tel.clear();
        mail.clear();
        type.setValue(null);
        clearEmployee();
    }
}
