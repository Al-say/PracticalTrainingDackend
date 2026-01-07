package cn.magic.controller;

import cn.magic.entity.Room;
import cn.magic.service.RoomService;
import cn.magic.utils.ResultVo;
import cn.magic.vo.BedVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 房间控制器
 */
@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    /**
     * 根据楼层获取房间列表及床位统计信息
     */
    @GetMapping("/findBedVo")
    public ResultVo<BedVo> findBedVo(String floor) throws Exception {
        return roomService.findBedVo(floor);
    }

    // 查询房间列表
    @GetMapping ("/listRoom" )
    public ResultVo<List<Room>> listRoom(){
        return ResultVo.ok(roomService.list());
    }
}
