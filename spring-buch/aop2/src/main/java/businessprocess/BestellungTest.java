package businessprocess;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import businessobjects.Einkaufswagen;

public class BestellungTest {

    public static void bestellungTesten(String springKonfiguration) throws BestellungException {
        System.out.println(springKonfiguration);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                springKonfiguration);
        BestellungBusinessProcess bestellung = (BestellungBusinessProcess) applicationContext
                .getBean("bestellung");
        bestellung.bestellen(new Einkaufswagen());
        bestellung.bestellen(null);
    }

}
