<template>
  <el-container style="height: 100%; border: 1px solid #eee">

    <el-aside width="180px" style="background-color: #eee;height: 1080px">
      <el-menu :default-openeds="['4', '3']" active-text-color="#409EFF" background-color="#eee" style="text-align: left" router>
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
              <el-breadcrumb-item></el-breadcrumb-item>
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
            <span style="margin-right: 10px">{{ username }}</span>
          </el-col>
        </el-row>
      </el-header>


      <el-main style="text-align: left">
        <div style="margin-top: 20px; margin-left:20px; font-size: 35px;">
          {{username}},
        </div>
        <div style="margin-top:5px;font-size: 40px; margin-left:20px; color: darkgray; ">
          欢迎光临！
        </div>
        <br />
        <el-row style="margin-top: 15px" :gutter="12">
          <el-col :span="6">
            <el-card class="box-card">
              <el-row>
                <el-col :span="12"><img src="../assets/shangpin.png" style="width: 50%; margin-top: 20px; margin-left: 20px"/></el-col>
                <el-col :span="12">
                  <div style="margin-top: 20px; font-size: 20px; color: darkgray">
                    上架商品数
                  </div><br/>
                  <div style="font-size: 20px">
                    {{ productNum }}
                  </div>
                </el-col>
              </el-row>
            </el-card>
          </el-col>

          <el-col :span="6">
            <el-card class="box-card">
              <el-row>
                <el-col :span="12"><img src="../assets/quehuoshangbao.png" style="width: 50%; margin-top: 20px; margin-left: 20px"/></el-col>
                <el-col :span="12">
                  <div style="margin-top: 20px; font-size: 20px; color: darkgray">
                    缺货商品数
                  </div><br/>
                  <div style="font-size: 20px">
                    {{zeroProduct}}
                  </div>
                </el-col>
              </el-row>
            </el-card>
          </el-col>

          <el-col :span="6">
            <el-card class="box-card">
              <el-row>
                <el-col :span="12"><img src="../assets/dingdandaifukuan.png" style="width: 50%; margin-top: 20px; margin-left: 20px"/></el-col>
                <el-col :span="12">
                  <div style="margin-top: 20px; font-size: 20px; color: darkgray">
                    待付款订单
                  </div><br/>
                  <div style="font-size: 20px">
                    {{unpayedNum}}
                  </div>
                </el-col>
              </el-row>
            </el-card>
          </el-col>

          <el-col :span="6">
            <el-card class="box-card">
              <el-row>
                <el-col :span="12"><img src="../assets/dingdanjihe.png" style="width: 50%; margin-top: 20px; margin-left: 20px"/></el-col>
                <el-col :span="12">
                  <div style="margin-top: 20px; font-size: 20px; color: darkgray">
                    待发货订单
                  </div><br/>
                  <div style="font-size: 20px">
                   {{ undeliveredNum }}
                  </div>
                </el-col>
              </el-row>
            </el-card>
          </el-col>
        </el-row>
        <br />
        <el-row style="margin-top: 15px">
          <el-col :span="24">
            <el-card class="box-card2">
              <div id="main" style="height:400px;"></div>
            </el-card>
          </el-col>
        </el-row>

      </el-main>
    </el-container>


  </el-container>
</template>

<script>

export default {
  name: "home",
  data(){
    return{
      chart: null,
      username:'',
      productNum:'',
      unpayedNum: '',
      undeliveredNum: '',
      zeroProduct:'',
    }
  },
  created() {
    this.getUsername();
    this.getUnpayedNum();
    this.getProductNum();
    this.getZeroProduct();
    this.getUndeliveredNum();
  },
  methods:{
    drawChart() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById("main"));
      // 指定图表的配置项和数据
      let option = {
        title: {
          text: "近一周交易额"
        },
        xAxis: {
          type: 'category',
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: [820, 932, 901, 934, 1290, 1330, 1320],
          type: 'line',
          color: '#409EFF',
          smooth: true
        }]
      };
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    },
    exit(){
      //退出登录，清空token
      localStorage.removeItem('Authorization');
      this.$router.push('/login');
    },
    getUndeliveredNum(){
      this.$axios({
        method: "get",
        url: 'http://localhost:8080/undeliveredNum'
      })
          .then(res => {
            this.undeliveredNum = res.data;
          })
          .catch(error => {
            console.log(error);
          });
    },
    getZeroProduct(){
      this.$axios({
        method: "get",
        url: 'http://localhost:8080/zeroProduct'
      })
          .then(res => {
            this.zeroProduct = res.data;
          })
          .catch(error => {
            console.log(error);
          });
    },
    getUnpayedNum(){
      this.$axios({
        method: "get",
        url: 'http://localhost:8080/unpayedNum'
      })
          .then(res => {
            this.unpayedNum = res.data;
          })
          .catch(error => {
            console.log(error);
          });
    },
    getProductNum(){
      this.$axios({
        method: "get",
        url: 'http://localhost:8080/productNum'
      })
          .then(res => {
            this.productNum = res.data;
          })
          .catch(error => {
            console.log(error);
          });
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
    }
  },
  mounted() {
    this.drawChart();
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

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 25px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}

.box-card {

  height: 150px;
}

.box-card2 {

  height: 400px;
}

</style>
