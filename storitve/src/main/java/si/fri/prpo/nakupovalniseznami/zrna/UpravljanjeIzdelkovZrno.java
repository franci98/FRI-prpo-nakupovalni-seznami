package si.fri.prpo.nakupovalniseznami.zrna;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.*;
import javax.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class UpravljanjeIzdelkovZrno {
    private Logger log = Logger.getLogger(UpravljanjeIzdelkovZrno.class.getName());

    private String idZrna;

    @PostConstruct
    private void init() {
        log.info("Inicializacija zrna " + UpravljanjeIzdelkovZrno.class.getSimpleName());

        // Initialize sources
    }

    @PreDestroy
    private void destroy() {
        log.info("Deinicializacija zrna " + UpravljanjeIzdelkovZrno.class.getSimpleName());

        // Deinitialize sources
    }

    @Inject
    private UporabnikZrno uporabnikZrno;

    @Inject
    private SeznamZrno seznamZrno;
}
