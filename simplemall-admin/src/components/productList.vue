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
          <el-tab-pane label="已上架商品">
            <el-input
                placeholder="请输入商品名称关键字"
                suffix-icon="el-icon-goods"
                v-model="input"
                style="width: 300px; margin-bottom: 20px; margin-right: 20px;"
                clearable>
            </el-input>
            <el-button icon="el-icon-search"
                       circle style="margin-right: 20px"
                       @click="search()"></el-button>
            <el-table :data="tableData" border>
              <el-table-column property="productId" label="商品ID" width="80">
              </el-table-column>
              <el-table-column property="productName" label="缩略图" width="120">
                <template width="90" slot-scope="scope">
                  <img style="width:80px;height:80px;border:none;" :src="scope.row.coverImg">
                </template>
              </el-table-column>
              <el-table-column property="productName" label="商品名" width="120">
              </el-table-column>
              <el-table-column property="price" label="价格" width="100">
              </el-table-column>
              <el-table-column property="productCount" label="库存数" width="90">
              </el-table-column>
              <el-table-column property="category" label="品类">
              </el-table-column>
              <el-table-column property="intro" label="介绍">
              </el-table-column>
              <el-table-column property="productName" label="细节图" width="120">
                <template width="90" slot-scope="scope">
                  <img style="width:80px;height:80px;border:none;" :src="scope.row.img">
                </template>
              </el-table-column>

              <el-table-column label="操作">
                <template slot-scope="scope">
                  <el-button
                      size="mini"
                      @click="editUser(scope.row)">编辑</el-button>
                  <el-button
                      size="mini"
                      type="success"
                      @click="offShelf(scope.row.productId)">下架</el-button>
                  <el-button
                      size="mini"
                      type="danger"
                      @click="deleteUser(scope.row.productId)">删除</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>

          <el-tab-pane label="新增商品">
            <el-form ref="productForm" :model="productForm" label-width="150px" style="margin-top: 20px">
              <el-form-item label="商品名称">
                <el-input v-model="productForm.productName" style="width: 300px"></el-input>
              </el-form-item>
              <el-form-item label="种类">
                <el-select v-model="productForm.categoryData" @change="selectChange" placeholder="请选择商品种类" style="width: 300px">
                  <el-option
                    v-for="category in categories"
                    :key="category.categoryId"
                    :label="category.categoryName"
                    :value="category.categoryName">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="价格（单位:元）">
                <el-input v-model="productForm.price" style="width: 300px"></el-input>
              </el-form-item>
              <el-form-item label="上传封面图">
                <el-upload
                    class="upload-demo"
                    action="http://localhost:8080/uploadFile"
                    :on-preview="handlePreview"
                    :on-remove="handleRemove"
                    :on-success="imgSuccess"
                    :file-list="fileList"
                    list-type="picture"
                    limit="1">
                  <el-button size="small" type="primary">点击上传</el-button>
                  <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
                </el-upload>
              </el-form-item>
              <el-form-item label="库存数">
                <el-input v-model="productForm.productCount" style="width: 300px"></el-input>
              </el-form-item>
              <el-form-item label="商品简介">
                <el-input v-model="productForm.intro" style="width: 300px"></el-input>
              </el-form-item>
              <el-form-item label="上传商品详情图">
                <el-upload
                    class="upload-demo"
                    action="http://localhost:8080/uploadFile"
                    :on-preview="handlePreview"
                    :on-remove="handleRemove"
                    :on-success="img2Success"
                    :file-list="fileList"
                    list-type="picture"
                    limit="1">
                  <el-button size="small" type="primary">点击上传</el-button>
                  <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
                </el-upload>
              </el-form-item>

              <el-form-item>
                <el-button type="primary" @click="onSubmit">确定</el-button>
                <el-button>取消</el-button>
              </el-form-item>
            </el-form>
          </el-tab-pane>
        </el-tabs>


      </el-main>
    </el-container>

    <el-dialog title="编辑用户" :visible.sync="editFormVisible">
      <el-form ref="editForm" :model="editForm" label-width="80px">
        <el-form-item label="商品ID">
          <el-input v-model="editForm.productId" style="width: 300px" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="商品名称">
          <el-input v-model="editForm.productName" style="width: 300px"></el-input>
        </el-form-item>
        <el-form-item label="种类">
          <el-select v-model="editForm.categoryData" @change="selectChange" placeholder="请选择商品种类" style="width: 300px">
            <el-option
                v-for="category in categories"
                :key="category.categoryId"
                :label="category.categoryName"
                :value="category.categoryName">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="价格（单位:元）">
          <el-input v-model="editForm.price" style="width: 300px"></el-input>
        </el-form-item>
        <el-form-item label="上传封面图">
          <el-upload
              class="upload-demo"
              action="http://localhost:8080/uploadFile"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              :on-success="imgSuccess"
              :file-list="fileList"
              list-type="picture"
              limit="1">
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </el-form-item>
        <el-form-item label="库存数">
          <el-input v-model="editForm.productCount" style="width: 300px"></el-input>
        </el-form-item>
        <el-form-item label="商品简介">
          <el-input v-model="editForm.intro" style="width: 300px"></el-input>
        </el-form-item>
        <el-form-item label="上传商品详情图">
          <el-upload
              class="upload-demo"
              action="http://localhost:8080/uploadFile"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              :on-success="img2Success"
              :file-list="fileList"
              list-type="picture"
              limit="1">
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
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
  name: "product",
  data() {
    return {
      userId:"",
      username: "",
      uploadData: {},
      fileList: [0,0],
      imgUrl: "",
      coverImgUrl: "",
      input:'',
      tableData: [],    // 用户列表数据
      allProductsTable: [],
      addFormVisible: false, // 新增页面 （默认不显示）
      editFormVisible: false, // 编辑页面 （默认不显示）
      addForm: {
        categoryName: ""
      },
      editForm: {
        productId: "",
        productName: "",
        categoryData: "",
        price: "",
        productCount: "",
        intro: "",
      },
      categories:"",
      productForm:{
        productName: "",
        categoryData: "",
        price: "",
        productCount: "",
        intro: "",
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
    this.getCategories();
    this.getUsername();
    this.getUserId();
  },
  methods: {
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
    getUserId(){
      this.$axios({
        method: "get",
        url: 'http://localhost:8080/userId'
      })
          .then(res => {
            this.userId = res.data;
          })
          .catch(error => {
            console.log(error);
          });
    },
    img2Success(response){
        this.coverImgUrl = response;
    },
    imgSuccess(response){
        this.imgUrl = response;
    },
    onSubmit(){
      console.log("保存新商品" + this.fileList[0]);
      this.$axios({
        method: "post",
        dataType: "json",
        url: "http://localhost:8080/product",
        params: {
          userId: this.userId,
          productName: this.productForm.productName,
          category: this.productForm.categoryData,
          price: this.productForm.price,
          coverImg: this.coverImgUrl,
          intro: this.productForm.intro,
          img: this.imgUrl,
          productCount: this.productForm.productCount
        }
      })
          .then(res => {
            console.log("返回的数据 =", res);
            Message({
              message: "添加成功！",
              type: "success",
              duration: 1 * 1000
            });
            this.$refs.productForm.resetFields();  // 清除form缓存
            this.initData();  // 刷新表单
          })
          .catch(error => {
            console.log(error);
          });
    },
    selectChange(){

    },
    handleChange(){

    },
    getCategories(){
      this.$axios({
        method: "get",
        dataType: "json",
        url: "http://localhost:8080/categories",
      })
          .then(res => {
            this.categories = res.data.data;   // 刷新列表
          })
          .catch(error => {
            console.log(error);
          });
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },

    search(){
      if (this.input){
        this.$axios({
          method: "get",
          dataType: "json",
          url: "http://localhost:8080/searchedProducts",
          params:{
            'word': this.input,
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
    exit(){
      //退出登录，清空token
      localStorage.removeItem('Authorization');
      this.$router.push('/login');
    },
    initData() {
      this.$axios({
        method: "get",
        url: 'http://localhost:8080/productsByUserId'
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
    initData2() {
      this.$axios({
        method: "get",
        url: 'http://localhost:8080/products'
      })
          .then(res => {
            console.log(" 商品总列表 =", res.data);
            this.allProductsTable = res.data.data;
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
            this.addFormVisible = false; // 关闭弹窗a
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
        url: "http://localhost:8080/product",
        params:{
          userId: this.userId,
          productId: this.editForm.productId,
          productName: this.editForm.productName,
          category: this.editForm.categoryData,
          price: this.editForm.price,
          coverImg: this.coverImgUrl,
          intro: this.editForm.intro,
          img: this.imgUrl,
          productCount: this.editForm.productCount
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
      this.editForm.productId = row.productId;
      this.editForm.productName = row.productName;
      this.editForm.categoryData = row.category;
      this.editForm.price = row.price;
      this.editForm.intro = row.intro;
      this.editForm.productCount = row.productCount;
    },

    // 删除用户
    deleteUser(productId) {
      console.log("删除操作");
      this.$confirm("将永久删除该用户, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$axios({
          method: "delete",
          dataType: "json",
          url: "http://localhost:8080/product",
          params:{
            'productId': productId
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
    },
    offShelf(productId) {
      console.log("下架操作");
      this.$confirm("将下架该商品, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$axios({
          url: "http://localhost:8080/offShelf",
          params:{
            'productId': productId
          }
        })
            .then(res => {
              console.log("返回的数据 =", res);
              this.$message({
                type: "success",
                message: "下架成功!"
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
