package org.gdutgoodfish.goodfish.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.gdutgoodfish.goodfish.entity.Items;

import java.util.List;

@Mapper
public interface ItemsMapper {

    Page<Items> pageQuery(List<Integer> ids);

    @Delete("delete from items where user_id = #{userId} and item_id = #{itemId}")
    void deleteById(Integer userId, Integer itemId);

    @Insert("insert into items (item_id, title, description, price, category_id, location, posted_date, user_id) VALUE " +
            "(#{itemId}, #{title}, #{description}, #{price}, #{categoryId}, #{location}, #{postedDate}, #{userId})")
    void insert(Items items);
}
