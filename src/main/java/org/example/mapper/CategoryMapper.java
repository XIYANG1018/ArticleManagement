package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Insert("insert into category(category_name, category_alias, create_user, create_time, update_time)" +
    "values(#{categoryName}, #{categoryAlias}, #{createUser}, #{createTime}, #{updateTime})")
    void add(Category category);

    // get the list of categories
    @Select("select * from category where create_user=#{userId}")
    List<Category> list(int userId);

    @Select("select * from category where id=#{id}")
    Category findById(int id);

    // 传入的是一个object但是在sql里面直接输入参数名
    @Update("update category set category_name=#{categoryName}, category_alias=#{categoryAlias}, update_time=#{updateTime} where id=#{id}")
    void update(Category category);

    @Delete("delete from category where id=#{id}")
    void delete(int id);

}
