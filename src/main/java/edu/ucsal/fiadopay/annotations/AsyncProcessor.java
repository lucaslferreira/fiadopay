package edu.ucsal.fiadopay.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AsyncProcessor {
    String pool() default "default";
}