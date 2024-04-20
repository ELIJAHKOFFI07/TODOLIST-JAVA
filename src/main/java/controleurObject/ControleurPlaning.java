package controleurObject;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import static appli.testDate.jouurr;
import static controleurObject.ControleurRoot.Currentime;
import static controleurObject.Tools.addFadeAnimation;

public class ControleurPlaning  implements Initializable  {
	@FXML
	private JFXButton acceuil;
	private Parent fxml;
	@FXML
	private JFXButton add;
	@FXML
	private Label date1;
	@FXML
	private Label date2;

	@FXML
	private Label heure;

	@FXML
	private Label historique;

	@FXML
	private JFXButton liste;

	@FXML
	private JFXButton modifier;

	@FXML
	private JFXButton plan;

	@FXML
	private GridPane plan_jeudi;
	@FXML
	private GridPane plan_lundi;

	@FXML
	private GridPane plan_mardi;

	@FXML
	private GridPane plan_mercredi;

	@FXML
	private GridPane plan_vendredi;

	@FXML
	private Label vendredi;

	@FXML
	void Acceuil_action(ActionEvent event) {

	}

	@FXML
	void liste_action(ActionEvent event) {

	}

	@FXML
	void modifier_action(ActionEvent event) {
		Stage home= new Stage();

		try {
			 fxml = FXMLLoader.load(getClass().getResource("/interfaces/ModifTache.fxml"));
			VBox container = new VBox(fxml);
			addFadeAnimation(container); // Ajoute l'animation de glissement

			Scene scene = new Scene(container);
			scene.setFill(Color.TRANSPARENT); // Rend le fond de la scène transparent (si vous utilisez StageStyle.TRANSPARENT)
			home.setScene(scene);
			home.show();



		} catch (IOException e) {

			e.printStackTrace();}

	}




	@FXML
	void plan_action(ActionEvent event) {

	}

	@FXML
	void sadd_action(ActionEvent event) {
		Stage home= new Stage();

		try {
			fxml = FXMLLoader.load(getClass().getResource("/interfaces/Tache.fxml"));
			VBox container = new VBox(fxml);
			addFadeAnimation(container); // Ajoute l'animation de glissement

			Scene scene = new Scene(container);
			scene.setFill(Color.TRANSPARENT); // Rend le fond de la scène transparent (si vous utilisez StageStyle.TRANSPARENT)
			home.setScene(scene);
			home.show();



		} catch (IOException e) {

			e.printStackTrace();}

	}
	void Date() throws SQLException {

		Date date = new Date();
		String p1= new SimpleDateFormat("dd-MM-yyyy").format(date);
		date2.setText(p1);
		date1.setText(p1);
	}


