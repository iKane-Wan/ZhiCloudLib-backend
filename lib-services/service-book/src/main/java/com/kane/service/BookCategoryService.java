package com.kane.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kane.entity.dto.BookCategoryDTO;
import com.kane.entity.po.BookCategory;

import java.util.List;

/**
 * 图书分类服务接口
 * 定义分类相关的业务逻辑方法
 */
public interface BookCategoryService extends IService<BookCategory> {

    /**
     * 添加图书分类
     *
     * @param dto 分类数据传输对象
     */
    void addCategory(BookCategoryDTO dto);

    /**
     * 更新图书分类
     *
     * @param dto 分类数据传输对象
     */
    void updateCategory(BookCategoryDTO dto);

    /**
     * 根据ID获取图书分类
     *
     * @param id 分类ID
     * @return 分类数据传输对象
     */
    BookCategoryDTO getCategory(Long id);

    /**
     * 查询所有图书分类列表
     *
     * @return 分类列表
     */
    List<BookCategoryDTO> listAllCategory();
}
