package cn.sliew.http.stream.service.enums;

import lombok.Getter;

@Getter
public enum JstMethodEnum {

    SHOPS_QUERY("shops.query", "店铺查询"),
    CATEGORY_QUERY("category.query", "商品类目查询"),
    COMBINE_SKU_QUERY("combine.sku.query", "组合商品查询"),
    MALL_ITEM_QUERY("mall.item.query", "普通商品查询（按款查询）"),
    SKUMAP_QUERY("skumap.query", "商品映射查询"),
    SKU_QUERY("sku.query", "普通商品资料查询（按sku查询）"),
    WMS_PARTNER_QUERY("wms.partner.query", "仓库查询"),
    INVENTORY_COUNT_QUERY("inventory.count.query", "库存盘点查询"),
    INVENTORY_QUERY("inventory.query", "库存查询"),
    PACK_QUERY("pack.query", "箱及仓位库存查询-按照修改时间查询"),
    PURCHASE_QUERY("purchase.query", "采购单查询"),
    PURCHASEIN_QUERY("purchasein.query", "采购入库查询"),
    PURCHASEOUT_QUERY("purchaseout.query", "采购退货查询"),
    SUPPLIER_QUERY("supplier.query", "供应商查询"),
    LOGISTIC_QUERY("logistic.query", "发货信息查询"),
    AFTERSALE_RECEIVED_QUERY("aftersale.received.query", "实际收货查询"),
    ALLOCATE_QUERY("allocate.query", "调拨单查询"),
    ORDER_ACTION_QUERY("order.action.query", "订单操作日志查询"),
    OTHER_INOUT_QUERY("other.inout.query", "其它出入库查询"),
    JUSHUITAN_INOUT_WATER_QUERY("jushuitan.inout.water.query", "进出仓流水"),
    JUSHUITAN_TRACKINFO_QUERY("jushuitan.trackinfo.query", "通过唯一码查询物流日志"),

    ORDERS_SINGLE_QUERY("orders.single.query", "订单查询"),
    REFUND_SINGLE_QUERY("refund.single.query", "退货退款查询"),
    ORDERS_OUT_SIMPLE_QUERY("orders.out.simple.query", "销售出库查询"),

    ORDERS_HISTORY_QUERY("orders.history.query", "历史订单查询"),
    REFUND_HISTORY_QUERY("refund.history.query", "历史退货退款查询"),
    JUSHUITAN_SALEOUT_HISTORY_QUERY("jushuitan.saleout.history.query", "历史销售出库单查询"),

    JUSHUITAN_ORDER_LIST_QUERY("jushuitan.order.list.query", "聚水潭奇门云订单"),
    JUSHUITAN_REFUND_LIST_QUERY("jushuitan.refund.list.query", "聚水潭奇门云售后"),
    JUSHUITAN_SALEOUT_LIST_QUERY("jushuitan.saleout.list.query", "聚水潭奇门云销售出库"),
    ;

    private String method;
    private String desc;

    JstMethodEnum(String method, String desc) {
        this.method = method;
        this.desc = desc;
    }
}