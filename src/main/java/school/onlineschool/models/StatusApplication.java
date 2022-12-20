package school.onlineschool.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class StatusApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nameStatus;

    // Mapping to the other table
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Application> ob;

    public StatusApplication() {
    }

    public StatusApplication(int id, String nameStatus) {
        this.id = id;
        this.nameStatus = nameStatus;
    }

    public StatusApplication(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    public Set<Application> getOb() {
        return ob;
    }

    public void setOb(Set<Application> ob) {
        this.ob = ob;
    }
}
