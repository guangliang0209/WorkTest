package factory.simple.fact;

import factory.simple.product.Benz;
import factory.simple.product.Bmw;
import factory.simple.product.Car;

/**
 * Created by Administrator on 2017\6\28 0028.
 */
public class Driver {

    public Car driveCar(String carName) throws Exception {
        if ("Bmw".equalsIgnoreCase(carName)) {
            return new Bmw();
        } else if ("Benz".equalsIgnoreCase(carName)) {
            return new Benz();
        } else {
            throw new Exception("no such car");
        }
    }
}
