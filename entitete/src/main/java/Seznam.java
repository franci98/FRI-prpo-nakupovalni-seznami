import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "seznam")
@NamedQueries(value =
        {
                @NamedQuery(name = "seznam.getAll", query = "SELECT s FROM seznam s")
        })
public class Seznam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer list_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private String list_name;
    @Temporal(TemporalType.DATE)
    private Date created_date;
    @Temporal(TemporalType.DATE)
    private Date last_modified;

    public Integer getList_id() {
        return list_id;
    }

    public void setList_id(Integer list_id) {
        this.list_id = list_id;
    }

    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(Date last_modified) {
        this.last_modified = last_modified;
    }
}
