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

public class ControleurModifTache22 implements Initializable   {
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
	    private Button supprimer;

	    @FXML
	    void actionModifie(ActionEvent event) throws SQLException {
	    	
	     	
	    	Connection  cn=BaseDeDonnes.connectbd();
	    	Statement stmt = cn.createStatement ();
	    	
	        
	            
	String sql = "UPDATE tache SET nom='"+nom.getText()+"',date='"+date.getAccessibleText()+"' "
			+ ",heueDepart='"+heure.getText()+"', heureFin='"+heure1.getText()+"'"
					+ " ,description='"+description.getText()+"',valider="+1+" WHERE nom='"+nom.getText()+"'";
	    	 stmt.executeUpdate(sql);
	    	 stmt.executeUpdate("commit");
	    	 stmt.close ();
			 cn.close ();
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	modifier.getScene().getWindow().hide();


	    }

	    @FXML
	    void actionSuppriler(ActionEvent event) {
	    	modifier.getScene().getWindow().hide();

	    }

	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
