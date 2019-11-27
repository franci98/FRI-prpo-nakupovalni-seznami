package si.fri.prpo.nakupovalniseznami.zrna;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.prpo.nakupovalniseznami.annotations.CountCalls;
import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class UporabnikZrno {

    private Logger log = Logger.getLogger(UporabnikZrno.class.getName());

    private String idZrna;

    @PostConstruct
    private void init() {
        idZrna = UUID.randomUUID().toString();

        log.info("Inicializacija zrna " + UporabnikZrno.class.getSimpleName() + "z ID: " + idZrna);

        // Initialize sources
    }

    @PreDestroy
    private void destroy() {
        log.info("Deinicializacija zrna " + UporabnikZrno.class.getSimpleName() + "z ID: " + idZrna);

        // Deinitialize sources
    }

    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
    private EntityManager em;

    @CountCalls
    public List<Uporabnik> getAllUsers(QueryParameters queryParameters) {
        List<Uporabnik> uporabniki = JPAUtils.queryEntities(em, Uporabnik.class, queryParameters);
        return uporabniki;
    }

    @CountCalls
    public Uporabnik get(int userId) {
        return em.find(Uporabnik.class, userId);
    }

    public List<Uporabnik> getByName(String name) {

        List<Uporabnik> uporabniki = em.createNamedQuery("Uporabnik.getByName").setParameter("name", name).getResultList();

        return uporabniki;
    }

    public List<Uporabnik> getByEmail(String email) {

        List<Uporabnik> uporabniki = em.createNamedQuery("Uporabnik.getByEmail").setParameter("email", email).getResultList();

        return uporabniki;
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

    @Transactional
    public Uporabnik create(Uporabnik u){
        if (u != null) {
            u.setJoined(new Date());
            em.persist(u);
        }

        return u;
    }

    @Transactional
    public Uporabnik update(int userId, Uporabnik uporabnik) {
        Uporabnik oldUporabnik = em.find(Uporabnik.class, userId);
        uporabnik.setId(oldUporabnik.getId());
        em.merge(uporabnik);
        return uporabnik;
    }

    @Transactional
    public Integer delete(int userId) {
        Uporabnik uporabnik = em.find(Uporabnik.class, userId);

        if (uporabnik != null)
            em.remove(uporabnik);

        return userId;
    }

}
