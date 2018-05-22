package be.vigilis.utility;

import be.vigilis.MainApp;
import javafx.print.*;
import javafx.scene.Node;

import java.util.List;

public interface Printers {
    static void print(List<Node> nodes){
        for(Node node : nodes) {
            PrinterJob printerJob = PrinterJob.createPrinterJob();
            PageLayout layout = Printer.getDefaultPrinter().createPageLayout(Paper.A4, PageOrientation.PORTRAIT,
                    Printer.MarginType.HARDWARE_MINIMUM);
            if (printerJob.showPrintDialog(MainApp.stage.getOwner()) && printerJob.printPage(layout, node)) {
                printerJob.endJob();
            }
        }
    }
}
