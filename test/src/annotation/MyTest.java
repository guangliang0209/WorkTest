package annotation;

/**
 * Created by Administrator on 2017\11\23 0023.
 */

public class MyTest {

    @Deprecated
    @SuppressWarnings("")
    @MyAnnotation(lannotation = @TestAnnotation(value = "baby"), world = "shanghai", lamp = EnumTest.TrafficLamp.RED, array = {1, 2, 3})
    public void output() {
        System.out.println("output something!");
    }
}
