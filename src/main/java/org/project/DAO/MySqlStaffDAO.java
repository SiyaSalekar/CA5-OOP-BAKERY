package org.project.DAO;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.project.DTO.Staff;
import org.project.Exceptions.DaoException;
import org.project.SortType;
import org.project.StaffFirstNameComparator;
import org.project.StaffWorkHoursComparator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;

import static org.project.BusinessObjects.App.VALID_EMAIL_ADDRESS_REGEX;


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
    public String deleteById(int id) throws DaoException {
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

            return "Deleted Successfully";

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

    /*Add Staff*/
    @Override
    public String addStaff(Staff staff) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int STAFF_ID = staff.getStaff_id();
        String FIRST_NAME = staff.getFirst_name();
        String LAST_NAME = staff.getLast_name();
        double RATE_PER_HOUR  = staff.getRate_per_hour();
        int WORK_HOURS  = staff.getWork_hours();
        String EMAIL = staff.getEmail();

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "INSERT INTO BAKERYSTAFF\n" +
                    "VALUES (?,?, ?, ?, ?,?);";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, STAFF_ID);
            preparedStatement.setString(2, FIRST_NAME);
            preparedStatement.setString(3, LAST_NAME);
            preparedStatement.setDouble(4, RATE_PER_HOUR);
            preparedStatement.setInt(5, WORK_HOURS);
            preparedStatement.setString(6, EMAIL);

            preparedStatement.executeUpdate();

            return "Added Successfully";

        } catch (SQLException e) {
            throw new DaoException("findAllStaffSet() " + e.getMessage());
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

    /*Find by Filter*/
    @Override
    /**Using DB Query - Order By*/
    public List<Staff> findStaffUsingFilterWorkHour() throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Staff> staffList = new ArrayList<>();

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "Select * from BAKERYSTAFF ORDER by workHours";
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
            throw new DaoException("findAllStaffFilter() " + e.getMessage());
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
    /**Using Comparator - StaffWorkHoursComparator*/
    public List<Staff> findStaffUsingFilterWorkHourComparator() throws DaoException {

        List<Staff> staffList = findAllStaff();
        StaffWorkHoursComparator workHourComparator = new StaffWorkHoursComparator(SortType.Ascending);
        Collections.sort( staffList, workHourComparator);

        return staffList;
    }
    @Override
    /**Using Comparator - StaffFirstNameComparator*/
    public List<Staff> findStaffUsingFilterFirstNameComparator() throws DaoException {

        List<Staff> staffList = findAllStaff();
        StaffFirstNameComparator firstNameComp = new StaffFirstNameComparator();
        Collections.sort( staffList, firstNameComp);

        return staffList;
    }


    @Override
    /**Using DB Query - Order By*/
    public List<Staff> findStaffUsingFilterFirstName() throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Staff> staffList = new ArrayList<>();

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "Select * from BAKERYSTAFF ORDER by firstName";
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
            throw new DaoException("findAllStaffFilter() " + e.getMessage());
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

    public String findAllStaffJSON(){
        System.out.println("All Staff as JSON String: ");
        try {
            List<Staff> staffList = findAllStaff();
            Gson gsonParser = new GsonBuilder().setPrettyPrinting().create();
                String StaffJSON = gsonParser.toJson(staffList);

                return StaffJSON;

        }catch ( DaoException e )
        {
            e.printStackTrace();
        }
        return "Error cannot Parse to JSON";

    }

    public String findStaffbyIDJSON(int id){

        try{

        List<Staff> staffList = findAllStaff();
        String StaffJSON="";

        Staff sTest= findStaffbyID(id);

        if(sTest==null){
            System.out.println("Staff with ID = "+id+" doesn't exist.");
        }
        else{
            System.out.println("Staff with id "+id+" as a JSON String: ");
        }

        Gson gsonParser =  new GsonBuilder().setPrettyPrinting().create();
        for(Staff s: staffList){
            if(s.getStaff_id()==id) {
                StaffJSON = gsonParser.toJson(s);
            }
        }

        return StaffJSON;
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
        return "Error cannot Parse to JSON";

    }

    public String findStaffbyIDJSONoFormatting(int id){

        try{

            List<Staff> staffList = findAllStaff();
            String StaffJSON="";

            Staff sTest= findStaffbyID(id);

            if(sTest==null){
                System.out.println("Staff with ID = "+id+" doesn't exist.");
            }
            else{
                System.out.println("Staff with id "+id+" as a JSON String: ");
            }

            Gson gsonParser = new Gson();
            for(Staff s: staffList){
                if(s.getStaff_id()==id) {
                    StaffJSON = gsonParser.toJson(s);
                }
            }

            return StaffJSON;
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
        return "Error cannot Parse to JSON";

    }

    public String findAllStaffJSONNoFormatting(){

        System.out.println("All Staff as JSON String: ");
        try {
            List<Staff> staffList = findAllStaff();
            Gson gsonParser = new Gson();
            String StaffJSON = gsonParser.toJson(staffList);

            return StaffJSON;

        }catch ( DaoException e )
        {
            e.printStackTrace();
        }
        return "Error cannot Parse to JSON";

    }

    public String summaryDataJSON(){
        String JSONRes="";
        HashMap<String,Double> res = new HashMap<>();
        try {
            //average salary
            List<Staff> staffList = findAllStaff();
            double total =0;
            double count =0;
            double avg = 0;
            for(Staff s: staffList){
                total += s.getRate_per_hour();
                count++;
            }
            avg = total/count;
            res.put("Average Salary of Staff",avg);

            //standard deviation
            double sub=0;
            double standardDeviation =0;
            double subTotal=0;
            double subCount=0;
            for(Staff s:staffList){
                sub = (Math.abs(s.getRate_per_hour()-avg))*(Math.abs(s.getRate_per_hour()-avg));
                subTotal+=sub;
                subCount++;
            }
            standardDeviation = Math.sqrt(subTotal/subCount);
            res.put("Standard Deviation of Salaries",standardDeviation);

            //total salary earned by each Staff
            for(Staff s:staffList){
                res.put("Total Salary of "+s.getFirst_name()+" "+s.getLast_name(),s.getWork_hours()*s.getRate_per_hour());
            }

            Gson gsonParser = new Gson();
            JSONRes = gsonParser.toJson(res);

        }catch ( DaoException e )
        {
            e.printStackTrace();
        }
        return JSONRes;
    }



}