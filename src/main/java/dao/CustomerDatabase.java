/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Customer;
import domain.Product;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author peani371
 */
public class CustomerDatabase implements CustomerDAOInterface {

    private String dbURL = "jdbc:h2:tcp://localhost:9047/project;IFEXISTS=TRUE";
    
    public CustomerDatabase(String newDbURL) {
        this.dbURL = newDbURL;
    }

    public CustomerDatabase() {
    }

    @Override
    public void save(Customer customer) {
        String sql = "Insert into Customer (Username, Password, "
                + "Firstname, Surname, Address, Email, CreditCard) "
                + "values (?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection dbCon = JdbcConnection.getConnection(dbURL);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {

            stmt.setString(1, customer.getUsername());
            stmt.setString(2, customer.getPassword());
            stmt.setString(3, customer.getFirstName());
            stmt.setString(4, customer.getSurname());
            stmt.setString(5, customer.getAddress());
            stmt.setString(6, customer.getEmailAddress());
            stmt.setString(7, customer.getCreditCardDetails());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }

    }

    @Override
    public Customer getCustomer(String username) {

        String sql = "select * from Customer where Username = ?";

        try (
                Connection dbCon = JdbcConnection.getConnection(dbURL);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Integer id = rs.getInt("CustomerID");
                String uname = rs.getString("Username");
                String password = null;
                String fname = rs.getString("Firstname");
                String lname = rs.getString("Surname");
                String address = rs.getString("Address");
                String email = rs.getString("Email");
                String creditCard = rs.getString("CreditCard");

                Customer c = new Customer();

                c.setPersonID(id);
                c.setUsername(uname);
                c.setPassword(password);
                c.setFirstName(fname);
                c.setSurname(lname);
                c.setAddress(address);
                c.setEmailAddress(email);
                c.setCreditCardDetails(creditCard);

                return c;

            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Boolean validateCredentials(String username, String password) {
        
        String sql = "select * from Customer where Username = ?, password = ?";

        try (
                Connection dbCon = JdbcConnection.getConnection(dbURL);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
                
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            
            if (rs != null) {
                return true;
            } else {
                return null;
            }
            
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

}
