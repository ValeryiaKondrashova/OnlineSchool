package school.onlineschool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import school.onlineschool.models.*;
import school.onlineschool.repository.*;

import java.util.*;

@Controller
public class TestController {

    @Autowired
    private ApplicationRepo applicationRepo;
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private StatusApplicationRepo statusApplicationRepo;

    @Autowired
    private PositionRepo positionRepo;
    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private GroupStudentRepo groupStudentRepo;

    @GetMapping("/testResult")  //первая страница, что видит любой пользователь
    public String testResult(Map<String, Object> model) {
        model.put("title", "PenTag");

        Iterable<Application> applications = applicationRepo.findAll();
        Iterable<Course> courses = courseRepo.findAll();
        Iterable<StatusApplication> statusApplications = statusApplicationRepo.findAll();

        Iterable<Position> positions = positionRepo.findAll();
        Iterable<Teacher> teachers = teacherRepo.findAll();

        model.put("applications", applications);
        model.put("courses", courses);
        model.put("statusApplications", statusApplications);

        model.put("positions", positions);
        model.put("teachers", teachers);

        return "testResult";
    }

    @PostMapping("/addStatus")
    public String addStatus(@RequestParam String nameStatus, Map<String, Object> model) {
        StatusApplication statusApplication = new StatusApplication(nameStatus);
        statusApplicationRepo.save(statusApplication);


        Iterable<StatusApplication> statusApplications = statusApplicationRepo.findAll();
        model.put("statusApplications", statusApplications);

        return "index";
    }

    @PostMapping("/addCourse")
    public String addCourse(@RequestParam String nameCourse, @RequestParam int duration, @RequestParam double price, Map<String, Object> model) {
        Course course = new Course(nameCourse, duration, price);
        courseRepo.save(course);


        Iterable<Course> courses = courseRepo.findAll();
        model.put("courses", courses);

        return "index";
    }

    @PostMapping("/addGroupStudent")
    public String addGroupStudent(@RequestParam int groupNumber, @RequestParam String startGroup, @RequestParam String course, @RequestParam String teacher, Map<String, Object> model) {
        Course courseF = courseRepo.findCourseByNameCourse(course);
        Teacher teacherF = teacherRepo.findTeacherByFirstName(teacher);

        GroupStudent group = new GroupStudent(groupNumber, startGroup, courseF, teacherF);
        groupStudentRepo.save(group);


        return "redirect:/testResult";
    }

    @PostMapping("/addPosition")
    public String addPosition(@RequestParam String namePosition, Map<String, Object> model) {
        Position position = new Position(namePosition);
        positionRepo.save(position);


        Iterable<Position> positions = positionRepo.findAll();
        model.put("positions", positions);

        return "index";
    }

//    @PostMapping("/addTeacher")
//    public String addTeacher(@RequestParam String firstName,
//                             @RequestParam String lastName,
//                             @RequestParam String patronymic,
//                             @RequestParam String birthday,
//                             @RequestParam int experience,
//                             @RequestParam String telephone,
//                             @RequestParam String email,
//                             @RequestParam String position,
//                             Map<String, Object> model) {
//        Position positionF = positionRepo.findPositionByNamePosition(position);
//
//        Teacher teacher = new Teacher(firstName, lastName, patronymic, birthday, experience, telephone, email, positionF);
//        teacherRepo.save(teacher);
//
//        Iterable<Teacher> teachers = teacherRepo.findAll();
//        model.put("teachers", teachers);
//
//        return "index";
//    }

    /*--------------------------------------*/
    @GetMapping("/dataTeacher")
    public String dataTeacher(Map<String, Object> model) {

        Iterable<Teacher> teachers = teacherRepo.findAll();

        return "chart2";
    }

    /*--------------------------------------*/
}
