package xyz.qumn.lb.client.api.request;

import lombok.Data;

@Data
public class CartCreateRequest {

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
}
