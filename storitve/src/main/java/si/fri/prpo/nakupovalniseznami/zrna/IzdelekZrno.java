package si.fri.prpo.nakupovalniseznami.zrna;

import si.fri.prpo.nakupovalniseznami.entitete.Izdelek;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RequestScoped
public class IzdelekZrno {

    private String idZrna;

    @PostConstruct
    private void init() {
        idZrna = UUID.randomUUID().toString();
        log.info("Inicializacija zrna " + IzdelekZrno.class.getName() + "z ID:" + idZrna);
    }

    @PreDestroy
    private void destroy() {
        log.info("Deinicializacija zrna" + IzdelekZrno.class.getName());
    }

    private Logger log = Logger.getLogger(IzdelekZrno.class.getName());

    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
    private EntityManager em;

    public List<Izdelek> getAllItems() {

        TypedQuery<Izdelek> namedQuery = em.createNamedQuery("Izdelek.getAll", Izdelek.class);
        List<Izdelek> izdelki = namedQuery.getResultList();

        return izdelki;
    }

    @Transactional
    public Izdelek create(Izdelek izdelek) {

        if (izdelek != null) {
            em.persist(izdelek);
        }

        return izdelek;
    }

    @Transactional
    public Izdelek get(int id) {

        Izdelek i = em.find(Izdelek.class, id);

        return i;
    }

    @Transactional
    public void update(int id, Izdelek izdelek) {

        Izdelek i = em.find(Izdelek.class, id);

        izdelek.setId(i.getId());
        em.merge(izdelek);
    }

    @Transactional
    public int delete(int id) {

        Izdelek i = em.find(Izdelek.class, id);

        if (i != null) {
            em.remove(i);
        }

        return id;
    }

}