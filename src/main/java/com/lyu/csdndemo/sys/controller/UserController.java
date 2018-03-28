package com.lyu.csdndemo.sys.controller;

import com.lyu.csdndemo.common.base.controller.GenericController;
import com.lyu.csdndemo.common.base.service.GenericService;
import com.lyu.csdndemo.sys.entity.QueryUser;
import com.lyu.csdndemo.sys.entity.User;
import com.lyu.csdndemo.sys.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends GenericController<User, QueryUser> {

    @Inject
    private UserService userService;

    @Override
    protected GenericService<User, QueryUser> getService() {
        return userService;
    }
}
