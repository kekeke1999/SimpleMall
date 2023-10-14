package com.keke.controller;

import com.keke.domain.Category;
import com.keke.service.CategoryService;
import com.keke.utils.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = {"http://localhost:8081","http://localhost:8082"})
public class CategoryController {

    //http://localhost:8081/#/home
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/category", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public AjaxResponse insertCategory(@RequestParam String categoryName){
        if(categoryService.insertCategory(categoryName) == 1){
            return AjaxResponse.success();
        }else {
            return AjaxResponse.fail();
        }
    }

    @RequestMapping(value = "/category",method = RequestMethod.DELETE, produces="application/json;charset=UTF-8")
    public AjaxResponse deleteCategory(@RequestParam("categoryId") Integer categoryId){
        if(categoryService.deleteCategory(categoryId) == 1){
            return AjaxResponse.success();
        }else {
            return AjaxResponse.fail();
        }
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    public AjaxResponse selectAllCategories(){
        return AjaxResponse.success(categoryService.selectAllCategories());
    }

    @RequestMapping(value = "/category", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public AjaxResponse updateCategory(@RequestParam String categoryName, @RequestParam Integer categoryId){
        Category category = new Category();
        category.setCategoryId(categoryId);
        category.setCategoryName(categoryName);
        if(categoryService.updateCategory(category) == 1){
            return AjaxResponse.success();
        }else {
            return AjaxResponse.fail();
        }
    }
}
