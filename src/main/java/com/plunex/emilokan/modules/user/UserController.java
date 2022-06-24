package com.plunex.emilokan.modules.user;

import com.plunex.emilokan.modules.user.dto.UserRequest;
import com.plunex.emilokan.modules.user.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public UserResponse addStore(@RequestBody UserRequest request) {
        return userService.create(request);
    }

}
