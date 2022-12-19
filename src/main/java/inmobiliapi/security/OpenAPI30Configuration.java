
package inmobiliapi.security;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
  info =@Info(
    title = "API InmobiliAPP",
    version = "1.0",
    contact = @Contact(
      name = "Julian Callejas", email = "jecallejitas@yahoo.com", url = "https://github.com/JulianCallejas/InmobiliApi-SpringBoot"
    ),
    license = @License(
      name = "Open Code"
    ),
    termsOfService = "API REST",
    description = "API de InmobiliAPP una aplicación para la gestión de inmuebles"
  ),
  servers = @Server(
    url = "http://localhost:8090",
    description = "Generated server url"
  )
)
@SecurityScheme(
  name = "Bearer Authentication",
  type = SecuritySchemeType.HTTP,
  bearerFormat = "JWT",
  scheme = "bearer"
)
public class OpenAPI30Configuration {}