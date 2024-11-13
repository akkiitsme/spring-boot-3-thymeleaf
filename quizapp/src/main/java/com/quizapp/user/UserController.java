package com.quizapp.user;

import com.quizapp.admin.subject.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.regex.Pattern;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private UserDao userDao;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute UserBean user) {
        userService.addUser(user);
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String homePage(HttpServletRequest request, Model model){
        model.addAttribute("subjectList",subjectDao.findAll());
        return "home";
    }

    @RequestMapping(value={"/access-denied"})
    public String AccessDenied() {
        return "accessDenied";
    }

    @RequestMapping(value="/ajaxCheckUser",method = RequestMethod.POST)
    public @ResponseBody String ajaxUserCheck(@RequestParam("username") String username,@RequestParam("password") String password ) {
        System.out.println("ajax userName : "+username);
       // password = passwordEncoder.encode(password); // use when bcrypt password is enabled
        UserBean userBean = new UserBean();
        if(!Pattern.matches("^[0-9]*$", username)) {
            userBean = userDao.findByEmailAndPassword(username,password);
        } else {
            userBean = userDao.findByPhoneNo(username).orElseThrow();
        }
        if(userBean==null) {
            System.out.println("User not found");
            return "0";
        } else
            return "1";
    }

    @RequestMapping(value={"/ajaxCheckUserValid"})
    public @ResponseBody String ajaxUserCheckValid(@RequestParam("username") String username) {
        System.out.println("ajax userName : "+username);
        if(!Pattern.matches("^[0-9]*$", username)) {
            Optional<UserBean> userBean = userDao.findByEmail(username);
            if(userBean.isPresent())
                return "1";
            return "0";
        } else {
            Optional<UserBean> userBean =  userDao.findByPhoneNo(username);
            if(userBean.isPresent())
                return "1";
            return "0";
        }
    }
    @RequestMapping(value={"/ajaxCheckPhoneNo"})
    public @ResponseBody String ajaxCheckPhoneNo(@RequestParam("phone") String phoneNo) {
        System.out.println("ajax phone number : "+phoneNo);
        Optional<UserBean> userBean =  userDao.findByPhoneNo(phoneNo);
        if(userBean.isPresent())
            return "1";
        return "0";

    }

}
