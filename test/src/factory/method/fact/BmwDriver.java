package factory.method.fact;

import factory.method.product.Bmw;
import factory.method.product.Car;

/**
 * Created by Administrator on 2017\6\28 0028.
 */
public class BmwDriver implements Driver {
    @Override
    public Car driveCar() {
        return new Bmw();
    }
}
