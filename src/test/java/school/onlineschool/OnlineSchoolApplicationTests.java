package school.onlineschool;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import school.onlineschool.models.User;
import school.onlineschool.repository.UserRepo;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class OnlineSchoolApplicationTests {

//    @Autowired
//	private UserRepo userRepo;

    /*

     * This test need for check user in Database and use in addUser(registration user).
     * 	Note! Two person don't have equals username!
     *
     * 	If user will be in Database, so Test return result - failed. (because: Two person don't have equals username!)
     * 	And
     * 	If we don't find user in Database, so Test return result - passed.
     *
     */

    @Test
    void contextLoads() {

//        User user = new User("userLogin");
//		User userFromDb = userRepo.findByUsername(user.getUsername());
//
//		assertsEquals(user, userFromDb);

    }

//	private String assertsEquals(User user, User userFromDb) {
//		if(user.equals(userFromDb)){
//			fail("User already exists!");
//			return "User already exists!";
//		}
//		else{
//			return "This is a new user! Good!";
//		}
//
//	}

}
