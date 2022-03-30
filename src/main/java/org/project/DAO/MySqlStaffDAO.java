package org.project.DAO;


import org.project.DTO.Staff;
import org.project.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MySqlStaffDAO extends MySqlDAO implements StaffDAOInterface {

    @Override
    public List<Staff> findAllStaff() throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Staff> staffList = new ArrayList<>();

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM bakerystaff";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int staffID = resultSet.getInt("staffID");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                double ratePerHour = resultSet.getDouble("ratePerHour");
                int workHours = resultSet.getInt("workHours");
                String email = resultSet.getString("email");
                Staff s = new Staff(staffID, firstName, lastName, ratePerHour, workHours, email);
                staffList.add(s);
            }
        } catch (SQLException e) {
            throw new DaoException("All Staff - " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllStaff() " + e.getMessage());
            }
        }
        return staffList;     // may be empty
    }

    @Override
    public Staff findStaffbyID(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Staff s = null;
        try {
            connection = this.getConnection();

            String query = "SELECT * FROM bakerystaff WHERE staffID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int staffID = resultSet.getInt("staffID");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                double ratePerHour = resultSet.getDouble("ratePerHour");
                int workHours = resultSet.getInt("workHours");
                String email = resultSet.getString("email");
                s = new Staff(staffID, firstName, lastName, ratePerHour, workHours, email);

            }
        } catch (SQLException e) {
            throw new DaoException("findStaffbyID() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findStaffbyID() " + e.getMessage());
            }
        }
        return s;     // reference to User object, or null value
    }


    /*Delete by ID*/
    @Override
    public void deleteById(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int staffID = id;

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "DELETE FROM bakerystaff WHERE staffID = ?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, staffID);

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new DaoException("findAllStaffResultSet() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllStaff() " + e.getMessage());
            }
        }
    }

}