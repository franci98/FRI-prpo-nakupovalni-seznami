package si.fri.prpo.nakupovalniseznami.storitve;

import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class UporabnikZrno {

    @PersistenceContext(unitName = "lokacijski-opomniki-jpa")
    private EntityManager em;

    public List<Uporabnik> getUporabniki() {

        // implementacija
        return null;
    }

}
