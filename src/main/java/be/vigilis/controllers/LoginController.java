package be.vigilis.controllers;

import be.vigilis.MainApp;
import be.vigilis.utility.validation.Warning;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class LoginController {
    @FXML
    private TextField userName, pass;

    @FXML
    public void initialize(){
        pass.setOnAction(event -> login());
    }

    @FXML
    private void login() {
        if(userName.getText().equals("Karen") && pass.getText().equals("W817Spv")){
            MainApp.window.close();
        } else{
            Warning.alert("Wrong Credentials!", "U hebt een verkeerde username of wachtwoord ingegeven!");
        }
    }

}
