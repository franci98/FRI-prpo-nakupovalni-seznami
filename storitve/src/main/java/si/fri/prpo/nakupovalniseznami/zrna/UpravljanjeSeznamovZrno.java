package si.fri.prpo.nakupovalniseznami.zrna;

import si.fri.prpo.nakupovalniseznami.entitete.Seznam;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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

    public Seznam ustvariNakupovalniSeznam () {

        return null;
    }

}
