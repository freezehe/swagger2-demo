package com.freeze.springboot.swagger2.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.TokenEndpoint;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>
 * Title: Swagger2Config
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author he_jiebing@jiuyv.com
 * @date 2019年7月10日 下午3:35:20
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
	
	private static final String CLIENT_ID = "admin";
	
	private static final String CLIENT_SECRET = "admin";
	
	private static final String AUTH_SERVER = "http:127.0.0.1:8080";

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.pathMapping("/")
				.select()
				.apis(RequestHandlerSelectors
						.basePackage("com.freeze.springboot.swagger2.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(
						new ApiInfoBuilder().title("Swagger2 Demo")
								.description("Swagger2 Demo").version("1.0")
								.build());
			//	.securitySchemes(Arrays.asList(securityScheme()))
				//.securityContexts(Arrays.asList(securityContext()));
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder()
			      .securityReferences(
			        Arrays.asList(new SecurityReference("spring_oauth", scopes())))
			      //.forPaths(PathSelectors.regex("/foos.*"))
			      .build();
	}
	
	private AuthorizationScope[] scopes() {
	    AuthorizationScope[] scopes = { 
	      new AuthorizationScope("read", "for read operations"), 
	      new AuthorizationScope("write", "for write operations"), 
	      new AuthorizationScope("foo", "Access foo API") };
	    return scopes;
	}

	private SecurityScheme securityScheme() {
		GrantType grantType = new AuthorizationCodeGrantBuilder()
        .tokenEndpoint(new TokenEndpoint(AUTH_SERVER + "/token", "oauthtoken"))
        .tokenRequestEndpoint(
          new TokenRequestEndpoint(AUTH_SERVER + "/authorize", CLIENT_ID, CLIENT_ID))
        .build();
 
    SecurityScheme oauth = new OAuthBuilder().name("spring_oauth")
        .grantTypes(Arrays.asList(grantType))
        .scopes(Arrays.asList(scopes()))
        .build();
    return oauth;
	}

	@Bean
	public SecurityConfiguration security() {
		return SecurityConfigurationBuilder.builder().clientId(CLIENT_ID)
				.clientSecret(CLIENT_SECRET).scopeSeparator(" ")
				.useBasicAuthenticationWithAccessCodeGrant(true).build();
	}

}
