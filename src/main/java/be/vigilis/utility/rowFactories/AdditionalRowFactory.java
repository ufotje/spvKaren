package be.vigilis.utility.rowFactories;

import be.vigilis.entities.Additional;
import be.vigilis.entities.Invoices;
import be.vigilis.utility.ShowInvoice;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;

import java.io.File;
import java.util.List;

public interface AdditionalRowFactory {
    static void set(TableView table) {
        table.setRowFactory(tv -> {
            TableRow<Additional> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    Additional clickedRow = row.getItem();
                    List<File> files = clickedRow.getAdditionalDocuments();
                    for(File file : files) {
                        ShowInvoice.show(file);
                    }
                }
            });
            return row;
        });
    }
}
