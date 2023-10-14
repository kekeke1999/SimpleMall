<script src="../../assets/js/area.js"></script>
<template>
  <div>
    <van-nav-bar
        title="新增地址"
        left-arrow
        @click-left="onClickLeft"
    />
    <van-address-edit
        :area-list="areaList"
        show-set-default
        :area-columns-placeholder="['请选择', '请选择', '请选择']"
        @save="onSave"
    />
  </div>
</template>

<script>
import areaList from "../../assets/js/area.js";
import { Toast,AddressEdit,NavBar } from 'vant';

export default {
  components: {
    [Toast.name]: Toast,
    [AddressEdit.name]: AddressEdit,
    [NavBar.name]: NavBar
  },
  created() {

  },
  data() {
    return {
      searchResult: [],
      areaList,
    };
  },
  methods: {
    onSave(content) {
      this.name=content.name;
      this.phone=content.tel;
      this.province=content.province;
      this.city=content.city;
      this.region=content.county;
      this.detail_addr=content.addressDetail;
      this.group_message=content.isDefault;
      this.areaCode=content.areaCode;
      console.log(this.areaCode);
      console.log(this.city);
      console.log(this.province);
      this.$axios({
        method: "post",
        dataType: "json",
        url: "http://localhost:8003/address",
        params: {
          name: this.name,
          tel:this.phone,
          province:this.province,
          city:this.city,
          region: this.region,
          detailAddress:this.detail_addr,
          defaultStatus:this.group_message,
          post:this.areaCode,
        }
      })
          .then(res => {
            console.log("返回的数据 =", res);
            Toast('已保存地址！');
            this.$router.push('/address');
          })
          .catch(error => {
            console.log(error);
          });
    },

  },
};
</script>
