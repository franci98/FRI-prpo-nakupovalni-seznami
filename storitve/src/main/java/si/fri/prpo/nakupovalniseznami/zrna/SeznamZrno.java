package si.fri.prpo.nakupovalniseznami.zrna;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.prpo.nakupovalniseznami.entitete.Izdelek;
import si.fri.prpo.nakupovalniseznami.entitete.Seznam;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.QueryParam;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class SeznamZrno {

    private Logger log = Logger.getLogger(SeznamZrno.class.getName());

    private String idZrna;

    @PostConstruct
    private void init() {
        idZrna = UUID.randomUUID().toString();

        log.info("Inicializacija zrna " + SeznamZrno.class.getSimpleName() + "z ID:" + idZrna);

        // Initialize sources
    }

    @PreDestroy
    private void destroy() {
        log.info("Deinicializacija zrna " + SeznamZrno.class.getSimpleName());

        // Deinitialize sources
    }

    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
    private EntityManager em;

    public List<Seznam> getAllLists(QueryParameters query) {

        List<Seznam> seznami = JPAUtils.queryEntities(em, Seznam.class, query);

        return seznami;
    }

    public List<Seznam> getByNameAndUser(String name, int userId) {

        TypedQuery<Seznam> namedQuery = em.createNamedQuery("Seznam.getByNameAndUser", Seznam.class).setParameter("name", "%" + name + "%").setParameter("userId", userId);
        List<Seznam> seznami = namedQuery.getResultList();

        return seznami;
    }

    public List<Seznam> getByUser(int userId) {

        TypedQuery<Seznam> namedQuery = em.createNamedQuery("Seznam.getByUserId", Seznam.class).setParameter("id", userId);
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
    public Seznam update(int seznamId, Seznam seznam) {
        Seznam oldSeznam = em.find(Seznam.class, seznamId);
        seznam.setId(oldSeznam.getId());
        seznam.setCreated(oldSeznam.getCreated());
        seznam.setModified(new Date());
        em.merge(seznam);
        return seznam;
    }


    @Transactional
    public Integer delete(int seznamId) {
        Seznam seznam = em.find(Seznam.class, seznamId);

        if (seznam != null) {
            for (Izdelek izdelek : seznam.getItems()) {
                if (izdelek != null)
                    em.remove(izdelek);
            }

            em.remove(seznam);
        }

        return seznamId;
    }


}
