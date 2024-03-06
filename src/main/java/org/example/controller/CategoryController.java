package org.example.controller;

import org.example.pojo.Category;
import org.example.pojo.Result;
import org.example.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired // automatically inject an instance of the CategoryService into the CategoryController class
    private CategoryService categoryService;
    @PostMapping
    public Result add(@RequestBody @Validated(Category.Add.class) Category category){
        // verify parameter
        categoryService.add(category);
        return Result.success();
    }

    @GetMapping
    public Result<List<Category>> list() {
        // call service
        List<Category> cl = categoryService.list();
        return Result.success(cl);
    }

    @GetMapping("/detail")
    public Result<Category> detail(int id) {
        Category c = categoryService.findById(id);
        return Result.success(c);
    }

    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category) {
        categoryService.update(category);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(int id){
        categoryService.delete(id);
        return Result.success();
    }
}
