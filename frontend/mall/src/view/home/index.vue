<template>
  <div class="home">
    <van-nav-bar
        title="Simple Shop"
    />

    <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
      <van-swipe-item>1</van-swipe-item>
      <van-swipe-item>2</van-swipe-item>
      <van-swipe-item>3</van-swipe-item>
      <van-swipe-item>4</van-swipe-item>
    </van-swipe>
    <p>&ensp;热门推荐</p>

  <!--  <van-grid :column-num="3" square>
      <van-grid-item v-for="(productItem, productIndex) in productList" :key="productIndex">
        <img :src="productItem.coverImg" style="width: 70%"/>
        <h1>{{ productItem.productName }}</h1>

      </van-grid-item>
    </van-grid>-->

   <van-grid :column-num="2" square>
      <van-grid-item v-for="(productItem, productIndex) in productList" :key="productIndex" @click.stop="onProductDetail(productItem.productId)">
        <img :src="productItem.coverImg" alt style="width: 180px; height:120px;margin-left: 0px"/>
        <div>
          <div class="product_item_text">
            <div class="product_title">{{ productItem.productName }}</div>
            <div class="product_price">
              <div><span class="price_pre">¥</span>{{ productItem.price }}
              </div>
            </div>
          </div>
        </div>
        <van-icon size="10px" name="cart-o" @click.stop="onAddCart(productItem)" style="margin-right: 0px"/>
      </van-grid-item>
    </van-grid>
    <h1>sssss</h1>

  <!--  <div class="content flex">
      <div v-for="(productItem, productIndex) in productList" class="product_item" :key="productIndex" @click.stop="onProductDetail(productItem.uid)">
        <img :src="productItem.coverImg" alt/>
        <div class="flex-space">
          <div class="product_item_text overflow_hidden">
            <div class="product_title">{{ productItem.productName }}</div>
            <div class="product_price flex">
              <div><span class="price_pre">¥</span>{{ productItem.price }}</div>
            </div>
          </div>
          <div class="goods-cartBox">
            <van-icon size="20px" style="margin-right: 10px" name="cart-o" />
          </div>
        </div>
      </div>
    </div>-->

    <van-tabbar v-model="active" active-color="#ee0a24" inactive-color="#000">
      <van-tabbar-item icon="shop-o" to="/home">主页</van-tabbar-item>
      <van-tabbar-item icon="cluster-o" to="/category">分类</van-tabbar-item>
      <van-tabbar-item icon="shopping-cart-o" to="/cart">购物车</van-tabbar-item>
      <van-tabbar-item icon="manager-o" to="/user">我的</van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script>
import { NavBar, Toast, Swipe, SwipeItem, Tabbar, TabbarItem, Grid, GridItem, Icon, Col, Row } from 'vant';

export default {
  name: "home",
  components: {
    [NavBar.name]: NavBar,
    [Swipe.name]: Swipe,
    [SwipeItem.name]: SwipeItem,
    [Tabbar.name]: Tabbar,
    [TabbarItem.name]: TabbarItem,
    [Grid.name]: Grid,
    [GridItem.name]: GridItem,
    [Icon.name]: Icon,
    [Col.name]: Col,
    [Row.name]: Row
  },

  data(){
    return {
      active: 0,
      productList:[],
    }
  },

  mounted() {
    this.getProductData();
  },

  methods:{
    getProductData(){
      this.$axios({
        method: "get",
        url: 'http://localhost:8003/products'
      })
          .then(res => {
            this.productList = res.data.data;
          })
    },
    onClickLeft() {
      Toast('返回');
    },
    onClickRight() {
      Toast('按钮');
    },
    onProductDetail(productId) {
      this.$router.push({
        path: "/goods",
        query: {
          productId: productId
        }
      });
    },
  }
}
</script>

<style lang="less" scoped>
@import "../../../public/less/variable.less";
.my-swipe .van-swipe-item {
  color: #fff;
  font-size: 20px;
  line-height: 200px;
  text-align: center;
  background-color: #39a9ed;
}
.content {
  flex-wrap: wrap;
  padding-bottom: 52px;
}
.product_item {
  width: 50%;
  box-sizing: border-box;
  border-right: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
  padding-bottom: 4px;
  background: white;
  &:nth-of-type(even) {
    border-right: none;
  }
  img {
    width: 100%;
    height: 180px;
  }
}
.product_item_text {
  margin-left: 0px;
  color: #666;
  padding: 1px 8px;
  font-size: 13px;
}
</style>
