package controleurObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControleurTache22 implements Initializable   {
	 @FXML
	    private Button ajouter;

	    @FXML
	    private DatePicker date;

	    @FXML
	    private TextField description;

	    @FXML
	    private TextField heure;

	    @FXML
	    private TextField heure1;

	    @FXML
	    private VBox modifier;

	    @FXML
	    private TextField nom;

	    @FXML
	    void Action_ajout(ActionEvent event) throws SQLException {
	    	
	    	Connection  cn=BaseDeDonnes.connectbd();
	    	Statement stmt = cn.createStatement ();

	String sql = "INSERT INTO Tache (nom,dateTache ,heueDepart, heureFin ,description,valider ) VALUES ( "
	    			+ "'"+nom.getText()+"', '"+date.getValue()+"','"+heure.getText()+"',"
	    					+ "'"+heure1.getText()+"','"+description.getText()+"',"+0+")";
	    	 stmt.executeUpdate(sql);
	    	 stmt.executeUpdate("commit");
	    	 stmt.close ();
			 cn.close ();
	    	
	    	
	    	modifier.getScene().getWindow().hide();

	    }

	


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
