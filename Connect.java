/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_2;

/**
 *
 * @author amnwaqar
 */

import java.sql.*;

public class Connect {
    private Connection conn;

    public Connect(String URL, String username, String pass) throws SQLException, ClassNotFoundException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        conn = DriverManager.getConnection(URL, username, pass);
        System.out.println("Connected successfully to database URL: " + URL);
    }

    boolean checkUser(String username) throws SQLException {

        Statement stmnt = conn.createStatement();
        username = username.toLowerCase();
        String query = "SELECT USERNAME FROM USERSCORES WHERE lower(USERNAME)='" + username + "'";
        ResultSet rs = stmnt.executeQuery(query);

        if (rs.next()) {
            return true;
        }
        return false;

    }

    void addUser(String username) throws SQLException {

        String query = "INSERT INTO USERSCORES (USERNAME) VALUES ('" + username + "')";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.execute();

    }

    void addPassword(String username, String password) throws SQLException {
        String query = "UPDATE USERSCORES SET PASSWORD=? WHERE USERNAME =?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, password);
        ps.setString(2, username);
        int resultSet = ps.executeUpdate();
        //ps.execute();
    }

    boolean updateHScore(User user) throws SQLException {

        String hscore = getHScore(user.getUsername());
        int prevHScore = Integer.parseInt(hscore);

        int currScore = user.getScore();
        if (Math.abs(currScore) > prevHScore) {
            String query = "UPDATE USERSCORES SET SCORE=? WHERE USERNAME =?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, String.valueOf(user.getScore()));
            ps.setString(2, user.getUsername());
            ps.executeUpdate();
            return true;
        }
        return false;

    }

    String getHScore(String username) throws SQLException {
        Statement stmnt = conn.createStatement();
        String query = "SELECT SCORE FROM USERSCORES WHERE lower(USERNAME)='" + username + "'ORDER BY SCORE";
        ResultSet rs = stmnt.executeQuery(query);
        if (rs.next()) {
            String highScore = rs.getString("SCORE");
            return highScore;

        }
        return "0";
    }

    String getAllScores() throws SQLException {
        Statement stmnt = conn.createStatement();
        String query = "SELECT USERNAME, SCORE FROM USERSCORES";
        ResultSet rs = stmnt.executeQuery(query);
        String line;
        String text = "\n\n";
        while (rs.next()) {
            String username = rs.getString("USERNAME");
            String score = String.valueOf(rs.getInt("SCORE"));

            line = "        " + username + "\t" + score + "";

            text += line + "\n";
        }

        return text;
    }

    public boolean checkPassword(String username, String enteredPass) throws SQLException {
        Statement stmt = conn.createStatement();

        String CHECK_USER = "SELECT * FROM USERSCORES WHERE lower(USERNAME) = '" + username + "' AND password = '" + enteredPass + "'";
        ResultSet rs = stmt.executeQuery(CHECK_USER);

        if (rs.next()) {
            return true;
        }
        return false;
    }
}
