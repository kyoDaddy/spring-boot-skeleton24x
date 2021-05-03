package com.kyo.basic.process.vo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * ConstraintValidator
 *      - 스프링 부트에서 Validation 체크할 때 커스텀한 어노테이션을 만들어서 사용 (자주 사용할만한 체크를 미리 만들기)
 */
public class GenderValidator implements ConstraintValidator<Gender, String> {

    @Override
    public void initialize(Gender genderValidator) {
        //어노테이션 등록 시, 입력했던 Parameter를 초기화한다.
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return true;
        } else {
            if (Objects.equals(value, GenderEnum.M.name()) || Objects.equals(value, GenderEnum.F.name())) {
                return true;
            } else {
                return false;
            }
        }
    }

}