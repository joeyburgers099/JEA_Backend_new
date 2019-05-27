package controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import domain.User;
import repository.UserDao;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Path("user")
public class UserController {

    @EJB
    UserDao userDao;

    @GET
    @Consumes("application/json")
    public List<User> findAllUsers() {

        return userDao.findAllUsers();
    }

    @POST
    @Consumes("application/json")
    public void create(User user) {
        System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEe----------------------------------EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEee");
        System.out.println(user.getEmail());
        System.out.println(user.getUserName());
        System.out.println(user.getPassWd());

        userDao.create( user );
    }
    @GET
    @Path("/getSelf")
    public User getSelf(@Context HttpHeaders headers) throws UnsupportedEncodingException {
        long id;
        DecodedJWT decodedJWT = userDao.verifyJWT(headers.getRequestHeader("token").get(0));
        id= decodedJWT.getClaim("ID").asLong();
        User u = userDao.find(id);
        if(u.getAuthenticationKey() == null){
            u.setAuthenticationKey("");
        }
        return u;
    }

    @GET
    @Path("/{id}")
    @Consumes("application/json")
    public User getById(@PathParam("id") Long id) {

        return userDao.getById( id );
    }

    @PUT
    @Consumes("application/json")
    public void update(User user) {
        System.out.println(user.getGebruikersiD());
        userDao.update( user );
    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    public void delete(@PathParam("id") Long id) {
        userDao.delete( id );
    }

    @GET
    @Path("/keys")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public String generateAuthkey(ContainerRequestContext requestContext) throws UnsupportedEncodingException {
        return userDao.generateAuthKey(requestContext);
    }

}
