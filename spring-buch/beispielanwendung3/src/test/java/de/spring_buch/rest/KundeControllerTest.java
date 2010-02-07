package de.spring_buch.rest;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.spring_buch.base.SpringTestCase;

public class KundeControllerTest extends SpringTestCase {
	
	@Autowired(required=false)
	private KundeController kundeController;
	
	
	@Test
	public void kundeControllerNotCreated() {
		Assert.assertNull(kundeController);
	}
	
	
	

}
