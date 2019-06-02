package controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import controller.Chat.ChatEndpoint;
import domain.User;
import repository.UserDao;


import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("authentication")
public class AuthenticationEndpoint {

    private static final Logger LOGGER = Logger.getLogger( ChatEndpoint.class.getName() );

    @EJB
    private UserDao userDao;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("username") String username,
                                     @FormParam("password") String password,
                                     @FormParam("factor2") String factor2) {

        try {

            // Authenticate the user using the credentials provided
            User user = authenticate(username, password);

            // Issue a token for the user
            String token = issueToken(user);
            if (user.getAuthenticationKey() == null) {
                return Response.ok(token).build();
            } else {
                if(decodeAuth(factor2, user) == true){
                    return Response.ok(token).build();
                }
                else{
                    return Response.status(Response.Status.FORBIDDEN).build();
                }
            }

        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    private User authenticate(String username, String password) throws Exception {
        return userDao.checkCreds(username, password);

        // Authenticate against a database, LDAP, file or whatever
        // Throw an Exception if the credentials are invalid
    }

    private boolean decodeAuth(String fa2, User user) {
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        return gAuth.authorize(user.getAuthenticationKey(), Integer.valueOf(fa2)); //returns if valid

    }

    private String issueToken(User user) {

        try (InputStream input = new FileInputStream("D://Semester 6/Jea_automotive/src/main/resources/config.properties")){

            Properties prop = new Properties();

            if (input == null) {
                LOGGER.log(Level.INFO,"Sorry, unable to find config.properties");
                return "Bestand niet gevonden";
            }

            // load a properties file
            prop.load(input);
            Algorithm algorithm = Algorithm.HMAC256("frontendgeeftmijstoring");
            String token = JWT.create()
                    .withIssuer("JEA_JOEY")
                    .withClaim("ID",user.getGebruikersiD())
                    .withClaim("username",user.getUserName())
                    .withClaim("Roles" , String.valueOf(user.getRole()))
                    .withIssuedAt(new Date(System.currentTimeMillis()))
                    .withExpiresAt(new Date(System.currentTimeMillis() + (5 * 60 * 1000)))
                    .sign(algorithm);
            return token;


        }catch (Exception e){
            return "";
        }
    }
}