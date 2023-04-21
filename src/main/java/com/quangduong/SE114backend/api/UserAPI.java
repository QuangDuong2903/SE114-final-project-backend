package com.quangduong.SE114backend.api;

import com.quangduong.SE114backend.dto.user.UserDTO;
import com.quangduong.SE114backend.dto.user.UserInfoDTO;
import com.quangduong.SE114backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserAPI {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<FindUsersResponse> findUser(@RequestParam("page") int page, @RequestParam("keyword") String keyword) {
        Pageable pageable = PageRequest.of(page - 1, 3);
        return ResponseEntity.ok(new FindUsersResponse(page, userService.findUser(keyword, pageable)));
    }


    record FindUsersResponse(int page, List<UserInfoDTO> users) {
    }

}