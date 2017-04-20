package merge;

import java.util.List;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MergeDemo implements InitializingBean, BeanNameAware {

    public void afterPropertiesSet() throws Exception {
        System.out.println(name);
        for (Object element : list) {
            System.out.println("key " + element);
        }
        System.out.println();
    }

    private List list;

    private String name;

    public List getList() {
		return list;
	}

	public void setList(List properties) {
        this.list = properties;
    }

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("merge.xml");
    }

    public void setBeanName(String name) {
        this.name = name;
    }

}
