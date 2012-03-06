
class MyException extends Exception {
		public MyException(String msg) {
			super(msg);			
		}
}
 
public class ExeptionTest {
	public void foo() throws MyException {
		System.out.println("Testing exceptions...");
		throw new MyException("Kernel panic!!!");
	}
 
	public static void main(String[] args) {
		try {
			ExeptionTest t = new ExeptionTest();
			t.foo();
		} catch (MyException e) {
			e.printStackTrace(System.err);
		}
	}
 
}
