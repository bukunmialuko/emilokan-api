package com.plunex.emilokan.modules.user;


import com.plunex.emilokan.modules.user.dto.UserRequest;
import com.plunex.emilokan.modules.user.dto.UserResponse;

public interface IUserService {

    User getById(String userId);

    UserResponse getUserResponseById(String userId);

    UserResponse create(UserRequest request);

}
