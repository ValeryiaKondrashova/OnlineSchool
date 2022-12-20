package school.onlineschool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import school.onlineschool.config.GlobalClass;
import school.onlineschool.models.*;
import school.onlineschool.repository.*;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

@Controller
public class IndexController {

    @Autowired
    GlobalClass globalClass;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private ApplicationRepo applicationRepo;

    @Autowired
    private StatusApplicationRepo statusApplicationRepo;

    @GetMapping("/")  //первая страница, что видит любой пользователь
    public String home(Map<String, Object> model) {
        model.put("title", "PenTag");

        Iterable<Course> courses = courseRepo.findAll();
        model.put("courses", courses);
        return "index";
    }

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("title", "Ваша система");

        Role roleAdmin = Role.ADMIN;
        Set<Role> allRolesUser = globalClass.getUserRole();

        System.out.println("roleAdmin = " + roleAdmin);

        Role roleTeacher = Role.TEACHER;
        Role roleStudent = Role.STUDENT;
        Role roleUser = Role.USER;


        for(int i=0; i<allRolesUser.size(); i++){
            if(allRolesUser.contains(roleAdmin)){
                System.out.println("This account have ADMIN!!");
                model.addAttribute("roleAdmin",true);
            }
            if(allRolesUser.contains(roleTeacher)){
                System.out.println("This account have TEACHER!!");
                model.addAttribute("roleTeacher",true);
            }
            if(allRolesUser.contains(roleStudent)){
                System.out.println("This account have STUDENT!!");
                model.addAttribute("roleStudent",true);
            }
            if(allRolesUser.contains(roleUser)){
                System.out.println("This account have USER!!");
                model.addAttribute("roleUser",true);
            }
        }


        return "main";
    }

    @GetMapping("/teachers")  //первая страница, что видит любой пользователь
    public String teachers(Map<String, Object> model) {
        model.put("title", "Преподаватели");

        return "teachers";
    }

    @GetMapping("/cources")  //первая страница, что видит любой пользователь
    public String cources(Map<String, Object> model) {
        model.put("title", "Курсы");

        return "cources";
    }

    @GetMapping("/blog")
    public String blog(Map<String, Object> model) {
        model.put("title", "Блог");

        return "blog";
    }

    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("title", "about");

        return "about";
    }

    @PostMapping("/addApplication")
    public String addApplication(@RequestParam String namePerson, @RequestParam String telephone, @RequestParam String timeCall, @RequestParam String course, Map<String, Object> model) {
        Course courseF = courseRepo.findCourseByNameCourse(course);

        StatusApplication statusApplicationF = statusApplicationRepo.findStatusApplicationByNameStatus("Не обработано");

        Application application = new Application(namePerson, telephone, timeCall, courseF,statusApplicationF);
        applicationRepo.save(application);

        System.out.println("!!!!!!----- " + namePerson +  telephone + timeCall + courseF + statusApplicationF);

        Iterable<Application> applications = applicationRepo.findAll();
        model.put("applications", applications);

        return "index";
    }





    //    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/registration")
    public String registration(){

        return "registration";
    }

    //    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping ("/registration")
    public String addUser(User user, Map<String, Object> model){
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if(userFromDb != null){
            model.put("message", "Такой пользователь уже существует!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.STUDENT));
        userRepo.save(user);

        return "/navigationAdmin";
    }
}
