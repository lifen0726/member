package com.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.library.exception.UserNotFoundException;
import com.library.model.Users;
import com.library.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<Users> getAllMembers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{userName}")
    public ResponseEntity<Users> getMemberByName(@PathVariable String userName) {
        try {
            Users users = userService.findByName(userName);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(HttpServletRequest request) {
        try {
            String username = request.getParameter("username");
            String phonenumber = request.getParameter("phonenumber");
            String password = request.getParameter("password");

            if (password != null && !password.trim().isEmpty()) {
                if (!userService.existsByUsername(username)) {
                    Users users = new Users();
                    users.setUserName(username);
                    users.setPhoneNumber(phonenumber);

                    // 在設置之前對密碼進行編碼
                    String encodedPassword = passwordEncoder.encode(password);
                    users.setPassword(encodedPassword);

                    userService.saveUser(users);
                    return ResponseEntity.ok("User registered successfully");
                } else {
                    return new ResponseEntity<>("用戶名已被使用", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("密碼不能為空", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("註冊失敗", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        try {
            // 對密碼進行編碼
            String encodedPassword = passwordEncoder.encode(password);

            // 使用 AuthenticationManager 進行身份驗證
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, encodedPassword)
            );

            // 設定身份驗證結果到 Security 上下文
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return "redirect:/login/welcome"; // 登入成功，重定向到 welcome 頁面
        } catch (Exception e) {
            return "redirect:/login/page?error=true"; // 登入失敗，重定向到 login 頁面並顯示錯誤
        }
    }


    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUsers(@PathVariable int userId, @RequestBody Users updatedUser) {
        try {
        	Users existingUser = userService.getUserById(userId);

            // Update member information
            existingUser.setUserName(updatedUser.getUserName());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());

            userService.saveUser(existingUser);

            return new ResponseEntity<>("Update OK", HttpStatus.OK);
        } catch (UserNotFoundException ex) {
            return new ResponseEntity<>("Member not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteMember(@PathVariable int userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
