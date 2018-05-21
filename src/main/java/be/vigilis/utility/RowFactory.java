package be.vigilis.utility;

import be.vigilis.entities.Invoices;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;

import java.io.File;

public interface RowFactory {
    static void set(TableView table) {
        table.setRowFactory(tv -> {
            TableRow<Invoices> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    Invoices clickedRow = row.getItem();
                    File file = clickedRow.getFile();
                    ShowInvoice.show(file);
                }
            });
            return row;
        });
    }
}
