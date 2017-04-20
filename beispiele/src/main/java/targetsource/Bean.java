package targetsource;

public class Bean {

    private String a = "";

    public Bean(String a) {
        this.a = a;
    }

    public Bean() {
    }

    public void doIt() {
        System.out.println("Bean" + a);
    }

}
