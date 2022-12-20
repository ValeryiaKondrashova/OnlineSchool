package school.onlineschool.controllers;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import school.onlineschool.models.*;
import school.onlineschool.repository.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class ManagerController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ApplicationRepo applicationRepo;

    @Autowired
    private StatusApplicationRepo statusApplicationRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private GroupStudentRepo groupStudentRepo;

    @Autowired
    private PositionRepo positionRepo;

    @GetMapping("/managerAccount")
    public String managerAccount(Map<String, Object> model) {
        model.put("title", "Личный кабинет Менеджера");

        Iterable<Student> students = studentRepo.findAll();
        model.put("students", students);

        return "managerAccount";
    }

    @GetMapping("/viewTeachers")
    public String viewTeachers(Map<String, Object> model) {
        model.put("title", "Просмотр преподавателей");

        Iterable<Teacher> teachers = teacherRepo.findAll();
        model.put("teachers", teachers);

        return "viewTeachers";
    }

    @GetMapping("/addTeacher")  //первая страница, что видит любой пользователь
    public String addTeacher(Map<String, Object> model) {
        model.put("title", "Добавление преподавателя");

        Iterable<Position> positions = positionRepo.findAll();
        model.put("positions", positions);

        return "addTeacher";
    }

    @PostMapping("/addTeacher")
    public String saveTeacher(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String patronymic,
            @RequestParam String birthday,
            @RequestParam int experience,
            @RequestParam String telephone,
            @RequestParam String email,
            @RequestParam String position,
            Map<String, Object> model
    ){
        
        if(position.isEmpty()){
            model.put("message", "Необходимо указать должность!");
            Iterable<Position> positions = positionRepo.findAll();
            model.put("positions", positions);

            return "addTeacher";
        }

        Position positionF = positionRepo.findPositionByNamePosition(position);

        Teacher teacher = new Teacher(firstName, lastName, patronymic, birthday, experience, telephone, email, positionF);
        teacherRepo.save(teacher);

        return "redirect:/viewTeachers";
    }

    @GetMapping("/editTeachers")
    public String editTeachers(Map<String, Object> model){

        Iterable<Teacher> teachers = teacherRepo.findAll();
        model.put("teachers", teachers);

        return "editTeachers";
    }

    @GetMapping("/editTeacher/{id}")
    public String editTeacher(@PathVariable(value = "id") long  id, Map<String, Object> model){

        Iterable<Position> positions = positionRepo.findAll();
        model.put("positions", positions);
//
//        if(!teacherRepo.existsById(id)){
//            return "editTeachers";
//        }

        Optional<Teacher> teacher = teacherRepo.findById(id);

        ArrayList<Teacher> resTeacher = new ArrayList<>();
        teacher.ifPresent(resTeacher::add);
        model.put("resTeacher", resTeacher);

        model.put("title", teacher.get().getLastName());

        return "editIdTeacher";
    }

    @PostMapping("/editTeacher/{id}")
    public String saveTeacher(@PathVariable(value = "id") long  id,
                              @RequestParam String firstName,
                              @RequestParam String lastName,
                              @RequestParam String patronymic,
                              @RequestParam String birthday,
                              @RequestParam int experience,
                              @RequestParam String email,
                              @RequestParam String telephone,
                              @RequestParam String position,
                              Map<String, Object> model){

        Teacher teacher = teacherRepo.findById(id).orElseThrow();

        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setPatronymic(patronymic);
        teacher.setBirthday(birthday);
        teacher.setExperience(experience);
        teacher.setEmail(email);
        teacher.setTelephone(telephone);

        Position position1 = positionRepo.findPositionByNamePosition(position);
        teacher.setPosition(position1);

        teacherRepo.save(teacher);

        return "redirect:/viewTeachers";
    }

    @GetMapping("/deleteTeacher")
    public String deleteTeacher(Map<String, Object> model){

        model.put("title", "Удаление преподавателя");

        Iterable<Teacher> teachers = teacherRepo.findAll();
        model.put("teachers", teachers);

        return "deleteTeacher";
    }

    @PostMapping("/deleteTeacher/{id}")
    public String deleteIdTeacher(@PathVariable(value = "id") long  id){

        teacherRepo.deleteById(id);

        return "redirect:/viewTeachers";
    }

    @GetMapping("/viewStudents")  //первая страница, что видит любой пользователь
    public String viewStudents(Map<String, Object> model) {
        model.put("title", "Просмотр учеников");

        Iterable<Student> students = studentRepo.findAll();
        model.put("students", students);

        return "viewStudents";
    }

    @GetMapping("/addStudent")  //первая страница, что видит любой пользователь
    public String addStudent(Map<String, Object> model) {
        model.put("title", "Добавление ученика");

        Iterable<GroupStudent> groupStudents = groupStudentRepo.findAll();
        model.put("groupStudents", groupStudents);

        Iterable<User> users = userRepo.findAll();
        model.put("users", users);

        return "addStudent";
    }

    @PostMapping("/addStudent")
    public String saveStudent(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String patronymic,
            @RequestParam String birthday,
            @RequestParam int groupStudent,
            @RequestParam String telephone,
            @RequestParam String email,
            @RequestParam String userName,
            Map<String, Object> model
    ){

        System.out.println("groupStudent " + groupStudent);
        if(groupStudent == 0){
            model.put("message", "Необходимо указать учебную группу!");
            Iterable<GroupStudent> groupStudents = groupStudentRepo.findAll();
            model.put("groupStudents", groupStudents);

            return "addStudent";
        }
        System.out.println("After position!");

        GroupStudent groupStudent1 = groupStudentRepo.findGroupStudentByIdGroup(groupStudent);

        User userName1 = userRepo.findByUsername(userName);

        System.out.println(1);
        Student student = new Student(firstName, lastName, patronymic, birthday, groupStudent1, userName1, telephone, email);
        studentRepo.save(student);
        System.out.println(2);

        return "redirect:viewStudents";
    }

    @GetMapping("/editStudent")
    public String editStudent(Map<String, Object> model){
        model.put("title", "Редактирование студентов");

        Iterable<Student> students = studentRepo.findAll();
//        Iterable<PositionEmployee> positionEmployees = positionEmployeeRepository.findAllById("");
        model.put("students", students);

        return "editStudents";
    }

    @GetMapping("/edit/{id}")
    public String editIdStudent(@PathVariable(value = "id") long  id, Map<String, Object> model){

        Iterable<GroupStudent> groupStudents = groupStudentRepo.findAll();
        model.put("groupStudents", groupStudents);

        if(!studentRepo.existsById(id)){
            return "editStudents";
        }

        Optional<Student> student = studentRepo.findById(id);

        ArrayList<Student> resStudent = new ArrayList<>();
        student.ifPresent(resStudent::add);
        model.put("resStudent", resStudent);

        model.put("title", student.get().getLastName());

        return "editIdStudent";
    }

    @PostMapping("/edit/{id}")
    public String editIdStudentUpdate(
            @PathVariable(value = "id") long  id,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String patronymic,
            @RequestParam String birthday,
            @RequestParam String email,
            @RequestParam String telephone,
            @RequestParam int groupStudent,
            Map<String, Object> model
    ){
//        System.out.println(firstName + " " + lastName + " " + patronymic + " " + birthday + " " + email + " " + telephone + " " + groupStudent);

        Student student = studentRepo.findById(id).orElseThrow();

        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setPatronymic(patronymic);
        student.setBirthday(birthday);
        student.setEmail(email);
        student.setTelephone(telephone);

        GroupStudent groupStudent1 = groupStudentRepo.findGroupStudentByIdGroup(groupStudent);
        student.setGroupStudent(groupStudent1);

        studentRepo.save(student);

        return "redirect:/viewStudents";
    }

    @GetMapping("/deleteStudent")
    public String deleteStudent(Map<String, Object> model){
        model.put("title", "Удаление студента");

        Iterable<Student> students = studentRepo.findAll();
//        Iterable<PositionEmployee> positionEmployees = positionEmployeeRepository.findAllById("");
        model.put("students", students);

        return "deleteStudent";
    }

    @PostMapping("/deleteStudent/{id}")
    public String deleteIdStudent(@PathVariable(value = "id") long  id){

        studentRepo.deleteById(id);

        return "redirect:/viewStudents";
    }

    @GetMapping("/applications")
    public String applications(Map<String, Object> model) {
        model.put("title", "Список заявок на курс");

        Iterable<Application> applications = applicationRepo.findAll();
        model.put("applications", applications);

        return "applications";
    }

    @GetMapping("/editApplications")
    public String editApplications(Map<String, Object> model){

        Iterable<Application> applications = applicationRepo.findAll();
        model.put("applications", applications);

        return "editApplications";
    }

    @GetMapping("/editApplication/{id}")
    public String editIdApplication(@PathVariable(value = "id") long  id, Map<String, Object> model){

        Iterable<Course> courses = courseRepo.findAll();
        model.put("courses", courses);

        Iterable<StatusApplication> statusApplications = statusApplicationRepo.findAll();
        model.put("statusApplications", statusApplications);

        if(!applicationRepo.existsById(id)) {
            return "editApplications";
        }

        Optional<Application> application = applicationRepo.findById(id);

        ArrayList<Application> resApplication = new ArrayList<>();
        application.ifPresent(resApplication::add);
        model.put("resApplication", resApplication);

        model.put("title", application.get().getNamePerson() + " " + application.get().getCourse().getNameCourse());

        return "editIdApplication";
    }

    @PostMapping("/editApplication/{id}")
    public String editIdApplicationUpdate(
            @PathVariable(value = "id") long  id,
            @RequestParam String namePerson,
            @RequestParam String telephone,
            @RequestParam String timeCall,
            @RequestParam String course,
            @RequestParam String status,
            Map<String, Object> model
    ){

        Application application = applicationRepo.findById(id).orElseThrow();

        application.setNamePerson(namePerson);
        application.setTelephone(telephone);
        application.setTimeCall(timeCall);

        Course course1 = courseRepo.findCourseByNameCourse(course);
        StatusApplication statusApplication = statusApplicationRepo.findStatusApplicationByNameStatus(status);

        application.setCourse(course1);
        application.setStatus(statusApplication);

        applicationRepo.save(application);

        return "redirect:/applications";
    }

    @GetMapping("/statisticCourses")
    public String statisticCourses(Map<String, Object> model){

        Iterable<Application> applications= applicationRepo.findAll();
        List<Application> applicationsList = new ArrayList<>();
        applications.forEach(applicationsList::add);

        Iterable<Course> courses = courseRepo.findAll();
        List<Course> coursesList = new ArrayList<>();
        courses.forEach(coursesList::add);

//        int[] result = new long[coursesList.size()];
//        int q=0;
//        for(Course c : coursesList){
//            int qq= applicationRepo.countByCourse(c.getNameCourse());
//            System.out.println("@@@@@@@@  countByCourse " + qq);
//            result[q]=qq;
//            q++;
//        }

        List<String> nameCourses = new ArrayList<>();
        int i=0;
        for(Course c : coursesList){
            nameCourses.add(i,c.getNameCourse());
            System.out.println("Название курса: " + c.getNameCourse());
            i++;
        }

        int[] result = new int[coursesList.size()];
        int q=coursesList.size();
        int w=0;
        for(Course c : coursesList){
            for(Application app : applicationsList){
                if((app.getCourse().getNameCourse()).equals(c.getNameCourse())){
                    System.out.println("1Название курса: " + c.getNameCourse());
                    System.out.println("1В заявке есть курс: " + app.getCourse().getNameCourse());
                    System.out.println("1q: " + (q-1));
                    result[q-1] = ++w;
                }
            }
            w=0;
            q--;
        }

        int[] resultReverse = new int[result.length];
        for (int j = 0; j < result.length; j++) {
            resultReverse[result.length - 1 - j] = result[j];
        }



        for(int j=0; i<result.length; j++){
            System.out.println("@@@@@@@@ " + result[j]);
        }

        model.put("nameCourses", nameCourses);
        model.put("result", resultReverse);

        return "statisticCourses";
    }

    @GetMapping("/statisticBoughtCourses")
    public String statisticBoughtCourses(Map<String, Object> model){

        Iterable<Application> applications= applicationRepo.findAll();
        List<Application> applicationsList = new ArrayList<>();
        applications.forEach(applicationsList::add);

        Iterable<Course> courses = courseRepo.findAll();
        List<Course> coursesList = new ArrayList<>();
        courses.forEach(coursesList::add);

        List<String> nameCourses = new ArrayList<>();
        int i=0;
        for(Course c : coursesList){
            nameCourses.add(i,c.getNameCourse());
            System.out.println("Название курса: " + c.getNameCourse());
            i++;
        }

        int[] result = new int[coursesList.size()];
        int q=coursesList.size();
        int w=0;
        for(Course c : coursesList){
            for(Application app : applicationsList){
                if((app.getCourse().getNameCourse()).equals(c.getNameCourse()) && (app.getStatus().getNameStatus()).equals("Курс куплен")){
                    System.out.println("app.getStatus().getNameStatus(): " + app.getStatus().getNameStatus());
                    System.out.println("Название курса: " + c.getNameCourse());
                    System.out.println("В заявке есть курс: " + app.getCourse().getNameCourse());
                    System.out.println("q: " + (q-1));
                    result[q-1] = ++w;
                }
            }
            w=0;
            q--;
        }

        int[] resultReverse = new int[result.length];
        for (int j = 0; j < result.length; j++) {
            resultReverse[result.length - 1 - j] = result[j];
        }


        for(int j=0; i<result.length; j++){
            System.out.println("@@@@@@@@ " + result[j]);
        }

        model.put("nameCourses", nameCourses);
        model.put("result", resultReverse);

        return "statisticBoughtCourses";
    }

    @GetMapping("/generateDocumentApplications")
    public String choiceGenerateDocument(Map<String, Object> model) {
        model.put("title", "Документы");

        return "generateDocumentApplications";
    }

    @PostMapping("/generateDocumentApplications")
    public String generateDocumentApplicatioins(Map<String, Object> model) throws Docx4JException {

        Iterable<Application> applications = applicationRepo.findAll();
//        model.put("applications", applications);

        ArrayList<Application> resApplication = new ArrayList<>();
        applications.forEach(resApplication::add);

        WordprocessingMLPackage wordPackage = WordprocessingMLPackage.createPackage();
        MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();
        mainDocumentPart.addStyledParagraphOfText("Title", "Список оставленных заявок потенциальными клиентами");

        mainDocumentPart.addParagraphOfText("№ | Имя клиента       Моб. телефон       Время обратной связи       Курс       Статус заявки");

        for(int i =0; i<resApplication.size();i++){
            mainDocumentPart.addParagraphOfText((i+1) + "| " + resApplication.get(i).getNamePerson() + " " + resApplication.get(i).getTelephone() + " " + resApplication.get(i).getTimeCall() + " " + resApplication.get(i).getCourse().getNameCourse() + " " + resApplication.get(i).getStatus().getNameStatus());
        }

        File exportFile = new File("Список оставленных заявок.docx");
        wordPackage.save(exportFile);

        model.put("message", "Документ успешно создан!");

        return "/generateDocumentApplications";
    }

    @GetMapping("/generateDocumentStudents")
    public String generateDocumentStudents(Map<String, Object> model) {
        model.put("title", "Документы");

        return "generateDocumentStudents";
    }

    @PostMapping("/generateDocumentStudents")
    public String generateDocumentStudentsSave(Map<String, Object> model) throws Docx4JException {

        Iterable<Student> students = studentRepo.findAll();
//        model.put("students", students);

        ArrayList<Student> resStudents = new ArrayList<>();
        students.forEach(resStudents::add);

        WordprocessingMLPackage wordPackage = WordprocessingMLPackage.createPackage();
        MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();
        mainDocumentPart.addStyledParagraphOfText("Title", "Список студентов, приступившие к обучению");

        mainDocumentPart.addParagraphOfText("№ | Фамилия       Имя       Отчество       Дата рождения       Моб.телефон       Уч.группа       Эл.почта");

        for(int i =0; i<resStudents.size();i++){
            mainDocumentPart.addParagraphOfText((i+1) + "| " + resStudents.get(i).getLastName() + " " +
                    resStudents.get(i).getFirstName() + " " +
                    resStudents.get(i).getPatronymic() + " " +
                    resStudents.get(i).getBirthday() + " " +
                    resStudents.get(i).getTelephone() + " " +
                    resStudents.get(i).getGroupStudent().getGroupNumber() + " " +
                    resStudents.get(i).getEmail());
        }

        File exportFile = new File("Список студентов.docx");
        wordPackage.save(exportFile);

        model.put("message", "Документ успешно создан!");

        return "/generateDocumentStudents";
    }
}
