package be.vigilis.utility.converters;


import be.vigilis.entities.Additional;
import be.vigilis.entities.Address;
import be.vigilis.entities.General;
import be.vigilis.entities.Invoices;
import javafx.scene.Node;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;

public interface NodesList {
    static List<Node> make(TableView<General> table, TableView<Address> addressTable, TableView<Invoices> tableInv, TableView<Additional> additionalTable){
        List<Node> nodes = new ArrayList<>();
        nodes.add(table);
        nodes.add(addressTable);
        nodes.add(tableInv);
        nodes.add(additionalTable);
        return nodes;
    }
}
