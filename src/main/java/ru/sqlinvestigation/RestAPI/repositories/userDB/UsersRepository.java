package ru.sqlinvestigation.RestAPI.repositories.userDB;

import ru.sqlinvestigation.RestAPI.models.userDB.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

}
