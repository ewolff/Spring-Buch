package komplexetypen;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/komplexetypenutil.xml")
public class KomplexeTypenUtilTest {

	@Autowired
	@Qualifier("eineBean")
	private EineBean eineBean;

	@Autowired
	@Qualifier("eineAndereBean")
	private EineBean eineAndereBean;

	@Test
	public void testKomplexeTypen() {
		eineBean.out();
		eineAndereBean.out();
	}

}
