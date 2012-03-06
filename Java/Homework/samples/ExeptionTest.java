
class MyException extends Exception {
		public MyException(String msg) {
			super(msg);			
		}
}
 
public class ExeptionTest {
	public static void foo() throws MyException {
		System.out.println("Testing exceptions...");
		throw new MyException("Kernel panic!!!");
	}
 
    public static void boo() { 
        try{
            foo();
        }
        catch (MyException e) {
            System.out.println("some dangerous exception!!!");
        }
        int z;
    }

  	public static void main(String[] args) {
	    boo();
        int a=0;    
        for(int i = 0; i < 5; i++) {
            a += i;
            System.out.println(a);
        }
	}
 
}
