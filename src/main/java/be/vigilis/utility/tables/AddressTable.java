package be.vigilis.utility.tables;

import be.vigilis.entities.Address;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public interface AddressTable {
    static void init(TableView<Address> table, TableColumn<Address, String> name, TableColumn<Address, String> street,
                     TableColumn<Address, String> zip, TableColumn<Address, String> city, TableColumn<Address, String> tel,
                     TableColumn<Address, String> mail, ObservableList<Address> list){
        name.setCellValueFactory(new PropertyValueFactory<>("nameOrg"));
        street.setCellValueFactory(new PropertyValueFactory<>("streetAndNumber"));
        zip.setCellValueFactory(new PropertyValueFactory<>("zip"));
        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        tel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        mail.setCellValueFactory(new PropertyValueFactory<>("email"));
        table.setItems(list);
    }
}
