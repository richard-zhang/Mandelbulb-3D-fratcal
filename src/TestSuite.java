/**
 * Created by R on 2/4/16.
 */
public class TestSuite {
    public static void main(String[] args) {
        System.out.println("Running Tests...");
        //testAdd();
        //testMul();
        testNorm();
        //testVertical();


    }

    public static void testNorm(){
        Vector n = new Vector(1,4,2);
        Vector n1 = new Vector(2,4,5);

        System.out.println(n.normlised().dotProtect(n1.normlised()));

    }


}
