package org.project;

import java.util.Objects;

public class Staff implements Comparable<Staff>{

    private int staff_id;
    private String first_name;
    private double rate_per_hour;
    private String last_name;
    private int work_hours;
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return staff_id == staff.staff_id && Double.compare(staff.rate_per_hour, rate_per_hour) == 0 && work_hours == staff.work_hours && Objects.equals(first_name, staff.first_name) && Objects.equals(last_name, staff.last_name) && Objects.equals(email, staff.email);
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 89 * hash + this.staff_id;
        return hash;
    }

    @Override
    public int compareTo(Staff s)
    {
        boolean bSameFirst =
                this.getFirst_name().equalsIgnoreCase(s.getFirst_name());

        boolean bSameLast =
                this.getLast_name().equalsIgnoreCase(s.getLast_name());

        if(bSameFirst && bSameLast) // Both first and last names are the same
        {
            //so, compare based on Work Hours
            return this.getWork_hours() - s.getWork_hours();
        }
        else if(!bSameFirst && bSameLast) //Different first, same last
        {
            return this.getFirst_name().compareToIgnoreCase(  // so compare on first
                    s.getFirst_name());
        }
        else //All other cases
        {
            return this.getLast_name().compareToIgnoreCase(
                    s.getLast_name());
        }
    }

    public Staff(int staff_id, String first_name, String last_name, double rate_per_hour , int work_hours, String email){
        this.staff_id = staff_id;
        this.first_name = first_name;
        this.rate_per_hour = rate_per_hour;
        this.last_name = last_name;
        this.work_hours = work_hours;
        this.email = email;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public double getRate_per_hour() {
        return rate_per_hour;
    }

    public void setRate_per_hour(double rate_per_hour) {
        this.rate_per_hour = rate_per_hour;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getWork_hours() {
        return work_hours;
    }

    public void setWork_hours(int work_hours) {
        this.work_hours = work_hours;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("Staff_ID:%5d\tFirst_Name:%-15s\tLast_Name:%-15s\tEmail:%-15s\tWork_Hours:%5d\tPay_Rate:%2.2f",this.staff_id,this.first_name,this.last_name,this.email,this.work_hours,this.rate_per_hour);
    }
}
