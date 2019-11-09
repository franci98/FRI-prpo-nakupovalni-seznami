package si.fri.prpo.nakupovalniseznami.zrna;

import si.fri.prpo.nakupovalniseznami.entitete.Kategorija;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class KategorijaZrno {

    private Logger log = Logger.getLogger(KategorijaZrno.class.getName());

    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
    private EntityManager em;

    public List<Kategorija> getAllCategories() {

        List<Kategorija> kategorije = em.createNamedQuery("Kategorija.getAll").getResultList();

        return kategorije;
    }

}