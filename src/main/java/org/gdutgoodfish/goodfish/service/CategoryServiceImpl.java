package org.gdutgoodfish.goodfish.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.gdutgoodfish.goodfish.mapper.CategoryMapper;
import org.gdutgoodfish.goodfish.pojo.entity.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {


}
