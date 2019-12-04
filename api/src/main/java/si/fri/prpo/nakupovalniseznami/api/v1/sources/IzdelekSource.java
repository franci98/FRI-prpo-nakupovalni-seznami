package si.fri.prpo.nakupovalniseznami.api.v1.sources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
@Path("izdelki")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class IzdelekSource {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private IzdelekZrno izdelekZrno;
    @Inject
    private UpravljanjeIzdelkovZrno upravljanjeIzdelkovZrno;

    @GET
    @Operation(summary = "Pridobi vse izdelke",
            description = "Vrne seznam vseh izdelkov, ki so v bazi.",
            tags = "izdelki"
    )
    public Response getItems() {

        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Izdelek> izdelki = izdelekZrno.getAllItems(query);

        return Response.ok(izdelki).build();
    }

    /***
     * On link v1/izdelki/{id}
     * where id connects to the method parameter
     * @param id
     * @return Response with the item
     */
    @GET
    @Path("{id}")
    @Operation(
            summary = "Pridobi izdelek z določenim ID-jem",
            tags = "izdelki",
            responses = {
                    @ApiResponse(description = "Izdelki",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Izdelek.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Izdelka s podanim identifikatorjem nismo našli.")
            },
            description = "V bazi najde in vrne izdelek s točno določenim ID-jem-"
    )
    public Response getItem(@PathParam("id") Integer id) {
        Izdelek izdelek = izdelekZrno.get(id);

        if (izdelek == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(izdelek).build();
    }

    @POST
    @Operation(
            summary = "Ustvari nov izdelek",
            tags = "izdelki",
            responses = {
                    @ApiResponse(
                            description = "Nov izdelek",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Izdelek.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Podatki niso pravilno izpolnjeni.")
            },
            description = "Ustvari nov izdelek s podanimi podatki"
    )
    public Response addItem(IzdelekDto izdelekDto) {
        return Response
                .status(Response.Status.CREATED)
                .entity(upravljanjeIzdelkovZrno.kreirajIzdelekSeznama(izdelekDto))
                .build();
    }

    @PUT
    @Path("{id}")
    @Operation(
            summary = "Posodobi izdelek",
            tags = "izdelki",
            responses = {
                    @ApiResponse(
                            description = "Posodobljeni izdelek",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Izdelek.class)
                            )
                    )
            },
            description = "Posodobi izdelek s podanim identifikatorjem."
    )
    public Response updateItem(@PathParam("id") Integer itemId, Izdelek izdelek) {
        return Response
                .status(Response.Status.CREATED)
                .entity(izdelekZrno.update(itemId, izdelek))
                .build();
    }

    @DELETE
    @Path("{id}")
    @Operation(
            summary = "Izbriši izdelek",
            tags = "izdelki",
            responses = {
                    @ApiResponse(
                            description = "Identifikator izbrisanega izdelka",
                            content = @Content(mediaType = "text/plain")
                    )
            },
            description = "Izbriše izdelek s podanim identifikatorjem"
    )
    public Response deleteItem(@PathParam("id") Integer itemId) {
        return Response
                .status(Response.Status.OK)
                .entity(izdelekZrno.delete(itemId))
                .build();
    }

    @GET
    @Path("/seznami/{id}")
    @Operation(
            summary = "Pridobi izdelke iz seznama",
            tags = "izdelki",
            responses = {
                    @ApiResponse(
                            description = "Seznam izdelkov",
                            content = @Content(mediaType = "application/json")
                    )
            },
            description = "Vrne seznam izdelkov iz seznama z podanim identifikatorjem"
    )
    public Response getItemsFromList(@PathParam("id") Integer seznamId) {
        return Response
                .status(Response.Status.OK)
                .entity(upravljanjeIzdelkovZrno.pridobiIzdelkeSeznama(seznamId))
                .build();
    }

}

