<template>
  <div>
    <van-nav-bar
        title="订单详情"
        left-arrow
        @click-left="onClickLeft"
    />
    <br/>
      <van-row>
        <van-col span="3"></van-col>
        <van-col span="13" >
          <div style="margin-top: 14%">未发货</div>

        </van-col>
        <van-col span="8">
          <van-icon name="gift" color="red" size="75"/>
        </van-col>
      </van-row>

    <br/>
      <van-cell>
        <van-row>
          <van-col span="4">
            <van-icon name="location" color="red" size="45"/>
          </van-col>
          <van-col span="20">
              {{listData.name}},{{listData.tel}}
            <br/>
            {{listData.province}}省/市,{{listData.city}}市,{{listData.region}}区,{{listData.detailAddress}}
          </van-col>
        </van-row>
      </van-cell>
    <br/>
      <van-cell>
        <div v-for="(productItem,index) in listData.products" :key="index" class="card-goods__item" >
          <van-card
              :num=productItem.num
              :price=productItem.price
              :title=productItem.productName
              :thumb=productItem.coverImg
          >
          </van-card>
        </div>
        <div style="margin-top: 10px; margin-bottom: 10px">
          <van-row type="flex" justify="end">
            总价：¥{{ listData.price }}<br />
          </van-row>
        </div>
      </van-cell>
    <br/>
      <van-cell>
        订单编号：{{listData.orderId}}
        <br/>
        下单时间：{{listData.createTime}}
        <br/>
        付款时间：{{listData.payTime}}
        <br />
        支付方式：{{listData.payWay}}
      </van-cell>
  </div>
</template>

<script>
import { Toast,Card,NavBar,Button,Tag,List,Cell, CellGroup,Col, Row,Icon } from 'vant';

export default {
  components: {
    [Toast.name]: Toast,
    [Card.name]: Card,
    [Tag.name]: Tag,
    [Button.name]: Button,
    [NavBar.name]: NavBar,
    [List.name]: List,
    [Cell.name]: Cell,
    [CellGroup.name]: CellGroup,
    [Col.name]: Col,
    [Row.name]: Row,
    [Icon.name]: Icon,
  },
  created() {
    this.initData();
  },
  data() {
    return {
      listData:[],
    };
  },
  methods: {
    initData(){
      this.$axios({
        method: "get",
        url: 'http://localhost:8003/oneOrderDetail',
        params:{
          orderId: this.$route.query.orderId
        }
      })
          .then(res => {
            this.listData = res.data.data;
          })
    }
  },
};
</script>

