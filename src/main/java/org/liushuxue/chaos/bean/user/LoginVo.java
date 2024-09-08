package org.liushuxue.chaos.bean.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Schema(description = "用户登录")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginVo implements Serializable {

    @NotBlank(message = "密码不能为空")
    @Schema(description = "用户名")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Schema(description = "密码")
    private String password;
}
