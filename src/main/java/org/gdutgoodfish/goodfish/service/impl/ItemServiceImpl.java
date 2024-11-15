package org.gdutgoodfish.goodfish.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import lombok.RequiredArgsConstructor;
import org.gdutgoodfish.goodfish.mapper.ItemMapper;
import org.gdutgoodfish.goodfish.pojo.dto.ItemPageQueryDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Item;
import org.gdutgoodfish.goodfish.pojo.entity.Category;
import org.gdutgoodfish.goodfish.pojo.entity.Users;
import org.gdutgoodfish.goodfish.pojo.vo.ItemVO;
import org.gdutgoodfish.goodfish.pojo.vo.PageQueryVO;
import org.gdutgoodfish.goodfish.service.IItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author J
 * @since 2024-11-14
 */
@Service
@RequiredArgsConstructor
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {


    @Override
    public ItemVO getItem(Long itemId) {
        Item item = getById(itemId);
        Long userId = item.getUserId();
        Users users = Db.lambdaQuery(Users.class).eq(Users::getId, userId).one();
        Long categoryId = item.getCategoryId();
        Category category = Db.lambdaQuery(Category.class).eq(Category::getId, categoryId).one();
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(item, itemVO);
        itemVO.setUsername(users.getUsername());
        itemVO.setCategoryName(category.getCategoryName());
        return itemVO;
    }

    @Override
    public PageQueryVO<ItemVO> pageQuery(ItemPageQueryDTO itemPageQueryDTO) {
        IPage<ItemVO> iPage = baseMapper.pageQuery(Page.of(itemPageQueryDTO.getPage(), itemPageQueryDTO.getPageSize()), itemPageQueryDTO);
        return new PageQueryVO<>(iPage.getTotal(), iPage.getRecords());
    }
}
