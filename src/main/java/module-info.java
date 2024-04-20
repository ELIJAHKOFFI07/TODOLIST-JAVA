module main.todoliste_projeect {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires mysql.connector.java;
    requires java.mail;
    requires activation;
    requires org.xerial.sqlitejdbc;



    opens appli to javafx.fxml;
    exports appli;
    exports controleurObject;
    opens controleurObject to javafx.fxml;
}