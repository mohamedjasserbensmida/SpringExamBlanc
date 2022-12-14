package com.example.springbootexamjas.Repository;

import com.example.springbootexamjas.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Integer> {

}
