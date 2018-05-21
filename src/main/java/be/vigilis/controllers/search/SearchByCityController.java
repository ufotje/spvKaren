package be.vigilis.controllers.search;

import be.vigilis.controllers.MenuController;
import be.vigilis.entities.Address;
import be.vigilis.entities.General;
import be.vigilis.entities.Invoices;
import be.vigilis.repositories.AddressRepo;
import be.vigilis.repositories.GeneralRepo;
import be.vigilis.utility.RowFactory;
import be.vigilis.utility.sceneControl.ChangeScene;
import be.vigilis.utility.tables.AddressTable;
import be.vigilis.utility.tables.GeneralTable;
import be.vigilis.utility.tables.InvoiceTable;
import be.vigilis.utility.validation.Warning;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;
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
    private TableColumn<Invoices, File> documentCol;

    @Autowired
    private GeneralRepo repo;
    @Autowired
    private AddressRepo aRepo;

    @FXML
    private void searchCity(){
        ObservableList<General> generals = observableArrayList();
        ObservableList<Invoices> invoices = observableArrayList();
        ObservableList<Address> addresses = observableArrayList(aRepo.findByCity(city.getText()));
        if(!addresses.isEmpty()){
            for(Address a : addresses){
                List<General> g = repo.findByAddress(a);
                for(General ge : g){
                    generals.add(ge);
                    invoices.add(ge.getInvoice());
                }
            }
            MenuController.stage.close();
            ChangeScene.init("/fxml/search/views/searchByCity.fxml", "Alle bedrijven gevestigd in " + city.getText());
            GeneralTable.init(table, languageCol, kboNrCol, kboNameCol, applicationDateCol, typeCol, notesCol, generals);
            AddressTable.init(addressTable, nameOrgCol, streetCol, zipCol, cityCol, telCol, mailCol, addresses);
            InvoiceTable.init(tableInv, receivedCol, confirmationCol, stateCol, documentCol, invoices);
            RowFactory.set(tableInv);
        } else{
            Warning.alert("No Records found!", "Er werden geen bedrijven gevonden die gevestigd zijn in \" + city.getText()");
        }
    }
}
