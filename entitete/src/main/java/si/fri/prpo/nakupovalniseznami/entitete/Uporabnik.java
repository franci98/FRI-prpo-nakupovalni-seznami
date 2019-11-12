package si.fri.prpo.nakupovalniseznami.entitete;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "public.user")
@NamedQueries(value =
        {
                @NamedQuery(name = "Uporabnik.getAll", query = "SELECT u FROM Uporabnik u"),

        })
public class Uporabnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    private String name;
    private String surname;
    private String email;
    private String password;
    @Temporal(TemporalType.DATE)
    private Date join_date;
    @Temporal(TemporalType.DATE)
    private Date last_login;

    @OneToMany(mappedBy = "user")
    private List<Seznam> lists;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getJoin_date() {
        return join_date;
    }

    public void setJoin_date(Date join_date) {
        this.join_date = join_date;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public List<Seznam> getLists() {
        return lists;
    }

    public void setLists(List<Seznam> lists) {
        this.lists = lists;
    }

    @Override
    public String toString() {
        return String.format("%s 5s, %s", this.name, this.surname, this.email);
    }
}
