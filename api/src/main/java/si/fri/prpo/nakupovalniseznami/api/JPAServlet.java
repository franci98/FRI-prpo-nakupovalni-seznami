package si.fri.prpo.nakupovalniseznami.api;

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
    private KategorijaZrno kategorijaZrno;

    private static final Logger log = Logger.getLogger(JPAServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType(("text/html; charset=UTF-8"));
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();

        // List<Uporabnik> uporabniki = uporabnikZrno.getAllUsers();

        // izpis uporabnikov na spletno stran
        writer.append("<br/><br/>Uporabniki:<br/>");
        uporabnikZrno.getAllUsers().stream().forEach(u -> writer.append(u.toString() + "<br/><br/>"));

        // izpis izdelkov na spletno stran
        writer.append("<br/><br/>izdelki:<br/>");
        izdelekZrno.getAllItems().stream().forEach(i -> writer.append(i.toString() + "<br/><br/>"));

        // izpis seznamov na spletno stran
        writer.append("<br/><br/>seznami:<br/>");
        seznamZrno.getAllLists().stream().forEach(u -> writer.append(u.toString() + "<br/><br/>"));

        // izpis kategorij na spletno stran
        writer.append("<br/><br/>kategorije:<br/>");
        kategorijaZrno.getAllCategories().stream().forEach(u -> writer.append(u.toString() + "<br/><br/>"));


        // izpis uporabnikov na spletno stran z criteriaAPI
        writer.append("<br/><br/>Uporabniki z criteriaAPI:<br/>");
        uporabnikZrno.getAllUsersWithCriteria().stream().forEach(u -> writer.append(u.toString() + "<br/><br/>"));

    }
}