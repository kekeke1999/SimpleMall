package com.keke.controller;

import com.keke.domain.Product;
import com.keke.domain.User;
import com.keke.service.ProductService;
import com.keke.utils.AjaxResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    private String url;

    @RequestMapping(value = "/categoryProducts", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    public AjaxResponse  selectProductsByCategory(@RequestParam String category){
        List<Product> products = productService.selectProductsByCategory(category);
        return AjaxResponse.success(products);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    public AjaxResponse selectAllProducts(){
        List<Product> products = productService.selectAllProducts();
        return AjaxResponse.success(products);
    }

    @RequestMapping(value = "/productCategory", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    public AjaxResponse productCategory(@RequestParam("category") String category){
        List<Product> products = productService.selectProductsByCategory(category);
        return AjaxResponse.success(products);
    }


    @RequestMapping(value = "/product", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    public AjaxResponse selectProductById(@RequestParam("productId") Integer productId){
        Product product = productService.selectProductById(productId);
        return AjaxResponse.success(product);
    }

    @RequestMapping("/abc")
    public String abc(){
        return "abc";
    }

    @RequestMapping(value = "/product", method = RequestMethod.PUT, produces="application/json;charset=UTF-8")
    public AjaxResponse updateProduct(@RequestParam Integer productId, @RequestParam String productName, @RequestParam Integer price, @RequestParam Integer productCount, @RequestParam String coverImg, @RequestParam String intro, @RequestParam String category, @RequestParam String img, HttpServletRequest httpServletRequest){
        System.out.println("我在修改商品！");
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        Product product = new Product();
        product.setUserId(user.getUserId());
        product.setProductId(productId);
        product.setCategory(category);
        System.out.println("category:"+category);
        if(coverImg!=""){
            product.setCoverImg(coverImg);
        }
        if(img!=""){
            product.setImg(img);
        }
        product.setIntro(intro);
        System.out.println("price:"+price);
        product.setPrice(new BigDecimal(price.toString()));
        product.setProductCount(productCount);
        product.setProductName(productName);
        System.out.println("product:"+product);
        if(productService.updateProduct(product) == 1){
            return AjaxResponse.success();
        }else{
            return AjaxResponse.fail();
        }
    }

    @RequestMapping(value = "/searchedProducts", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    public AjaxResponse searchProduct(@RequestParam String word){
        List<Product> products = productService.searchProduct(word);
        return AjaxResponse.success(products);
    }

    @RequestMapping(value="/uploadFile")
    public String uploadImage(@RequestBody MultipartFile file){
        System.out.print("上传文件==="+"\n");
        //判断文件是否为空
        if (file.isEmpty()) {
            return null;
        }
        String fileName = file.getOriginalFilename();
        System.out.print("上传的文件名为: "+fileName+"\n");
        String path = "/Users/uu/Documents/MyProjects/upload/" +fileName;
        System.out.print("保存文件绝对路径"+path+"\n");
        File dest = new File(path);
        if (dest.exists()) {
            System.out.println("文件重复");
            return "http://localhost:8080/productImg/" + fileName;
        }
        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            //上传文件
            file.transferTo(dest); //保存文件
            System.out.print("保存文件路径" + path + "\n");
            //url="http://你自己的域名/项目名/images/"+fileName;//正式项目
            url = "http://localhost:8080/productImg/" + fileName;//本地运行项目
            System.out.print("保存的完整url===="+url+"\n");
        } catch (IOException e) {
            return null;
        }

        return url;
    }

    @RequestMapping(value = "/productsByUserId", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    public AjaxResponse selectProductsByUserId(HttpServletRequest httpServletRequest){
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        List<Product> products = productService.selectProductsByUserId(user.getUserId());
        return AjaxResponse.success(products);
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public AjaxResponse insertProduct(@RequestParam String productName, @RequestParam Integer price, @RequestParam Integer productCount, @RequestParam String coverImg, @RequestParam String intro, @RequestParam String category, @RequestParam String img, HttpServletRequest httpServletRequest){
        System.out.println("我在插入商品！");
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        Product product = new Product();
        product.setUserId(user.getUserId());
        product.setCategory(category);
        product.setCoverImg(coverImg);
        product.setImg(img);
        product.setIntro(intro);
        product.setPrice(new BigDecimal(price.toString()));
        product.setProductCount(productCount);
        product.setProductName(productName);
        if(productService.insertProduct(product) == 1){
            return AjaxResponse.success();
        }else{
            return AjaxResponse.fail();
        }
    }

    @RequestMapping(value = "/offShelf", produces="application/json;charset=UTF-8")
    public AjaxResponse offShelf(@RequestParam("productId") Integer productId){
        if(productService.offShelf(productId) == 1){
            return AjaxResponse.success();
        }else{
            return AjaxResponse.fail();
        }
    }

    @RequestMapping(value = "/onShelf", produces="application/json;charset=UTF-8")
    public AjaxResponse onShelf(@RequestParam("productId") Integer productId){
        if(productService.onShelf(productId) == 1){
            return AjaxResponse.success();
        }else{
            return AjaxResponse.fail();
        }
    }

    @RequestMapping(value = "/product",method = RequestMethod.DELETE, produces="application/json;charset=UTF-8")
    public AjaxResponse deleteProduct(@RequestParam("productId") Integer productId){
        if(productService.deleteProduct(productId) == 1){
            return AjaxResponse.success();
        }else{
            return AjaxResponse.fail();
        }
    }

    @RequestMapping(value = "/productTemp",method = RequestMethod.DELETE, produces="application/json;charset=UTF-8")
    public AjaxResponse deleteFromProductTemp(@RequestParam("productId") Integer productId){
        if(productService.deleteFromProductTemp(productId) == 1){
            return AjaxResponse.success();
        }else{
            return AjaxResponse.fail();
        }
    }

    @RequestMapping(value = "/productsTempByUserId", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    public AjaxResponse selectTempProductsByUserId(HttpServletRequest httpServletRequest){
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        List<Product> products = productService.selectTempProductsByUserId(user.getUserId());
        return AjaxResponse.success(products);
    }

    @RequestMapping("/productNum")
    public String getProductNum(HttpServletRequest httpServletRequest){
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        return String.valueOf(productService.getProductNum(user.getUserId()));
    }

    @RequestMapping("/zeroProduct")
    public String getZeroProduct(){
        return String.valueOf(productService.selectZeroProducts());
    }

    @RequestMapping(value = "/searchedTempProducts", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    public AjaxResponse searchTempProduct(@RequestParam String word){
        List<Product> products = productService.searchTempProduct(word);
        return AjaxResponse.success(products);
    }
}
