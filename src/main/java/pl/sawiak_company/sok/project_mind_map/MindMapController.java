package pl.sawiak_company.sok.project_mind_map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sawiak_company.sok.project_mind_map.mind_map_dtos.NodeDto;
import pl.sawiak_company.sok.project_mind_map.mind_map_dtos.NodesPackageDto;
import pl.sawiak_company.sok.project_mind_map.mind_map_dtos.UpdatePositionDto;
import pl.sawiak_company.sok.project_mind_map.question.dto.QuestionRequest;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/web/mind-map")
public class MindMapController {
    @Autowired
    MindMapService mindMapService;

    @GetMapping("/{hypothesisId}")
    public ResponseEntity<NodesPackageDto> getPackage(@PathVariable(name = "hypothesisId") Integer hypothesisId) {
        NodesPackageDto packageDto = mindMapService.getPackage(hypothesisId);
        return new ResponseEntity<>(packageDto, HttpStatus.OK);
    }

    @PostMapping("/{hypothesisId}")
    public ResponseEntity<Void> setNodes(@PathVariable(name = "hypothesisId") Integer hypothesisId,
                                         @RequestBody UpdatePositionDto request) {
        mindMapService.setNodes(request.getNodes());
        log.info("Zaktualizowano pozycję dla pytań hipotezy o id: " + hypothesisId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
