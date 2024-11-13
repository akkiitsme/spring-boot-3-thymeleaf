package com.quizapp.admin.question;

import com.quizapp.admin.answer.Answer;
import com.quizapp.admin.answer.AnswerDao;
import com.quizapp.admin.answer.CorrectAnswer;
import com.quizapp.admin.answer.CorrectAnswerDao;
import com.quizapp.admin.subject.Subject;
import com.quizapp.admin.subject.SubjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin")
public class QuestionController {

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private AnswerDao answerDao;

    @Autowired
    private CorrectAnswerDao correctAnswerDao;

    @GetMapping("/add-question")
    public String QuestionPage(Model model){
        Question question = new Question();
        question.setAnsList(IntStream.range(0, 4).mapToObj(i -> new Answer()).collect(Collectors.toList()));
        model.addAttribute("queAns", question);
        model.addAttribute("subjectList",subjectDao.findAll());
        return "question/question";
    }

    @GetMapping("/question-list")
    public String questionListPage(Model model){
        List<Question> questionList = questionDao.findAll(Sort.by(Sort.Direction.DESC,"questionId"));
        questionList.forEach(x->{
            List<Answer> answers = answerDao.findByQuestionIdAndStatus(x.getQuestionId(),1);
            CorrectAnswer correctAnswer = correctAnswerDao.findByQuestionId(x.getQuestionId());
            x.setSubjectName(subjectDao.findById(x.getSubjectId()).map(Subject::getSubjectName)
                    .stream().findFirst().orElse(null));
            x.setAnsList(answers);
            x.setCorrectAnswerId(correctAnswer.getCorrectAnswerId());
            x.setCorrectAnswer(answers.stream()
                    .filter(a -> a.getAnswerId() == correctAnswer.getAnswerId())
                    .map(Answer::getAnswer)
                    .findFirst()
                    .orElse(null));
        });

        model.addAttribute("questionList",questionList);
        return "question/question-list";
    }

    @PostMapping("/add-question")
    public String saveQuestion(@ModelAttribute("queAns") Question question){
        System.out.println(question);
        Question que = questionDao.save(question);
        AtomicInteger index = new AtomicInteger(0);
        question.getAnsList().forEach(answer->{
            answer.setQuestionId(que.getQuestionId());
            Answer ans = answerDao.save(answer);
            if(index.get()==question.getCorrectAnswerId()){
                CorrectAnswer correctAnswer = new CorrectAnswer();
                correctAnswer.setQuestionId(que.getQuestionId());
                correctAnswer.setAnswerId(ans.getAnswerId());
                correctAnswerDao.save(correctAnswer);
            }
            index.getAndIncrement();
        });
        return "redirect:/admin/question-list";
    }

    @GetMapping("/get-question/{questionId}")
    public String getQuestion(Model model, @PathVariable int questionId){
        Optional<Question> ques = questionDao.findById(questionId);
        if(ques.isPresent()) {
            Question question = ques.get();
            List<Answer> answers = answerDao.findByQuestionIdAndStatus(question.getQuestionId() , 1);
            CorrectAnswer correctAnswer = correctAnswerDao.findByQuestionId(question.getQuestionId());
            question.setSubjectName(subjectDao.findById(question.getSubjectId()).map(Subject::getSubjectName)
                    .stream().findFirst().orElse(null));
            question.setAnsList(answers);
            question.setCorrectAnswerId(correctAnswer.getAnswerId());
            question.setCorrectAnswer(answers.stream()
                    .filter(a -> a.getAnswerId() == correctAnswer.getAnswerId())
                    .map(Answer::getAnswer)
                    .findFirst()
                    .orElse(null));
            model.addAttribute("queAns",question);
            model.addAttribute("subjectList",subjectDao.findAll());
        }
        return "question/update-question";
    }

    @PostMapping("/update-question/{questionId}")
    public String updateQuestion(@ModelAttribute("queAns") Question question,@PathVariable int questionId){
        System.out.println(question);
        Question que = questionDao.findById(questionId).orElseThrow();
        que.setQuestion(question.getQuestion());
        que.setQueStatus(question.getQueStatus());
        questionDao.save(que);
       Map<Integer,Answer> answerMap =  answerDao.findByQuestionIdAndStatus(questionId,1)
               .stream().collect(Collectors.toMap(Answer::getAnswerId, Function.identity()));
       List<Answer> answers = new ArrayList<>();
       question.getAnsList().forEach(answer -> {
           Answer ans = answerMap.get(answer.getAnswerId());
           ans.setAnswer(answer.getAnswer());
           answers.add(ans);
       });
       answerDao.saveAll(answers);
       CorrectAnswer correctAnswer = correctAnswerDao.findByQuestionId(questionId);
       correctAnswer.setAnswerId(question.getCorrectAnswerId());
       correctAnswerDao.save(correctAnswer);
        return "redirect:/admin/question-list";
    }

    @GetMapping("/delete-question/{questionId}")
    public String deleteQuestion(@PathVariable int question){
        questionDao.deleteById(question);
        return "redirect:/admin/question-list";
    }
}
