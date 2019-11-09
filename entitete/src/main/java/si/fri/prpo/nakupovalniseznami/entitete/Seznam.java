package si.fri.prpo.nakupovalniseznami.entitete;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "Seznam")
@NamedQueries(value =
        {
                @NamedQuery(name = "Seznam.getAll", query = "SELECT s FROM Seznam s"),
                @NamedQuery(name = "Seznam.getByName", query = "SELECT s FROM Seznam s WHERE s.name = :name"),
                @NamedQuery(name = "Seznam.getLastModified", query = "SELECT s FROM Seznam s WHERE s.modified_date=(SELECT MAX(s.modified_date) FROM s)"),
                @NamedQuery(name = "Seznam.getByUser", query = "SELECT s FROM Seznam s WHERE s.user = :user"),
                //@NamedQuery(name = "Seznam.getByCategory", query = "SELECT s FROM Seznam s WHERE s.category_id = (SELECT k.id FROM Kategorija k WHERE k.name LIKE :category_name)")
                @NamedQuery(name = "Seznam.getByCategory", query = "SELECT s FROM Seznam s WHERE s.category LIKE :category")
        })
public class Seznam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Temporal(TemporalType.DATE)
    private Date created_date;

    @Temporal(TemporalType.DATE)
    private Date modified_date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Uporabnik user;

    @OneToMany(mappedBy = "list")
    private List<Izdelek> items;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "kategorija_seznama",
            joinColumns = @JoinColumn(name = "list_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Kategorija> categories;

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

    public List<Kategorija> getCategories() {
        return categories;
    }

    public void setCategories(List<Kategorija> categories) {
        this.categories = categories;
    }
}
