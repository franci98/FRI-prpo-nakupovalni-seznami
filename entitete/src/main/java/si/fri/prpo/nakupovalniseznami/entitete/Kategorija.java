package si.fri.prpo.nakupovalniseznami.entitete;

import javax.persistence.*;

@Entity(name = "Kategorija")
@NamedQueries(value =
        {
                @NamedQuery(name = "Kategorija.getAll", query = "SELECT k FROM Kategorija k"),
                @NamedQuery(name = "Kategorija.getByName", query = "SELECT k FROM Kategorija k WHERE k.name = :name"),
                @NamedQuery(name = "Kategorija.getByDescription", query = "SELECT k FROM Kategorija k WHERE k.description LIKE :description")

        })
public class Kategorija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
