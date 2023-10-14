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
              <el-breadcrumb-item>个人信息</el-breadcrumb-item>
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
        <el-tabs type="border-card">

          <el-tab-pane label="修改个人信息">

            <el-form ref="formData" :model="formData" label-width="150px" style="margin-top: 20px">
              <el-form-item label="用户ID">
                <el-input v-model="formData.userId" style="width: 300px" :disabled="true"></el-input>
              </el-form-item>

              <el-form-item label="用户名">
                <el-input v-model="formData.username" style="width: 300px"></el-input>
              </el-form-item>
              <el-form-item label="性别">
                <el-select v-model="formData.sex" @change="selectChange" placeholder="请选择商品种类" style="width: 300px">
                  <el-option label="男" value="男"></el-option>
                  <el-option label="女" value="女"></el-option>
                </el-select>
              </el-form-item>

              <el-form-item label="密码">
                <el-input v-model="formData.password" style="width: 300px"></el-input>
              </el-form-item>

              <el-form-item label="手机号码">
                <el-input v-model="formData.phoneNumber" style="width: 300px"></el-input>
              </el-form-item>
              <el-form-item label="个人简介">
                <el-input v-model="formData.intro" style="width: 300px"></el-input>
              </el-form-item>

              <br />
              <br />
              <el-form-item>
                <el-button type="primary" @click="onSubmit">确定</el-button>
                <el-button>取消</el-button>
              </el-form-item>
            </el-form>
          </el-tab-pane>
        </el-tabs>
      </el-main>
    </el-container>


  </el-container>
</template>

<script>
import {Message} from "element-ui";

export default {
  name: "info",
  data(){
    return {
      formData:{
        userId:"",
        username:"",
        password:"",
        phoneNumber:"",
        intro:"",
        sex:"",
      },
      username:'',
    }
  },
  created() {
    this.initData2();
    this.getUsername();
  },
  methods:{
    initData2(){
      this.$axios({
        method: "get",
        url: 'http://localhost:8080/user'
      })
          .then(res => {
            this.formData = res.data.data;
            console.log(res.data);
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
    },
    onSubmit(){
      this.$axios({
        /*userId:"",
        username:"",
        password:"",
        phoneNumber:"",
        intro:"",
        sex:"",*/
        method: "put",
        dataType: "json",
        url: "http://localhost:8080/user",
        params: {
          userId: this.formData.userId,
          username: this.formData.username,
          password: this.formData.password,
          phoneNumber: this.formData.phoneNumber,
          intro: this.formData.intro,
          sex: this.formData.sex,
        }
      })
          .then(res => {
            console.log("返回的数据 =", res);
            Message({
              message: "修改成功！请重新登录！！",
              type: "success",
              duration: 1 * 1000
            });
            this.$router.push('/login');
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
