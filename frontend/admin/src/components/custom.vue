<template>
  <el-container style="height: 100%; border: 1px solid #eee">

    <el-aside width="180px" style="background-color: #eee;height: 1080px">
      <el-menu :default-openeds="['4', '3']" background-color="#eee" style="text-align: left" router>
        <el-menu-item index="home">
          <i class="el-icon-menu"></i>首页
        </el-menu-item>
        <el-menu-item index="category">
          <i class="el-icon-star-on"></i>类别管理
        </el-menu-item>
        <el-submenu index="3">
          <template slot="title"><i class="el-icon-s-goods"></i>商品管理</template>
          <el-menu-item-group>
            <el-menu-item index="product">已上架商品</el-menu-item>
            <el-menu-item index="offProducts">下架商品</el-menu-item>
            <el-menu-item index="allProducts">所有商品</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="4">
          <template slot="title"><i class="el-icon-s-order"></i>订单管理</template>
          <el-menu-item-group>
            <el-menu-item index="unpayedOrder">未支付订单</el-menu-item>
            <el-menu-item index="undeliveredOrder">待发货订单</el-menu-item>
            <el-menu-item index="unconfirmedOrder">已发货订单</el-menu-item>
            <el-menu-item index="finishedOrder">已完成订单</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-menu-item index="custom">
          <i class="el-icon-s-custom"></i>顾客列表
        </el-menu-item>
        <el-menu-item index="info">
          <i class="el-icon-user-solid"></i>个人信息
        </el-menu-item>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="height:40px; text-align: right; font-size: 15px">
        <el-row>
          <el-col :span="16">
            <el-breadcrumb separator="/" style="margin-top: 20px;">
              <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
              <el-breadcrumb-item>已上架商品</el-breadcrumb-item>
            </el-breadcrumb>
          </el-col>
          <el-col :span="8">
            <el-dropdown>
              <i class="el-icon-setting" style="margin-right: 15px;"></i>
              <el-dropdown-menu slot="dropdown" >
                <el-dropdown-item>
                  <el-button type="text" @click="exit">登出</el-button>
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
            <el-divider direction="vertical" style="margin-right: 10px"></el-divider>
            <span style="margin-right: 10px">{{username}}</span>
          </el-col>
        </el-row>
      </el-header>

      <el-main style="text-align: left">
        <el-tabs type="border-card">
          <el-tab-pane label="顾客列表">

            <el-table :data="formData" border>
              <el-table-column property="userId" label="顾客ID" width="140">
              </el-table-column>
              <el-table-column property="username" label="顾客名" width="120">
              </el-table-column>
              <el-table-column property="sex" label="性别">
              </el-table-column>
              <el-table-column property="phoneNumber" label="电话号码">
              </el-table-column>
              <el-table-column property="intro" label="介绍">
              </el-table-column>
            </el-table>

          </el-tab-pane>
        </el-tabs>
      </el-main>
    </el-container>


  </el-container>
</template>

<script>
export default {
  name: "custom",
  data(){
    return{
      formData:[],
      username: '',
    }
  },
  created() {
    this.initData();
    this.getUsername();
  },
  methods:{
    getUsername(){
      this.$axios({
        method: "get",
        url: 'http://localhost:8080/username'
      })
          .then(res => {
            this.username = res.data;
          })
          .catch(error => {
            console.log(error);
          });
    },
    exit(){
      //退出登录，清空token
      localStorage.removeItem('Authorization');
      this.$router.push('/login');
    },
    initData(){
      this.$axios({
        method: "get",
        url: 'http://localhost:8080/users'
      })
          .then(res => {
            this.formData = res.data.data;
            console.log(res.data);
          })
          .catch(error => {
            console.log(error);
          });
    },
  }
}
</script>

<style>
.el-header {
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>
