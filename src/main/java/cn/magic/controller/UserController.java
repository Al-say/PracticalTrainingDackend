package cn.magic.controller;

import cn.magic.dto.LoginDTO;
import cn.magic.dto.UserDTO;
import cn.magic.entity.User;
import cn.magic.service.UserService;
import cn.magic.utils.ResultVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

//用户控制层
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DefaultKaptcha defaultKaptcha; // 验证码

    //查询系统用户-分页
    @GetMapping("/findUserPage")
    public ResultVo<Page<User>> findUserPage(UserDTO userDTO) throws Exception {
        Page<User> page = new Page<>(userDTO.getPageSize(), 6);
        QueryWrapper<User> qw = new QueryWrapper<>();
        if (userDTO.getNickname()!= null && userDTO.getNickname() != "") {
            qw.like("nickname", userDTO.getNickname());
        }
        qw.eq("role_id", userDTO.getRoleId());
        qw.eq("is_deleted", 0); // 显示
        userService.page(page, qw);
        return ResultVo.ok(page);
    }
    //查询全部用户-分页
    @GetMapping("/findAllUserPage")
    public ResultVo<Page<User>> findAllUserPage(UserDTO userDTO) throws Exception {
        Page<User> page = new Page<>(userDTO.getPageSize(), 6);
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("is_deleted", 0); // 显示
        qw.like("nickname",userDTO.getNickname());
        userService.page(page, qw);
        return ResultVo.ok(page);
    }
    //添加用户
    @PostMapping("/addUser")
    public ResultVo addUser(@RequestBody User user) throws Exception {
        user.setIsDeleted(0);
        userService.save(user);
        return ResultVo.ok("添加成功");
    }
    //修改用户
    @PostMapping("/updateUser")
    public ResultVo updateUser(@RequestBody User user) throws Exception {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>();
        updateWrapper.eq("id", user.getId());
        userService.update(user, updateWrapper);
        return ResultVo.ok("修改成功");
    }
    //删除用户
    @DeleteMapping("/delUser/{id}")
    public ResultVo deleteUser(@PathVariable("id") Integer id) throws Exception{
        userService.removeById(id);
        return ResultVo.ok("删除成功");
    }
    //生成验证码
    @PostMapping("/image")
    public ResultVo imageCode(HttpServletRequest request) {
        //生成验证码 4562
        String text = defaultKaptcha.createText();
        //验证码存到session
        HttpSession session = request.getSession();
        session.setAttribute("code", text);
        //生成图片,转换为base64
        BufferedImage bufferedImage = defaultKaptcha.createImage(text);
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
            Base64.Encoder encoder = Base64.getEncoder();
            //BASE64Encoder encoder = new BASE64Encoder();
            String base64 = encoder.encodeToString(outputStream.toByteArray());
            // String base64 = encoder.encode(outputStream.toByteArray());
            String captchaBase64 = "data:image/jpeg;base64," + base64.replaceAll("\r\n", "");
            return ResultVo.ok(captchaBase64,"生成成功" );
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    //登录
    @PostMapping("/login")
    public ResultVo login(@RequestBody LoginDTO dto, HttpServletRequest request){
        //获取sesson里面的code验证码
        HttpSession session = request.getSession();
        String code = (String)session.getAttribute("code");
        //获取前端传递过来的验证码
        String codeParm = dto.getCode();
        if(StringUtils.isEmpty(code)){
            return ResultVo.fail("验证码过期!");
        }
        //对比验证码
        if(!codeParm.equals(code)){
            return ResultVo.fail("验证码错误!");
        }
        //验证用户信息
        QueryWrapper<User> query = new QueryWrapper<>();
        query.lambda().eq(User::getUsername,dto.getUsername())
                .eq(User::getPassword, DigestUtils.md5DigestAsHex(dto.getPassword().getBytes()));
        User user = userService.getOne(query);
        if(user == null){
            return ResultVo.fail("用户名或者密码错误!");
        }
        if(user.getIsDeleted().equals("1")){
            return ResultVo.fail("账户被停用，请联系管理员!");
        }
        //返回登录信息
        return ResultVo.ok(user);
    }
}
