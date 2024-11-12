package org.gdutgoodfish.goodfish.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.gdutgoodfish.goodfish.bean.Favorites;

import java.util.List;

@Mapper
public interface FavoritesMapper {

    @Insert("insert into favorites (user_id, item_id) value (#{userId}, #{itemId})")
    void insertFavorites(Favorites favorites);

    @Delete("delete from favorites where user_id = #{userId} and item_id = #{itemId}")
    void deleteById(Favorites favorites);

    @Select("select item_id from favorites where user_id = #{userId}")
    List<Integer> selectItemIds(Integer userId);
}
