package com.acculytixs.wayuparty.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
public @interface Type {
    public String name() default "String";
    public int minLength() default 0;
    public int maxLength() default 0;
    public String desc();
}
