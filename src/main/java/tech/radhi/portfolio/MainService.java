package tech.radhi.portfolio;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import tech.radhi.portfolio.content.ContentService;
import tech.radhi.portfolio.content.ContentTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainService {
    private final ContentService contentService;

    public MainService(ContentService service){
        contentService = service;
    }

    public void getIndexContent(Model model){
        var pics = contentService.getListOfContent("img");
        var map = pics.stream().collect(Collectors.toMap(
                ContentTemplate::id,
                ContentTemplate::contentBody,
                (oldV, newV) -> newV
        ));
        model.addAttribute("imgs", map);
    }

    public void handleHtmxRequests(Model model, String header){
        if ("true".equals(header)) {
            model.addAttribute("headless", true);
        }
    }

    public void getAboutContent(Model model) {
        var skills = contentService.getContentById("skills").split("\\.");
        var questions = contentService.getListOfContent("q");
        var projects = contentService.getListOfProjects();

        model.addAttribute("questions", questions);
        model.addAttribute("projects", projects);

        if (skills.length <= 2) skills = new String[]{"sorry","error", "happened"};
        model.addAttribute("frontend", List.of(skills[0].split(",")));
        model.addAttribute("backend", List.of(skills[1].split(",")));
        model.addAttribute("tools", List.of(skills[2].split(",")));
    }
}
