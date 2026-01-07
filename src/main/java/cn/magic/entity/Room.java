package cn.magic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

// 房间
@Data
@EqualsAndHashCode(callSuper = false)
public class Room {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;  // 主键
    private String roomFloor; // 房间楼层
    private Integer roomNo; // 房间编号
    @TableField(exist = false)
    private List<Bed> bedList;  // 床位列表
}
