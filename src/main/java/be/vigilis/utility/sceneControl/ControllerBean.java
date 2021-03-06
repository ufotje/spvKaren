package be.vigilis.utility.sceneControl;

import be.vigilis.MainApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * Created by Demesmaecker on 25/10/2017.
 */
public interface ControllerBean {

    /**
     * Returns a bean for the corresponding controllerClass
     * @param resource String
     * @return Parent
     * @throws Exception ex
     */
    static Parent getBean(String resource)throws Exception{
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(resource));
        loader.setControllerFactory(MainApp.springContext::getBean);
        return loader.load();
    }
}
