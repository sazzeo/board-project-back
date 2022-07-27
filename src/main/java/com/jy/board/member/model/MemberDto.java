package com.jy.board.member.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Alias("memberDto")
public class MemberDto {

    @NotEmpty
    private String id;

    @NotEmpty
    private String password;

    @NotEmpty
    @Pattern(regexp = "^[가-힣]{3,10}$" , message = "이름은 3글자이상 10글자 미만 입력 가능합니다.")
    private String name;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Pattern(regexp="^[0]+[1]+[0-9]{1}[0-9]{4}[0-9]{4}$" , message = "유효한 휴대폰 번호를 입력하세요.")
    private String phone;

    private int postCode;

    private String addr;

    private String detailAddr;

    private LocalDateTime regDate;

    private String authToken;

}
