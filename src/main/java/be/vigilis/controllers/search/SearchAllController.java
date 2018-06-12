package be.vigilis.controllers.search;

import be.vigilis.entities.Additional;
import be.vigilis.entities.Address;
import be.vigilis.entities.General;
import be.vigilis.entities.Invoices;
import be.vigilis.repositories.GeneralRepo;
import be.vigilis.utility.Printers;
import be.vigilis.utility.converters.NodesList;
import be.vigilis.utility.rowFactories.AdditionalRowFactory;
import be.vigilis.utility.rowFactories.InvoiceRowFactory;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javafx.collections.FXCollections.observableArrayList;

@Component
public class SearchAllController {
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
    private TableColumn<Additional, LocalDate> requestedCol, expirationCol, receivedOnCol, continuanceCol,
            notificationCol, payedCol;
    @FXML
    private TableColumn<Additional, List<File>> missingCol;

    @Autowired
    private GeneralRepo repo;

    @FXML
    public void initialize() {
        ObservableList<General> generals = observableArrayList(repo.findAll());
        ObservableList<Invoices> invoices = observableArrayList();
        ObservableList<Address> addresses = observableArrayList();
        ObservableList<Additional> additionals = observableArrayList();
        if (!generals.isEmpty()) {
            for (General g : generals) {
                invoices.add(g.getInvoice());
                addresses.add(g.getAddress());
                additionals.add(g.getAdditional());
            }
            GeneralTable.init(table, languageCol, kboNrCol, kboNameCol, applicationDateCol, typeCol, notesCol, generals);
            AddressTable.init(addressTable, nameOrgCol, streetCol, zipCol, cityCol, telCol, mailCol, addresses);
            InvoiceTable.init(tableInv, receivedCol, confirmationCol, stateCol, documentCol, invoices);
            AdditionalTable.init(additionalTable, receivedOnCol, notificationCol, expirationCol, payedCol, requestedCol,
                    continuanceCol, missingCol, additionals);
            InvoiceRowFactory.set(tableInv);
            AdditionalRowFactory.set(additionalTable);
        } else {
            Warning.alert("No Records found!", "Er werden geen records terug gevonden.");
        }
    }

    @FXML
    private void printPage() {
        Printers.print(NodesList.make(table, addressTable, tableInv, additionalTable));
    }
}