package controleurObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControleurValideur implements Initializable   {
    int id;
    @FXML
    private Label chrono;

    @FXML
    private Button effectuer;

    @FXML
    void action_chrono(ActionEvent event) {
        try {
            Connection cn = BaseDeDonnes.connectbd();
            Statement stmt = cn.createStatement();

            String sql = "UPDATE tache SET valider = 30 WHERE id = '" + id + "'";
            int rowsAffected = stmt.executeUpdate(sql);

           // stmt.executeUpdate("commit");
            stmt.close();
            cn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

    }

}