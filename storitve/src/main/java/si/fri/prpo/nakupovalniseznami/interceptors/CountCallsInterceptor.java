package si.fri.prpo.nakupovalniseznami.interceptors;

import si.fri.prpo.nakupovalniseznami.annotations.CountCalls;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@CountCalls
public class CountCallsInterceptor {

    @AroundInvoke
    public Object countCalls(InvocationContext invocationContext) throws Exception {

        return invocationContext.proceed();
    }
}
