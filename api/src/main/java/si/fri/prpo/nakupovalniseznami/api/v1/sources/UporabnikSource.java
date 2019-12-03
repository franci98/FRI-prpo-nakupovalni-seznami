package si.fri.prpo.nakupovalniseznami.api.v1.sources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.eclipse.jetty.server.Authentication;
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
    @Operation(summary = "Pridobi vse uporabnike",
            description = "Vrne seznam vseh uporabnikov, ki so v bazi.",
            tags = "uporabniki"
    )
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
    @Operation(
            summary = "Pridobi uporabnika z določenim ID-jem",
            tags = "uporabniki",
            responses = {
                    @ApiResponse(description = "Uporabnik",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Uporabnik.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Uporabnika s podanim identifikatorjem nismo našli.")
            },
            description = "V bazi najde in vrne uporabnika s točno določenim ID-jem-"
    )
    public Response getUser(
            @Parameter(description = "Identifikator uporabnika", required = true) @PathParam("id") Integer id
    ) {
        Uporabnik uporabnik = uporabnikZrno.get(id);

        if (uporabnik == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(uporabnik).build();
    }

    @POST
    @Operation(
            summary = "Ustvari novega uporabnika",
            tags = "uporabniki",
            responses = {
                    @ApiResponse(
                            description = "Novi uporabnik",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Uporabnik.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Podatki niso pravilno izpolnjeni.")
            },
            description = "Ustvari novega uporabnika s podanimi podatki"
    )
    public Response addUser(
            @RequestBody(
                    description = "Objekt uporabnika z vsemi pripadajočimi podatki", required = true,
                    content = @Content(schema = @Schema(implementation = Uporabnik.class))
            ) Uporabnik uporabnik) {
        return Response
                .status(Response.Status.CREATED)
                .entity(uporabnikZrno.create(uporabnik))
                .build();
    }

    @PUT
    @Path("{id}")
    @Operation(
            summary = "Posodobi uporabnika",
            tags = "uporabniki",
            responses = {
                    @ApiResponse(
                            description = "Posodobljeni uporabnik",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Uporabnik.class)
                            )
                    )
            },
            description = "Posodobi uporabnika s podanim identifikatorjem."
    )
    public Response updateUser(
            @Parameter(description = "Identifikator uporabnika", required = true) @PathParam("id") Integer userId,
            @RequestBody(
                    description = "Posodobljeni objekt uporabnika z vsemi pripadajočimi podatki", required = true,
                    content = @Content(schema = @Schema(implementation = Uporabnik.class))
            ) Uporabnik uporabnik) {
        return Response
                .status(Response.Status.CREATED)
                .entity(uporabnikZrno.update(userId, uporabnik))
                .build();
    }

    @DELETE
    @Path("{id}")
    @Operation(
            summary = "Izbriši uporabnika",
            tags = "uporabniki",
            responses = {
                    @ApiResponse(
                            description = "Identifikator izbrisanega uporabnika",
                            content = @Content(mediaType = "text/plain")
                    )
            },
            description = "Izbriše uporabnika s podanim identifikatorjem"
    )
    public Response deleteUser(
            @Parameter(description = "Identifikator uporabnika", required = true) @PathParam("id") Integer userId
    ) {
        return Response
                .status(Response.Status.OK)
                .entity(uporabnikZrno.delete(userId))
                .build();
    }
}
