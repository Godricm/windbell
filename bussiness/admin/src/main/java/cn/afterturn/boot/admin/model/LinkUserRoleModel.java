/**
 * Copyright 2017-2018 JueYue (qrb.jueyue@foxmail.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.afterturn.boot.admin.model;

import cn.afterturn.boot.bussiness.model.IdBaseModel;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 角色和用户关联表
 *
 * @author JueYue
 * @Date 2018-11-13 15:07:54
 */
@Data
@ApiModel("角色和用户关联表")
@TableName(value = "t_system_link_user_role", resultMap = LinkUserRoleModel.BASE_RESULT_MAP)
public class LinkUserRoleModel extends IdBaseModel<LinkUserRoleModel> {

    private static final long serialVersionUID = 1L;

    @Excel(name = "用户ID")
    @TableField(value = "USER_ID", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("用户ID")
    private String userId;

    @Excel(name = "角色ID")
    @TableField(value = "ROLE_ID", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty("角色ID")
    private String roleId;
}
