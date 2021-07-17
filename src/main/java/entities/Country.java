package entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "country")
public class Country implements Serializable {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @OneToOne
    @JoinColumn(name = "CAPITAL", referencedColumnName = "ID")
    private Capital capital;

    public Country(Integer id, String name, Capital capital) {
        this.id = id;
        this.name = name;
        this.capital = capital;
    }

    public Country() {

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

    public Capital getCapital() {
        return capital;
    }

    public void setCapital(Capital capital) {
        this.capital = capital;
    }


    @Override
    public String toString() {
        return id + " " + name + " " + capital.getName();
    }

}
