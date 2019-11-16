package si.fri.prpo.nakupovalniseznami.entitete;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "public.list")
@NamedQueries(value =
        {
                @NamedQuery(name = "Seznam.getAll", query = "SELECT s FROM Seznam s"),
                @NamedQuery(name = "Seznam.getByUser", query = "SELECT s FROM Seznam s WHERE s.user.id = :user_id"),
                @NamedQuery(name = "Seznam.getLastModified", query = "SELECT s FROM Seznam s WHERE s.modified_date=(SELECT MAX(s2.modified_date) FROM Seznam s2)"),
        })
public class Seznam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "list_id")
    private Integer id;

    private String name;

    @Temporal(TemporalType.DATE)
    private Date created_date;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_modified")
    private Date modified_date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Uporabnik user;

    @OneToMany(mappedBy = "list")
    private List<Izdelek> items;

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

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getModified_date() {
        return modified_date;
    }

    public void setModified_date(Date modified_date) {
        this.modified_date = modified_date;
    }

    public Uporabnik getUser() {
        return user;
    }

    public void setUser(Uporabnik user) {
        this.user = user;
    }

    public List<Izdelek> getItems() {
        return items;
    }

    public void setItems(List<Izdelek> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return String.format("%s od %s", this.name, this.user.toString());
    }
}
