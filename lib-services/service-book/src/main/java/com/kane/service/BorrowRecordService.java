package com.kane.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kane.entity.dto.BorrowRecordDTO;
import com.kane.entity.dto.PaginationDTO;
import com.kane.entity.po.BorrowRecord;
import com.kane.entity.vo.PaginationVO;

/**
 * 借阅记录服务接口
 * 定义借阅相关的业务逻辑方法
 */
public interface BorrowRecordService extends IService<BorrowRecord> {

    /**
     * 借阅图书
     *
     * @param dto 借阅记录数据传输对象
     */
    void borrowBook(BorrowRecordDTO dto);

    /**
     * 归还图书
     *
     * @param recordId 借阅记录ID
     */
    void returnBook(Long recordId);

    /**
     * 续借图书
     *
     * @param recordId 借阅记录ID
     * @param days     续借天数
     */
    void renewBook(Long recordId, Integer days);

    /**
     * 根据ID获取借阅记录
     *
     * @param id 记录ID
     * @return 借阅记录数据传输对象
     */
    BorrowRecordDTO getRecord(Long id);

    /**
     * 分页查询借阅记录列表
     *
     * @param dto 分页查询条件
     * @return 分页结果
     */
    PaginationVO<BorrowRecordDTO> listRecord(PaginationDTO dto);

    /**
     * 分页查询当前用户借阅记录列表
     *
     * @param dto 分页查询条件
     * @return 分页结果
     */
    PaginationVO<BorrowRecordDTO> listCurrentUserRecord(String token,PaginationDTO dto);
}
