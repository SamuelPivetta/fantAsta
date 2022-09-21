package fantasta.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
@OpenAPIDefinition(info = @Info(title = "Fantacalcio API", version = "v1", description = "Servizo per gestione asta fantacalcio",
contact = @Contact(email = "samuel_91_@hotmail.it",name= "Samuel",url = "https://www.fantagazzetta.it")))
public class SwaggerConfig {

}
