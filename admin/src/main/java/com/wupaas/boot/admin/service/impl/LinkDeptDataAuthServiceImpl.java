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
package com.wupaas.boot.admin.service.impl;

import com.wupaas.boot.admin.model.LinkDeptDataAuthModel;
import com.wupaas.boot.admin.repository.LinkDeptDataAuthRepository;
import com.wupaas.boot.admin.service.ILinkDeptDataAuthService;
import com.wupaas.boot.bussiness.base.service.BaseServiceCacheImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 部门数据权限表关联表服务实现
 *
 * @author JueYue
 * @Date 2018-09-18 16:52:18
 */
@Service("linkDeptDataAuthService")
public class LinkDeptDataAuthServiceImpl extends BaseServiceCacheImpl<LinkDeptDataAuthRepository, LinkDeptDataAuthModel> implements ILinkDeptDataAuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinkDeptDataAuthServiceImpl.class);

    @Autowired
    private LinkDeptDataAuthRepository linkDeptDataAuthRepository;

}
