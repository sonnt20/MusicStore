package com.btec.store.musicstore.service;

import com.btec.store.musicstore.common.Constants;
import com.btec.store.musicstore.common.UtilsValidator;
import com.btec.store.musicstore.model.dto.request.UserRegisterRequest;
import com.btec.store.musicstore.model.entity.KhUserEntity;
import com.btec.store.musicstore.repository.KhUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service // @Service đưa class vào bean để quản lí
public class UserService {
    @Autowired
    private KhUserRepository userRepository;

    public KhUserEntity registerUser(UserRegisterRequest request) {
        try {
//           Tìm xem user đã có trong db chưa
            KhUserEntity user = userRepository.findByUsername(request.getEmail().toLowerCase());
//            nếu user đã tồn tại thì không thực hiện đăng kí nữa
            if (user != null) {
//                return 1 đối tượng mới, chưa có data gì cả để bên client biết thằng ranh này đã có acc
                return new KhUserEntity();
            }
            user = new KhUserEntity();
//           Set giá trị từ client gửi lên cho user
            user.setEmail(request.getEmail().toUpperCase());
            user.setUsername(request.getEmail().toLowerCase());
//           Fullname = firstname + lastname
            user.setFullName(request.getFirstName() + " " + request.getLastName());
//           Mật khẩu mã hóa md5
            user.setPassword(UtilsValidator.md5(request.getPassword()));

            user.setGrRoleId(Constants.USER);
            user.setEnable(Constants.ACTIVE);
            user.setGenDate(new Date(new java.util.Date().getTime()));

//           Lưu người dùng xuống db
            user = userRepository.save(user);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
