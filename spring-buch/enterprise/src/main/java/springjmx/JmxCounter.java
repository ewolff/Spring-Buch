package springjmx;

public class JmxCounter implements IJmxCounter {

    private int count;

    public int getCount() {
        return count;
    }

    public void inc() {
        count++;
    }
    
}
