package si.fri.prpo.nakupovalniseznami.zrna;

import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class UporabnikZrno {

    private Logger log = Logger.getLogger(UporabnikZrno.class.getName());

    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
    private EntityManager em;

    public List<Uporabnik> getAllUsers() {

        List<Uporabnik> uporabniki =  em.createNamedQuery("Uporabnik.getAll").getResultList();

        return uporabniki;
    }

    public List<Uporabnik> getByName(String name) {

        List<Uporabnik> uporabniki = em.createNamedQuery("Uporabnik.getByName").setParameter("name", name).getResultList();

        return uporabniki;
    }

    public List<Uporabnik> getByEmail(String email) {

        List<Uporabnik> uporabniki = em.createNamedQuery("Uporabnik.getByEmail").setParameter("email", email).getResultList();

        return uporabniki;
    }

    public Uporabnik getLastLogined() {

        return (Uporabnik) em.createNamedQuery("Uporabnik.getLastLogined").getSingleResult();
    }

}
