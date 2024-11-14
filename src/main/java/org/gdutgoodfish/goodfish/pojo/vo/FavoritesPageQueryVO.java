package org.gdutgoodfish.goodfish.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gdutgoodfish.goodfish.pojo.entity.Items;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoritesPageQueryVO {
    private Long total;
    private List<Items> favorites;
}
