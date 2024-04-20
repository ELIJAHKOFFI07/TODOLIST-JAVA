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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import static controleurObject.Tools.addFadeAnimation;


public class ControleurRoot implements Initializable{
	@FXML
	private TextField Recherche;
	@FXML
	private Label second;
	@FXML
	private GridPane gridDay;

	@FXML
	private AnchorPane tachebut;

	@FXML
	private Label titreTache;
	@FXML
	private Label heurDep;

	@FXML
	private JFXButton but_tache;

	@FXML
	private AnchorPane gradian;

	@FXML
	private  Label labelChrono;

	@FXML
	private  Label labelDate;

	@FXML
	private Label labelHeureCourant;

	@FXML
	private  Label labelheureTache;
	@FXML
	private AnchorPane window;
	private Parent fxml;

	@FXML
	private ScrollPane scroll;
	@FXML
	private Label labelnomTache;
	@FXML
	private ImageView uploadedImageView;
	private Hyperlink profil;

	@FXML
	private void handleUploadButton(ActionEvent event) throws SQLException {
		Stage stage = (Stage) uploadedImageView.getScene().getWindow();

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir une image");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png", "*.jpeg", "*.gif")
		);

		File selectedFile = fileChooser.showOpenDialog(stage);

		if (selectedFile != null) {
			String imagePath = selectedFile.getAbsolutePath();

			try (Connection cn = BaseDeDonnes.connectbd();
				 PreparedStatement pstmt = cn.prepareStatement("INSERT INTO admin(image) VALUES(?)")) {

				pstmt.setString(1, imagePath);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	void MiseAJours() throws SQLException {
		Connection  cn=BaseDeDonnes.connectbd();
		Statement stmt = cn.createStatement ();
		String sql = "SELECT COUNT(*) AS total FROM tache";


		String h1=null;
		String	h3=null;
		ResultSet res= stmt.executeQuery(sql);
		int count=0,taille=0;
		//ResultSetMetaData resTab= res.getMetaData();
		if (res.next()) {
			count = res.getInt("total");
		}
		Date date = new Date();
		String p1= new SimpleDateFormat("dd-MM-yyyy").format(date);


		//l'action qui doit s'effectuer quand il y  n'a pas de tache dans la base de donne ou le jour courant
		String sql5 = "SELECT COUNT(*) AS total1 FROM tache WHERE dateTache='"+p1+"'";
		ResultSet res3= stmt.executeQuery(sql5);
		if (res3.next()) {
			taille = res3.getInt("total1");}

		if( count==1 || taille==0) {


			String sql2 = "SELECT nom FROM tache WHERE idTache=1";
			String sql6 = "SELECT heueDepart FROM tache WHERE idTache=1";
			String sql4 = "SELECT heureFin FROM tache WHERE idTache=1";


			ResultSet res2= stmt.executeQuery(sql2);
			if (res2.next()) {
				System.out.println(res2.getString(1));
				labelnomTache.setText(res2.getString(1));
			}


			ResultSet res4= stmt.executeQuery(sql4);

			if (res4.next()) { h3=res4.getString(1);}
			ResultSet res6= stmt.executeQuery(sql6);
			if (res6.next()) {
				h1=res6.getString(1);
			}

			System.out.println(h1+"c'est h1"+h3);
			//"10:30:00"

			labelheureTache.setText(diffTime(h3, h1));


			ResultSet res5= stmt.executeQuery(sql4);
			//ResultSetMetaData resTab= (ResultSetMetaData) res2.getMetaData();
			if (res5.next()) {
				labelChrono.setText(res5.getString(1));
				System.out.println(res5.getString(1));
			}
			labelDate.setText(p1);
			cn.close ();
		}
	}

	String diffTime( String t1, String t2) {
		//"10:30:00"
		// Exemple de valeurs de temps
		Time   time1 = Time.valueOf(t1);
		Time      time2 = Time.valueOf(t2);

		// Calcul de la différence entre les deux temps
		long diffMillis = time2.getTime() - time1.getTime();

		// Conversion de la différence en heures, minutes, secondes
		int hours = (int) (diffMillis / (1000 * 60 * 60));
		int minutes = (int) ((diffMillis / (1000 * 60)) % 60);
		//int seconds = (int) ((diffMillis / 1000) % 60);
		return hours+"h"+minutes ;
	}


	// Méthode pour formater la durée en jours, heures, minutes et secondes
	protected static String formatDuree(int duree) {
		// int jours = duree / (24 * 60 * 60);
		int heures = (duree ) / (60 * 60);//% (24 * 60 * 60)
		int minutes = (duree % (60 * 60)) / 60;

		return  heures + ":" + minutes ;
	}

	protected static String formatDureeS(int duree) {
		// int jours = duree / (24 * 60 * 60);
		int heures = (duree ) / (60 * 60);//% (24 * 60 * 60)
		int minutes = (duree % (60 * 60)) / 60;
		int secondes = duree % 60;
		return String.valueOf(secondes) ;
	}

	public static String Currentime() {

		Date now = new Date();// recupere la date du systeme
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");// avoir l'heure dans ce format
		String formattedNow = formatter.format(now);
		return( formattedNow);
	}

	@FXML
	void actTacheDay(String id) {
		try {
			Connection cn = BaseDeDonnes.connectbd();
			Statement stmt = cn.createStatement();

			// Supprimer une tâche en utilisant l'ID fourni
			String sql = "DELETE FROM `tache` WHERE idTache = " + id;
			int rowsAffected = stmt.executeUpdate(sql);

			if (rowsAffected > 0) {
				System.out.println("Tâche supprimée avec succès !");
			} else {
				System.out.println("Aucune tâche trouvée avec l'ID fourni.");
			}

			stmt.close();
			cn.close();
			// chargement



		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@FXML
	void act_tache() {
		//pour supprimer l'ancien fenetre
		Stage home= new Stage();

		try {

			Parent fxml = FXMLLoader.load(getClass().getResource("/interfaces/Tache.fxml"));
			VBox container = new VBox(fxml);
			addFadeAnimation(container); // Ajoute l'animation de glissement

			Scene scene = new Scene(container);
			scene.setFill(Color.TRANSPARENT); // Rend le fond de la scène transparent (si vous utilisez StageStyle.TRANSPARENT)
			home.setScene(scene);
			home.show();



		} catch (IOException e) {

			e.printStackTrace();}

	}
	private  int duree=1000;

	//action lorsqu'on click sur un boutton de la zone day
	/*public  void actionDay(int id) throws SQLException {
		Connection  cn=BaseDeDonnes.connectbd();
		Statement stmt = cn.createStatement ();

		String sql2 = "SELECT nom FROM tache WHERE idTache="+id+"";
		String sql3 = "SELECT date FROM tache WHERE idTache="+id+"";
		String sql4 = "SELECT heureFin FROM tache WHERE idTache="+id+"";
		ResultSet res2= stmt.executeQuery(sql2);
		ResultSet res3= stmt.executeQuery(sql3);
		ResultSet res4= stmt.executeQuery(sql3);
		//ResultSetMetaData resTab= (ResultSetMetaData) res2.getMetaData();
		labelheureTache.setText(res2.getString(id));
		labelDate.setText(res3.getString(id));
		cn.close ();

	}*/

	void ajoutContaint() {
		try {
			Connection  cn=BaseDeDonnes.connectbd();
			Statement stmt = cn.createStatement ();

			String sql = "SELECT COUNT(*) AS total FROM tache";
			int count = 0;
			ResultSet res= stmt.executeQuery(sql);
			if (res.next()) {
				count = res.getInt("total");
			}

			//  gridDay=new GridPane();
			String sql2 = "SELECT `idTache`, `nom`, `heueDepart` FROM `tache` WHERE idTache > 1";
			ResultSet res2= stmt.executeQuery(sql2);

			int i = 0; // Variable pour parcourir les lignes de la grille
			while (res2.next()) {
				String nom = res2.getString("nom");
				String heueDepart = res2.getString("heueDepart");
				String idTache = res2.getString("idTache");

				AnchorPane space = new AnchorPane();
				gridDay.setVgap(3.0);
				space.setId("space");
				space.setPrefHeight(34.0);
				space.setPrefWidth(315.0);
				space.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 8; -fx-border-radius: 8;");

				JFXButton butTacheDay = new JFXButton();
				butTacheDay.setId("butTacheDay");
				butTacheDay.setLayoutX(244.0);
				butTacheDay.setLayoutY(5.0);
				butTacheDay.setOnAction(event -> actTacheDay(idTache));
				butTacheDay.setPrefHeight(38.0);
				butTacheDay.setPrefWidth(80.0);
				butTacheDay.setText("supprimer");

				Label titreTache = new Label();
				titreTache.setId("titreTache");
				titreTache.setLayoutX(44.0);
				titreTache.setLayoutY(6.0);
				titreTache.setText(nom);
				Font titreFont = Font.font("System Bold", 14.0);
				titreTache.setFont(titreFont);

				Label heurDep = new Label();
				heurDep.setId("heurDep");
				heurDep.setLayoutX(47.0);
				heurDep.setLayoutY(30.0);
				heurDep.setText(heueDepart);

				DropShadow dropShadow = new DropShadow();
				dropShadow.setBlurType(BlurType.GAUSSIAN);
				dropShadow.setOffsetY(2.0);
				Color shadowColor = Color.color(0.8100000023841858,0.8100000023841858,0.8100000023841858,0.4870820641517639);
				dropShadow.setColor(shadowColor);

				space.getChildren().addAll(butTacheDay, titreTache, heurDep);
				space.setEffect(dropShadow);
				gridDay.add(space, 0, i);

				i++;
			}

			//stmt.executeUpdate("commit");
			stmt.close ();
			cn.close ();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			MiseAJours();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ajoutContaint();
		// TODO Auto-generated method stub
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Animation.INDEFINITE);
		KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {

			labelHeureCourant.setText(Currentime());
			labelChrono.setText(formatDuree(duree));
			second.setText(formatDureeS(duree));
			duree--;
		});

		gradian.setStyle("-fx-background-color: linear-gradient(to right ,#D6D6D6,white)");
		timeline.getKeyFrames().add(keyFrame);
		timeline.play();

	}
}