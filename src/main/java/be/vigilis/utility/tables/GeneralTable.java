package be.vigilis.utility.tables;

import be.vigilis.entities.General;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public interface GeneralTable {
    static void init(TableView<General> table, TableColumn<General, String> languagCol, TableColumn<General, Long> kboNrCol,
                     TableColumn<General, String> kboNameCol, TableColumn<General, LocalDate> aplicationDateCol,
                     TableColumn<General, String> typeCol, TableColumn<General, String> notesCol, ObservableList<General> list) {
        languagCol.setCellValueFactory(new PropertyValueFactory<>("language"));
        kboNrCol.setCellValueFactory(new PropertyValueFactory<>("kboNumber"));
        kboNameCol.setCellValueFactory(new PropertyValueFactory<>("nameKbo"));
        aplicationDateCol.setCellValueFactory(new PropertyValueFactory<>("application"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("applicationType"));
        notesCol.setCellValueFactory(new PropertyValueFactory<>("notes"));
        table.setItems(list);
    }
}
