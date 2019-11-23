package si.fri.prpo.nakupovalniseznami.servlets;

import si.fri.prpo.nakupovalniseznami.dtos.IzdelekDto;
import si.fri.prpo.nakupovalniseznami.dtos.SeznamDto;
import si.fri.prpo.nakupovalniseznami.entitete.Izdelek;
import si.fri.prpo.nakupovalniseznami.entitete.Seznam;
import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;
import si.fri.prpo.nakupovalniseznami.zrna.*;
import si.fri.prpo.nakupovalniseznami.zrna.IzdelekZrno;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/servlet")
public class JPAServlet extends HttpServlet {

    @Inject
    private UporabnikZrno uporabnikZrno;
    @Inject
    private IzdelekZrno izdelekZrno;
    @Inject
    private SeznamZrno seznamZrno;
    @Inject
    private UpravljanjeSeznamovZrno upravljanjeSeznamovZrno;
    @Inject
    private UpravljanjeIzdelkovZrno upravljanjeIzdelkovZrno;

    private static final Logger log = Logger.getLogger(JPAServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType(("text/html; charset=UTF-8"));
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();

        // izpis uporabnikov na spletno stran
        this.izpisiUporabnike(writer);
        // Kreiranje uporabnika
        writer.append("<strong>Kreiramo novega uporabnika.</strong><br/>");
        Uporabnik uporabnik = new Uporabnik();
        uporabnik.setName("Novi");
        uporabnik.setSurname("Uporabnik");
        uporabnik.setEmail("novi@uporabnik.com");
        uporabnik.setPassword("uporabnik123");
        uporabnik = uporabnikZrno.create(uporabnik);
        // izpis uporabnikov na spletno stran
        this.izpisiUporabnike(writer);

        // Izpis seznamov
        this.izpisiSezname(writer);
        // Kreiramo seznam novega uporabnika
        writer.append("<strong>Kreiramo seznam novega uporabnika.</strong><br/>");
        SeznamDto seznamDto = new SeznamDto();
        seznamDto.setName("Za pico");
        seznamDto.setUserId(uporabnik.getId());
        Seznam seznam = upravljanjeSeznamovZrno.ustvariSeznam(seznamDto);
        // Zopet izpišemo
        this.izpisiSezname(writer);
        // Izpis seznamov določenega uporabnika
        writer.append("<br/>Seznami uporabnika " + uporabnik.toString() + "<br/>");
        List<Seznam> uporabnikovi = seznamZrno.getByUser(uporabnik.getId());
        uporabnikovi.stream().forEach(s -> writer.append(s.toString() + "<br/>"));

        // Izpis izdelkov
        this.izpisiIzdelke(writer);
        // Kreiramo izdelek
        writer.append("<br/><strong>Dodamo izdelek</strong><br/>");
        IzdelekDto izdelekDto = new IzdelekDto();
        izdelekDto.setSeznamId(seznam.getId());
        izdelekDto.setName("Šunka");
        izdelekDto.setDescription("50 dag");
        Izdelek izdelek = upravljanjeIzdelkovZrno.kreirajIzdelekSeznama(izdelekDto);
        // Zopet izpišemo
        this.izpisiIzdelke(writer);
        // Izpišemo izdelke seznama
        writer.append("<br/>Izdelki seznama " + seznam.toString() + "<br/>");
        List<Izdelek> izdelekList = upravljanjeIzdelkovZrno.pridobiIzdelkeSeznama(seznam.getId());
        izdelekList.stream().forEach(i -> writer.append(i.toString() + "<br/><br/>"));

        // Obkljukajmo izdelek
        writer.append("<br/><strong>Obkljukajmo izdelek</strong> <br/>");
        upravljanjeIzdelkovZrno.obkljukajIzdelek(izdelek);
        writer.append("<br/>Izdelki seznama " + seznam.toString() + "<br/>");
        izdelekList = upravljanjeIzdelkovZrno.pridobiIzdelkeSeznama(seznam.getId());
        izdelekList.stream().forEach(i -> writer.append(i.toString() + "<br/><br/>"));

        // Izbrišemo izdelek
        writer.append("<br/><strong>Izbrišemo izdelek</strong> <br/>");
        izdelekZrno.delete(izdelek.getId());
        this.izpisiIzdelke(writer);

    }

    private void izpisiUporabnike(PrintWriter writer) {
        writer.append("<br/><br/>Uporabniki:<br/>");
        uporabnikZrno.getAllUsers().stream().forEach(u -> writer.append(u.toString() + "<br/><br/>"));
    }

    private void izpisiSezname(PrintWriter writer) {
        List<Seznam> vsiSeznami = seznamZrno.getAllLists();
        writer.append("<br/><br/>Seznami:<br/>");
        vsiSeznami.stream().forEach(s -> writer.append(s.toString() + "<br/></br>"));
    }

    private void izpisiIzdelke(PrintWriter printWriter) {
        printWriter.append("<br/><br/>izdelki:<br/>");
        izdelekZrno.getAllItems().stream().forEach(i -> printWriter.append(i.toString() + "<br/><br/>"));
    }
}