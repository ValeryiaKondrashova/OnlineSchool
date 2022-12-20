package school.onlineschool;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import school.onlineschool.models.*;
import school.onlineschool.repository.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@SpringBootApplication
public class MappingApplication implements CommandLineRunner {

    @Autowired
    StudentRepo ob;


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
    private StudentRepo studentRepo;

    @Autowired
    private GroupStudentRepo groupStudentRepo;

    public static void main(String[] args)
    {
        SpringApplication.run(MappingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
//        StudentInformation student = new StudentInformation(1, "Aayush");
//        ob.save(student);
//        Address address = new Address(1, "Sonipat", student);
//        ob1.save(address);
//
//        Author author = new Author(1, "Sergey");
//        Author author1 = new Author(2, "Elena");
//        Author author2 = new Author(3, "Ivan");
//        authorRepo.save(author);
//        authorRepo.save(author1);
//        authorRepo.save(author2);
////        Message message = new Message(3, "text1", "tag1", author);
////        messageRepo.save(message);
//
//
//        StatusApplication statusApplication = new StatusApplication("На рассмотрении");
//        statusApplicationRepo.save(statusApplication);
//
//        Course course = new Course("WEB", 3, 1500);
//        courseRepo.save(course);
//
//        Application application = new Application("Валерия", "+375447311805", "17:00", course, statusApplication);
//        applicationRepo.save(application);

//        Position position1 = new Position("Преподаватель по WEB");
//        Position position2 = new Position("Преподаватель по C++");
//        Position position = new Position("Преподаватель по Java");
//        positionRepo.save(position);
//        positionRepo.save(position1);
//        positionRepo.save(position2);
//        Teacher teacher = new Teacher("Кондрашова", "Валерия", "Андреевна", "2002-08-13", 2, "+375447311805", "kamei2002@mal.ru", position);
//        teacherRepo.save(teacher);


//        LocalDate date = LocalDate.now();
//        int year = date.getYear();
//        int month = date.getMonthValue();
//        int dayOfMonth = date.getDayOfMonth();
//        DayOfWeek dayOfWeek = date.getDayOfWeek();
//        System.out.println(date);
//        System.out.println(dayOfWeek);
//        System.out.printf("%d.%d.%d \n", dayOfMonth, month, year);
//
//
//
//
//        LocalDate date1 = LocalDate.of(year, month, dayOfMonth);
//        System.out.println("date1 = " + date1);
//
//        testDate testDate = new testDate();
//        testDate.setId(5);
//        testDate.setNameDZ("create something");
//        testDate.setDateDZ(date1);
//
//        dayOfMonth +=3;
//        LocalDate date2 = LocalDate.of(year, month, dayOfMonth);
//        testDate.setDateDeadline(date2);
//
//        testDateRepo.save(testDate);



//        GroupStudent groupFound = groupStudentRepo.findGroupStudentByIdGroup(1);
//        Student student = new Student("Петрович", "Николай", "Сергеевич", "27-05-2004", groupFound, "+375447895641", "nikos2004@gmail.com");
//        studentRepo.save(student);


    }
}
