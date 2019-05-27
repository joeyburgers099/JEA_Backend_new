package interceptor;

import domain.User;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class UserInterceptor {

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {

        System.out.println("UserInterceptor - Before methode : "+ context.getMethod().getName());
        Object result = context.proceed();
        System.out.println("UserInterceptor - After methode : "+ context.getMethod().getName());
        return result;
    }

}
