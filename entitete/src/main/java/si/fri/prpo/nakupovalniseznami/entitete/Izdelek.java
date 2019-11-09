package si.fri.prpo.nakupovalniseznami.entitete;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Izdelek")
@NamedQueries(value =
        {
                @NamedQuery(name = "Izdelek.getAll", query = "SELECT i FROM Izdelek i"),
                @NamedQuery(name = "Izdelek.getByName", query = "SELECT i FROM Izdelek i WHERE i.name = :name"),
                @NamedQuery(name = "Izdelek.getLastCreated", query = "SELECT i FROM Izdelek i WHERE i.created_date=(SELECT MAX(i.created_date) FROM i)"),
                @NamedQuery(name = "Izdelek.getByDescription", query = "SELECT i FROM Izdelek i WHERE i.description LIKE :description")
        })
public class Izdelek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date created_date;

    @ManyToOne
    private Seznam list;

    // Getters and Setters

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

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Seznam getList() {
        return list;
    }

    public void setList(Seznam list) {
        this.list = list;
    }
}
