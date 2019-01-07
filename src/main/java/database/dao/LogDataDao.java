package database.dao;

import controller.signInPanelController;
import database.DbConnector;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LogDataDao {
    private Connection connection;
    private signInPanelController signInController;

    public LogDataDao(String login, String pass) {
        this.connection= DbConnector.getInstance().getConnection(login, pass);
    }

    public void normalSelect() {
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery( "select * from category");

            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public void isUser(String login, String password) {
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT loginValue, passwordValue FROM logData WHERE loginValue=login and" +
                    " passwordValue=password");
            if(rs.next()==false) {
                //zerwij połączenie ? // co jak jest true ? wtedy łączę ?
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }*/
}
