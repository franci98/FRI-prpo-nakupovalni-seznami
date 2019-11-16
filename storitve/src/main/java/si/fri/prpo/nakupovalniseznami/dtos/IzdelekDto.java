package si.fri.prpo.nakupovalniseznami.dtos;

import java.time.Instant;
import java.util.Date;

public class IzdelekDto {

    private Integer seznamId;
    private String name;
    private String description;
    private Instant created;
    private boolean checked;

    public Integer getSeznamId() {
        return seznamId;
    }

    public void setSeznamId(Integer seznamId) {
        this.seznamId = seznamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
