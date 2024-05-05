# Http-Sync

众多电商、直播、saas 企业会提供开放平台供客户使用 API 对接，支持生态解决方案。对接开放平台就成为了一个技术需求。

作者来自大数据团队，对接众多开放平台，同步企业自身数据供团队和公司数据分析使用，`http-sync` 项目即是通过 `HTTP` 对接开放平台实现数据同步的开源项目。

## 对接过的开放平台

项目中并未添加所有的开放平台，只是添加了个最常见的场景

* 快手
  * [开放平台](https://open.kuaishou.com/platform/openApi?menu=5)
  * [电商开放平台](https://open.kwaixiaodian.com/docs/dev?pageSign=a068e6b0409a9ee55f5b6f5760ff9d391614263559910)
  * [本地生活开放平台](https://open.kwailocallife.com/)
  * [磁力引擎开放平台](https://developers.e.kuaishou.com/welcome)
* [淘宝](https://open.taobao.com/)
* 聚水潭
  * [开放平台-旧](https://open.jushuitan.com/document.html)
  * [开放平台-新](https://openweb.jushuitan.com/index)
* [有赞](https://doc.youzanyun.com/home)
* [京东联盟](https://union.jd.com/openplatform)
* [企业微信](https://developer.work.weixin.qq.com/)
* [北森](https://open.italent.cn/#/open-document?menu=develop-guide)
* [易快报](https://docs.ekuaibao.com/)
* [天眼查](https://open.tianyancha.com/)
* [得物](https://open.dewu.com/)
* [七鱼](https://qiyukf.com/docs/)

### 接口设计

* 时间+分页
  * 时间类型
    * 创建时间、更新时间
    * 业务时间。订单创建时间、库存出库时间、账单结算时间。
  * 分页
    * pageNum + pageSize
    * cursor + pageSize
* 全量数据
  * 店铺列表，店铺内商品列表，仓库列表，部门、人员列表
  * 快照。如发生人员离职情况，昨日接口返回 100 个员工信息，今日只有 99 个员工。部分平台接口此效果，如北森
    * 处理方式有 2 种。第一种：将今日同步的 99 个员工与现有的 100 的员工（其实就是昨天同步的数据）对比，发现有 1 个员工不存在了，则标记为离职。第二种：记录数据同步的日期，并将同步日期作为唯一键之一。如员工表的唯一键应为员工 id，可改为 sync_date + 员工id，存储的数据每日都为一个全量。后续使用的时候需根据场景优化查询，或通过数据处理流程（ETL）产出目前员工信息，所有员工信息，员工在离职记录等数据
* 详情接口。只能通过对应数据唯一键查询的接口，如订单详情、员工详情信息接口

## 同步要点

* 鉴权方式
* 同步方式
* 单线程、多线程
* 数据存储

## 同步任务

* 时间+分页
* 反复同步
* 详情接口
