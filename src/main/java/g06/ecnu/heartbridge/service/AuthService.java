package g06.ecnu.heartbridge.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import g06.ecnu.heartbridge.entity.Users;
import g06.ecnu.heartbridge.mapper.UsersMapper;
import g06.ecnu.heartbridge.utils.PatternValidator;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import g06.ecnu.heartbridge.utils.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    @Resource
    private UsersMapper usersMapper;

    public ResponseEntity register(String username, String password, String phone, String email){
        if (!PatternValidator.validatePattern(username).equals("USERNAME")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"用户名不合法\"}");
        }
        if (!PatternValidator.validatePattern(phone).equals("PHONE")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"电话不合法\"}");
        }
        if (!PatternValidator.validatePattern(username).equals("EMAIL")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"邮箱不合法\"}");
        }
        if (usersMapper.ifUserExists(username, phone, email)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"用户名或电话或邮箱已存在\"}");
        } else {
            int userType = 0;
            Users user = new Users(username, password, phone, email, userType);
            int result = usersMapper.insert(user);
            String token = JwtUtil.generateToken(username, userType);
            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return result > 0 ? ResponseEntity.ok(response) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"数据库错误，请重试\"}");
        }
    }

    public ResponseEntity login(String loginParam, String password){
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();

        switch (PatternValidator.validatePattern(loginParam)){
            case "EMAIL":
                queryWrapper.eq("email", loginParam);
                Users user = usersMapper.selectOne(queryWrapper);
                break;
                case "PHONE":
                    queryWrapper.eq("phone", loginParam);
        }
    }

    public ResponseEntity loginAdmin(String username, String password){
        return ResponseEntity.ok(null);
    }
}
