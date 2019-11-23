package si.fri.prpo.nakupovalniseznami.entitete;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "public.item")
@NamedQueries(value =
        {
                @NamedQuery(name = "Izdelek.getAll", query = "SELECT i FROM Izdelek i"),
                @NamedQuery(name = "Izdelek.getByName", query = "SELECT i FROM Izdelek i WHERE i.name = :name"),
                @NamedQuery(name = "Izdelek.getByListId", query = "SELECT i FROM Izdelek i WHERE i.list.id = :id"),
                @NamedQuery(name = "Izdelek.getLastCreated", query = "SELECT i FROM Izdelek i WHERE i.created=(SELECT MAX(i2.created) FROM Izdelek i2)"),
        })
public class Izdelek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Integer id;

    private String name;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date created;

    private boolean checked;

    @JsonbTransient
    @ManyToOne
    @JoinColumn(name = "list_id")
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Seznam getList() {
        return list;
    }

    public void setList(Seznam list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return String.format("%s - %s %tF %b", this.name, this.description, this.created, this.checked);
    }
}
