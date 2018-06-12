package be.vigilis;

import be.vigilis.utility.sceneControl.NewStage;
import be.vigilis.utility.validation.Warning;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "be.vigilis.repositories")
@EnableScheduling
public class MainApp extends Application {
    public static Stage stage;
    public static ConfigurableApplicationContext springContext;
    private Parent root;
    public static Stage window;


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(MainApp.class);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
        loader.setControllerFactory(springContext::getBean);
        root = loader.load();
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("Aanvraag Vergunning Camera");
        Image icon = new Image(getClass().getResourceAsStream("/images/ibz.jpg"));
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root, 600, 600));
        stage.show();
        window = NewStage.getStage("Login", "/fxml/login.fxml");
        window.show();
        window.setOnCloseRequest(event -> {
            event.consume();
            Warning.alert("Need to login in!", "Je moet eerst inloggen!");
        });
    }
}
