package controleurObject;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static controleurObject.Tools.addFadeAnimation;
import static controleurObject.Tools.addSlideAnimation;


public class ControleurAceuil implements Initializable {

    @FXML
    private JFXButton but_Acceuil;

    @FXML
    private JFXButton but_tache;

    @FXML
    private JFXButton but_list;

    @FXML
    private JFXButton but_planning;

    @FXML
    private AnchorPane menu;
    private Parent fxml;
    @FXML
    private BorderPane window;

    @FXML
    void act_list() {
        try {

            fxml = FXMLLoader.load(getClass().getResource("/interfaces/ListeTache.fxml"));

            VBox container = new VBox(fxml);
            addSlideAnimation(container,2000); // Ajoute l'animation de glissement

            menu.getChildren().clear();
            menu.getChildren().add(container);

        } catch (IOException e) {

            e.printStackTrace();}


    }

    @FXML
    void act_planning() {
        try {

            fxml = FXMLLoader.load(getClass().getResource("/interfaces/Planing.fxml"));

            VBox container = new VBox(fxml);
            addFadeAnimation(container); // Ajoute l'animation de glissement

            menu.getChildren().clear();
            menu.getChildren().add(container);
        } catch (IOException e) {

            e.printStackTrace();}
        // changer de fenetre

    }



    @FXML
    void clik_acceuil() {


        try {
            fxml= FXMLLoader.load(getClass().getResource("/interfaces/root.fxml"));
            VBox container = new VBox(fxml);
            addFadeAnimation(container); // Ajoute l'animation de glissement

            menu.getChildren().clear();
            menu.getChildren().add(container);

        } catch (IOException e) {

            e.printStackTrace();}

    }
    @FXML
    void act_tache(ActionEvent event) {


    }





    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // on va mettre le code relatif a la mise a jours de label ect...


        clik_acceuil();

    }

}