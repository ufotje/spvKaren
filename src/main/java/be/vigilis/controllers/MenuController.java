package be.vigilis.controllers;


import be.vigilis.entities.General;
import be.vigilis.entities.Invoices;
import be.vigilis.repositories.GeneralRepo;
import be.vigilis.utility.Mail;
import be.vigilis.utility.sceneControl.ChangeScene;
import be.vigilis.utility.sceneControl.NewStage;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class MenuController {
    public static Stage stage;
    @Autowired
    private GeneralRepo repo;
    private List<General> list ;

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
    private void searchAll() {
        ChangeScene.init( "/fxml/search/views/searchAll.fxml", "Alle beschikbare records!");
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

    @FXML
    private void mailMissing(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        list = repo.findAll();
        for(General g : list) {
            Invoices invoice = g.getInvoice();
            if (invoice.getFilesNow().size() < 2 || invoice.getFile().size() < 1) {
                String subject = "Onvoldoende facturen";
                StringBuilder message = new StringBuilder("Geachte " + g.getAddress().getContactPersonName() +
                        "<br>  <br>Er werden onvoldoende facturen aangeleverd.<br>");
                if (invoice.getFile().size() < 1 && invoice.getFilesNow().size() < 2) {
                    if (invoice.getFilesNow().size() == 0) {
                        message.append("U heeft geen facturen opgestuurd.<br>");
                    } else {
                        message.append("U heeft maar 1 factuur van 2017 opgestuurd.<br>");
                    }
                } else {
                    if (invoice.getFilesNow().size() < 2) {
                        if (invoice.getFilesNow().size() == 0) {
                            message.append("U heeft geen facturen van 2017 opgestuurd.<br>");
                        } else {
                            message.append("U heeft maar 1 factuur van 2017 opgestuurd.<br>");
                        }
                    } else {
                        message.append("U heeft geen facturen van andere jaren opgestuurd.<br>");
                    }
                }
                message.append("Om te kunnen genieten van de overgangsmaatragel in toepassing van artikel 276 van de wet op " +
                        "private en bijzondere veiligheid, dienen er voldoende schriftelijke bewijsstukken aanwezig te zijn:" +
                        "<br> - Minstens 2 facturen op naam van uw onderneming in het jaar 2017.<br> - Minstens 1 factuur of contract uit andere jaren." +
                        "<br>U gelieve deze stukken bij voorkeur via mail tegen uiterlijk " + LocalDate.now().plusDays(30).format(formatter) +
                        " te bezorgen.<br> <br>Indien u de stukken niet tijdig overmaakt kan u niet van de overgangsmaatregel genieten en zal uw aanvraag als een nieuw dossier behandeld worden." +
                        "<br>  <br> <br> Met vriendelijke groet<br> <br> <br>  <br>  <br> <img src='https://upload.wikimedia.org/wikipedia/commons/b/b5/Logo_FOD_Binnenlandse_Zaken.jpg' height='42' width='42'><br>Karen Peeters"
                        + "<br>Attaché<br>T 02 557 34 73<br>F 02 557 33 27<br>karen.peeters@ibz.fgov.be<br> <br>FOD Binnenlandse Zaken<br>Algemene Directie Veiligheid en Preventie<br>Directie Private Veiligheid<br> <br>Waterloolaan 76<br>1000 Brussel");
                List<String> recipients = new ArrayList<>();
                recipients.add(g.getAddress().getEmail());
                Mail.sendMail(recipients, subject, message.toString());
                g.getAdditional().setRequested(LocalDate.now());
                g.getAdditional().setExpiration(LocalDate.now().plusDays(30));
                repo.save(g);
            }
        }
    }

    @FXML
    private void mailOverdue(){}
}
