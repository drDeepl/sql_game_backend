package ru.sqlinvestigation.RestAPI.services.userDB;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Transactional(transactionManager = "userTransactionManager")
@Service
public class AdminService {

    @PreAuthorize("hasRole('ROLE_ADMIN') and hasRole('ROLE_SOME_OTHER')")
    public void doAdminStuff() {
        System.out.println("Only admin here");
    }
}
