package com.example.springbootexamjas.Controller;

import com.example.springbootexamjas.Entity.Project;
import com.example.springbootexamjas.Entity.Sprint;
import com.example.springbootexamjas.Entity.User;
import com.example.springbootexamjas.Repository.ProjectRepository;
import com.example.springbootexamjas.Repository.SprintRepository;
import com.example.springbootexamjas.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test")
@AllArgsConstructor
public class Controller {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    SprintRepository sprintRepository;
    @Autowired
    UserRepository userRepository;
    @PostMapping("/AddUser")
    public User addUser (@RequestBody User user){
        return userRepository.save(user);
    }
    @PostMapping("/addProject")
    public Project addProject(@RequestBody Project project){
    List<Sprint> sprints = project.getSprints();
    sprints.stream().forEach(sprint -> sprint.setProject(project));
    project.setSprints(project.getSprints());
    projectRepository.save(project);
    sprintRepository.saveAll(project.getSprints());
    return project;
    }
    @PostMapping("/AddProjToUser/{projectId}/{userId}")
    public void assignProjectToUser(@PathVariable int projectId , @PathVariable int userId){
    Project project = projectRepository.findById(projectId).orElse(null);
    User user = userRepository.findById(userId).orElse(null);
    if(project!=null && user!=null){
        user.getProjects().add(project);
       userRepository.save(user);
    }
    }
    @PostMapping("/assignProjectToUser/{projectId}/{firstName}/{lastName}")
    public void assignProjectToClient(@PathVariable int projectId , @PathVariable String firstName , @PathVariable String lastName){
        Project project = projectRepository.findById(projectId).orElse(null);
        User user = userRepository.findUserByRoleAndFNameAndLName("Client", firstName, lastName);
        if ( (project != null) && (user!=null) )
        {
            user.getProjects().add(project);
            userRepository.save(user);
        }

    }



    @GetMapping("/getAllProjectByScrumMaster")
     public List<Project> getProjectsByScrumMaster(@PathVariable String fName , @PathVariable String lName) {
        User user = userRepository.findByName(fName, lName);
        if (user != null) {
            List<Project> projects = user.getProject();
            List<User> users = new ArrayList<User>();
            projects.stream().forEach(project -> project.getUsers().forEach(user1 -> users.add(user1)));
            users.stream().filter(user1 -> user.getRole().equals("SCRUM_MASTER"));
            return projects;
        } else {
            return null;
        }
    }
}
