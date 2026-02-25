package com.kane.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kane.entity.dto.BookCategoryDTO;
import com.kane.entity.po.BookCategory;
import com.kane.mapper.BookCategoryMapper;
import com.kane.service.BookCategoryService;
import com.kane.utils.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 图书分类服务实现类
 * 实现分类相关的业务逻辑
 */
@Service
public class BookCategoryServiceImpl extends ServiceImpl<BookCategoryMapper, BookCategory> implements BookCategoryService {

    /**
     * 添加图书分类
     *
     * @param dto 分类数据传输对象
     */
    @Override
    public void addCategory(BookCategoryDTO dto) {
        BookCategory category = BeanUtils.copyProperties(dto, BookCategory.class);
        baseMapper.insert(category);
    }

    /**
     * 更新图书分类
     *
     * @param dto 分类数据传输对象
     */
    @Override
    public void updateCategory(BookCategoryDTO dto) {
        BookCategory category = BeanUtils.copyProperties(dto, BookCategory.class);
        baseMapper.updateById(category);
    }

    /**
     * 根据ID获取图书分类
     *
     * @param id 分类ID
     * @return 分类数据传输对象
     */
    @Override
    public BookCategoryDTO getCategory(Long id) {
        BookCategory category = baseMapper.selectById(id);
        return BeanUtils.copyProperties(category, BookCategoryDTO.class);
    }

    /**
     * 查询所有图书分类列表
     * 按排序字段升序排列
     *
     * @return 分类列表
     */
    @Override
    public List<BookCategoryDTO> listAllCategory() {
        // 构建查询条件，按排序字段升序
        LambdaQueryWrapper<BookCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(BookCategory::getSort);

        // 查询所有分类
        List<BookCategory> categoryList = baseMapper.selectList(queryWrapper);

        // 转换为DTO列表
        return categoryList.stream()
                .map(category -> BeanUtils.copyProperties(category, BookCategoryDTO.class))
                .collect(Collectors.toList());
    }
}
