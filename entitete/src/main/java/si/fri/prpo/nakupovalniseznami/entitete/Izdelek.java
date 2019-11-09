package si.fri.prpo.nakupovalniseznami.entitete;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "izdelek")
@NamedQueries(value =
        {
                @NamedQuery(name = "izdelek.getAll", query = "SELECT i FROM Izdelek i"),
                @NamedQuery(name = "izdelek.getByName", query = "SELECT i FROM Izdelek i WHERE i.name = :name"),
                @NamedQuery(name = "izdelek.getLastCreated", query = "SELECT i FROM Izdelek i WHERE i.created_date=(SELECT MAX(i.created_date) FROM i)"),
                @NamedQuery(name = "izdelek.getByDescription", query = "SELECT i FROM Izdelek i WHERE i.description LIKE :description")
        })
public class Izdelek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer item_id;

    @ManyToOne
    @JoinColumn(name = "list_id")
    private Seznam list;

    private String name;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date created_date;

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public Seznam getList() {
        return list;
    }

    public void setList(Seznam list) {
        this.list = list;
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
}
