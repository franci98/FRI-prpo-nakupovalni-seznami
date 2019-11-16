package si.fri.prpo.nakupovalniseznami.zrna;

import si.fri.prpo.nakupovalniseznami.dtos.SeznamDto;
import si.fri.prpo.nakupovalniseznami.entitete.Seznam;
import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class UpravljanjeSeznamovZrno {
    private Logger log = Logger.getLogger(UpravljanjeSeznamovZrno.class.getName());

    private String idZrna;

    @PostConstruct
    private void init() {
        log.info("Inicializacija zrna " + UpravljanjeSeznamovZrno.class.getSimpleName());

        // Initialize sources
    }

    @PreDestroy
    private void destroy() {
        log.info("Deinicializacija zrna " + UpravljanjeSeznamovZrno.class.getSimpleName());

        // Deinitialize sources
    }

    @Inject
    private UporabnikZrno uporabnikZrno;

    @Inject
    private SeznamZrno seznamZrno;

    @Transactional
    public Seznam ustvariNakupovalniSeznam (SeznamDto seznamDto) {
        Uporabnik uporabnik = uporabnikZrno.get(seznamDto.getUserId());

        if (uporabnik == null) {
            log.info("Nemorem ustvariti nakupovalnega seznama. Uporabnik ne obstaja.");
            return null;
        }

        Seznam seznam = new Seznam();
        seznam.setUser(uporabnik);
        seznam.setName(seznamDto.getName());
        seznam.setCreated_date(new Date());
        seznam.setModified_date(new Date());

        return seznamZrno.create(seznam);
    }

    @Transactional
    public void posodobiSeznam (Integer seznamId, SeznamDto seznamDto) {
        Seznam seznam = seznamZrno.get(seznamId);
        seznam.setName(seznamDto.getName());
        seznam.setModified_date(new Date());

        seznamZrno.update(seznamId, seznam);
    }

    @Transactional
    public Integer odstraniSeznam(Integer seznamId) {
        return seznamZrno.delete(seznamId);
    }

    @Transactional
    public List<Seznam> vsiSeznamiUporabnika (Integer uporabnikId) {
        Uporabnik uporabnik = uporabnikZrno.get(uporabnikId);

        if (uporabnik == null) {
            log.info("Ne morem najti seznamov uporabnika. Uporabnik ne obstaja.");
            return null;
        }

        return seznamZrno.getAllListsForUser(uporabnik);
    }


}
