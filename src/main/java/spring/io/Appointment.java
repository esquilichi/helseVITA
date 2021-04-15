package spring.io;

public class Appointment {
    
    private int hour;
    private int month;
    private int year;
    private long id;
    
    public Appointment(int hour, int month, int year) {
        this.hour = hour;
        this.month = month;
        this.year = year;
    }

    public int getHour() {
        return this.hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public Appointment() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
