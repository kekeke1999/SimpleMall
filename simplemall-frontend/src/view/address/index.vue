<template>
  <div>
    <van-nav-bar
        title="地址列表"
        left-arrow
        @click-left="onClickLeft"
    />
    <van-address-list
        :list="listData"
        v-model="chosenAddressId"
        default-tag-text="默认"
        @add="onAdd"
        @edit="onEdit"
    />
  </div>
</template>

<script>
import { Toast,AddressList,NavBar } from 'vant';

export default {
  components: {
    [Toast.name]: Toast,
    [AddressList.name]: AddressList,
    [NavBar.name]: NavBar
  },
  created() {
    this.initData();
  },
  data() {
    return {
      chosenAddressId: '1',
      listData:[],
    };
  },
  methods: {
    initData(){
      this.$axios({
        method: "get",
        url: 'http://localhost:8003/addresses',
      })
          .then(res => {
            this.listData = res.data.data;
            //  console.log(res.data);
          });
    },
    onAdd() {
    //  Toast('新增地址');
      this.$router.push("/addressSave");
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
