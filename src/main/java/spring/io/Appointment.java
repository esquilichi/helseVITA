package spring.io;

public class Appointment {
    
    private int hour;
    private int day;
    private int month;
    private int year;
    private long id;
    
    public Appointment(int hour, int day, int month, int year) {
		super();
		this.hour = hour;
		this.day = day;
		this.month = month;
		this.year = year;
	}

    public int getHour() {
        return this.hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }
    
     public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
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
