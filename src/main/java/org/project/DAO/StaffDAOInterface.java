package org.project.DAO;

import org.project.DTO.Staff;
import org.project.Exceptions.DaoException;
import org.project.StaffFirstNameComparator;

import java.util.ArrayList;
import java.util.List;

public interface StaffDAOInterface
{
    public List<Staff> findAllStaff() throws DaoException;
    public Staff findStaffbyID(int StaffID) throws DaoException;
    public String deleteById(int Id) throws DaoException;
    public String addStaff(Staff staff) throws DaoException;

    /**Using DB Query - Order By*/
    public List<Staff> findStaffUsingFilterWorkHour() throws DaoException;

    /**Using Comparator - StaffWorkHoursComparator*/
    public List<Staff> findStaffUsingFilterWorkHourComparator() throws DaoException;

    /**Using DB Query - Order By*/
    public List<Staff> findStaffUsingFilterFirstName() throws DaoException;

    /**Using Comparator - StaffFirstNameComparator*/
    public List<Staff> findStaffUsingFilterFirstNameComparator() throws DaoException;

    public String findAllStaffJSON() throws DaoException;
    public String findStaffbyIDJSON(int id) throws DaoException;

    public String findAllStaffJSONNoFormatting() throws DaoException;
    public String findStaffbyIDJSONoFormatting(int id) throws DaoException;

}