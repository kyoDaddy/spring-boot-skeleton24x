package com.kyo.basic.process.vo.req;

import com.kyo.basic.process.vo.validator.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class ReqUserVo {

    private Long id;

    private String email;

    private String username;

    private String password;

    @Gender
    private String gender;

}
