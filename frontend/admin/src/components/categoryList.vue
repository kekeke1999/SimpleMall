<template>
  <el-container style="height: 100%; border: 1px solid #eee">

    <el-aside width="180px" style="background-color: #eee;height: 1080px">
      <el-menu :default-openeds="['3','4']" background-color="#eee" style="text-align: left" router>
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
              <el-breadcrumb-item>类别管理</el-breadcrumb-item>
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
          <el-tab-pane label="所有类别">
            <el-button type="primary"  @click="addUser()"  round style="margin-bottom: 10px">新增类别</el-button>
            <el-table :data="tableData" border>
              <el-table-column property="categoryId" label="种类ID" width="200">
              </el-table-column>
              <el-table-column property="categoryName" label="种类名称" width="400">
              </el-table-column>
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <el-button
                      size="mini"
                      @click="editUser(scope.row)">编辑</el-button>
                  <el-button
                      size="mini"
                      type="danger"
                      @click="deleteUser(scope.row.categoryId)">删除</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
        </el-tabs>
      </el-main>
    </el-container>

    <!-- 新增用户的弹框 -->
    <el-dialog title="新增类别" :visible.sync="addFormVisible">
      <el-form ref="addForm" :model="addForm" label-width="80px">
        <el-form-item  prop="categoryName"  label="姓名："    label-width="100px">
          <el-input v-model="addForm.categoryName" ></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="addFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveAdd()">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 编辑用户的弹框 -->
    <el-dialog title="编辑用户" :visible.sync="editFormVisible">
      <el-form ref="editForm" :model="editForm" label-width="80px">
        <el-form-item  prop="categoryId"  label="ID："   label-width="100px">
          <el-input v-model="editForm.categoryId" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item  prop="categoryName"  label="姓名："    label-width="200px">
          <el-input v-model="editForm.categoryName" ></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="editFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveEdit()">确 定</el-button>
      </div>
    </el-dialog>
  </el-container>

</template>

<style>
.el-header {

  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>

<script>
import { Message } from "element-ui";

export default {
  name: "category",
  data() {
    return {
      tableData: [],    // 用户列表数据
      username: "",
      addFormVisible: false, // 新增页面 （默认不显示）
      editFormVisible: false, // 编辑页面 （默认不显示）
      addForm: {
        categoryName: ""
      },
      editForm: {
        categoryName: ""
      },

      row_data: {} // 传给编辑组件的数据
    };
  },

  // 子组件引用
  components: {
  },


  created() {
    this.initData();
    this.initData2();
    this.getUsername();
  },
  methods: {
    exit(){
      //退出登录，清空token
      localStorage.removeItem('Authorization');
      this.$router.push('/login');
    },
    initData() {
      this.$axios({
        method: "get",
        url: 'http://localhost:8080/categories'
      })
          .then(res => {
            console.log(" 种类列表 =", res.data);
            this.tableData = res.data.data;
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
    initData2() {
      this.$axios({
        method: "get",
        url: 'http://localhost:8080/categories'
      })
          .then(res => {
            console.log(" 种类列表 =", res.data);
            this.tableData = res.data.data;
            console.log(res.data);
          })
          .catch(error => {
            console.log(error);
          });
    },
    // 新增用户
    saveAdd() {
      console.log("保存新增类别");
      this.$axios({
        method: "post",
        dataType: "json",
        url: "http://localhost:8080/category",
        params: {
          categoryName: this.addForm.categoryName
        }
      })
          .then(res => {
            console.log("返回的数据 =", res);
            this.addFormVisible = false; // 关闭弹窗
            Message({
              message: "添加成功！",
              type: "success",
              duration: 1 * 1000
            });
            this.$refs.addForm.resetFields();  // 清除form缓存
            this.initData();  // 刷新表单
          })
          .catch(error => {
            console.log(error);
          });
    },

    //用户编辑
    saveEdit() {
      this.$axios({
        method: "put",
        dataType: "json",
        url: "http://localhost:8080/category",
        params:{
          'categoryId': this.editForm.categoryId,
          'categoryName': this.editForm.categoryName
        }
      })
          .then(res => {
            console.log("返回的数据 =", res);
            this.editFormVisible = false; // 关闭弹窗
            Message({
              message: "编辑成功！",
              type: "success",
              duration: 1 * 1000
            });
            this.$refs.editForm.resetFields();  // 清除form缓存
            this.initData();   // 刷新列表
          })
          .catch(error => {
            console.log(error);
          });
    },

    // 新增用户
    addUser() {
      console.log("新增用户");
      this.addFormVisible = true;
    },

    // 编辑用户
    editUser(row) {
      this.editFormVisible = true;
      this.editForm.categoryName = row.categoryName;
      this.editForm.categoryId = row.categoryId;
    },

    // 删除用户
    deleteUser(categoryId) {
      console.log("删除操作");
      this.$confirm("将永久删除该用户, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$axios({
          method: "delete",
          dataType: "json",
          url: "http://localhost:8080/category",
          params:{
            'categoryId': categoryId
          }
        })
            .then(res => {
              console.log("返回的数据 =", res);
              this.$message({
                type: "success",
                message: "删除成功!"
              });
              this.initData();  // 刷新列表
            })
            .catch(error => {
              console.log(error);
            });
      });
    }
  }
};
</script>
