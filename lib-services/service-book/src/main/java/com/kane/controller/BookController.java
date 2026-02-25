package com.kane.controller;


import com.kane.R;
import com.kane.annotation.Authorization;
import com.kane.entity.dto.BookDTO;
import com.kane.entity.dto.PaginationDTO;
import com.kane.entity.vo.PaginationVO;
import com.kane.service.BookService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Resource
    private BookService bookService;

    /**
     * 添加图书
     * @return R
     */
    @PostMapping
    @Authorization(role = 1)
    public R<String> addBook(@RequestBody BookDTO bookDTO){
        bookService.addBook(bookDTO);
        return R.success("添加成功");
    }

    @Authorization(role = 1)
    @DeleteMapping("/{id}")
    public R<String> deleteBook(@PathVariable Long id){
        bookService.removeById(id);
        return R.success("删除成功");
    }

    @PutMapping
    @Authorization(role = 1)
    public R<String> updateBook(@RequestBody BookDTO bookDTO){
        bookService.updateBook(bookDTO);
        return R.success("更新成功");
    }

    @GetMapping
    public R<PaginationVO<BookDTO>> listBook(@RequestBody PaginationDTO dto){
        PaginationVO<BookDTO> bookDTOPaginationVO = bookService.listBook(dto);
        return R.success(bookDTOPaginationVO);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<BookDTO> getBook(@PathVariable Long id){
        BookDTO bookDTO = bookService.getBook(id);
        return R.success(bookDTO);
    }

}
