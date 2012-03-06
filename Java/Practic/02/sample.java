public class Sample {
    private static void foo(Integer value) {
        value+= 3;
    }
    public static void main(String[] args) {
        Integer val =  5;
        foo(value);
        System.out.println(value);
        return;        
    }
}
