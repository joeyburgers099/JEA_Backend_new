package controller;

import domain.Auto;
import repository.AutoDao;

import javax.ejb.EJB;
import javax.ws.rs.*;
import java.util.List;

@Path("auto")
public class AutoController {

    @EJB
    AutoDao autoDao;

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
//    @PUT
//    @Path("/nieuwBod")
//    @Consumes("application/json")
//    public void update(double bod){
//
//
//
//        autoDao.update(auto);
//    }

}

