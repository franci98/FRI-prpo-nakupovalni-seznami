package si.fri.prpo.nakupovalniseznami.mappers;

import si.fri.prpo.nakupovalniseznami.izjeme.NeveljavenSeznamException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class NeveljavniSeznamExceptionMapper implements ExceptionMapper<NeveljavenSeznamException> {

    @Override
    public Response toResponse(NeveljavenSeznamException e) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("{\"napaka\": \"" + e.getMessage() + "\"}")
                .build();
    }
}
