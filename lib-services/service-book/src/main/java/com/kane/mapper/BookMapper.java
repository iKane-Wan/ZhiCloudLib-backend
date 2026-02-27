package com.kane.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kane.entity.po.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper extends BaseMapper<Book> {

    int updateBookIntroductionByID(@Param("id") Long id, @Param("introduction") String introduction);
}
