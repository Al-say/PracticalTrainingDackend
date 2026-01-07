package cn.magic.service.impl;

import cn.magic.entity.Bed;
import cn.magic.entity.Room;
import cn.magic.mapper.BedMapper;
import cn.magic.mapper.RoomMapper;
import cn.magic.service.RoomService;
import cn.magic.utils.ResultVo;
import cn.magic.vo.BedVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//房间服务实现类
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {
    @Autowired
    private BedMapper bedMapper;  //床位Mapper

    @Override
    public List<Room> getRoomListWithBeds() {
        // 获取所有房间
        List<Room> roomList = this.list();

        // 为每个房间查询床位信息
        for (Room room : roomList) {
            QueryWrapper<Bed> bedQueryWrapper = new QueryWrapper<>();
            bedQueryWrapper.eq("room_no", room.getRoomNo());
            List<Bed> bedList = bedMapper.selectList(bedQueryWrapper);
            room.setBedList(bedList);
        }

        return roomList;
    }

    //查询床位数量和房间和床位列表，传入楼层
    @Override
    public ResultVo<BedVo> findBedVo(String floor) throws Exception {
        //查询床位数量
        BedVo bedVo = bedMapper.selectBedCount();
        //构造查询条件
        QueryWrapper<Room> roomQueryWrapper = new QueryWrapper<>();
        roomQueryWrapper.eq("room_floor", floor); //查询指定楼层

        List<Room> rooms = list(roomQueryWrapper);
        //循环查询房间和床位列表
        for (Room room : rooms) {
            QueryWrapper<Bed> bedQueryWrapper = new QueryWrapper<>();
            bedQueryWrapper.eq("room_no", room.getRoomNo()); //根据房间编号查询 床位列表
            room.setBedList(bedMapper.selectList(bedQueryWrapper));
        }
        bedVo.setRoomList(rooms);
        return ResultVo.ok(bedVo);
    }
}
