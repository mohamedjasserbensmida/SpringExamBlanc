package com.example.springbootexamjas.Repository;

import com.example.springbootexamjas.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    public User findByName(String firstName,String lastName);
    public User findUserByRoleAndFNameAndLName(String role, String fName, String lName);

}
