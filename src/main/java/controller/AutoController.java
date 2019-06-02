package controller;

import controller.Chat.ChatEndpoint;
import domain.Auto;
import domain.Role;
import jwt.JWT;
import repository.AutoDao;

import javax.ejb.EJB;
import javax.ws.rs.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@JWT(Permissions = Role.Admin)
@Path("auto")
public class AutoController {

    private static final Logger LOGGER = Logger.getLogger( ChatEndpoint.class.getName() );

    @EJB
    AutoDao autoDao;

    @JWT(Permissions = Role.User)
    @GET
    @Consumes("application/json")
    public List<Auto> findAllCars(){
        return autoDao.findallAutos();
    }

    @POST
    @Consumes("application/json")
    public void create(Auto auto){
        autoDao.create(auto);
    }

    @JWT(Permissions = Role.User)
    @PUT
    @Consumes("application/json")
    public void update(Auto auto){
        autoDao.update(auto);
    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    public void delete(@PathParam("id") Long id) {
        autoDao.delete( id );
    }

    @JWT(Permissions = Role.User)
    @PUT
    @Path("/nieuwBod")
    @Consumes("application/json")
    public void update(@FormParam("id") Long id, @FormParam("nieuweBod") double nieuweBod){

        Auto auto = autoDao.find(id);
        LOGGER.log(Level.INFO, auto.toString());
        auto.getHuidigBod();
        if(auto.getHuidigBod() >= nieuweBod ){

        }
        else{
            auto.setHuidigBod(nieuweBod);
            autoDao.update(auto);
        }


    }

}

