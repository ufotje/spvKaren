package be.vigilis.controllers.search;

import be.vigilis.controllers.MenuController;
import be.vigilis.entities.Address;
import be.vigilis.entities.General;
import be.vigilis.entities.Invoices;
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

import static javafx.collections.FXCollections.observableArrayList;


@Component
public class SearchByKboNameController {
    @FXML
    private TextField kboName;
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

    @FXML
    private void searchKboName(){
        ObservableList<General> generals = observableArrayList( repo.findByNameKbo(kboName.getText()));
        ObservableList<Invoices> invoices = observableArrayList();
        ObservableList<Address> addresses = observableArrayList();
        if(!generals.isEmpty()){
            for(General g : generals){
                invoices.add(g.getInvoice());
                addresses.add(g.getAddress());
            }
            MenuController.stage.close();
            ChangeScene.init("/fxml/search/views/searchByKboName.fxml", "Het Bedrijf met KBO-Benaming: " + kboName.getText());
            GeneralTable.init(table, languageCol, kboNrCol, kboNameCol, applicationDateCol, typeCol, notesCol, generals);
            AddressTable.init(addressTable, nameOrgCol, streetCol, zipCol, cityCol, telCol, mailCol, addresses);
            InvoiceTable.init(tableInv, receivedCol, confirmationCol, stateCol, documentCol, invoices);
            RowFactory.set(tableInv);
        } else{
            Warning.alert("Record Not found!", "Het record voor het bedrijf met benaming: " +
                    kboName.getText() + " werd niet terug gevonden.");
        }
    }
}