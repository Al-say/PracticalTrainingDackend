package cn.magic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

// 床位
@Data
@EqualsAndHashCode(callSuper = false)
public class Bed {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;  // 主键
    private Integer roomNo; // 房间编号
    private Integer bedStatus; // 床位状态  1：空闲，2：有人 3：外出
    private String remarks; // 备注
    private String bedNo; // 床位编号
}
