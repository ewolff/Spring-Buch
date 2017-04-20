package security;

import java.util.Collection;

import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AbstractAclVoter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import businessobjects.Kunde;


public class KundeVoter extends AbstractAclVoter {
	
	public KundeVoter() {
		setProcessDomainObjectClass(Kunde.class);
	}
	
	protected Object getDomainObjectInstance(Object secureObject) {
        Object[] args;
        Class[] params;

        if (secureObject instanceof MethodInvocation) {
            MethodInvocation invocation = (MethodInvocation) secureObject;
            params = invocation.getMethod().getParameterTypes();
            args = invocation.getArguments();
        } else {
            JoinPoint jp = (JoinPoint) secureObject;
            params = ((CodeSignature) jp.getStaticPart().getSignature()).getParameterTypes();
            args = jp.getArgs();
        }

        for (int i = 0; i < params.length; i++) {
            if (getProcessDomainObjectClass().isAssignableFrom(params[i])) {
                return args[i];
            }
        }
        return null;
    }

	public boolean supports(ConfigAttribute attribute) {
		return attribute.getAttribute().equals("ACL_KUNDE");
	}

	public int vote(Authentication authentication, Object object,
			Collection<ConfigAttribute> config) {
		Object domainObject = getDomainObjectInstance(object);
		if (domainObject==null) {
			return ACCESS_ABSTAIN;
		}
		if (!(authentication instanceof UsernamePasswordAuthenticationToken)) {
			return ACCESS_ABSTAIN;
		}
		Kunde kunde = (Kunde) domainObject;
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) authentication;
		if (kunde.getName().equalsIgnoreCase(
				usernamePasswordAuthenticationToken.getName())) {
			return ACCESS_GRANTED;
		} else {
			return ACCESS_DENIED;
		}
	}

}
