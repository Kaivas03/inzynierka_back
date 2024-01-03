package pl.sawiak_company.sok.sociological_project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/web/project")
public class SociologicalProjectController {
    @Autowired
    private SociologicalProjectService projectService;

    @PostMapping("/")
    public ResponseEntity<SociologicalProject> create(@RequestBody String name) {
        SociologicalProject project = projectService.createProject(name);
        log.info("Created new Project {}", project);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<SociologicalProject> edit(@PathVariable(name = "id") Integer id, @RequestBody String name) {
        SociologicalProject project = projectService.editProject(id, name);
        log.info("Updated Project {}", id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SociologicalProject> getById(@PathVariable(name = "id") Integer id) {
        SociologicalProject project = projectService.getById(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<SociologicalProject>> getAll() {
        List<SociologicalProject> projects = projectService.getAll();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
        projectService.deleteProject(id);
        log.info("Deleted Project {}", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
