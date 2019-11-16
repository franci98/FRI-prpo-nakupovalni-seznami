package si.fri.prpo.nakupovalniseznami.dtos;

import java.time.Instant;

public class UporabnikDto {

    private String name;
    private String surname;
    private String email;
    private String password;
    private Instant joined;
    private Instant last_login;

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

    public Instant getJoined() {
        return joined;
    }

    public void setJoined(Instant joined) {
        this.joined = joined;
    }

    public Instant getLast_login() {
        return last_login;
    }

    public void setLast_login(Instant last_login) {
        this.last_login = last_login;
    }
}
