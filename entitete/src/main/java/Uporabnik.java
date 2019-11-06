import javax.persistence.*;
import java.util.Date;

@Entity(name = "uporabnik")
@NamedQueries(value =
        {
                @NamedQuery(name = "Uporabnik.getAll", query = "SELECT o FROM uporabnik o")
        })
public class Uporabnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ime;
    private String priimek;
    private String email;
    private String password;
    private Date join_date;
    private Date last_login;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
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

    public Date getJoinDate() {
        return join_date;
    }

    public void setJoinDate(Date join_date) {
        this.join_date = join_date;
    }

    public Date getLastLogin() {
        return last_login;
    }

    public void setLastLogin(Date last_login) {
        this.last_login = last_login;
    }
}