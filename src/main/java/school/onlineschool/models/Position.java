package school.onlineschool.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String namePosition;

    public Position() {
    }

    public Position(String namePosition) {
        this.namePosition = namePosition;
    }

    public Position(int id, String namePosition) {
        this.id = id;
        this.namePosition = namePosition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamePosition() {
        return namePosition;
    }

    public void setNamePosition(String namePosition) {
        this.namePosition = namePosition;
    }


}
