package com.kane.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kane.entity.dto.BookDTO;
import com.kane.entity.dto.PaginationDTO;
import com.kane.entity.po.Book;
import com.kane.entity.vo.PaginationVO;
import com.kane.mapper.BookMapper;
import com.kane.service.BookService;
import com.kane.utils.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Override
    public void addBook(BookDTO bookDTO) {
        Book book = BeanUtils.copyProperties(bookDTO, Book.class);
        book.setIntroduction("");
        baseMapper.insert(book);
    }

    @Override
    public void updateBook(BookDTO bookDTO) {
        Book book = BeanUtils.copyProperties(bookDTO, Book.class);
        baseMapper.updateById(book);
    }

    @Override
    public BookDTO getBook(Long id) {
        Book book = baseMapper.selectById(id);
        return BeanUtils.copyProperties(book, BookDTO.class);
    }

    @Override
    public PaginationVO<BookDTO> listBook(PaginationDTO vo) {
        // 创建分页对象，设置当前页和每页大小
        Page<Book> page = new Page<>(vo.getPageNow(), vo.getPageSize());

        // 构建查询条件
        LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(vo.getBookName()), Book::getBookName, vo.getBookName());
        queryWrapper.like(StringUtils.isNotBlank(vo.getAuthor()), Book::getAuthor, vo.getAuthor());
        queryWrapper.like(StringUtils.isNotBlank(vo.getBookIsbn()), Book::getBookIsbn, vo.getBookIsbn());
        queryWrapper.like(StringUtils.isNotBlank(vo.getSubCategory()), Book::getSubCategory, vo.getSubCategory());
        queryWrapper.eq(vo.getCategoryId() != null, Book::getCategoryId, vo.getCategoryId());

        // 执行分页查询
        Page<Book> bookPage = baseMapper.selectPage(page, queryWrapper);

        // 将查询结果转换为分页VO对象
        PaginationVO<BookDTO> paginationVO = new PaginationVO<>();
        paginationVO.setTotal(bookPage.getTotal());         // 设置总记录数
        paginationVO.setPageNow(bookPage.getCurrent());     // 设置当前页码
        paginationVO.setPageSize(bookPage.getSize());       // 设置每页大小
        paginationVO.setTotalPage(bookPage.getPages());     // 设置总页数

        // 遍历查询结果，将每个Book实体转换为BookDTO并添加到分页VO的数据列表中
        bookPage.getRecords().forEach(book -> {
            BookDTO bookDTO = BeanUtils.copyProperties(book, BookDTO.class);
            paginationVO.getData().add(bookDTO);
        });
        return paginationVO;
    }
}
