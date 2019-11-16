package si.fri.prpo.nakupovalniseznami.zrna;

import si.fri.prpo.nakupovalniseznami.entitete.Izdelek;
import si.fri.prpo.nakupovalniseznami.entitete.Seznam;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class SeznamZrno {

    private Logger log = Logger.getLogger(SeznamZrno.class.getName());

    private String idZrna;

    @PostConstruct
    private void init() {
        log.info("Inicializacija zrna " + SeznamZrno.class.getSimpleName());

        // Initialize sources
    }

    @PreDestroy
    private void destroy() {
        log.info("Deinicializacija zrna " + SeznamZrno.class.getSimpleName());

        // Deinitialize sources
    }

    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
    private EntityManager em;

    public List<Seznam> getAllLists() {

        TypedQuery<Seznam> namedQuery = em.createNamedQuery("Seznam.getAll", Seznam.class);
        List<Seznam> seznami = namedQuery.getResultList();

        return seznami;
    }

    public List<Seznam> getByNameAndUser(String name, int userId) {

        TypedQuery<Seznam> namedQuery = em.createNamedQuery("Seznam.getByNameAndUser", Seznam.class).setParameter("name", name).setParameter("user", userId);
        List<Seznam> seznami = namedQuery.getResultList();

        return seznami;
    }

    public Seznam get(int seznamId) {
        return em.find(Seznam.class, seznamId);
    }

    @Transactional
    public Seznam create(Seznam s) {
        if (s != null)
            em.persist(s);

        return s;
    }

    @Transactional
    public void update(int seznamId, Seznam seznam) {
        Seznam oldSeznam = em.find(Seznam.class, seznamId);
        seznam.setId(oldSeznam.getId());
        em.merge(seznam);
    }


    @Transactional
    public Integer delete(int seznamId) {
        Seznam uporabnik = em.find(Seznam.class, seznamId);

        if (uporabnik != null)
            em.remove(uporabnik);

        return seznamId;
    }


}
