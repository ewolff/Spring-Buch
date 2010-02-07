package springperformance;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class AspectJLogger {

	protected transient Log defaultLogger = LogFactory.getLog(getClass());

	@Around("execution(void springperformance.AspectJLogging.doIt())")
	public void enter(ProceedingJoinPoint joinPoint) throws Throwable {
		String signature = joinPoint.getStaticPart().getSignature().toShortString();
		defaultLogger.trace("Entering "
				+ signature);
		joinPoint.proceed();
		defaultLogger.trace("Exiting "
				+ signature);
	}

	@Around("execution(void springperformance.AspectJLoggingOhneInfos.doIt())")
	public void exitOhneInfos(ProceedingJoinPoint joinPoint) throws Throwable {
		defaultLogger.trace("abcdefghijklmnExiting ABean.doItLog()");
		joinPoint.proceed();
		defaultLogger.trace("abcdefghijklmnEntering ABean.doItLog()");
	}


}
