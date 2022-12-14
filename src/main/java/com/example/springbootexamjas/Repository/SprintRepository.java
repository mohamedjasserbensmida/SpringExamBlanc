package com.example.springbootexamjas.Repository;

import com.example.springbootexamjas.Entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintRepository extends JpaRepository<Sprint,Integer> {
}
