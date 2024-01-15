package pl.sawiak_company.sok.project_mind_map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sawiak_company.sok.project_mind_map.mind_map_dtos.*;
import pl.sawiak_company.sok.project_mind_map.question.Question;
import pl.sawiak_company.sok.project_mind_map.question.QuestionService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MindMapService {
    @Autowired
    private QuestionService questionService;

    public NodesPackageDto getPackage(Integer hypothesisId) {
        List<Question> questions = questionService.getAllByHypothesis(hypothesisId);
        List<EdgeDto> edges = new ArrayList<>();
        List<NodeDto> nodes = new ArrayList<>();

        final Integer[] edgeId = {0};
        questions.forEach(question -> {
            nodes.add(new NodeDto(question.getId().toString(), new DataFlow(question.getText()), "mindmap", new Position(question.getPosX(), question.getPosY())));
            question.getQuestions().forEach(childQuestion -> {
                edgeId[0] += 1;
                edges.add(new EdgeDto(edgeId[0].toString(), question.getId().toString(), childQuestion.getId().toString()));
            });
        });

        return new NodesPackageDto(nodes, edges);
    }

    public void setNodes(List<NodeDto> nodes) {
        nodes.forEach(node -> {
            questionService.editQuestionPosition(Integer.valueOf(node.getId()),
                    node.getPosition().getX(),
                    node.getPosition().getY()
            );
        });
    }
}
