package si.fri.prpo.nakupovalniseznami.zrna;

import si.fri.prpo.nakupovalniseznami.entitete.Izdelek;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class IzdelekZrno {

    private Logger log = Logger.getLogger(IzdelekZrno.class.getName());

    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
    private EntityManager em;

    public List<Izdelek> getAllItems() {

        List<Izdelek> izdelki = em.createNamedQuery("Izdelek.getAll").getResultList();

        return izdelki;
    }

}