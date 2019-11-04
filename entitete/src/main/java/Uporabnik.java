import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "uporabnik")
@NamedQueries(value =
        {
                @NamedQuery(name = "Uporabnik.getAll", query = "SELECT o FROM Uporabnik o")
        })
public class Uporabnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;
    private String password;
    private LocalDateTime join_date;
    private LocalDateTime last_login;

}
