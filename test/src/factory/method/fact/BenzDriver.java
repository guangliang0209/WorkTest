package factory.method.fact;

import factory.method.product.Benz;
import factory.method.product.Car;

/**
 * Created by Administrator on 2017\6\28 0028.
 */
public class BenzDriver implements Driver {
    @Override
    public Car driveCar() {
        return new Benz();
    }
}
