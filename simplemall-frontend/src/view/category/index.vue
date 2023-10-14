<template>
  <div class="page">
    <van-nav-bar title="商品分类" fixed />
    <div class="container flex" id="container">
      <van-tabs @click="onBar" animated sticky>
        <van-tab :title="menuItem.categoryName" v-for="(menuItem, menuIndex) in menuList" :key="menuIndex">
          <van-list
              v-model="loading"
              :finished="finished"
              finished-text="没有更多了"
              @load="onLoad"
          >
            <van-cell style="background-color: white" v-for="(categoryItem, categoryIndex) in categoryList" @click="onDetail(categoryItem.productId)" :key="categoryIndex">
              <van-row style="background-color: white">
                <van-col span="12">
                  <img class="goods-img" :src="categoryItem.coverImg"  />
                </van-col>
                <van-col span="12">
                  <p class="product_title" style="margin-top: 15px">{{ categoryItem.productName }}</p>
                  <p class="product_price" style="color: #ff4444; margin-top: -5px"><span class="price_pre">¥</span> {{ categoryItem.price }}</p>
                  <van-icon size="20px" style="margin-top: 5px" name="cart-o" @click.stop="onAddCart(categoryItem)" />
                </van-col>
              </van-row>
              <van-divider :style="{marginTop:'0px',color: '#1989fa',}"/>
            </van-cell>
          </van-list>
        </van-tab>
      </van-tabs>
    </div>
    <van-tabbar v-model="active" active-color="#ee0a24" inactive-color="#000">
      <van-tabbar-item icon="shop-o" to="/home">主页</van-tabbar-item>
      <van-tabbar-item icon="cluster-o" to="/category">分类</van-tabbar-item>
      <van-tabbar-item icon="shopping-cart-o" to="/cart">购物车</van-tabbar-item>
      <van-tabbar-item icon="manager-o" to="/user">我的</van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script>

import {Tabbar, TabbarItem, NavBar, Tab, Tabs, Toast, Icon, Col, Row, Divider} from "vant";

export default {
  data() {
    return {
      menuList: [],
      categoryList: [],
      categoryContent: [],
      pageNum: 0,
      category: 2,
      active: 1
    };
  },
  components: {
    [NavBar.name]: NavBar,
    [Tabbar.name]: Tabbar,
    [TabbarItem.name]: TabbarItem,
    [Tab.name]: Tab,
    [Tabs.name]: Tabs,
    [Toast.name]: Toast,
    [Icon.name]: Icon,
    [Col.name]: Col,
    [Row.name]: Row,
    [Divider.name]: Divider
  },
  computed: {},

  created() {
    this.getMenuList();

  },

  mounted() {
    this.getCategoryList();
  },

  methods: {
    getMenuList() {
      this.$axios({
            method: "get",
            url: 'http://localhost:8003/categories',
      })
          .then(res => {
            this.menuList = res.data.data;
          });
      this.getCategoryList();
    },
    getCategoryList() {
      this.$axios({
        method: "get",
        url: 'http://localhost:8080/productCategory',
        params:{
            category: this.category,
        }
      })
          .then(res => {
            this.categoryList = res.data.data;
          })
    //  this.categoryList = res.data.data;
    },
    onBar(index, categoryName) {
      let menuItem = this.menuList.find(function(item) {
        return item.categoryName === categoryName;
      });
      this.category = menuItem.categoryName;
      this.getCategoryList();
    },
 /*   async onAddCart(item) {
      let res = await apiAddCart(item.uid);
      if (res.data.code === 0) {
        Toast.success({
          message: this.$t("m.message.addSuccess"),
          duration: 500
        });
      } else {
        Toast.fail({
          message: res.data.msg,
          position: "bottom"
        });
      }
    },*/
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
    onDetail(productId) {
      this.$router.push({
        path: "/goods",
        query: {
          productId: productId
        }
      });
    }
  }
};
</script>

<style lang="less" scoped>
@import "../../../public/less/variable.less";
.container {
  display: flex;
  align-items: flex-start;
  padding-top: 40px;
  // padding-bottom: 52px;
}
.van-tab__pane {
  padding-bottom: 52px;
}
.van-nav-bar {
  z-index: 1000 !important;
}
.category-item {
  border-bottom: 1px solid #ccc;
  height: 110px;
  width: 100%;
  background: white;
}
.goods-img {
  margin: 10px 10px 10px 10px;
  width: 150px;
  height: 100px;
}
.product_price {
  margin: 10px 0;
}
.van-tabs--line {
  width: 100%;
}
</style>
