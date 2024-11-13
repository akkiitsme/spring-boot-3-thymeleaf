package com.quizapp.admin;

import com.quizapp.admin.answer.AnswerDao;
import com.quizapp.admin.question.QuestionDao;
import com.quizapp.admin.subject.SubjectDao;
import com.quizapp.user.Role;
import com.quizapp.user.UserDao;
import com.quizapp.user.questionanswer.QueAnsDao;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private QueAnsDao queAnsDao;

    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request, Model model){
        model.addAttribute("totalStudent",userDao.countByRole(Role.User));
        model.addAttribute("quizAttempt",queAnsDao.getCountByUserId());
        model.addAttribute("totalSubject",subjectDao.count());
        model.addAttribute("totalQuestion",questionDao.count());
        return "dashboard";
    }

}
