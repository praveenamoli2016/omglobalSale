package com.omglobal.omglobal.service;

import com.omglobal.omglobal.model.request.RoleRequest;
import com.omglobal.omglobal.model.request.UserLoginRequest;
import com.omglobal.omglobal.model.request.UserRequest;
import com.omglobal.omglobal.model.response.Response;

public interface UtilityService {
    Response createRole(RoleRequest roleRequest);

    Response createUser(UserRequest userRequest);

    Response deleteUser(Long id);

    Response userLogin(UserLoginRequest userLoginRequest);

   // User findOne(String username);
}
