package org.project;

public class Staff {

    private int staff_id;
    private String first_name;
    private double rate_per_hour;
    private String last_name;
    private int work_hours;
    private String email;

    public Staff(int staff_id, String first_name, String last_name,  double rate_per_hour ,int work_hours, String email){
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
        return "Staff{" +
                "staff_id=" + staff_id +
                ", first_name='" + first_name + '\'' +
                ", rate_per_hour=" + rate_per_hour +
                ", last_name='" + last_name + '\'' +
                ", work_hours=" + work_hours +
                ", email='" + email + '\'' +
                '}';
    }
}
