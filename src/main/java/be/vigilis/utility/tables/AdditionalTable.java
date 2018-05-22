package be.vigilis.utility.tables;

import be.vigilis.entities.Additional;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public interface AdditionalTable {
    static void init(TableView<Additional> table, TableColumn<Additional, LocalDate> received,
                     TableColumn<Additional, LocalDate> notification, TableColumn<Additional, LocalDate> expiration,
                     TableColumn<Additional, LocalDate> payed, TableColumn<Additional, LocalDate> requested,
                     TableColumn<Additional, LocalDate> continuance, TableColumn<Additional, List<File>> files, ObservableList<Additional> additionals){
        received.setCellValueFactory(new PropertyValueFactory<>("receiveDate"));
        requested.setCellValueFactory(new PropertyValueFactory<>("requested"));
        notification.setCellValueFactory(new PropertyValueFactory<>("notification"));
        expiration.setCellValueFactory(new PropertyValueFactory<>("expiration"));
        continuance.setCellValueFactory(new PropertyValueFactory<>("continuity"));
        payed.setCellValueFactory(new PropertyValueFactory<>("charge"));
        files.setCellValueFactory(new PropertyValueFactory<>("additionalDocuments"));
        table.setItems(additionals);
    }
}
