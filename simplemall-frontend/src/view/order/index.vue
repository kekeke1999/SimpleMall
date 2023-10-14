<template>
  <div>
    <van-nav-bar
        title="全部订单"
        left-arrow
        @click-left="onClickLeft"
    />
    <van-tabs v-model="active">
      <van-tab title="未付款">
        <van-list
            v-model="loading"
            :finished="finished"
            finished-text="没有更多了"
            @load="onLoad"
        >

          <van-cell v-for="orderItem in unpayedList" :key="orderItem" :title="item" @click.stop="onUnpayedDetail(orderItem.orderId)">
            <div v-for="(productItem,index) in orderItem.products" :key="index" class="card-goods__item">
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
                总价：¥{{ orderItem.price }}<br />
              </van-row>
            </div>
            <van-row type="flex" justify="end">
              <van-col span="7"></van-col>
              <van-col span="7"></van-col>
              <van-col span="5"><van-button plain type="primary" size="small">确认支付</van-button></van-col>
            </van-row>
          </van-cell>
        </van-list>
      </van-tab>
      <van-tab title="未发货">
        <van-list
            v-model="loading"
            :finished="finished"
            finished-text="没有更多了"
            @load="onLoad"
        >

          <van-cell v-for="orderItem in undeliveredList" :key="orderItem" :title="item" @click.stop="onUndeliveredDetail(orderItem.orderId)">
            <div v-for="(productItem,index) in orderItem.products" :key="index" class="card-goods__item" >
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
                总价：¥{{ orderItem.price }}<br />
              </van-row>
            </div>
            <van-row type="flex" justify="end">
              <van-col span="7"></van-col>
              <van-col span="7"></van-col>
              <van-col span="5"><van-button plain type="primary" size="small">提醒发货</van-button></van-col>
            </van-row>

          </van-cell>
        </van-list>
      </van-tab>
      <van-tab title="待收货">
        <van-list
            v-model="loading"
            :finished="finished"
            finished-text="没有更多了"
            @load="onLoad"
        >

          <van-cell v-for="orderItem in unconfirmedList" :key="orderItem" :title="item" @click.stop="onUnconfirmedDetail(orderItem.orderId)">
            <div v-for="(productItem,index) in orderItem.products" :key="index" class="card-goods__item">
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
                总价：¥{{ orderItem.price }}<br />
              </van-row>
            </div>
            <van-row type="flex" justify="end">
              <van-col span="7"></van-col>
              <van-col span="7"></van-col>
              <van-col span="5"><van-button plain type="primary" size="small">确认收货</van-button></van-col>
            </van-row>

          </van-cell>
        </van-list>
      </van-tab>
      <van-tab title="已完成">
        <van-list
            v-model="loading"
            :finished="finished"
            finished-text="没有更多了"
            @load="onLoad"
        >

          <van-cell v-for="orderItem in finishedList" :key="orderItem" :title="item" @click.stop="onFinishedDetail(orderItem.orderId)">
            <div v-for="(productItem,index) in orderItem.products" :key="index" class="card-goods__item">
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
                总价：¥{{ orderItem.price }}<br />
              </van-row>
            </div>

          </van-cell>
        </van-list>
      </van-tab>
    </van-tabs>
  </div>
</template>

<script>
import { Toast,Card,NavBar,Button,Tag,List,Cell, CellGroup,Col, Row,Tab, Tabs } from 'vant';

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
    [Tab.name]: Tab,
    [Tabs.name]: Tabs,

  },
  created() {
    this.getQuery1();
    window.console.log("active!"+this.active);
    this.initData1();
    this.initData2();
    this.initData3();
    this.initData4();
  },
  data() {
    return {
      finishedList:[],
      unconfirmedList:[],
      unpayedList:[],
      undeliveredList:[],
      active: null,
      chosenAddressId: '1',
      listData:[],
      list: [],
      loading: false,
      finished: false,
    };
  },
  methods: {
    getQuery1(){
      this.active = this.$route.query.active;
      window.console.log("active:"+this.active);
    },
    onUndeliveredDetail(orderId){
      this.$router.push({
        path: "/undeliveredDetail",
        query: {
          orderId: orderId
        }
      });
    },
    onUnpayedDetail(orderId){
      this.$router.push({
        path: "/unpayedDetail",
        query: {
          orderId: orderId
        }
      });
    },
    onUnconfirmedDetail(orderId){
      this.$router.push({
        path: "/unconfirmedDetail",
        query: {
          orderId: orderId
        }
      });
    },
    onFinishedDetail(orderId){
      this.$router.push({
        path: "/finishedDetail",
        query: {
          orderId: orderId
        }
      });
    },
    onLoad() {
      // 异步更新数据
      // setTimeout 仅做示例，真实场景中一般为 ajax 请求
      setTimeout(() => {
        for (let i = 0; i < 10; i++) {
          this.list.push(this.list.length + 1);
        }

        // 加载状态结束
        this.loading = false;

        // 数据全部加载完成
        if (this.list.length >= 40) {
          this.finished = true;
        }
      }, 1000);
    },
    initData1(){
      this.$axios({
        method: "get",
        url: 'http://localhost:8003/unpayedOrdersDetail',
      })
          .then(res => {
            this.unpayedList = res.data.data;
            //  console.log(res.data);
          });
    },
    initData2(){
      this.$axios({
        method: "get",
        url: 'http://localhost:8003/undeliveredOrdersDetail',
      })
          .then(res => {
            this.undeliveredList = res.data.data;
            //  console.log(res.data);
          });
    },
    initData3(){
      this.$axios({
        method: "get",
        url: 'http://localhost:8003/unconfirmedOrdersDetail',
      })
          .then(res => {
            this.unconfirmedList = res.data.data;
            //  console.log(res.data);
          });
    },
    initData4(){
      this.$axios({
        method: "get",
        url: 'http://localhost:8003/finishedOrdersDetail',
      })
          .then(res => {
            this.finishedList = res.data.data;
            //  console.log(res.data);
          });
    },
    closeOrder(orderId){
      this.$axios({
        method: "put",
        dataType: "json",
        url: "http://localhost:8080/closeOrder",
        params:{
          'orderId': orderId
        }
      })
          .then(res => {
            Toast.success('关闭订单成功！');
            window.console.log("返回的数据 =", res);
            this.initData();   // 刷新列表
          })
          .catch(error => {
            window.console.log(error);
          });
    },
    onAdd() {
      //  Toast('新增地址');
      this.$router.push({name:'/addressSave'});
    },
    onEdit(item, index) {
      //  Toast('编辑地址:' + index);
      window.console.log("addressId："+this.listData[index].id);
      this.$router.push({path:"/addressEdit", query:{addressId: this.listData[index].id}});
    },
    onClickLeft() {
      Toast('返回');
    },
  },
};
</script>
