package repository;


import controller.AutoController;
import domain.Auto;
import interceptor.UserInterceptor;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Local
@Stateless
public class AutoDao {

    @PersistenceContext (unitName = "myPU")
    public EntityManager entityManager;


    public Auto find(Long id) {
        return entityManager.createNamedQuery("Auto.findOne", Auto.class).setParameter("id", id).getSingleResult();
    }

    public List<Auto> findallAutos(){
        List<Auto> autos;
        try {
            autos = entityManager.createQuery(
                    "SELECT a FROM Auto a", Auto.class).getResultList();
            return autos;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void create(Auto auto) {
        entityManager.persist(auto);
        //userEJB.createUser( user );
    }


    public void update(Auto auto) {
        entityManager.merge(auto);
    }

    public void delete(Long id) {
        entityManager.remove(id);
    }
}