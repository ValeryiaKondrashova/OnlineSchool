package school.onlineschool.models;

import javax.persistence.*;

@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namePerson;
    private String telephone;
    private String timeCall;

    @ManyToOne
    //Adding the name
    @JoinColumn(name = "course_id")
    Course course;

    @ManyToOne
    //Adding the name
    @JoinColumn(name = "status_id")
    StatusApplication status;

    public Application() {
    }

    public Application(Long id, String namePerson, String telephone, String timeCall, Course course, StatusApplication statusApplication) {
        this.id = id;
        this.namePerson = namePerson;
        this.telephone = telephone;
        this.timeCall = timeCall;
        this.course = course;
        this.status = statusApplication;
    }

    public Application(String namePerson, String telephone, String timeCall, Course course,  StatusApplication statusApplication) {
        this.namePerson = namePerson;
        this.telephone = telephone;
        this.timeCall = timeCall;
        this.course = course;
        this.status = statusApplication;
    }

    public Long getId() {
        return id;
    }

    public void setId(int Long) {
        this.id = id;
    }

    public String getNamePerson() {
        return namePerson;
    }

    public void setNamePerson(String namePerson) {
        this.namePerson = namePerson;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTimeCall() {
        return timeCall;
    }

    public void setTimeCall(String timeCall) {
        this.timeCall = timeCall;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public StatusApplication getStatus() {
        return status;
    }

    public void setStatus(StatusApplication status) {
        this.status = status;
    }
}
