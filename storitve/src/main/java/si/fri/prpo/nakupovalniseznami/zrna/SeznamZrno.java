package si.fri.prpo.nakupovalniseznami.zrna;

import si.fri.prpo.nakupovalniseznami.entitete.Seznam;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class SeznamZrno {

    private Logger log = Logger.getLogger(SeznamZrno.class.getName());

    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
    private EntityManager em;

    public List<Seznam> getAllLists() {

        List<Seznam> seznami = em.createNamedQuery("Seznam.getAll").getResultList();

        return seznami;
    }

}
