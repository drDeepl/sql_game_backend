//package ru.alishev.springcourse.FirstRestApp;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import ru.alishev.springcourse.FirstRestApp.controllers.PeopleController;
//
//import static org.junit.matchers.JUnitMatchers.containsString;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
////RunWith - аннотация JUint (производит тестирование)
////SpringRunner - окружение, в котором будет стартовать тестирование
//@RunWith(SpringRunner.class)
//@SpringBootTest
////Создает окружение MVC
//@AutoConfigureMockMvc
//public class MyTestClass {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private PeopleController controller;
//
//    //Тест - тестовые методы
//    @Test
//    public void testMethod() throws Exception{
//        //Слой mockMVC
//        //
//        this.mockMvc.perform(get("/people"))
//                .andDo(print())
//                //статус запроса будет 200
//                .andExpect(status().isOk())
//                //Вернётся контент. Сравниваем что содержит Hello guest
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
