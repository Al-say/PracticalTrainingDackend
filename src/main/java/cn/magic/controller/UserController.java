package cn.magic.controller;

import cn.magic.dto.LoginDTO;
import cn.magic.dto.UpdatePasswordDTO;
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
    private UserService userService; // 注入用户业务层服务
    @Autowired
    private DefaultKaptcha defaultKaptcha; // 验证码

    /**
     * 查询系统用户 - 分页
     * 对应前端接口: /user/findUserPage
     */
    @GetMapping("/findUserPage")
    public ResultVo<Page<User>> findUserPage(UserDTO userDTO) throws Exception {
        // 1. 创建分页对象 (当前页, 每页条数)
        // 注意：这里需要做空值处理，防止前端未传参数导致空指针
        int current = userDTO.getCurPage() == null ? 1 : userDTO.getCurPage();
        int size = userDTO.getPageSize() == null ? 10 : userDTO.getPageSize();
        Page<User> page = new Page<>(current, size);

        // 2. 构建查询条件
        QueryWrapper<User> qw = new QueryWrapper<>();
        // 昵称模糊查询
        if (userDTO.getNickname() != null && !userDTO.getNickname().isEmpty()) {
            qw.like("nickname", userDTO.getNickname());
        }
        // 角色查询
        if (userDTO.getRoleId() != null) {
            qw.eq("role_id", userDTO.getRoleId());
        }
        // 只查询未删除的用户
        qw.eq("is_deleted", 0);

        // 3. 执行分页查询
        userService.page(page, qw);
        return ResultVo.ok(page);
    }
    /**
     * 查询全部用户 - 分页 (似乎与上面功能类似，可能是针对不同角色的通用查询)
     * 对应前端接口: /user/findAllUserPage
     */
    @GetMapping("/findAllUserPage")
    public ResultVo<Page<User>> findAllUserPage(UserDTO userDTO) throws Exception {
        // 1. 创建分页对象
        int current = userDTO.getCurPage() == null ? 1 : userDTO.getCurPage();
        int size = userDTO.getPageSize() == null ? 10 : userDTO.getPageSize();
        Page<User> page = new Page<>(current, size);

        // 2. 构建查询条件
        QueryWrapper<User> qw = new QueryWrapper<>();
        if (userDTO.getNickname() != null && !userDTO.getNickname().isEmpty()) {
            qw.like("nickname", userDTO.getNickname());
        }
        qw.eq("is_deleted", 0); // 显示未删除用户

        // 3. 执行查询
        userService.page(page, qw);
        return ResultVo.ok(page);
    }
    /**
     * 添加用户
     * 对应前端接口: /user/addUser
     */
    @PostMapping("/addUser")
    public ResultVo addUser(@RequestBody User user) throws Exception {
        user.setIsDeleted(0); // 默认状态为正常
        // 建议在此处对密码进行加密处理，例如：user.setPassword(SecureUtil.md5(user.getPassword()));
        userService.save(user);
        return ResultVo.ok("添加成功");
    }
    /**
     * 修改用户
     * 对应前端接口: /user/updateUser
     */
    @PostMapping("/updateUser")
    public ResultVo updateUser(@RequestBody User user) throws Exception {
        // 使用 UpdateWrapper 指定更新条件
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", user.getId());
        
        // 执行更新
        userService.update(user, updateWrapper);
        return ResultVo.ok("修改成功");
    }
    /**
     * 删除用户 (逻辑删除)
     * 对应前端接口: /user/delUser/{id}
     */
    @DeleteMapping("/delUser/{id}")
    public ResultVo deleteUser(@PathVariable("id") Integer id) throws Exception {
        // 如果配置了 @TableLogic，removeById 会自动执行逻辑删除 (update is_deleted = 1)
        // 如果没配置，这里则是物理删除。根据你的代码逻辑，可能需要手动更新 isDeleted 字段
        // 建议使用 userService.removeById(id); 配合 MyBatisPlus 的逻辑删除配置
        
        // 手动逻辑删除写法（如果没配全局逻辑删除）：
        // User user = new User();
        // user.setId(id);
        // user.setIsDeleted(1);
        // userService.updateById(user);
        
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
        query.lambda().eq(User::getUsername,dto.getUsername()); //根据用户名查询
                //.eq(User::getPassword, DigestUtils.md5DigestAsHex(dto.getPassword().getBytes()));
        User user = userService.getOne(query);
        if(user == null){
            return ResultVo.fail("用户名不存在，重新输入用户昵称!");
        }

        if(!user.getPassword().equals(DigestUtils.md5DigestAsHex(dto.getPassword().getBytes()))){
            return ResultVo.fail("密码错误!重新输入密码！！！");
        }
        if(user.getIsDeleted().equals("1")){
            return ResultVo.fail("账户被停用，请联系管理员!");
        }
        //返回登录信息
        return ResultVo.ok(user);
    }
    //修改密码
    @PutMapping("/updatePassword")
    public ResultVo updatePassword(@RequestBody UpdatePasswordDTO param){
        //验证原密码是否正确
        User user = userService.getById(param.getUserId());
        //原密码加密
        String oldPassword = DigestUtils.md5DigestAsHex(param.getOldPassword().getBytes());
        if(!user.getPassword().equals(oldPassword)){
            return ResultVo.fail("原密码不正确!");
        }
        UpdateWrapper<User> query = new UpdateWrapper<>();
        query.lambda().set(User::getPassword,DigestUtils.md5DigestAsHex(param.getPassword().getBytes()))
             .eq(User::getId,param.getUserId());
        if(userService.update(query)){
            return ResultVo.ok("修改成功!");
        }
        return ResultVo.ok("修改失败！");
    }
}
