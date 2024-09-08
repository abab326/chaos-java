package org.liushuxue.chaos.bean.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Schema(description = "注册参数")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterVo implements Serializable {
    @NotBlank(message = "用户名不能为空")
    @Schema(description = "用户名")
    private String username;

    /** 密码长度为6~12位，且为数字、字母、特殊符号的组合*/
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$", message = "密码长度为6~12位，且为数字、字母、特殊符号的组合")
    @NotBlank(message = "密码不能为空")
    @Schema(description = "密码")
    private String password;

    @NotBlank(message = "验证码不能为空")
    @Schema(description = "验证码")
    private String code;

}
