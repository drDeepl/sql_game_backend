package ru.sqlinvestigation.RestAPI.services.userDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sqlinvestigation.RestAPI.models.userDB.Users;
import ru.sqlinvestigation.RestAPI.repositories.userDB.UsersRepository;
import ru.sqlinvestigation.RestAPI.util.PersonNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
//@Transactional(readOnly = true)
//@Transactional(readOnly = true, transactionManager = "userTransactionManager")
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    public Users findOne(int id) {
        Optional<Users> foundPerson = usersRepository.findById(id);
        return foundPerson.orElseThrow(PersonNotFoundException::new);
    }

    @Transactional(transactionManager = "userTransactionManager")
    public void save(Users users){
        usersRepository.save(users);
    }
}
