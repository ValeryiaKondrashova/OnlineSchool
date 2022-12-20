package school.onlineschool.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String firstName;
    private String lastName;
    private String patronymic;

    @JsonFormat(pattern="yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private String birthday;

    @ManyToOne
    GroupStudent groupStudent;

    @ManyToOne
    User userAccountName1;

    private String telephone;
    private String email;

    public Student() {
    }

    public Student(Long id, String firstName, String lastName, String patronymic, String birthday, GroupStudent groupStudent, User userAccountName1, String telephone, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.groupStudent = groupStudent;
        this.userAccountName1 = userAccountName1;
        this.telephone = telephone;
        this.email = email;
    }

    public Student(String firstName, String lastName, String patronymic, String birthday, GroupStudent groupStudent, User userAccountName1, String telephone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.groupStudent = groupStudent;
        this.userAccountName1 = userAccountName1;
        this.telephone = telephone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public GroupStudent getGroupStudent() {
        return groupStudent;
    }

    public void setGroupStudent(GroupStudent groupStudent) {
        this.groupStudent = groupStudent;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUserAccountName1() {
        return userAccountName1;
    }

    public void setUserAccountName1(User userAccountName1) {
        this.userAccountName1 = userAccountName1;
    }
}
