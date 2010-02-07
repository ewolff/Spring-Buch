package de.spring_buch.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Qualifier;


@Qualifier
@Documented
@Target(value={ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RUNTIME)
public @interface MyDIAnnotation {

	String value() default "";
	
}
