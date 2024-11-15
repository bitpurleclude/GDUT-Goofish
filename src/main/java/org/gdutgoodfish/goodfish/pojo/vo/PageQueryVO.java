package org.gdutgoodfish.goodfish.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageQueryVO<T> {
    private Long total;
    private List<T> record;
}
