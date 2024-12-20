<template>
  <el-container style="height: 100%; border: 1px solid #eee">

    <el-aside width="180px" style="background-color: #eee;height: 1080px">
      <el-menu :default-openeds="['3' ,'4']" background-color="#eee" style="text-align: left" router>
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
              <el-breadcrumb-item>订单管理</el-breadcrumb-item>
              <el-breadcrumb-item>已发货订单</el-breadcrumb-item>
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
          <el-tab-pane label="已发货订单">
            <el-input
                placeholder="请输入日期，例如：2020_01_01"
                suffix-icon="el-icon-date"
                v-model="input"
                style="width: 300px; margin-bottom: 20px; margin-right: 20px;"
                clearable>
            </el-input>
            <el-button icon="el-icon-search"
                       circle style="margin-right: 20px"
                       @click="search()"></el-button>
            <el-table :data="tableData" border>
              <el-table-column property="orderId" label="订单编号" width="80">
              </el-table-column>
              <el-table-column property="price" label="总金额" width="90">
              </el-table-column>
              <el-table-column property="createTime" label="创建时间" width="120">
              </el-table-column>
              <el-table-column property="userId" label="用户ID" width="90">
              </el-table-column>
              <el-table-column property="username" label="用户名" width="90">
              </el-table-column>
              <el-table-column property="status" label="状态" width="90">
                <template slot-scope="scope">
                  <el-tag
                      type="success"
                      disable-transitions>{{ scope.row.status }}</el-tag>
                </template>
              </el-table-column>
              <el-table-column property="address" label="收货地址">
              </el-table-column>
              <el-table-column property="payTime" label="付款时间" width="120">
              </el-table-column>
              <el-table-column property="payWay" label="付款方式" width="90">
              </el-table-column>

              <el-table-column label="操作">
                <template slot-scope="scope">
                  <el-button
                      size="mini"
                      type="success"
                      @click="selectDetail(scope.row.orderId)">查看详情</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
        </el-tabs>
      </el-main>
    </el-container>

    <el-dialog title="订单商品" :visible.sync="detailFormVisible">
      <el-table :data="detailData" border>
        <el-table-column property="orderId" label="订单编号" width="80">
        </el-table-column>

        <el-table-column property="productName" label="商品名" width="120">
        </el-table-column>

        <el-table-column property="coverImg" label="封面图" width="120">
          <template width="90" slot-scope="scope">
            <img style="width:80px;height:80px;border:none;" :src="scope.row.coverImg">
          </template>
        </el-table-column>

        <el-table-column property="price" label="价格">
        </el-table-column>

        <el-table-column property="num" label="数量">
        </el-table-column>
      </el-table>
    </el-dialog>


  </el-container>
</template>

<script>
// eslint-disable-next-line no-unused-vars
import {Message} from "element-ui";

export default {
  name: "unconfirmedOrder",
  data(){
    return{
      username: "",
      tableData:[],
      detailData:[],
      detailFormVisible: false,
      input:'',
    //  value1:'',
    }
  },
  created() {
    this.initData();
    this.getUsername();
  },
  methods:{
    search(){
      if (this.input){
        this.$axios({
          method: "get",
          dataType: "json",
          url: "http://localhost:8080/searchOrder",
          params:{
            'word': this.input,
            'status': 3,
          }
        })
            .then(res => {
              console.log("返回的数据 =", res);
              Message({
                message: "搜索成功！",
                type: "success",
                duration: 1 * 1000
              });
              this.tableData = res.data.data;   // 刷新列表
            })
            .catch(error => {
              console.log(error);
            });
      }else{
        this.initData();
      }
    },
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
    selectDetail(orderId){
      this.detailFormVisible = true;
      this.$axios({
        method: "get",
        url: 'http://localhost:8080/orderDetail',
        params:{
          'orderId': orderId
        }
      })
          .then(res => {
            this.detailData = res.data.data;
            console.log(res.data);
          })
          .catch(error => {
            console.log(error);
          });
    },
    initData(){
      this.$axios({
        method: "get",
        url: 'http://localhost:8080/unconfirmedOrders'
      })
          .then(res => {
            console.log(" 已发货订单 =", res.data);
            this.tableData = res.data.data;
            console.log(res.data);
          })
          .catch(error => {
            console.log(error);
          });
    },
    exit(){
      //退出登录，清空token
      localStorage.removeItem('Authorization');
      this.$router.push('/login');
    }
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
