////package com.legitukused.legituuapi.configuration;
////
////import io.swagger.v3.oas.annotations.OpenAPIDefinition;
////import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
////import io.swagger.v3.oas.annotations.security.SecurityScheme;
////import io.swagger.v3.oas.models.Components;
////import io.swagger.v3.oas.models.OpenAPI;
////import io.swagger.v3.oas.models.info.Info;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////
////
////@Configuration
////@SecurityScheme(
////        name = "bearerAuth",
////        type = SecuritySchemeType.HTTP,
////        bearerFormat = "JWT",
////        scheme = "bearer"
////)
////public class SwaggerConfig {
////
////    @Bean
////    public OpenAPI api() {
////        return new OpenAPI().components(new Components())
////                .info(new Info()
////                        .title("LegitUU Api Docs")
////                        .version("v1")
////                        .description("The No.1 place to get original products"));
////    }
////}
////
////// Controllers should be annotated
////// @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "bearerAuth"))
//
//
//package com.plunex.emilokan.config;
//
//import com.google.common.base.Predicates;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)//
//                .select()//
//                .apis(RequestHandlerSelectors.any())//
//                .paths(Predicates.not(PathSelectors.regex("/error")))//
//                .build()//
//                .apiInfo(metadata())//
//                .useDefaultResponseMessages(false)//
//                .securitySchemes(Collections.singletonList(apiKey()))
//                .securityContexts(Collections.singletonList(securityContext()))
//                .genericModelSubstitutes(Optional.class);
//
//    }
//
//    private ApiInfo metadata() {
//        return new ApiInfoBuilder()//
//                .title("LegitUU Api Docs")//
//                .description("The No.1 place to get original products. Once you have successfully logged in and obtained the token, you should click on the right top button `Authorize` and introduce it with the prefix \"Bearer \".")//
//                .version("1.0.0")//
//                .contact(new Contact(null, null, "olwabukunmi.aluko@gmail.com"))//
//                .build();
//    }
//
//    private ApiKey apiKey() {
//        return new ApiKey("Authorization", "Authorization", "header");
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(defaultAuth())
//                .forPaths(PathSelectors.any())
//                .build();
//    }
//
//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
//    }
//
//}
