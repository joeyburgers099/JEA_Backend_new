package interceptor;


import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserInterceptor {

    private static final Logger LOGGER = Logger.getLogger( UserInterceptor.class.getName() );

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {

        LOGGER.log(Level.INFO,"UserInterceptor - Before methode : "+ context.getMethod().getName() );

        Object result = context.proceed();

        LOGGER.log(Level.INFO, "UserInterceptor - After methode : "+ context.getMethod().getName());

        return result;

    }

}
