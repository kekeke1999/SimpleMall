<template>
  <div class="login">
    <van-nav-bar
        title="登录"
    />
    <br/>
    <van-form @submit="onSubmit">
      <van-field
          v-model="username"
          name="用户名"
          label="用户名"
          placeholder="用户名"
          :rules="[{ required: true, message: '请填写用户名' }]"
      />
      <van-field
          v-model="password"
          type="password"
          name="密码"
          label="密码"
          placeholder="密码"
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

import {
  Form,
  Field,
  NavBar,
  Button,
  Toast
} from "vant";
import {mapMutations} from "vuex";

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
      username: '',
      password: '',
      userToken:'',
    };
  },
  methods: {
    ...mapMutations(['setToken']),
    onSubmit() {
      let v=this;
      this.$axios({
        method: 'post',
        url: 'http://localhost:8003/user/login',
        params:{
          username: v.username,
          password: v.password
        },
      }).then(function(res){
        if(res.data){
          window.console.log(res.data.token);
          v.setToken({ token: res.data.token});

       //   console.log(v.userToken);
          // 将用户token保存到vuex中
          //跳转至其他页面
          v.$router.push('/home');
          Toast.success('登录成功');
          //v.$message('登录成功');
        }else{
          Toast.fail('密码或用户名错误');
        //  v.$message('密码或用户名错误');
        }

      }).catch(function(){
        Toast.fail('密码或用户名错误');
      })
    },
  },
};
</script>
