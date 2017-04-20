package springperformance;


public class NoLogging implements IDoIt {

    private String name;

    public void doIt() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
