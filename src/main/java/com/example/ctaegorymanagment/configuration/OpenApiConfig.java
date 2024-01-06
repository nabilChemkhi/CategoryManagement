package com.example.ctaegorymanagment.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                contact =@Contact(
                        name = "Chemkhi Nabil",
                        email = "Nabil.CHEMKHI@esprit.tn",
                        url= "https://github.com/nabilChemkhi"
                ),
                description = "OpenApi documentation for Category Management App",
                title = "OpenApi specification - Nabil",
                version = "1.0"

        ),
        servers = {
                @Server(
                        description = "Local dev env",
                        url = "http://localhost:8080/"
                )
        }
)
public class OpenApiConfig {




}
