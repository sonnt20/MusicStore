package com.btec.store.musicstore.controller.api;

import com.btec.store.musicstore.common.UtilsValidator;
import com.btec.store.musicstore.model.dto.request.UserLoginRequest;
import com.btec.store.musicstore.model.dto.request.UserRegisterRequest;
import com.btec.store.musicstore.model.entity.KhUserEntity;
import com.btec.store.musicstore.repository.KhUserRepository;
import com.btec.store.musicstore.repository.RoleRepository;
import com.btec.store.musicstore.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class LoginApi {
    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private KhUserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    //    ------------------------------------API------------------------------------------
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserLoginRequest loginRequest, HttpServletRequest request) {
//        try-catch để gặp lỗi server vẫn chạy tiếp
        try {
//        loginRequest là cái đối tượng nhận từ client gửi lên, Xài RequestBody nó sẽ tự parse thành đối tượng
//        (Thằng UserLoginRequest là class)
//        đối tượng chứa username và password để login, nếu null thì nghỉ khỏi check luôn
            ;
            if (loginRequest == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
//            Check xem có thông tin đăng nhập nào null không, nếu có cũng nghỉ khỏi login
            if (loginRequest.getUsername() == null && loginRequest.getPassword() == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
//            VIết ra log là thằng nào đăng nhập để debug thôi
            logger.info(String.format("Login user: %s", loginRequest.getUsername()));
//            Tìm xem trong db user có tồn tại không
            KhUserEntity user = repository.findByUsername(loginRequest.getUsername());

            if (user == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
//            user tồn tại, check mật khẩu (Mã dạng MD5)
            if (!Objects.equals(UtilsValidator.md5(loginRequest.getPassword()), user.getPassword())) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
//            set user name vào session
            request.getSession().setAttribute("username", user);


            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequest userRegisterRequest, HttpServletRequest request) {
        if (userRegisterRequest == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
//        kiểm tra các tham số
        if (userRegisterRequest.getPassword() == null || userRegisterRequest.getEmail() == null || userRegisterRequest.getFirstName() == null || userRegisterRequest.getLastName() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
//        .trim() xóa khoảng trống trước và đầu chuỗi
//        .equals("") so sánh với "" xem nó có rỗng không
        if (userRegisterRequest.getPassword().trim().equals("")|| userRegisterRequest.getEmail().trim().equals("") || userRegisterRequest.getFirstName().trim().equals("") || userRegisterRequest.getLastName().trim().equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        KhUserEntity user = userService.registerUser(userRegisterRequest);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
