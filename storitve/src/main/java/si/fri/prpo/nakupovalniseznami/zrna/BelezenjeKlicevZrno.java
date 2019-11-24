package si.fri.prpo.nakupovalniseznami.zrna;

import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

@ApplicationScoped
public class BelezenjeKlicevZrno {
    private Integer steviloKlicev = 0;

    private Logger logger = Logger.getLogger(BelezenjeKlicevZrno.class.getSimpleName());
    public void zabeleziKlic() {
        steviloKlicev++;

        logger.info("Klici so bili povecani na " + steviloKlicev);
    }
}
