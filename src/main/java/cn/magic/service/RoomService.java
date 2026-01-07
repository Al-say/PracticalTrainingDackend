package cn.magic.service;

import cn.magic.entity.Room;
import cn.magic.utils.ResultVo;
import cn.magic.vo.BedVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface RoomService extends IService<Room> {
    /**
     * 获取房间列表及床位信息
     */
    List<Room> getRoomListWithBeds();

    // 查询床位列表
    public ResultVo<BedVo> findBedVo(String floor) throws Exception;
}
