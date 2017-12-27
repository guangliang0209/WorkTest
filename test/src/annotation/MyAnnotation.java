package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017\11\23 0023.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String hello() default "gege";

    String world();

    int[] array() default {2, 4, 5, 6};

    EnumTest.TrafficLamp lamp() ;

    TestAnnotation lannotation() default @TestAnnotation(value = "ddd");

    Class style() default String.class;

}
