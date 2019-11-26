package si.fri.prpo.nakupovalniseznami.api.v1.sources;

import si.fri.prpo.nakupovalniseznami.dtos.IzdelekDto;
import si.fri.prpo.nakupovalniseznami.dtos.SeznamDto;
import si.fri.prpo.nakupovalniseznami.entitete.Izdelek;
import si.fri.prpo.nakupovalniseznami.entitete.Seznam;
import si.fri.prpo.nakupovalniseznami.zrna.IzdelekZrno;
import si.fri.prpo.nakupovalniseznami.zrna.SeznamZrno;
import si.fri.prpo.nakupovalniseznami.zrna.UpravljanjeIzdelkovZrno;
import si.fri.prpo.nakupovalniseznami.zrna.UpravljanjeSeznamovZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("izdelki")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class IzdelekSource {

    @Inject
    private IzdelekZrno izdelekZrno;
    @Inject
    private UpravljanjeIzdelkovZrno upravljanjeIzdelkovZrno;

    @GET
    public Response getItems() {
        return Response.ok(izdelekZrno.getAllItems()).build();
    }

    /***
     * On link v1/izdelki/{id}
     * where id connects to the method parameter
     * @param id
     * @return Response with the item
     */
    @GET
    @Path("{id}")
    public Response getItem(@PathParam("id") Integer id) {
        Izdelek izdelek = izdelekZrno.get(id);

        if (izdelek == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(izdelek).build();
    }

    @POST
    public Response addItem(IzdelekDto izdelekDto) {
        return Response
                .status(Response.Status.CREATED)
                .entity(upravljanjeIzdelkovZrno.kreirajIzdelekSeznama(izdelekDto))
                .build();
    }

    @PUT
    @Path("{id}")
    public Response updateItem(@PathParam("id") Integer itemId, Izdelek izdelek) {
        return Response
                .status(Response.Status.CREATED)
                .entity(izdelekZrno.update(itemId, izdelek))
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteItem(@PathParam("id") Integer itemId) {
        return Response
                .status(Response.Status.OK)
                .entity(izdelekZrno.delete(itemId))
                .build();
    }

    @GET
    @Path("/seznami/{id}")
    public Response getItemsFromList(@PathParam("id") Integer seznamId) {
        return Response
                .status(Response.Status.OK)
                .entity(upravljanjeIzdelkovZrno.pridobiIzdelkeSeznama(seznamId))
                .build();
    }

}

