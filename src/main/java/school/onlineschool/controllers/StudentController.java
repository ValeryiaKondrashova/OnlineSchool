package school.onlineschool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import school.onlineschool.config.GlobalClass;
import school.onlineschool.models.*;
import school.onlineschool.repository.HomeWorkRepo;
import school.onlineschool.repository.StudentRepo;

import java.util.*;

@Controller
public class StudentController {

    @Autowired
    private HomeWorkRepo homeWorkRepo;

    @Autowired
    GlobalClass globalClass;

    @Autowired
    private StudentRepo studentRepo;

    @GetMapping("/studentAccount")  //первая страница, что видит любой пользователь
    public String student(Map<String, Object> model) {
        model.put("title", "Личный кабинет Ученика");

        return "studentAccount";
    }

    @GetMapping("/abstractCourse")  //первая страница, что видит любой пользователь
    public String abstractCourse(Map<String, Object> model) {
        model.put("title", "Конспект лекций");

        return "abstractCourse";
    }

    @GetMapping("/viewHomeworkStudent")
    public String viewHomeworkStudent(Map<String, Object> model){

        model.put("title", "Просмотр д/з");

        Iterable<HomeWork> homeWorks = homeWorkRepo.findAll();
        List<HomeWork> homeWorksList = new ArrayList<>();
        homeWorks.forEach(homeWorksList::add);

        String userUsername = globalClass.getUserUsername();

        Iterable<Student> students = studentRepo.findAll();
        List<Student> studentsList = new ArrayList<>();
        students.forEach(studentsList::add);

        List<HomeWork> homeWorksListNew = new ArrayList<>();
        for(int i=0; i<studentsList.size();i++){
            if( (studentsList.get(i).getUserAccountName1().getUsername()).equals(userUsername)){

                System.out.println("studentsList.get(i).getUserAccountName1().getUsername() = " + studentsList.get(i).getUserAccountName1().getUsername());
                System.out.println("userUsername = " + userUsername);

                GroupStudent groupUnique = studentsList.get(i).getGroupStudent();

                for(int j=0; j<homeWorksList.size();j++){
                    if( homeWorksList.get(j).getGroupStudent1().getGroupNumber() == groupUnique.getGroupNumber() ){
                        System.out.println("homeWorksList.get(i).getGroupStudent1().getGroupNumber() = " + homeWorksList.get(i).getGroupStudent1().getGroupNumber());
                        System.out.println("groupUnique.getGroupNumber() = " + groupUnique.getGroupNumber());
                        homeWorksListNew.add(homeWorksList.get(j));
                    }
                }
            }
        }

        model.put("homeWorksListNew", homeWorksListNew);

        return "viewHomeworkStudent";
    }
}
