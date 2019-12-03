package si.fri.prpo.nakupovalniseznami.api.v1;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;

@SecurityScheme(name = "openid-connect", type = SecuritySchemeType.OPENIDCONNECT,
        openIdConnectUrl = "http://auth-server-url/.well-known/openid-configuration")
@OpenAPIDefinition(
        info = @Info(
                title = "API za nakupovalni seznam",
                version = "v1",
                description = "API, ki omogoča enostavno upravljanje z nakupovalnimi seznami.",
                contact = @Contact(
                        name = "Franc Klavž, Tim Vučina",
                        url = "https://github.com/franci98",
                        email = "franciklavz@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"
                )
        ),
        security = @SecurityRequirement(name = "openid-connect"),
        servers = @Server(url ="http://localhost:8080/v1"))
@ApplicationPath("v1")
public class NakupovalniSeznamiApplication extends Application {
}
