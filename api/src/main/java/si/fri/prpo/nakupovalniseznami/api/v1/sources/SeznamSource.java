package si.fri.prpo.nakupovalniseznami.api.v1.sources;

import si.fri.prpo.nakupovalniseznami.dtos.SeznamDto;
import si.fri.prpo.nakupovalniseznami.entitete.Seznam;
import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;
import si.fri.prpo.nakupovalniseznami.zrna.IzdelekZrno;
import si.fri.prpo.nakupovalniseznami.zrna.SeznamZrno;
import si.fri.prpo.nakupovalniseznami.zrna.UpravljanjeSeznamovZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("seznami")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class SeznamSource {

    @Inject
    private SeznamZrno seznamZrno;
    @Inject
    private UpravljanjeSeznamovZrno upravljanjeSeznamovZrno;

    @GET
    public Response getLists() {
        return Response.ok(seznamZrno.getAllLists()).build();
    }

    /***
     * On link v1/seznami/{id}
     * where id connects to the method parameter
     * @param id
     * @return Response with the list
     */
    @GET
    @Path("{id}")
    public Response getList(@PathParam("id") Integer id) {
        Seznam seznam = seznamZrno.get(id);

        if (seznam == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(seznam).build();
    }

    @POST
    public Response addList(SeznamDto seznamDto) {
        return Response
                .status(Response.Status.CREATED)
                .entity(upravljanjeSeznamovZrno.ustvariSeznam(seznamDto))
                .build();
    }

    @PUT
    @Path("{id}")
    public Response updateList(@PathParam("id") Integer seznamId, Seznam seznam) {
        return Response
                .status(Response.Status.CREATED)
                .entity(seznamZrno.update(seznamId, seznam))
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteList(@PathParam("id") Integer seznamId) {
        return Response
                .status(Response.Status.OK)
                .entity(seznamZrno.delete(seznamId))
                .build();
    }

    @GET
    @Path("uporabniki/{id}")
    public Response getListsFromUser(@PathParam("id") Integer userId) {
        return Response
                .status(Response.Status.OK)
                .entity(upravljanjeSeznamovZrno.pridobiSeznameUporabnika(userId))
                .build();
    }

    @GET
    @Path("/fromUserByName")
    public Response getListsWithName(SeznamDto seznamDto) {
        return Response
                .status(Response.Status.OK)
                .entity(upravljanjeSeznamovZrno.poisciSeznamePoImenu(seznamDto))
                .build();
    }

}

