package com.kane.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kane.entity.bo.BookIntroductionBO;
import com.kane.entity.dto.BookDTO;
import com.kane.entity.dto.PaginationDTO;
import com.kane.entity.po.Book;
import com.kane.entity.vo.PaginationVO;

public interface BookService extends IService<Book> {
    /**
     * 添加图书信息
     *
     * @param bookDTO 图书数据传输对象
     */
    void addBook(BookDTO bookDTO);

    /**
     * 更新图书信息
     *
     * @param bookDTO 图书数据传输对象
     */
    void updateBook(BookDTO bookDTO);

    /**
     * 根据ID获取图书信息
     *
     * @param id 图书ID
     * @return 图书数据传输对象
     */
    BookDTO getBook(Long id);

    /**
     * 分页查询图书列表
     *
     * @param vo 分页参数对象
     * @return 分页结果对象
     */
    PaginationVO<BookDTO> listBook(PaginationDTO vo);

    /**
     * 更新图书简介信息
     *
     * @param bo 图书简介业务对象
     */
    void updateBookIntroduction(BookIntroductionBO bo);
}
