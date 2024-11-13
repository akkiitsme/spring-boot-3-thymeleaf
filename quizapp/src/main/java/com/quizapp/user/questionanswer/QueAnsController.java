package com.quizapp.user.questionanswer;

import com.quizapp.admin.answer.Answer;
import com.quizapp.admin.answer.AnswerDao;
import com.quizapp.admin.answer.CorrectAnswer;
import com.quizapp.admin.answer.CorrectAnswerDao;
import com.quizapp.admin.question.Question;
import com.quizapp.admin.question.QuestionDao;
import com.quizapp.admin.subject.Subject;
import com.quizapp.admin.subject.SubjectDao;
import com.quizapp.user.UserBean;
import com.quizapp.user.UserDao;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class QueAnsController {

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private AnswerDao answerDao;

    @Autowired
    private CorrectAnswerDao correctAnswerDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private QueAnsDao queAnsDao;

    @GetMapping("/quiz/questions")
    public String getQuestions(Model model, @RequestParam int subjectId) {
        List<Question> questions = questionDao.findBySubjectIdAndStatus(subjectId,1);
        questions.forEach(x->{
            List<Answer> answers = answerDao.findByQuestionIdAndStatus(x.getQuestionId(),1);
            CorrectAnswer correctAnswer = correctAnswerDao.findByQuestionId(x.getQuestionId());
            x.setSubjectName(subjectDao.findById(x.getSubjectId()).map(Subject::getSubjectName)
                    .stream().findFirst().orElse(null));
            x.setAnsList(answers);
            x.setCorrectAnswerId(correctAnswer.getAnswerId());
            x.setCorrectAnswer(answers.stream()
                    .filter(a -> a.getAnswerId() == correctAnswer.getAnswerId())
                    .map(Answer::getAnswer)
                    .findFirst()
                    .orElse(null));
        });
        model.addAttribute("questionList",questions);
        return "quiz";
    }

    @PostMapping("/quiz/save-answers")
    public String saveQuizAnswers(HttpServletRequest request){
        System.out.println();
        return "redirect:/home";
    }

    @RequestMapping("/api/save-question-answer")
    public @ResponseBody String saveQuestionAnswer(@RequestParam int queId,@RequestParam int ansId,@RequestParam int userId){
        System.out.println("Que: "+queId);
        System.out.println("Ans: "+ansId);
        System.out.println("userId: "+userId);
        Optional<UserBean> userBean =  userDao.findById(userId);
        if(userBean.isPresent()){
            QuestionAnswer questionAnswer = new QuestionAnswer();
            questionAnswer.setAnswerId(ansId);
            questionAnswer.setQuestionId(queId);
            questionAnswer.setUserId(userId);
            queAnsDao.save(questionAnswer);
        }
        return "Done";
    }



}
