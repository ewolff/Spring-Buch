package methodreplacement;

public class Bean {
	
	public static int doItCount=0;

    private String a = "";

    public Bean(String a) {
        this.a = a;
    }

    public Bean() {
    }

    public void doIt() {
        System.out.println("Bean" + a);
        doItCount++;
    }

}
