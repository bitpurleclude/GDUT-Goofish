package org.gdutgoodfish.goodfish.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gdutgoodfish.goodfish.entity.Items;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoritesPageQueryVO {
    private Long total;
    private List<Items> favorites;
}
