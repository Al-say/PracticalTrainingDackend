package cn.magic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
//食品信息
@Data
@TableName("food")
public class Food {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id; // 主键
    private String foodName; //食品名称
    private String foodType; //食品类型
    private Double price; //食品价格
    private Integer isHalal; //是否清真
    private String foodImg;
}
