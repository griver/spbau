public class Sample {
    private static void foo(Integer value) {
        value+= 3;
        System.out.println(value);
    }
    public static void main(String[] args) {
        Integer val =  5;
        foo(val);
        System.out.println(val);
        int z = 6;
        Integer pz = z;
        pz++;
        System.out.println(z);
        return;        
    }
}
