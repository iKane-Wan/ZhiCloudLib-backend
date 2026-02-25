package com.kane.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kane.entity.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    User selectByPhone(String phone);
}
