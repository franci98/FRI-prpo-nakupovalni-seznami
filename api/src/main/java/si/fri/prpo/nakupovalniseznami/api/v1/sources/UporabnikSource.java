package si.fri.prpo.nakupovalniseznami.api.v1.sources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;
import si.fri.prpo.nakupovalniseznami.zrna.UporabnikZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
@Path("uporabniki")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class UporabnikSource {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private UporabnikZrno uporabnikZrno;

    @GET
    public Response getUsers() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Uporabnik> uporabniki = uporabnikZrno.getAllUsers(query);
        return Response.ok(uporabniki).build();
    }

    /***
     * On link v1/uporabniki/{id}
     * where id connects to the method parameter
     * @param id
     * @return Response with the user
     */
    @GET
    @Path("{id}")
    public Response getUser(@PathParam("id") Integer id) {
        Uporabnik uporabnik = uporabnikZrno.get(id);

        if (uporabnik == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(uporabnik).build();
    }

    @POST
    public Response addUser(Uporabnik uporabnik) {
        return Response
                .status(Response.Status.CREATED)
                .entity(uporabnikZrno.create(uporabnik))
                .build();
    }

    @PUT
    @Path("{id}")
    public Response updateUser(@PathParam("id") Integer userId, Uporabnik uporabnik) {
        return Response
                .status(Response.Status.CREATED)
                .entity(uporabnikZrno.update(userId, uporabnik))
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") Integer userId) {
        return Response
                .status(Response.Status.OK)
                .entity(uporabnikZrno.delete(userId))
                .build();
    }
}
