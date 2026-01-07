package cn.magic.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

//CustomerVo对象客户视图
@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerVo {
    private Integer id; // 主键ID
    private Integer isDeleted; //逻辑删除标记（0：显示；1：隐藏）
    private String customerName; //客户姓名
    private Integer customerSex; //性别 （0-女 1-男）
    private Integer customerAge; //年龄
    private String idCard; //身份证号
    private Integer roomNo; //房间号
    private String buildingNo; //所属楼房
    private Date checkinTime; //入住时间
    private Date expirationDate; //合同到期时间
    private String contactTel; // 联系电话
    private String bedId; //床号
    private String psychosomaticState; //身心状况
    private String attention; //注意事项
    private Date birthDay; //出生日期
    private String height; //身高
    private String weight; //体重
    private String bloodType; //血型
    private String filepath;  //头像路径
    private Integer userId;  //关联系统健康管家(护工)
    private Integer levelId;  //护理等级
    private String familyMember; //家属
    private String nickName; //健康管家(护工)
    private String levelName; //护理级别
    private String bedNo; //床位编号
}
