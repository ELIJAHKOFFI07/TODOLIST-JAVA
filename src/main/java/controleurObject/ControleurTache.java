package controleurObject;

import java.net.URL;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.fxml.Initializable;

import static controleurObject.ControleurSignIn.champsSuivant;
import static controleurObject.ControleurSignIn.showTooltip;

public class ControleurTache implements Initializable   {
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

	// controle sur saisie heure
	static 	Boolean control(String debut,String fin){
		String []heures =debut.split(":");
		String []heure =fin.split(":");
		return (Integer.parseInt(heures[0])>Integer.parseInt(heure[0]))?false:true;

	}
	@FXML
	void Action_ajout() throws SQLException {
		Tooltip tooltip=new Tooltip();
		Tooltip falsedate=new Tooltip("L'heure de fin doit etre plus grande ");
		Tooltip informal=new Tooltip("Votre mot de passe doit contenir au moins 8 caracteres");

		if (nom.getText().isEmpty()) {
			nom.setTooltip(tooltip);
			tooltip.setText("Veuillez Entrer le libelle de la tache");
			showTooltip(nom);
		} else if (date.getValue()==null) {

			date.setTooltip(tooltip);
			tooltip.setText("Veuillez Entrer la date");
			showTooltip(date);

		}




		else if (heure.getText().isEmpty()) {

			heure.setTooltip(tooltip);
			tooltip.setText("Veuillez renseigner le champ email");
			showTooltip(heure);
		}
		else if (control(heure1.getText(),heure.getText())) {

			heure1.setTooltip(falsedate);
			showTooltip(heure1);

		}



		else if (heure1.getText().isEmpty()) {

			tooltip.setText("Veuillez entrer l'heure de fin");
			heure1.setTooltip(tooltip);
			showTooltip(heure1);}
		else if (description.getText().isEmpty()) {

			tooltip.setText("Veuillez entrer la description");
			description.setTooltip(tooltip);
			showTooltip(description);}


		else{
			Connection cn=BaseDeDonnes.connectbd();
			Statement stmt = cn.createStatement ();

			String sql = "INSERT INTO Tache (nom,dateTache ,heueDepart, heureFin ,description,valider ) VALUES ( "
					+ "'"+nom.getText()+"', '"+date.getValue()+"','"+heure.getText()+"',"
					+ "'"+heure1.getText()+"','"+description.getText()+"',"+0+")";
			stmt.executeUpdate(sql);
			//stmt.executeUpdate("commit");
			stmt.close ();
			cn.close ();


			modifier.getScene().getWindow().hide();
			}

	}




	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// lorque qu'on tape entree on passe au champs suivant
		champsSuivant(nom,date);
		champsSuivant(date,heure);
		champsSuivant(heure,heure1);
		champsSuivant(heure1,description);
		description.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				try {
					Action_ajout();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
				//ajouter.requestFocus(); // Passer au champ ajouter

			}
		});

	}

}
