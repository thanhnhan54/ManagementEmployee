package model;

import java.io.Console;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Employee {
    private String id;
    private String name;
    private String phone;
    private String birthDate;
    private String email;
    private int age;
    private double salary;
    private LocalDate date;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private static final double HOURLY_RATE = 10.0;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Employee(String id, String name, String birthDate, String phone, String email) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email=email;
    }
    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    public double calculateSalary() {
        Duration duration = Duration.between(startTime, endTime);
        long hoursWorked = duration.toHours();
        return hoursWorked * HOURLY_RATE;
    }


    @Override
    public String toString() {
        return String.format("║    %-6s║%-22s║  %-14s║    %-16s║ %-28s║", id, name, birthDate, phone,email);
    }


}
