package com.acculytixs.wayuparty.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
public @interface Required {
    public String desc();
    public boolean alphaNumericOnly() default false;
    
    public String dependsOn() default "";
    public String[] parentValue() default "";
    public boolean onlyDependsContainsValue() default true;
    public boolean timesheetListValidate() default false;
    public boolean timesheetHours() default false;
    public boolean parentValueAny() default false;
    public String[] either() default "";
    public boolean isPositiveNumber() default false;
}
