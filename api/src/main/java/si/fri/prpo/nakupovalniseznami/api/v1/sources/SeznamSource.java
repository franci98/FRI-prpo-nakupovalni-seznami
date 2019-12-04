package si.fri.prpo.nakupovalniseznami.api.v1.sources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import si.fri.prpo.nakupovalniseznami.dtos.SeznamDto;
import si.fri.prpo.nakupovalniseznami.entitete.Seznam;
import si.fri.prpo.nakupovalniseznami.zrna.SeznamZrno;
import si.fri.prpo.nakupovalniseznami.zrna.UpravljanjeSeznamovZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
@Path("seznami")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class SeznamSource {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private SeznamZrno seznamZrno;
    @Inject
    private UpravljanjeSeznamovZrno upravljanjeSeznamovZrno;

    @GET
    @Operation(summary = "Pridobi vse sezname",
            description = "Vrne seznam vseh seznamov, ki so v bazi.",
            tags = "seznami"
    )
    public Response getLists() {

        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Seznam> seznami = seznamZrno.getAllLists(query);

        return Response.ok(seznami).build();
    }

    /***
     * On link v1/seznami/{id}
     * where id connects to the method parameter
     * @param id
     * @return Response with the list
     */
    @GET
    @Path("{id}")
    @Operation(
            summary = "Pridobi seznam z določenim ID-jem",
            tags = "seznami",
            responses = {
                    @ApiResponse(description = "Seznam",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Seznam.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Seznama s podanim identifikatorjem nismo našli.")
            },
            description = "V bazi najde in vrne seznam s točno določenim ID-jem-"
    )
    public Response getList(@PathParam("id") Integer id) {
        Seznam seznam = seznamZrno.get(id);

        if (seznam == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(seznam).build();
    }

    @POST
    @Operation(
            summary = "Ustvari nov seznam",
            tags = "seznami",
            responses = {
                    @ApiResponse(
                            description = "Nov seznam",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Seznam.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Podatki niso pravilno izpolnjeni.")
            },
            description = "Ustvari nov seznam s podanimi podatki"
    )
    public Response addList(SeznamDto seznamDto) {
        return Response
                .status(Response.Status.CREATED)
                .entity(upravljanjeSeznamovZrno.ustvariSeznam(seznamDto))
                .build();
    }

    @PUT
    @Path("{id}")
    @Operation(
            summary = "Posodobi seznam",
            tags = "seznami",
            responses = {
                    @ApiResponse(
                            description = "Posodobljeni seznami",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Seznam.class)
                            )
                    )
            },
            description = "Posodobi seznam s podanim identifikatorjem."
    )
    public Response updateList(@PathParam("id") Integer seznamId, Seznam seznam) {
        return Response
                .status(Response.Status.CREATED)
                .entity(seznamZrno.update(seznamId, seznam))
                .build();
    }

    @DELETE
    @Path("{id}")
    @Operation(
            summary = "Izbriši seznam",
            tags = "seznami",
            responses = {
                    @ApiResponse(
                            description = "Identifikator izbrisanega seznama",
                            content = @Content(mediaType = "text/plain")
                    )
            },
            description = "Izbriše seznam s podanim identifikatorjem"
    )
    public Response deleteList(@PathParam("id") Integer seznamId) {
        return Response
                .status(Response.Status.OK)
                .entity(seznamZrno.delete(seznamId))
                .build();
    }

    @GET
    @Path("uporabniki/{id}")
    @Operation(
            summary = "Pridobi sezname od uporabnika",
            tags = "seznami",
            responses = {
                    @ApiResponse(
                            description = "Seznam izdelkov",
                            content = @Content(mediaType = "application/json")
                    )
            },
            description = "Vrne seznam seznamov od uporabnika z podanim identifikatorjem"
    )
    public Response getListsFromUser(@PathParam("id") Integer userId) {
        return Response
                .status(Response.Status.OK)
                .entity(upravljanjeSeznamovZrno.pridobiSeznameUporabnika(userId))
                .build();
    }

    @GET
    @Path("/fromUserByName")
    @Operation(
            summary = "Pridobi sezname s podanim imenom",
            tags = "seznami",
            responses = {
                    @ApiResponse(
                            description = "Seznam izdelkov",
                            content = @Content(mediaType = "application/json")
                    )
            },
            description = "Vrne seznam seznamov s podobnim podanim imenom"
    )
    public Response getListsWithName(SeznamDto seznamDto) {
        return Response
                .status(Response.Status.OK)
                .entity(upravljanjeSeznamovZrno.poisciSeznamePoImenu(seznamDto))
                .build();
    }

}

