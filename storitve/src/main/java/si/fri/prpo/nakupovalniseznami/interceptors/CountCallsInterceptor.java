package si.fri.prpo.nakupovalniseznami.interceptors;

import si.fri.prpo.nakupovalniseznami.annotations.CountCalls;
import si.fri.prpo.nakupovalniseznami.zrna.BelezenjeKlicevZrno;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

@Interceptor
@CountCalls
public class CountCallsInterceptor {
    @Inject
    private BelezenjeKlicevZrno belezenjeKlicevZrno;

    private Logger log = Logger.getLogger(CountCallsInterceptor.class.getName());
    private int callCounter = 0;

    @AroundInvoke
    public Object countCalls(InvocationContext invocationContext) throws Exception {

        callCounter++;
        log.info("ENTERING method: <"
                + invocationContext.getMethod().getName() + "> IN CLASS <"
                + invocationContext.getMethod().getDeclaringClass().getName() +
                "> for the " + callCounter + ". time");

        return invocationContext.proceed();
    }
}
