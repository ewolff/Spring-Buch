package de.spring_buch.framework;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GreaterOrEqualZeroValidator implements
		ConstraintValidator<GreaterOrEqualZero, Double> {

	public void initialize(GreaterOrEqualZero constraintAnnotation) {

	}

	public boolean isValid(Double value, ConstraintValidatorContext context) {
		return (value.doubleValue() >= 0.0D);
	}

}
