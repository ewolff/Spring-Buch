package exception;

import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointExceptionResolver;

public class MyExceptionResolver implements EndpointExceptionResolver {

	public boolean resolveException(MessageContext messageContext,
			Object endpoint, Exception ex) {
		System.out.println(endpoint);
		System.out.println(endpoint.getClass());
		System.out.println(ex);
		System.out.println(messageContext);
		return true;
	}

}
