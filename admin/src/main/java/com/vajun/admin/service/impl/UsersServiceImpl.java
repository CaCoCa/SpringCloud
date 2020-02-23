package com.vajun.admin.service.impl;

import com.vajun.admin.entity.Users;
import com.vajun.admin.mapper.UsersMapper;
import com.vajun.admin.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author vajun
 * @since 2020-02-23
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
