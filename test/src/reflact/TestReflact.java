package reflact;

/**
 * Created by fengguangliang on 2016/12/15.
 */
public class TestReflact {
    public static void main(String[] args) {
        fruit f = Factory.getInstance("reflact.Apple");
        if (f != null) {
            f.eat();
        }
    }
}
