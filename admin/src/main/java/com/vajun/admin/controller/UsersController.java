package com.vajun.admin.controller;


import com.vajun.admin.entity.Users;
import com.vajun.admin.service.IUsersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author vajun
 * @since 2020-02-22
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Resource
    private IUsersService usersService;

    @GetMapping("/{id}")
    public Users get(@PathVariable String id){
        return usersService.getById(id);
    }

}
