package si.fri.prpo.nakupovalniseznami.dtos;

import java.util.Date;

public class IzdelekDto {

    private Integer seznamId;
    private String name;
    private String description;
    private Date created_date;
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

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
