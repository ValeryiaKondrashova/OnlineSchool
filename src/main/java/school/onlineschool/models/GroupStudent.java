package school.onlineschool.models;

import javax.persistence.*;

@Entity
@Table(name = "GroupStudent")
public class GroupStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGroup;

    private int groupNumber;

    private String startGroup;

    @ManyToOne
    Course course;

    @ManyToOne
    Teacher teacher;


    public GroupStudent() {
    }

    public GroupStudent(int idGroup, int groupNumber, String startGroup) {
        this.idGroup = idGroup;
        this.groupNumber = groupNumber;
        this.startGroup = startGroup;
    }

    public GroupStudent(int groupNumber, String startGroup, Course course, Teacher teacher) {
        this.idGroup = idGroup;
        this.groupNumber = groupNumber;
        this.startGroup = startGroup;
        this.course = course;
        this.teacher = teacher;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getStartGroup() {
        return startGroup;
    }

    public void setStartGroup(String startGroup) {
        this.startGroup = startGroup;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
