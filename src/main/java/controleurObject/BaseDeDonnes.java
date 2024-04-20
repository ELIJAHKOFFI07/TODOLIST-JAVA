package controleurObject;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDeDonnes {

	public static Connection connectbd() {

		try {

			Class.forName("org.sqlite.JDBC");
			Path fichier = Paths.get("todolistbdd.sqlite"); // chemin relatif
			String path = fichier.toAbsolutePath().toString();
			path = path.replaceAll("\\\\", "\\\\\\\\");




			Connection connect = DriverManager.getConnection("jdbc:sqlite:"+path);
			return connect;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}