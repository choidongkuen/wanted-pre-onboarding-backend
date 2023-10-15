package com.example.pre_onboarding.dto.user;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequestDto {
    @Email(message = "올바은 유저 이메일 형식이 아닙니다.")
    @NotBlank(message = "유저 이메일은 필수 입력값입니다.")
    private String email;
    @NotBlank(message = "유저 비밀번호는 필수 입력값입니다.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$",
            message = "비밀번호는 영어/숫자/특수문자를 포함한 8 ~ 20 크기여야 합니다.")
    private String password;
}
