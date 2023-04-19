//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.junit.matchers.JUnitMatchers.containsString;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
////RunWith - ��������� JUint (���������� ������������)
////SpringRunner - ���������, � ������� ����� ���������� ������������
//@RunWith(SpringRunner.class)
//@SpringBootTest
////������� ��������� MVC
//@AutoConfigureMockMvc
//public class MyTestClass {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private PeopleController controller;
//
//    //���� - �������� ������
//    @Test
//    public void testMethod() throws Exception{
//        //���� mockMVC
//        //
//        this.mockMvc.perform(get("/people"))
//                .andDo(print())
//                //������ ������� ����� 200
//                .andExpect(status().isOk())
//                //������� �������. ���������� ��� �������� Hello guest
//                .andExpect(content().string(containsString("Hello guest")));
//    }
//
//    @Test
//    public void echeTest() throws Exception{
//
//    }
//
////    @Test
////    public void whenCreatePerson_thenStatus201() {
////        Person person = new Person("Michail");
////
////        given().log().body()
////                .contentType("application/json").body(person)
////
////                .when().post("/persons")
////
////                .then().log().body()
////                .statusCode(HttpStatus.CREATED.value());
////    }
//}
