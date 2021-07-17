package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "capital")
public class Capital {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "NAME")
    private String name;

    public Capital(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Capital() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }

}
