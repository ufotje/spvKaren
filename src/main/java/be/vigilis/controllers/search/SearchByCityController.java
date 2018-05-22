package be.vigilis.controllers.search;

import be.vigilis.controllers.MenuController;
import be.vigilis.entities.Additional;
import be.vigilis.entities.Address;
import be.vigilis.entities.General;
import be.vigilis.entities.Invoices;
import be.vigilis.repositories.AddressRepo;
import be.vigilis.repositories.GeneralRepo;
import be.vigilis.utility.Printers;
import be.vigilis.utility.rowFactories.AdditionalRowFactory;
import be.vigilis.utility.rowFactories.InvoiceRowFactory;
import be.vigilis.utility.sceneControl.ChangeScene;
import be.vigilis.utility.tables.AdditionalTable;
import be.vigilis.utility.tables.AddressTable;
import be.vigilis.utility.tables.GeneralTable;
import be.vigilis.utility.tables.InvoiceTable;
import be.vigilis.utility.validation.Warning;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javafx.collections.FXCollections.observableArrayList;


@Component
public class SearchByCityController {
    @FXML
    private TextField city;
    @FXML
    private TableView<General> table;
    @FXML
    private TableColumn<General, String> languageCol;
    @FXML
    private TableColumn<General, Long> kboNrCol;
    @FXML
    private TableColumn<General, String> kboNameCol;
    @FXML
    private TableColumn<General, LocalDate> applicationDateCol;
    @FXML
    private TableColumn<General, String> typeCol;
    @FXML
    private TableColumn<General, String> notesCol;

    @FXML
    private TableView<Address> addressTable;
    @FXML
    private TableColumn<Address, String> nameOrgCol;
    @FXML
    private TableColumn<Address, String> streetCol;
    @FXML
    private TableColumn<Address, String> zipCol;
    @FXML
    private TableColumn<Address, String> cityCol;
    @FXML
    private TableColumn<Address, String> telCol;
    @FXML
    private TableColumn<Address, String> mailCol;

    @FXML
    private TableView<Invoices> tableInv;
    @FXML
    private TableColumn<Invoices, String> receivedCol;
    @FXML
    private TableColumn<Invoices, LocalDate> confirmationCol;
    @FXML
    private TableColumn<Invoices, String> stateCol;
    @FXML
    private TableColumn<Invoices, List<File>> documentCol;

    @FXML
    private TableView<Additional> additionalTable;
    @FXML
    private TableColumn<Additional, LocalDate> requestedCol;
    @FXML
    private TableColumn<Additional, LocalDate> expirationCol;
    @FXML
    private TableColumn<Additional, LocalDate> receivedOnCol;
    @FXML
    private TableColumn<Additional, LocalDate> continuanceCol;
    @FXML
    private TableColumn<Additional, LocalDate> notificationCol;
    @FXML
    private TableColumn<Additional, List<File>> missingCol;
    @FXML
    private TableColumn<Additional, LocalDate> payedCol;

    @Autowired
    private GeneralRepo repo;
    @Autowired
    private AddressRepo aRepo;

    @FXML
    private void searchCity(){
        ObservableList<General> generals = observableArrayList();
        ObservableList<Invoices> invoices = observableArrayList();
        ObservableList<Address> addresses = observableArrayList(aRepo.findByCity(city.getText()));
        ObservableList<Additional> additionals = observableArrayList();
        if(!addresses.isEmpty()){
            for(Address a : addresses){
                List<General> g = repo.findByAddress(a);
                for(General ge : g){
                    generals.add(ge);
                    invoices.add(ge.getInvoice());
                    additionals.add(ge.getAdditional());
                }
            }
            MenuController.stage.close();
            ChangeScene.init("/fxml/search/views/searchByCity.fxml", "Alle bedrijven gevestigd in " + city.getText());
            GeneralTable.init(table, languageCol, kboNrCol, kboNameCol, applicationDateCol, typeCol, notesCol, generals);
            AddressTable.init(addressTable, nameOrgCol, streetCol, zipCol, cityCol, telCol, mailCol, addresses);
            InvoiceTable.init(tableInv, receivedCol, confirmationCol, stateCol, documentCol, invoices);
            AdditionalTable.init(additionalTable, receivedOnCol, requestedCol, notificationCol, expirationCol,
                    continuanceCol, payedCol, missingCol, additionals);
            InvoiceRowFactory.set(tableInv);
            AdditionalRowFactory.set(additionalTable);
        } else{
            Warning.alert("No Records found!", "Er werden geen bedrijven gevonden die gevestigd zijn in \" + city.getText()");
        }
    }

    @FXML
    private void printPage(){
        List<Node> nodes = new ArrayList<>();
        nodes.add(table);
        nodes.add(addressTable);
        nodes.add(tableInv);
        Printers.print(nodes);
    }
}
