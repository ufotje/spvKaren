package be.vigilis.utility;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public interface FileSelector {

    static File chooseFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Kies het Bestand");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        return fileChooser.showOpenDialog(new Stage());
    }
}
