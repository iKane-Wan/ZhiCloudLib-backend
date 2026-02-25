package com.kane.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kane.entity.dto.BorrowRecordDTO;
import com.kane.entity.dto.PaginationDTO;
import com.kane.entity.po.BorrowRecord;
import com.kane.entity.vo.AuthorizationVO;
import com.kane.entity.vo.PaginationVO;
import com.kane.mapper.BorrowRecordMapper;
import com.kane.service.BorrowRecordService;
import com.kane.utils.BeanUtils;
import com.kane.utils.JwtUtils;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * 借阅记录服务实现类
 * 实现借阅相关的业务逻辑
 */
@Service
public class BorrowRecordServiceImpl extends ServiceImpl<BorrowRecordMapper, BorrowRecord> implements BorrowRecordService {

    /**
     * 借阅图书
     * 设置借阅时间为当前时间，应归还时间为30天后，状态为"借阅中"
     *
     * @param dto 借阅记录数据传输对象
     */
    @Override
    public void borrowBook(BorrowRecordDTO dto) {
        BorrowRecord record = BeanUtils.copyProperties(dto, BorrowRecord.class);

        // 设置借阅时间为当前时间
        Date now = new Date();
        record.setBorrowTime(now);

        // 设置应归还时间为30天后
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        record.setDueTime(calendar.getTime());

        // 设置借阅状态为"借阅中"
        record.setBorrowStatus(1);

        baseMapper.insert(record);
    }

    /**
     * 归还图书
     * 设置实际归还时间为当前时间，状态改为"已归还"
     *
     * @param recordId 借阅记录ID
     */
    @Override
    public void returnBook(Long recordId) {
        BorrowRecord record = baseMapper.selectById(recordId);
        if (record != null) {
            // 设置实际归还时间为当前时间
            record.setReturnTime(new Date());
            // 设置状态为"已归还"
            record.setBorrowStatus(2);
            baseMapper.updateById(record);
        }
    }

    /**
     * 续借图书
     * 在应归还时间基础上延长指定天数
     *
     * @param recordId 借阅记录ID
     * @param days     续借天数
     */
    @Override
    public void renewBook(Long recordId, Integer days) {
        BorrowRecord record = baseMapper.selectById(recordId);
        if (record != null && record.getBorrowStatus() == 1) {
            // 在应归还时间基础上延长
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(record.getDueTime());
            calendar.add(Calendar.DAY_OF_MONTH, days);
            record.setDueTime(calendar.getTime());
            baseMapper.updateById(record);
        }
    }

    /**
     * 根据ID获取借阅记录
     *
     * @param id 记录ID
     * @return 借阅记录数据传输对象
     */
    @Override
    public BorrowRecordDTO getRecord(Long id) {
        BorrowRecord record = baseMapper.selectById(id);
        return BeanUtils.copyProperties(record, BorrowRecordDTO.class);
    }

    /**
     * 分页查询借阅记录列表
     * 支持按图书ID、用户ID、借阅状态筛选
     *
     * @param dto 分页查询条件
     * @return 分页结果
     */
    @Override
    public PaginationVO<BorrowRecordDTO> listRecord(PaginationDTO dto) {
        // 创建分页对象，设置当前页和每页大小
        Page<BorrowRecord> page = new Page<>(dto.getPageNow(), dto.getPageSize());
        // 构建查询条件
        LambdaQueryWrapper<BorrowRecord> queryWrapper = new LambdaQueryWrapper<>();
        // 按图书ID筛选
        queryWrapper.eq(dto.getCategoryId() != null, BorrowRecord::getBookId, dto.getCategoryId());
        // 按借阅状态筛选
        queryWrapper.orderByDesc(BorrowRecord::getBorrowTime);
        return listRecord(dto,queryWrapper);
    }

    @Override
    public PaginationVO<BorrowRecordDTO> listCurrentUserRecord(String token,PaginationDTO dto) {
        // 解析JWT令牌获取用户授权信息
        AuthorizationVO auth = JwtUtils.parseToken(token);
        // 创建查询条件包装器
        LambdaQueryWrapper<BorrowRecord> queryWrapper = new LambdaQueryWrapper<>();
        // 根据用户ID筛选借阅记录
        queryWrapper.eq(BorrowRecord::getUserId, auth.getUserId());
        // 调用通用分页查询方法并返回结果
        return listRecord(dto, queryWrapper);
    }

    private PaginationVO<BorrowRecordDTO> listRecord(PaginationDTO dto,LambdaQueryWrapper<BorrowRecord> queryWrapper) {
        // 创建分页对象，设置当前页和每页大小
        Page<BorrowRecord> page = new Page<>(dto.getPageNow(), dto.getPageSize());
        // 执行分页查询，获取借阅记录分页数据
        Page<BorrowRecord> recordPage = baseMapper.selectPage(page, queryWrapper);
        // 初始化分页VO对象，用于封装分页结果
        PaginationVO<BorrowRecordDTO> paginationVO = new PaginationVO<>();
        // 设置总记录数
        paginationVO.setTotal(recordPage.getTotal());
        // 设置当前页码
        paginationVO.setPageNow(recordPage.getCurrent());
        // 设置每页记录数
        paginationVO.setPageSize(recordPage.getSize());
        // 设置总页数
        paginationVO.setTotalPage(recordPage.getPages());
        // 遍历查询结果，将每个借阅记录实体转换为DTO并添加到分页VO的数据列表中
        recordPage.getRecords().forEach(record -> {
            BorrowRecordDTO recordDTO = BeanUtils.copyProperties(record, BorrowRecordDTO.class);
            paginationVO.getData().add(recordDTO);
        });
        // 返回装好的分页结果
        return paginationVO;
    }
}
