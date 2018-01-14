package com.prudprudi4.persons.db;

import com.prudprudi4.persons.Person;

import java.sql.*;
import java.util.ArrayList;

public class DBConn {
    public DBConn() {
    }

    private Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/persons", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public Person getPerson(String name, String pass) {

        String sql = "SELECT * FROM person WHERE `name` LIKE ? and `pass` LIKE ?";

        try (Connection conn = getConnection()) {
            PreparedStatement prepSt = conn.prepareStatement(sql);
            prepSt.setString(1, name);
            prepSt.setString(2, pass);
            ResultSet resSet = prepSt.executeQuery();

            while (resSet.next()) {
                Person person = new Person();
                person.name = name;
                person.pass = pass;
                person.isAdmin = resSet.getBoolean("is_admin");
                person.limitation = resSet.getBoolean("limitation");
                person.isBlocked = resSet.getBoolean("is_blocked");
                person.errorCount = resSet.getInt("error_count");

                return person;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void incErrCount(String name) {
        String sql = "UPDATE person SET `error_count` = `error_count` + 1 WHERE `name` LIKE ?";

        try (Connection conn = getConnection()) {
            PreparedStatement prepSt = conn.prepareStatement(sql);
            prepSt.setString(1, name);
            prepSt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changePass(String name, String oldPass, String newPass) {
        String sql = "UPDATE person SET pass = ? WHERE `name` LIKE ? and `pass` LIKE ?";

        try (Connection conn = getConnection()) {
            PreparedStatement prepSt = conn.prepareStatement(sql);
            prepSt.setString(1, newPass);
            prepSt.setString(2, name);
            prepSt.setString(3, oldPass);
            prepSt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Person> getAllPersons() {
        String sql = "SELECT * FROM person WHERE name NOT LIKE 'ADMIN'";

        ArrayList<Person> persons = new ArrayList<>();

        try (Connection conn = getConnection()) {
            PreparedStatement prepSt = conn.prepareStatement(sql);
            ResultSet resSet = prepSt.executeQuery();

            while (resSet.next()) {
                Person person = new Person();
                person.name = resSet.getString("name");
                person.pass = resSet.getString("pass");
                person.isAdmin = resSet.getBoolean("is_admin");
                person.limitation = resSet.getBoolean("limitation");
                person.isBlocked = resSet.getBoolean("is_blocked");
                person.errorCount = resSet.getInt("error_count");
                persons.add(person);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return persons;
    }

    public void changePersonByAdmin(String name, boolean isBlocked, boolean limitation) {
        String sql = "UPDATE person SET is_blocked = ?, limitation = ? WHERE `name` LIKE ?";

        try (Connection conn = getConnection()) {
            PreparedStatement prepSt = conn.prepareStatement(sql);
            prepSt.setBoolean(1, isBlocked);
            prepSt.setBoolean(2, limitation);
            prepSt.setString(3, name);
            prepSt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNewPerson(String name) {
        String sql = "INSERT INTO person (name, pass) VALUE(?, ?)";

        try (Connection conn = getConnection()) {
            PreparedStatement prepSt = conn.prepareStatement(sql);
            prepSt.setString(1, name);
            prepSt.setString(2, "");
            prepSt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getErrorCount(String name) {
        String sql = "SELECT error_count FROM person WHERE name LIKE ?";

        int errorCount = -1;
        try (Connection conn = getConnection()) {
            PreparedStatement prepSt = conn.prepareStatement(sql);
            prepSt.setString(1, name);
            ResultSet resSet = prepSt.executeQuery();

            while (resSet.next()) {
                errorCount = resSet.getInt("error_count");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return errorCount;
    }

    public void blockPerson(String name) {
        String sql = "UPDATE person SET is_blocked = true WHERE `name` LIKE ?";

        try (Connection conn = getConnection()) {
            PreparedStatement prepSt = conn.prepareStatement(sql);
            prepSt.setString(1, name);
            prepSt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isBlockPerson(String name) {
        String sql = "SELECT is_blocked FROM person WHERE name LIKE ?";

        boolean isBlock = false;
        try (Connection conn = getConnection()) {
            PreparedStatement prepSt = conn.prepareStatement(sql);
            prepSt.setString(1, name);
            ResultSet resSet = prepSt.executeQuery();

            while (resSet.next()) {
                isBlock = resSet.getBoolean("is_blocked");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isBlock;
    }

    public void nullErrorCount(String name) {
        String sql = "UPDATE person SET error_count = 0 WHERE `name` LIKE ?";

        try (Connection conn = getConnection()) {
            PreparedStatement prepSt = conn.prepareStatement(sql);
            prepSt.setString(1, name);
            prepSt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
