package org.gdutgoodfish.goodfish.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.gdutgoodfish.goodfish.mapper.CategoryMapper;
import org.gdutgoodfish.goodfish.pojo.entity.Category;
import org.gdutgoodfish.goodfish.service.ICategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {


}
