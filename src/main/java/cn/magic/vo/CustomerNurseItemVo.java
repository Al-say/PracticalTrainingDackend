package cn.magic.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
//客户护理项目视图类
@Data
public class CustomerNurseItemVo {
    private Integer id; //主键
    private Integer itemId; //护理项目id
    private Integer customerId; //客户编号
    private Integer nurseNumber; //购买数量
    private Integer isDelete; //逻辑删除标记（0：显示；1：隐藏）
    private Date buyTime; //服务购买时间
    private Date maturityTime; //服务到期时间
    private String customerName; //客户姓名
    private String serialNumber; // 编号
    private String nursingName; //护理项目名称
    private String servicePrice; // 价格
}
