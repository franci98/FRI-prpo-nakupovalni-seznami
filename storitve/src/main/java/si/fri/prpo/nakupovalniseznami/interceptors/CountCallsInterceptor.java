package si.fri.prpo.nakupovalniseznami.interceptors;

import si.fri.prpo.nakupovalniseznami.annotations.CountCalls;
import si.fri.prpo.nakupovalniseznami.zrna.BelezenjeKlicevZrno;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@CountCalls
public class CountCallsInterceptor {
    @Inject
    private BelezenjeKlicevZrno belezenjeKlicevZrno;

    @AroundInvoke
    public Object countCalls(InvocationContext invocationContext) throws Exception {
        belezenjeKlicevZrno.zabeleziKlic();
        return invocationContext.proceed();
    }
}
