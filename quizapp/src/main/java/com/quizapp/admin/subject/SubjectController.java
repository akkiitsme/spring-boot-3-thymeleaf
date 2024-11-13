package com.quizapp.admin.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class SubjectController {

    @Autowired
    private SubjectDao subjectDao;

    @GetMapping("/add-subject")
    public String SubjectPage(){
        return "subject/subject";
    }

    @GetMapping("/subject-list")
    public String SubjectListPage(Model model){
        List<Subject> subjectList = subjectDao.findAll(Sort.by(Sort.Direction.DESC,"subjectId"));
        model.addAttribute("subjectList",subjectList);
        return "subject/subject-list";
    }

    @PostMapping("/add-subject")
    public String saveSubject(@ModelAttribute Subject subject){
        System.out.println(subject);
        subjectDao.save(subject);
        return "redirect:/admin/subject-list";
    }

    @GetMapping("/get-subject/{subjectId}")
    public String getSubject(Model model, @PathVariable int subjectId){
        Subject subject = subjectDao.findById(subjectId).orElseThrow();
        model.addAttribute("subject",subject);
        return "subject/update-subject";
    }

    @PostMapping("/update-subject/{subjectId}")
    public String updateSubject(@ModelAttribute Subject subject,@PathVariable int subjectId){
        Subject newSubject = subjectDao.findById(subjectId).orElseThrow();
        newSubject.setSubjectName(subject.getSubjectName());
        newSubject.setSubjectStatus(subject.getSubjectStatus());
        subjectDao.save(newSubject);
        return "redirect:/admin/subject-list";
    }

    @GetMapping("/delete-subject/{subjectId}")
    public String deleteSubject(@PathVariable int subjectId){
        subjectDao.deleteById(subjectId);
        return "redirect:/admin/subject-list";
    }
}
