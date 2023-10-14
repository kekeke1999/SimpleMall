<template>
  <div class="me">
    <van-nav-bar
        title="个人信息"
    />

    <van-form @submit="onSubmit">
      <van-field
          v-model="formData.userId"
          name="用户ID"
          label="用户ID"
          placeholder="用户ID"
          :rules="[{ required: true, message: '请填写用户名' }]"
      />
      <van-field
          v-model="formData.username"
          name="用户名"
          label="用户名"
          placeholder="用户名"
          :rules="[{ required: true, message: '请填写用户名' }]"
      />
      <van-field
          v-model="formData.sex"
          name="性别"
          label="性别"
          placeholder="性别"
          :rules="[{ required: true, message: '请填写用户名' }]"
      />
      <van-field
          v-model="formData.password"
          type="password"
          name="密码"
          label="密码"
          placeholder="密码"
          :rules="[{ required: true, message: '请填写密码' }]"
      />
      <van-field
          v-model="formData.phoneNumber"
          name="手机号码"
          label="手机号码"
          placeholder="手机号码"
          :rules="[{ required: true, message: '请填写密码' }]"
      />
      <van-field
          v-model="formData.intro"
          name="个人简介"
          label="个人简介"
          placeholder="个人简介"
          :rules="[{ required: true, message: '请填写密码' }]"
      />
      <div style="margin: 16px;">
        <van-button round block type="info" native-type="submit">
          提交
        </van-button>
      </div>
    </van-form>
  </div>
</template>

<script>
import {Button, Field, Form, NavBar, Toast} from "vant";

export default {
  components: {
    [NavBar.name]: NavBar,
    [Form.name]: Form,
    [Field.name]: Field,
    [Button.name]: Button,
    [Toast.name]: Toast
  },
  data() {
    return {
      formData:{
        userId:"",
        username:"",
        password:"",
        phoneNumber:"",
        intro:"",
        sex:"",
      },
    };
  },
  created() {
    this.initData2();
  },
  methods: {
    initData2(){
      this.$axios({
        method: "get",
        url: 'http://localhost:8003/user'
      })
          .then(res => {
            this.formData = res.data.data;
          //  console.log(res.data);
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
        url: "http://localhost:8003/user",
        params: {
          userId: this.formData.userId,
          username: this.formData.username,
          password: this.formData.password,
          phoneNumber: this.formData.phoneNumber,
          intro: this.formData.intro,
          sex: this.formData.sex,
        }
      })
          .then(function () {
            Toast.success('修改成功！请重新登录！');
            localStorage.removeItem('token');
            this.$router.push('/login');
          });
    },
  }
}
</script>
