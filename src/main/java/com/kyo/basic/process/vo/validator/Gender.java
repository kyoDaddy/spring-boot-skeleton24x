package com.kyo.basic.process.vo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenderValidator.class)
public @interface Gender {

    String message() default "성별이 유효하지 않습니다. 남자:M,여자:W";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}