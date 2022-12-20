package school.onlineschool.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class HomeWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    GroupStudent groupStudent1;

    private String taskHomework;
    private LocalDate dateTask;
    private LocalDate deadlineTask;

    public HomeWork() {
    }

    public HomeWork(Long id, String taskHomework, LocalDate dateTask, LocalDate deadlineTask, GroupStudent groupStudent1) {
        this.id = id;
        this.taskHomework = taskHomework;
        this.dateTask = dateTask;
        this.deadlineTask = deadlineTask;
        this.groupStudent1 = groupStudent1;
    }

    public HomeWork(String taskHomework, LocalDate dateTask, LocalDate deadlineTask, GroupStudent groupStudent1) {
        this.taskHomework = taskHomework;
        this.dateTask = dateTask;
        this.deadlineTask = deadlineTask;
        this.groupStudent1 = groupStudent1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskHomework() {
        return taskHomework;
    }

    public void setTaskHomework(String taskHomework) {
        this.taskHomework = taskHomework;
    }

    public LocalDate getDateTask() {
        return dateTask;
    }

    public void setDateTask(LocalDate dateTask) {
        this.dateTask = dateTask;
    }

    public LocalDate getDeadlineTask() {
        return deadlineTask;
    }

    public void setDeadlineTask(LocalDate deadlineTask) {
        this.deadlineTask = deadlineTask;
    }

    public GroupStudent getGroupStudent1() {
        return groupStudent1;
    }

    public void setGroupStudent1(GroupStudent groupStudent1) {
        this.groupStudent1 = groupStudent1;
    }
}
