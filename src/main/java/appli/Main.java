package appli;

import controleurObject.BaseDeDonnes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import static controleurObject.Tools.addSlideAnimation;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Connection cn = BaseDeDonnes.connectbd();
			Statement stmt = cn.createStatement();

			String sql = "SELECT COUNT(*) AS total FROM admin";
			int count = 0;

			ResultSet res = stmt.executeQuery(sql);
			ResultSetMetaData resTab = res.getMetaData();
			if (res.next()) {
				count = res.getInt("total");
			}

			stmt.close();
			cn.close();

			Parent root;
			if (count == 0) {
				root = FXMLLoader.load(getClass().getResource("/interfaces/SignIn.fxml"));
			} else {
				root = FXMLLoader.load(getClass().getResource("/interfaces/MotDePasse.fxml"));
			}

			VBox container = new VBox(root);
			addSlideAnimation(container,2500 ); // Ajoute l'animation de glissement

			Scene scene = new Scene(container);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
// animations et transitions


	public static void main(String[] args) {
		launch(args);
	}
}
