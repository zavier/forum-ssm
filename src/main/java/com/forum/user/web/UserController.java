package com.forum.user.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.forum.entity.User;
import com.forum.user.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserName/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public User getUserName(@PathVariable String userId) {
        return userService.findById(userId);
    }

    @RequestMapping(value = "/showUserInformation/{userId}")
    public ModelAndView showUserInformation(@PathVariable String userId) {
        ModelAndView view = new ModelAndView();
        // User user = userService.findById(userId);
        // String pictureUrl = user.getPictureUrl();
        // view.addObject("pictureUrl", pictureUrl);
        view.setViewName("/user/showUserInformation");
        return view;
    }

    @RequestMapping(value = "/uploadPicture/{userId}", method = RequestMethod.POST)
    public String uploadPicture(@PathVariable String userId, HttpServletRequest request,
            @RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty()) {
            log.warn("File is empty!");
            return "redirect:/";
        }
        // 获取上传的文件名的后缀，与当前的时间组成新的文件名进行保存
        String originalFileName = file.getOriginalFilename();
        int indexOfPoint = originalFileName.lastIndexOf(".");
        String suffixOfFileName = originalFileName.substring(indexOfPoint);
        String fileName = System.currentTimeMillis() + suffixOfFileName;

        // 得到目标文件
        String path = request.getSession().getServletContext().getRealPath("/images/userPhoto");
        File targetFile = new File(path, fileName);

        // 如果文件已存在则删除
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        // 保存保存
        try {
            file.transferTo(targetFile);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 将图片得相对路径存储到用户表中
        String userPicUrl = request.getContextPath() + "/images/userPhoto/" + fileName;
        savePictureUrl(userId, userPicUrl);
        
        // 更新session中用户头像信息
        User user = (User) request.getSession().getAttribute("LOGINUSER");
        user.setPictureUrl(userPicUrl);
        request.getSession().setAttribute("LOGINUSER", user);

        return "redirect:/";
    }

    /**
     * 将用户的头像图片的地址存储到用户表中
     * 
     * @param userId
     * @param url
     */
    private void savePictureUrl(String userId, String url) {
        User user = userService.findById(userId);
        user.setPictureUrl(url);
        userService.update(user);
    }
}
