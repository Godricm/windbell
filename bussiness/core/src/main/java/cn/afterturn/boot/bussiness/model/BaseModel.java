package cn.afterturn.boot.bussiness.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 基础对象
 *
 * @author JueYue on 2017/9/14.
 */
@Data
public abstract class BaseModel<T> extends Model {

    public final static String BASE_RESULT_MAP = "BaseResultMap";

    @TableField(value = "CRT_USER_ID")
    @ApiModelProperty("创建人Id")
    private Long crtUserId;

    @TableField(exist = false)
    @ApiModelProperty("创建人名字")
    private String crtUserName;

    @TableField(value = "CRT_TIME")
    @ApiModelProperty("创建时间")
    private Date crtTime;

    @TableField(value = "MDF_USER_ID")
    @ApiModelProperty("修改人Id")
    private Long mdfUserId;

    @TableField(exist = false)
    @ApiModelProperty("修改人名字")
    private String mdfUserName;

    @TableField(value = "MDF_TIME")
    @ApiModelProperty("修改时间")
    private Date mdfTime;

}
