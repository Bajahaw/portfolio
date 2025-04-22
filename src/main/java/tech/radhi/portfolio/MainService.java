package tech.radhi.portfolio;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import tech.radhi.portfolio.content.ContentService;

import java.util.List;

@Service
public class MainService {
    ContentService contentRepo;

    public MainService(ContentService service){
        contentRepo = service;
    }

    public void handleHtmxRequests(Model model, String header){
        if ("true".equals(header)) {
            model.addAttribute("headless", true);
        }
    }

    public void getAboutContent(Model model) {
        var skills = contentRepo.getContentById("skills").split("\\.");
        var questions = contentRepo.getListOfContent("q");
        model.addAttribute("questions", questions);

        if (skills.length <= 2) {
            skills = new String[]{"sorry","error", "happened"};
        }

        model.addAttribute("frontend", List.of(skills[0].split(",")));
        model.addAttribute("backend", List.of(skills[1].split(",")));
        model.addAttribute("tools", List.of(skills[2].split(",")));
    }
}
