package si.fri.prpo.nakupovalniseznami.zrna;

import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    public List<Uporabnik> getAllUsersWithCriteria() {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Uporabnik> q = cb.createQuery(Uporabnik.class);
        Root<Uporabnik> u = q.from(Uporabnik.class);
        q.select(u);

        TypedQuery<Uporabnik> query = em.createQuery(q);
        List<Uporabnik> results = query.getResultList();

        return results;
    }

}
