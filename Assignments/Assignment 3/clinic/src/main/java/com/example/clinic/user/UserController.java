package com.example.clinic.user;

import com.example.clinic.user.dto.UpdatePasswordUser;
import com.example.clinic.user.dto.UserListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.clinic.UrlMapping.ENTITY;
import static com.example.clinic.UrlMapping.USERS;

@RestController
@RequestMapping(USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserListDTO> allUsers() {
        return userService.allUsersForList();
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PatchMapping(ENTITY)
    public UpdatePasswordUser changePassword(@PathVariable Long id, @RequestBody String newPassword){
        return userService.updatePassword(id, newPassword);
    }

}