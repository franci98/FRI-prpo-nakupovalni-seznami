package si.fri.prpo.nakupovalniseznami.entitete;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "public.list")
@NamedQueries(value =
        {
                @NamedQuery(name = "Seznam.getAll", query = "SELECT s FROM Seznam s"),
                @NamedQuery(name = "Seznam.getByName", query = "SELECT s FROM Seznam s WHERE s.name = :name"),
                @NamedQuery(name = "Seznam.getLastModified", query = "SELECT s FROM Seznam s WHERE s.modified=(SELECT MAX(s2.modified) FROM Seznam s2)"),
                @NamedQuery(name = "Seznam.getByNameAndUser", query = "SELECT s FROM Seznam s WHERE s.user = :user AND s.name LIKE :name")
        })
public class Seznam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "list_id")
    private Integer id;

    private String name;

    @Temporal(TemporalType.DATE)
    private Date created;

    @Temporal(TemporalType.DATE)
    @Column(name = "modified")
    private Date modified;

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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
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
