package si.fri.prpo.nakupovalniseznami.zrna;

import si.fri.prpo.nakupovalniseznami.dtos.IzdelekDto;
import si.fri.prpo.nakupovalniseznami.dtos.SeznamDto;
import si.fri.prpo.nakupovalniseznami.entitete.Izdelek;
import si.fri.prpo.nakupovalniseznami.entitete.Seznam;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.*;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class UpravljanjeIzdelkovZrno {
    private Logger log = Logger.getLogger(UpravljanjeIzdelkovZrno.class.getName());

    private String idZrna;

    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
    private EntityManager em;

    @PostConstruct
    private void init() {
        idZrna = UUID.randomUUID().toString();

        log.info("Inicializacija zrna " + UpravljanjeIzdelkovZrno.class.getSimpleName() + "z ID:" + idZrna);

        // Initialize sources
    }

    @PreDestroy
    private void destroy() {
        log.info("Deinicializacija zrna " + UpravljanjeIzdelkovZrno.class.getSimpleName());

        // Deinitialize sources
    }

    @Inject
    private IzdelekZrno izdelekZrno;

    @Inject
    private SeznamZrno seznamZrno;

    public Izdelek kreirajIzdelekSeznama(IzdelekDto izdelekDto) {
        Seznam seznam = seznamZrno.get(izdelekDto.getSeznamId());
        List<Izdelek> izdelkiSeznama = seznam.getItems();

        Izdelek izdelek = new Izdelek();
        izdelek.setCreated(new Date());
        izdelek.setName(izdelekDto.getName());
        izdelek.setDescription(izdelekDto.getDescription());
        izdelek.setChecked(false);
        izdelek.setList(seznam);

        izdelkiSeznama.add(izdelek);
        seznam.setItems(izdelkiSeznama);

        Seznam updatedSeznam = seznamZrno.update(seznam.getId(), seznam);

        return izdelekZrno.create(izdelek);

    }

    public List<Izdelek> pridobiIzdelkeSeznama(Integer seznamId) {
        TypedQuery<Izdelek> query = em.createNamedQuery("Izdelek.getByListId", Izdelek.class).setParameter("id", seznamId);
        return query.getResultList();
    }

    public Izdelek obkljukajIzdelek(Izdelek izdelek) {
        izdelek.setChecked(true);
        izdelekZrno.update(izdelek.getId(), izdelek);
        return izdelek;
    }

}
