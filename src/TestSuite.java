/**
 * Created by R on 2/4/16.
 */
public class TestSuite {
    public static void main(String[] args) {

        System.out.println("Running Tests...");
        //testAdd();
        //testMul();
        //testNorm();
        //testVertical();
        testMap();

    }

    public static void testNorm(){
        Vector n1 = new Vector(2,4,5);
        Vector n = new Vector(1,4,2);

    }

    public static void testMap() {
        Vector n = new Vector(1,4,2);

        System.out.println(Vector.trace(256, 256));

    }


}
