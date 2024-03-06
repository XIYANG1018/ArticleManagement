package org.example.anno;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.validation.StateValidation;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented // 元注解
@Constraint(validatedBy = {StateValidation.class}) // which class will provide the rules for validation
@Target({FIELD}) // 元注解, where we can use this annotation
@Retention(RUNTIME)

public @interface State {

    // provide the message after validation failure
    String message() default "The value can only be '已发布' or '草稿'";

    // assign group
    Class<?>[] groups() default { };

    // get the state additional info
    Class<? extends Payload>[] payload() default { };

    /**
     * Defines several {@code @NotEmpty} constraints on the same element.
     *
     * @see jakarta.validation.constraints.NotEmpty
     */
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        jakarta.validation.constraints.NotEmpty[] value();
    }
}