	void ajoutContaint() {
		try {
			Connection cn=BaseDeDonnes.connectbd();
			Statement stmt = cn.createStatement ();

			//  gridDay=new GridPane();
			String sql2 = "SELECT `nom`, `dateTache` FROM `tache` WHERE valider = 0";
			ResultSet res2= stmt.executeQuery(sql2);

			int i = 0; // Variable pour parcourir les lignes de la grille
			while (res2.next()) {
				String nom = res2.getString("nom");
				String dateTache = res2.getString("dateTache");


				if ((jouurr(dateTache)).equals("LUNDI")){
					AnchorPane space = new AnchorPane();
					plan_lundi.setVgap(3.0);
					space.setId("space");
					space.setPrefHeight(34.0);
					space.setPrefWidth(315.0);
					space.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 8; -fx-border-radius: 8;");

					Label titreTache = new Label();
					titreTache.setId("titreTache");
					titreTache.setLayoutX(15.0);
					titreTache.setLayoutY(6.0);
					titreTache.setText(nom);
					Font titreFont = Font.font("System Bold", 11.7);
					titreTache.setFont(titreFont);

					DropShadow dropShadow = new DropShadow();
					dropShadow.setBlurType(BlurType.GAUSSIAN);
					dropShadow.setOffsetY(2.0);
					Color shadowColor = Color.color(0.8100000023841858,0.8100000023841858,0.8100000023841858,0.4870820641517639);
					dropShadow.setColor(shadowColor);

					space.getChildren().addAll(titreTache);
					space.setEffect(dropShadow);
					plan_lundi.add(space, 0, i);

				} else if ((jouurr(dateTache)).equals("MARDI")) {
					AnchorPane space = new AnchorPane();
					plan_mardi.setVgap(3.0);
					space.setId("space");
					space.setPrefHeight(34.0);
					space.setPrefWidth(315.0);
					space.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 8; -fx-border-radius: 8;");

					Label titreTache = new Label();
					titreTache.setId("titreTache");
					titreTache.setLayoutX(3.0);
					titreTache.setLayoutY(6.0);
					titreTache.setText(nom);
					Font titreFont = Font.font("System Bold", 11.7);
					titreTache.setFont(titreFont);

					DropShadow dropShadow = new DropShadow();
					dropShadow.setBlurType(BlurType.GAUSSIAN);
					dropShadow.setOffsetY(2.0);
					Color shadowColor = Color.color(0.8100000023841858,0.8100000023841858,0.8100000023841858,0.4870820641517639);
					dropShadow.setColor(shadowColor);

					space.getChildren().addAll(titreTache);
					space.setEffect(dropShadow);
					plan_mardi.add(space, 0, i);

				} else if ((jouurr(dateTache)).equals("MERCREDI")) {
					AnchorPane space = new AnchorPane();
					plan_mercredi.setVgap(3.0);
					space.setId("space");
					space.setPrefHeight(34.0);
					space.setPrefWidth(315.0);
					space.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 8; -fx-border-radius: 8;");

					Label titreTache = new Label();
					titreTache.setId("titreTache");
					titreTache.setLayoutX(5.0);
					titreTache.setLayoutY(6.0);
					titreTache.setText(nom);
					Font titreFont = Font.font("System Bold", 11.7);
					titreTache.setFont(titreFont);

					DropShadow dropShadow = new DropShadow();
					dropShadow.setBlurType(BlurType.GAUSSIAN);
					dropShadow.setOffsetY(2.0);
					Color shadowColor = Color.color(0.8100000023841858,0.8100000023841858,0.8100000023841858,0.4870820641517639);
					dropShadow.setColor(shadowColor);

					space.getChildren().addAll(titreTache);
					space.setEffect(dropShadow);
					plan_mercredi.add(space, 0, i);
				}else if ((jouurr(dateTache)).equals("JEUDI")) {
					AnchorPane space = new AnchorPane();
					plan_jeudi.setVgap(3.0);
					space.setId("space");
					space.setPrefHeight(34.0);
					space.setPrefWidth(315.0);
					space.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 8; -fx-border-radius: 8;");

					Label titreTache = new Label();
					titreTache.setId("titreTache");
					titreTache.setLayoutX(5.0);
					titreTache.setLayoutY(6.0);
					titreTache.setText(nom);
					Font titreFont = Font.font("System Bold", 11.7);
					titreTache.setFont(titreFont);

					DropShadow dropShadow = new DropShadow();
					dropShadow.setBlurType(BlurType.GAUSSIAN);
					dropShadow.setOffsetY(2.0);
					Color shadowColor = Color.color(0.8100000023841858,0.8100000023841858,0.8100000023841858,0.4870820641517639);
					dropShadow.setColor(shadowColor);

					space.getChildren().addAll(titreTache);
					space.setEffect(dropShadow);
					plan_jeudi.add(space, 0, i);
				}else if ((jouurr(dateTache)).equals("VENDREDI")) {
					AnchorPane space = new AnchorPane();
					plan_vendredi.setVgap(3.0);
					space.setId("space");
					space.setPrefHeight(34.0);
					space.setPrefWidth(315.0);
					space.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 8; -fx-border-radius: 8;");

					Label titreTache = new Label();
					titreTache.setId("titreTache");
					titreTache.setLayoutX(5.0);
					titreTache.setLayoutY(6.0);
					titreTache.setText(nom);
					Font titreFont = Font.font("System Bold", 11.7);
					titreTache.setFont(titreFont);

					DropShadow dropShadow = new DropShadow();
					dropShadow.setBlurType(BlurType.GAUSSIAN);
					dropShadow.setOffsetY(2.0);
					Color shadowColor = Color.color(0.8100000023841858,0.8100000023841858,0.8100000023841858,0.4870820641517639);
					dropShadow.setColor(shadowColor);

					space.getChildren().addAll(titreTache);
					space.setEffect(dropShadow);
					plan_vendredi.add(space, 0, i);
				}



				i++;
			}

			//stmt.executeUpdate("commit");
			stmt.close ();
			cn.close ();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* public boolean teste(String x) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date parsedDate = sdf.parse(x);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(parsedDate);

			boolean isMonday = calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
			System.out.println(isMonday);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} */



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			ajoutContaint();
			Date();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Animation.INDEFINITE);
		KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
			heure.setText(Currentime());
		});

		timeline.getKeyFrames().add(keyFrame);
		timeline.play();
	}


}