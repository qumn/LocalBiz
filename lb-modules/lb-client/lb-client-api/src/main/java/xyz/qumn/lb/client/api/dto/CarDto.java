package xyz.qumn.lb.client.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.qumn.lb.client.api.enums.CarStatusEnum;
import xyz.qumn.lb.management.api.dto.CommodityDto;
import xyz.qumn.lb.management.api.dto.SpecificationDto;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    /**
     * car id
     */
    private Long carId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 商家id, 冗余字段方便查询
     */
    private Long mid;
    /**
     * 商品id
     */
    private Long cid;
    /**
     * 商品规格id
     */
    private Long sid;
    /**
     * 数量
     */
    private Integer count;
    /**
     * 状态, 是否被选中
     */
    private CarStatusEnum status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

    private CommodityDto commodity;

    private SpecificationDto specDto;
}

