package cn.magic.vo;

import cn.magic.entity.Room;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

//床位视图
@Data
@EqualsAndHashCode(callSuper = false)
public class BedVo {
    private Integer zcw; //总床位
    private Integer kx; //空闲床位
    private Integer yr; //有人床位
    private Integer wc; //外出床位
    private List<Room> roomList; //房间和床位列表
}
