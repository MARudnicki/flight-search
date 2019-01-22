package com.tokigames.swagger;

import io.swagger.annotations.Contact;
import io.swagger.annotations.ExternalDocs;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition(
        info = @Info(
                description = "Flight Search API",
                version = "v0.0.1",
                title = "Flight Search API",
                contact = @Contact(
                   name = "Tran Trong Loc", 
                   email = "trantrongloc@gmail.com", 
                   url = "https://github.com/trantrongloc/flight-search"
                ),
                license = @License(
                   name = "Apache 2.0", 
                   url = "http://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        consumes = {"application/json"},
        produces = {"application/json"},
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS},
        externalDocs = @ExternalDocs(value = "Read This For More Details", url = "https://github.com/trantrongloc/flight-search")
)
public interface ApiDocumentationConfig {

}