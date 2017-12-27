package reflact;

/**
 * Created by fengguangliang on 2016/12/15.
 */
public class Factory {
    public static fruit getInstance(String className) {
        fruit f = null;
        try {
            f = (fruit) Class.forName(className).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return f;
    }
}
