package si.fri.prpo.nakupovalniseznami.dtos;
import java.time.Instant;
import java.util.Date;

public class SeznamDto {

    private Integer userId;
    private String name;
    private Instant created;
    private Instant modified;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }
}
