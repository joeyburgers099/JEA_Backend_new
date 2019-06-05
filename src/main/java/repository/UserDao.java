package repository;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import domain.User;

import interceptor.UserInterceptor;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import java.io.*;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

@Local
@Stateless
@Interceptors(UserInterceptor.class)
public class UserDao {

    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger( UserDao.class.getName() );

    @PersistenceContext (unitName = "myPU")
    private EntityManager entityManager;


    public User find(Long id) {
        return entityManager.createNamedQuery("User.findOne", User.class).setParameter("id", id).getSingleResult();
    }
    public User findOne(String username, String password) {
        return entityManager.createNamedQuery("User.checkcreds", User.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getSingleResult();
    }


    public List<User> findAllUsers(){
        List<User> users;
        try {
            users = entityManager.createQuery(
                    "SELECT u FROM User u", User.class).getResultList();
            return users;
        }catch (Exception ex){
            LOGGER.log(Level.INFO, ex.getMessage());
            return null;
        }
    }

    public void create(User user) {
        entityManager.persist(user);
        //userEJB.createUser( user );
    }

    public User getById(Long id) {
        try {
            return entityManager.find(User.class, id);
        }catch (Exception ex){
            LOGGER.log(Level.INFO, ex.getMessage());
            return null;
        }
    }
    public void update(User user) {
        entityManager.merge(user);
    }

    public void delete(Long id) {
        entityManager.remove(id);
    }

    public User checkCreds(String username, String password){
        return entityManager.createNamedQuery("User.checkcreds", User.class).setParameter("username", username).setParameter("password",password).getSingleResult(); //throwt exception als meer dan 1 row
    }

    public String generateAuthKey(ContainerRequestContext requestContext) throws UnsupportedEncodingException {

        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        String token = authorizationHeader.substring("Bearer".length()).trim();
        DecodedJWT decodedjwt = verifyJWT(token);
        Long id = decodedjwt.getClaim("ID").asLong();
        User u = find(id);

        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        final GoogleAuthenticatorKey key = gAuth.createCredentials();

        u.setAuthenticationKey(key.getKey());
        update(u);
        return key.getKey();
    }

    // verify JWT
    public DecodedJWT verifyJWT(String token) throws UnsupportedEncodingException {

        try (InputStream input = new FileInputStream("D://Semester 6/Jea_automotive/src/main/resources/config.properties")){
            Properties prop = new Properties();

            if (input == null) {
                LOGGER.log(Level.INFO, "Sorry, unable to find config.properties");
                return null;
            }

            // load a properties file
            prop.load(input);
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256("frontendgeeftmijstoring")).build();
            return verifier.verify(token);
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }


}
