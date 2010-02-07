package axis;

import javax.xml.namespace.QName;
import javax.xml.rpc.Service;
import javax.xml.rpc.encoding.TypeMapping;
import javax.xml.rpc.encoding.TypeMappingRegistry;

import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.springframework.remoting.jaxrpc.JaxRpcServicePostProcessor;

import businessobjects.BestellPosition;
import businessobjects.Bestellung;
import businessobjects.Einkaufswagen;
import businessobjects.EinkaufswagenInhalt;
import businessobjects.Kunde;
import businessobjects.Ware;
import businessprocess.EinfacherKreditkartenAutorisierer;
import framework.BusinessObject;

public class BeanMappingPostProcessor implements JaxRpcServicePostProcessor {

    public void postProcessJaxRpcService(Service service) {
        TypeMappingRegistry registry = service.getTypeMappingRegistry();
        TypeMapping mapping = registry.createTypeMapping();
        registerMapping(mapping, Kunde.class, "Kunde");
        registerMapping(mapping, Ware.class, "Ware");
        registerMapping(mapping, Bestellung.class, "Bestellung");
        registerMapping(mapping, BestellPosition.class, "BestellPosition");
        registerMapping(mapping, Einkaufswagen.class, "Einkaufswagen");
        registerMapping(mapping, EinkaufswagenInhalt.class,
                "EinkaufswagenInhalt");
        registerMapping(mapping, EinfacherKreditkartenAutorisierer.class,
                "EinfacherKreditkartenAutorisierer");
        registerMapping(mapping, BusinessObject.class, "BusinessObject");
        
        registry.register("http://schemas.xmlsoap.org/soap/encoding/", mapping);
    }

    protected void registerMapping(TypeMapping mapping, Class type, String name) {
        QName xmlType = new QName("urn:Bestellung", name);
        mapping.register(type, xmlType,
                new BeanSerializerFactory(type, xmlType),
                new BeanDeserializerFactory(type, xmlType));
    }

}