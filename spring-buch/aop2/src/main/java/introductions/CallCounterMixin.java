package introductions;


public class CallCounterMixin 
        implements CallCounter {

    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    public void incCounter() {
        counter++;
    }
}
