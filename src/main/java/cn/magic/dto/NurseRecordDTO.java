package cn.magic.dto;

import lombok.Data;
// 护理记录DTO
@Data
public class NurseRecordDTO {
    private Integer customerId; // 客户编号
    private Integer pageSize; // 页码
}
