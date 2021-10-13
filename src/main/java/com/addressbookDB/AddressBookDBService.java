package com.addressbookDB;

import com.bridgelab.PersonInfo;
import com.bridgelabz.contactInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService {
    private static AddressBookDBService addressBookDBService;

    public AddressBookDBService() {
    }

    /**
     * Purpose : For creating a singleton object
     *
     * @return the classpath
     */
    public static AddressBookDBService getInstance() {
        if (addressBookDBService == null)
            addressBookDBService = new AddressBookDBService();
        return addressBookDBService;
    }

    /**
     * Purpose : To create connection with database
     *
     * @return return the connection path
     * @throws SQLException if the connection is not successful
     */
    private Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost/addressbook?useSSL=false";
        String userName = "root";
        String password = "asim2123";
        Connection connection;
        System.out.println("Connecting to database:" + jdbcURL);
        connection = DriverManager.getConnection(jdbcURL, userName, password);
        System.out.println("Connection is successful!!!!" + connection);
        return connection;
    }

    /**
     * Purpose : To execute query after creating connection
     *
     * @param sql : taking the query from the database for passing the values
     * @return the contact details list
     */
    private List<contactInfo> getContactsDataUsingDB(String sql) {
        List<contactInfo> personInfoList = new ArrayList<>();
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("SNo");
                String type = resultSet.getString("Type");
                String fName = resultSet.getString("First_Name");
                String lName = resultSet.getString("Last_Name");
                String address = resultSet.getString("Address");
                String city = resultSet.getString("City");
                String state = resultSet.getString("State");
                String zip = resultSet.getString("Zip");
                String phoneNo = String.valueOf(resultSet.getLong("Phone_Number"));
                String email = resultSet.getString("Email");
                personInfoList.add(new PersonInfo(fName, lName, address, city, state, zip, phoneNo, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personInfoList;
    }

    /**
     * Purpose : To read the person info from the database
     *
     * @return the contact details
     */
    public List<contactInfo> readData() {
        String sql = "SELECT * FROM address_book";
        return getContactsDataUsingDB(sql);
    }
}