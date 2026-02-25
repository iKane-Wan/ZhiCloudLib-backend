package com.kane.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kane.entity.po.BookCategory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookCategoryMapper extends BaseMapper<BookCategory> {
}
