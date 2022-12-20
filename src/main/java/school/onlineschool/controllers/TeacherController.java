package school.onlineschool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import school.onlineschool.models.Course;
import school.onlineschool.models.GroupStudent;
import school.onlineschool.models.HomeWork;
import school.onlineschool.models.Teacher;
import school.onlineschool.repository.CourseRepo;
import school.onlineschool.repository.GroupStudentRepo;
import school.onlineschool.repository.HomeWorkRepo;
import school.onlineschool.repository.TeacherRepo;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Controller
public class TeacherController {

    @Autowired
    private GroupStudentRepo groupStudentRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private HomeWorkRepo homeWorkRepo;

    @GetMapping("/teacherAccount")  //первая страница, что видит любой пользователь
    public String teacherAccount(Map<String, Object> model) {
        model.put("title", "Личный кабинет Преподавателя");

        Iterable<GroupStudent> groups = groupStudentRepo.findAll();
        model.put("groups", groups);

        Iterable<Course> course = courseRepo.findAll();
        model.put("course", course);

        Iterable<Teacher> teacher = teacherRepo.findAll();
        model.put("teacher", teacher);

        return "teacherAccount";
    }

    @GetMapping("/addHomework/{idGroup}")
    public String teacherAccountHomeWork(@PathVariable(value = "idGroup") int idGroup, Map<String, Object> model){

//        if(!groupStudentRepo.existsById((long) idGroup)){
//            return "teacherAccount";
//        }

        GroupStudent groupFound = groupStudentRepo.findGroupStudentByIdGroup(idGroup);
        model.put("numberGroup", groupFound.getGroupNumber());

        return "addHomeWork";
    }

    @PostMapping("/addHomework/{idGroup}")
    public String editIdEmployeeUpdate(
            @PathVariable(value = "idGroup") int idGroup,
            @RequestParam String task_homework,
            @RequestParam int countDays,
            Map<String, Object> model
    ){

        GroupStudent groupFound = groupStudentRepo.findGroupStudentByIdGroup(idGroup);

        LocalDate nowDate = LocalDate.now();
        int year = nowDate.getYear();
        int month = nowDate.getMonthValue();
        int dayOfMonth = nowDate.getDayOfMonth();
        nowDate = LocalDate.of(year, month, dayOfMonth);

        dayOfMonth += countDays;
        LocalDate deadlineDate = LocalDate.of(year, month, dayOfMonth);


        HomeWork homeWork = new HomeWork(task_homework, nowDate, deadlineDate, groupFound);
        homeWorkRepo.save(homeWork);

        return "redirect:/teacherAccount";
    }

    @GetMapping("/viewHomework")
    public String viewHomework(Map<String, Object> model){

        model.put("title", "Просмотр д/з");

        Iterable<HomeWork> homeWorks = homeWorkRepo.findAll();
        model.put("homeWorks", homeWorks);

        return "viewHomework";
    }

}
