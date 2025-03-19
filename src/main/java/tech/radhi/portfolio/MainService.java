package tech.radhi.portfolio;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import tech.radhi.portfolio.content.ContentService;
import tech.radhi.portfolio.content.ContentTemplate;

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
        List<ContentTemplate> questions = contentRepo.getListOfContent("q");
        model.addAttribute("questions", questions);
    }
}
