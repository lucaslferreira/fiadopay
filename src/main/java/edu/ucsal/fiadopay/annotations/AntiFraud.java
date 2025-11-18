package edu.ucsal.fiadopay.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AntiFraud {
    String name();
    double threshold() default 1000.0;
}