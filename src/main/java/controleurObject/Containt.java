package controleurObject;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class Containt extends Node{
	
	String titre;
	String description;
	String hDep;
	int id;

	  @FXML
	    private JFXButton butTacheDay;

	    @FXML
	    private Label heurDep;

	    @FXML
	    private Label titreTache;
	    public Containt(String t, String descri, String hD, int i) {
	    	this.id=i;
			this.titre=t;
		  this.description=descri;
		  this.hDep=hD;


	  // 	heurDep.setText(hD);
	   
	   	//titreTache.setText(titre);
	   
			// TODO Auto-generated constructor stub
		}







		@FXML
	    void actTacheDay(ActionEvent event) throws SQLException {
	    	//ControleurRoot.actionDay(this.id);
	    }

	   


	    
		
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	

}
