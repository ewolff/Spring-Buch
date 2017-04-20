package springperformance;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.interceptor.SimpleTraceInterceptor;

public class MyInterceptor extends SimpleTraceInterceptor{

	@Override
	protected String getInvocationDescription(MethodInvocation invocation) {
		return "NoInfo";
	}

}
