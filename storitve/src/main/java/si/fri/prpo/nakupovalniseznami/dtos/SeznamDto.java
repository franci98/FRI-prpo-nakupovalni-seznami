package si.fri.prpo.nakupovalniseznami.dtos;

import java.io.Serializable;
import java.util.Date;

public class SeznamDto implements Serializable {

    private Integer userId;
    private String name;
    private Date created;
    private Date modified;

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
}
