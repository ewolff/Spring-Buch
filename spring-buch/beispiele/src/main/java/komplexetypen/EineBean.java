package komplexetypen;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Level;

public class EineBean {

    private Map map;

    private List list;

    private Properties properties;

    private Level level;
    
    private EineEnumeration eineEnumeration;

    public void setEineEnumeration(EineEnumeration eineEnumeration) {
        this.eineEnumeration = eineEnumeration;
    }

    public void setList(List eineListe) {
        this.list = eineListe;
    }

    public void setMap(Map eineMap) {
        this.map = eineMap;
    }

    public void out() {
        System.out.println("list : ");
        outList(list);
        System.out.println();
        System.out.println("map : ");
        outMap(map);
        System.out.println();
        System.out.println("properties : ");
        outMap(properties);
        System.out.println();
        outObject("level", level);
        System.out.println();
        outObject("eineEnumeration", eineEnumeration);
    }

    private void outList(List list) {
        if (list == null) {
            System.out.println("Ist null!");
        } else {

            for (Object element : list) {
                String name = "element";
                outObject(name, element);
            }
        }
    }

    private void outMap(Map map) {
        if (map == null) {
            System.out.println("Ist null!");
        } else {
            for (Object key : map.keySet()) {
                outObject("key", key);
                outObject("value", map.get(key));
            }
        }
    }

    private void outObject(String name, Object element) {
        if (element == null) {
            System.out.println(name + " ist null!");
        } else {
            System.out.println(name + " " + element + " " + element.getClass());
        }
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public List getList() {
        return list;
    }

}
