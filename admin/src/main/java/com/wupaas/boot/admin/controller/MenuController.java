/**
 * Copyright 2017-2018 JueYue (qrb.jueyue@foxmail.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wupaas.boot.admin.controller;

import com.wupaas.boot.admin.model.MenuModel;
import com.wupaas.boot.admin.service.IMenuService;
import com.wupaas.boot.bussiness.base.controller.BaseController;
import com.wupaas.boot.bussiness.request.RequestParams;
import com.wupaas.boot.bussiness.response.Response;
import com.wupaas.boot.bussiness.response.SuccessResponse;
import com.wupaas.boot.core.support.BeanKit;
import com.wupaas.boot.web.iview.IViewTree;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wupaas.boot.admin.common.Constant.ADMIN;


/**
 * 菜单表控制器
 *
 * @author JueYue
 * @Date 2018-10-12 14:51:42
 */
@Api("菜单表")
@RestController
@RequestMapping(ADMIN + "/menu")
public class MenuController extends BaseController<IMenuService, MenuModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private IMenuService menuService;

    @ApiOperation(value = "获取机构列表")
    @RequestMapping(value = "/treeList", method = RequestMethod.POST)
    public Response<List<MenuModel>> treeList(@RequestBody RequestParams<MenuModel> params) {
        QueryWrapper wrapper = new QueryWrapper<>(params.getModel());
        if (params.getMap() != null) {
            handlerWrapper(wrapper, params.getMap(), params);
        }
        if (BeanKit.isAllFieldNullNoTransient(params.getModel())) {
            wrapper.isNull("P_ID");
        }
        wrapper.orderByAsc("NUM");
        List<MenuModel> list = menuService.tree(wrapper);
        return new SuccessResponse(list);
    }

    @ApiOperation(value = "获取机构树")
    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    public Response<List<IViewTree>> tree(@RequestParam String roleId) {
        List<IViewTree> list = menuService.getTreeByRoleId(roleId);
        return new SuccessResponse(list);
    }

    @ApiOperation(value = "查询用户访问权限")
    @GetMapping(value = "/queryUserPermissions/{productCode}/{userId}")
    public Response<List<String>> queryUserPermissions(@PathVariable String productCode,@PathVariable String userId) {
        List<String> access = menuService.getAllByUserId(userId,productCode);
        return new SuccessResponse(access);
    }


}