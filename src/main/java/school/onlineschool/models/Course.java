package school.onlineschool.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nameCourse;
    private int duration;
    private double price;

    // Mapping to the other table
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Application> ob;

    public Course() {
    }

    public Course(int id, String nameCourse, int duration, double price) {
        this.id = id;
        this.nameCourse = nameCourse;
        this.duration = duration;
        this.price = price;
    }

    public Course(String nameCourse, int duration, double price) {
        this.nameCourse = nameCourse;
        this.duration = duration;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<Application> getOb() {
        return ob;
    }

    public void setOb(Set<Application> ob) {
        this.ob = ob;
    }
}
