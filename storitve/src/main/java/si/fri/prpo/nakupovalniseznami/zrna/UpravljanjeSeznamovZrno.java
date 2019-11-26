package si.fri.prpo.nakupovalniseznami.zrna;

import si.fri.prpo.nakupovalniseznami.dtos.SeznamDto;
import si.fri.prpo.nakupovalniseznami.dtos.UporabnikDto;
import si.fri.prpo.nakupovalniseznami.entitete.Seznam;
import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped
public class UpravljanjeSeznamovZrno {
    private Logger log = Logger.getLogger(UpravljanjeSeznamovZrno.class.getName());

    private String idZrna;

    @PostConstruct
    private void init() {

        idZrna = UUID.randomUUID().toString();

        log.info("Inicializacija zrna " + UpravljanjeSeznamovZrno.class.getSimpleName() + "z ID: " + idZrna);

        // Initialize sources
    }

    @PreDestroy
    private void destroy() {
        log.info("Deinicializacija zrna " + UpravljanjeSeznamovZrno.class.getSimpleName() + "z ID: " + idZrna);

        // Deinitialize sources
    }

    @Inject
    private UporabnikZrno uporabnikZrno;

    @Inject
    private SeznamZrno seznamZrno;

    public Seznam ustvariSeznam (SeznamDto seznamDto) {

        Uporabnik uporabnik = uporabnikZrno.get(seznamDto.getUserId());

        if (uporabnik == null) {
            log.info("Nemorem ustvariti novega senama. Uporabni ne obstaja.");
            return null;
        }
        if(!preveriPolja(seznamDto)) {
            log.info("Nemorem ustvariti novega senama. Podatki niso veljavni.");
            return null;
        }

        Seznam seznam = new Seznam();
        seznam.setUser(uporabnik);
        seznam.setName(seznamDto.getName());
        seznam.setCreated(new Date());
        seznam.setModified(new Date());

        return seznamZrno.create(seznam);

    }

    public List<Seznam> poisciSeznamePoImenu (SeznamDto seznamDto) {

        Uporabnik uporabnik = uporabnikZrno.get(seznamDto.getUserId());

        if (uporabnik == null) {
            log.info("Uporabni ne obstaja.");
            return null;
        }
        if(!preveriPolja(seznamDto)) {
            log.info("Nemorem ustvariti novega senama. Podatki niso veljavni.");
            return null;
        }

        List<Seznam> najdeniSeznami = seznamZrno.getByNameAndUser(seznamDto.getName(), seznamDto.getUserId());

        if (!najdeniSeznami.isEmpty())
            return najdeniSeznami;

        log.info("Uporabnik " + uporabnik.getName() + " nima seznama s tem imenom");
        return null;

    }

    public boolean preveriPolja (SeznamDto seznamDto) {

        log.info("Preverjanje polj seznama...");

        if (seznamDto.getUserId() == null) {
            log.info("UserId polje je prazno.");
            return false;
        }
        else {
            Pattern namePattern = Pattern.compile("^[a-zA-Z0-9 ]+$");
            Matcher nameCheck = namePattern.matcher(seznamDto.getName());
            if (seznamDto.getName() == null) {
                log.info("Name polje je prazno.");
                return false;
            }
            else {
                if (!nameCheck.find()) {
                    log.info("Name polje vsebuje prepovedane znake.");
                    return false;
                }
                else {
                    if (seznamDto.getName().length() > 50) {
                        log.info("Name polje vsebuje prevec znakov.");
                        return false;
                    }
                    else {
                        log.info("Preverjanje uspesno. Polja so ustrezna.");
                        return true;
                    }
                }
            }
        }

    }



}
