<template>
  <div>
    <van-image
        round
        width="8rem"
        height="8rem"
        style="margin-left: 37%; margin-top: 30px;"
        src="https://img.yzcdn.cn/vant/cat.jpeg"
    />
    <p style="font-size: xx-large;text-align: center;margin-top: 7px;margin-bottom: 13px">{{username}}</p>
    <van-row class="user-links">
      <van-col span="6">
        <van-icon name="pending-payment" @click.stop="onUnpayed()" />
        待付款
      </van-col>
      <van-col span="6">
        <van-icon name="tosend" @click.stop="onUndelivered()"/>
        待发货
      </van-col>
      <van-col span="6">
        <van-icon name="logistics" @click.stop="onUnconfirmed()"/>
        已发货
      </van-col>
      <van-col span="6">
        <van-icon name="completed" @click.stop="onFinished()"/>
        已完成
      </van-col>
    </van-row>

    <van-cell-group class="user-group">
      <van-cell icon="records" title="全部订单" to="/order" />
      <van-cell icon="user-o" title="个人信息" to="/me" />
      <van-cell icon="logistics" title="收货地址" to="/address" />
      <van-cell icon="down" title="退出登录" @click="exit" />
    </van-cell-group>

    <van-tabbar v-model="active" active-color="#ee0a24" inactive-color="#000">
      <van-tabbar-item icon="shop-o" to="/home">主页</van-tabbar-item>
      <van-tabbar-item icon="cluster-o" to="/category">分类</van-tabbar-item>
      <van-tabbar-item icon="shopping-cart-o" to="/cart">购物车</van-tabbar-item>
      <van-tabbar-item icon="manager-o" to="/user">我的</van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script>
import {Row, Col, Icon, Cell, CellGroup, Tabbar, TabbarItem, Image } from 'vant';

export default {
  components: {
    [Row.name]: Row,
    [Col.name]: Col,
    [Icon.name]: Icon,
    [Cell.name]: Cell,
    [CellGroup.name]: CellGroup,
    [Tabbar.name]: Tabbar,
    [TabbarItem.name]: TabbarItem,
    [Image.name]: Image,
  },
  data(){
    return{
      active:3,
      username:'',
    }
  },
  created() {
    this.getUsername();
  },
  methods:{
    onUnpayed(){
      this.$router.push({
        path: "/order",
        query: {
          active: 0
        }
      });
    },
    onUndelivered(){
      this.$router.push({
        path: "/order",
        query: {
          active: 1
        }
      });
    },
    onUnconfirmed(){
      this.$router.push({
        path: "/order",
        query: {
          active: 2
        }
      });
    },
    onFinished(){
      this.$router.push({
        path: "/order",
        query: {
          active: 3
        }
      });
    },
    exit(){
      //退出登录，清空token
      localStorage.removeItem('token');
      this.$router.push('/login');
    },
    getUsername(){
      this.$axios({
        method: "get",
        url: 'http://localhost:8003/username'
      })
          .then(res => {
            this.username = res.data;
          })
          .catch(error => {
            window.console.log(error);
          });
    },
  }
};
</script>

<style lang="less">
.user {
  &-poster {
    width: 100%;
    height: 53vw;
    display: block;
  }

  &-group {
    margin-bottom: 15px;
  }

  &-links {
    padding: 15px 0;
    font-size: 12px;
    text-align: center;
    background-color: #fff;

    .van-icon {
      display: block;
      font-size: 24px;
    }
  }
}
</style>
