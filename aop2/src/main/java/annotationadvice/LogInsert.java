package annotationadvice;

public class LogInsert {

	private static boolean called;

	public static void reset() {
		called=false;
	}
	
	public static boolean isCalled() {
		return called;
	}

	public LogInsert() {
	}

	public void log(final String message) {
		called=true;
		System.out.println("DBLog : " + message);
	}

}