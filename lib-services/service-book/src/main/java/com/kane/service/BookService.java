package com.kane.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kane.entity.dto.BookDTO;
import com.kane.entity.dto.PaginationDTO;
import com.kane.entity.po.Book;
import com.kane.entity.vo.PaginationVO;

public interface BookService extends IService<Book> {
    void addBook(BookDTO bookDTO);

    void updateBook(BookDTO bookDTO);

    BookDTO getBook(Long id);

    PaginationVO<BookDTO> listBook(PaginationDTO vo);
}
