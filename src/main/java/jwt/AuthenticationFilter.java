package jwt;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import domain.Role;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;

@JWT
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final String REALM = "example";
    private static final String AUTHENTICATION_SCHEME = "Bearer";

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get the Authorization header from the request
        String authorizationHeader =
                requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Validate the Authorization header
        if (!isTokenBasedAuthentication(authorizationHeader)) {
            abortWithUnauthorized(requestContext);
            return;
        }

        // Extract the token from the Authorization header
        String token = authorizationHeader
                .substring(AUTHENTICATION_SCHEME.length()).trim();

        try {

            // Validate the token
            validateToken(token);

        } catch (Exception e) {
            abortWithUnauthorized(requestContext);
        }
    }

    private boolean isTokenBasedAuthentication(String authorizationHeader) {

        // Check if the Authorization header is valid
        // It must not be null and must be prefixed with "Bearer" plus a whitespace
        // The authentication scheme comparison must be case-insensitive
        return authorizationHeader != null && authorizationHeader.toLowerCase()
                .startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {

        // Abort the filter chain with a 401 status code response
        // The WWW-Authenticate header is sent along with the response
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .header(HttpHeaders.WWW_AUTHENTICATE,
                                AUTHENTICATION_SCHEME + " realm=\"" + REALM + "\"")
                        .build());
    }


    private void rolecheck(){

    }

    private JWT GetAnnotation(AnnotatedElement annotatedElement){
        return  annotatedElement.getAnnotation(JWT.class);
    }

    private void validateToken(String token) throws Exception {
        // Check if the token was issued by the server and if it's not expired
        // Throw an Exception if the token is invalid


            Gson g = new Gson();
            Algorithm algorithm = Algorithm.HMAC256("frontendgeeftmijstoring");

            JWTVerifier verifier = com.auth0.jwt.JWT.require(algorithm)
                    .withIssuer("JEA_JOEY")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            JWT JWTContext = null;

            JWTContext = GetAnnotation(resourceInfo.getResourceMethod());
            if (JWTContext == null) {
                JWTContext = GetAnnotation(resourceInfo.getResourceClass());
            }

            if (JWTContext != null) {
                Role[] permission = JWTContext.Permissions();

                if (!Arrays.asList(permission).contains(Role.User)) {
                    String roles = jwt.getClaim("Roles").asString();

                    boolean check = false;
                    for (Role r : Arrays.asList(permission)) {
                        if (roles.toUpperCase().contains(r.toString().toUpperCase())) {
                            check = true;
                        }

                    }
                    if (!check) {
                        throw new Exception("no roles");
                    }

//                if (!Arrays.asList(permission).contains(rolelist)) {
//                    throw new Exception("no roles");
//
//                }

                }

            }

        }

    }