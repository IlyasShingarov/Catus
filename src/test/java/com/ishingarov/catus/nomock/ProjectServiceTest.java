//package com.ishingarov.catus.nomock;
//
//import com.ishingarov.catus.model.Project;
//import com.ishingarov.catus.service.ProjectService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@Slf4j
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//public class ProjectServiceTest {
//
//    @Autowired
//    private ProjectService projectService;
//
//    @Test
//    public void createProjectAndGetTest() {
//        Project p0 = new Project();
//        p0.setCreatorId(1);
//        p0.setTitle("TestProject");
//        p0.setDescription("TestProjectDescription");
//        Project p = projectService.createProject(p0);
//        Project pp = projectService.getProject(p.getId());
//        Assertions.assertEquals(p, pp);
//    }
//}
