package repository;


import domain.Auto;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Local
@Stateless
public class AutoDao {

    private static final Logger LOGGER = Logger.getLogger( AutoDao.class.getName() );

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
            LOGGER.log(Level.INFO, ex.getMessage());
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