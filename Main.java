package com.company;
import java.sql.*;
import java.util.*;
public class Main {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/project";
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.getCause();
            System.out.println("Error found");
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
        }
        return connection;
    }
    public static void getChoice() {
        System.out.println("\n1. Add\n2. Remove\n3. Exit\n");
    }
    public static int add(Person person) {
        int res = -1;
        try {
            PreparedStatement statement = getConnection().prepareStatement("insert into data(username,password)values(?,?)");
            statement.setString(1,person.getUsername());
            statement.setString(2,person.getPassword());
            res = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    public static int delete(Person person) {
        int res = -1;
        try {
            PreparedStatement statement = getConnection().prepareStatement("delete from data where username=? and password=?");
            statement.setString(1, person.getUsername());
            statement.setString(2, person.getPassword());
            res = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    public static void main(String[] args) {
        System.out.println(getConnection() + "\n\n");
        System.out.println("------------- SQL PROJECT -------------");
        int choice = 0;
        Scanner in = new Scanner(System.in);
        while (true) {
            getChoice();
            choice = in.nextInt();
            if (choice == 3) {
                System.exit(0);
            } else {
                System.out.print("username : ");
                String username = in.next();
                System.out.print("password : ");
                String password = in.next();
                Person person = new Person(username, password);
                if (choice == 1) {
                    add(person);
                } else {
                    delete(person);
                }
            }
        }
    }
}