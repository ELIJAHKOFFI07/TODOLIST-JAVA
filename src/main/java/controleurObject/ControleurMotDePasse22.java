package controleurObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControleurMotDePasse22 implements Initializable  {
	@FXML
    private Button effectuer;
	 private Parent fxml;

    @FXML
    private TextField motDePass;
    @FXML
    private AnchorPane anchorpa;

    @FXML
    void acrioEffectuer(ActionEvent event) throws SQLException {


    	Connection  cn=BaseDeDonnes.connectbd();
    	Statement stmt = cn.createStatement ();


    	String sql = "UPDATE admin SET passApp= "+Integer.valueOf(motDePass.getText())+" WHERE id=1";
    	 stmt.executeUpdate(sql);
    	 stmt.executeUpdate("commit");
    	 stmt.close ();
		 cn.close ();









    	anchorpa.getScene().getWindow().hide();//pour supprimer l'ancien fenetre
    	Stage home= new Stage();

    	try {

			fxml= FXMLLoader.load(getClass().getResource("/interfaces/Acceuil.fxml"));
			Scene scene=new Scene(fxml);
			home.setScene(scene);
			home.show();



		} catch (IOException e) {

			e.printStackTrace();}

    }



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
