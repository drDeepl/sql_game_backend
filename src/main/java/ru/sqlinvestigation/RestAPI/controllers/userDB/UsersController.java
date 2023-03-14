package ru.sqlinvestigation.RestAPI.controllers.userDB;

import ru.sqlinvestigation.RestAPI.models.userDB.Users;
import ru.sqlinvestigation.RestAPI.services.userDB.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // @Controller + @ResponseBody над каждым методом
@RequestMapping("api/userDB/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/index")
    public List<Users> getPeople() {
        return usersService.findAll(); // Jackson конвертирует эти объекты в JSON
    }
}
