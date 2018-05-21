package be.vigilis.utility.tables;

import be.vigilis.entities.Invoices;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.time.LocalDate;

public interface InvoiceTable {
    static void init(TableView<Invoices> table, TableColumn<Invoices, String> receivedCol,
                     TableColumn<Invoices, LocalDate> confirmationCol, TableColumn<Invoices, String> stateCol,
                     TableColumn<Invoices, File> documentCol, ObservableList<Invoices> list){
        receivedCol.setCellValueFactory(new PropertyValueFactory<>("received"));
        confirmationCol.setCellValueFactory(new PropertyValueFactory<>("confirmationDate"));
        stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));
        documentCol.setCellValueFactory(new PropertyValueFactory<>("file"));
        table.setItems(list);
    }
}
