package tech.radhi.portfolio;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Controller
public class MainController {

    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/")
    public String index(Model model,
            @RequestHeader(value = "HX-Request", required = false) String hxRequest
    ) {
        mainService.getIndexContent(model);
        mainService.handleHtmxRequests(model, hxRequest);
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model,
            @RequestHeader(value = "HX-Request", required = false) String hxRequest
    ) {
        mainService.getAboutContent(model);
        mainService.handleHtmxRequests(model, hxRequest);
        return "about";
    }

    @GetMapping("/403")
    public String forbidden(Model model,
                            @RequestHeader(value = "HX-Request", required = false) String hxRequest
    ) {
        mainService.handleHtmxRequests(model, hxRequest);
        return "fragments/403";
    }

    @GetMapping("/soon")
    public String social(Model model,
                        @RequestHeader(value = "HX-Request", required = false) String hxRequest
    ) {
        var attributes  = Map.of(
                "status",503,
                "timestamp",Date.from(Instant.now()),
                "error","Page Under Construction"
        );
        model.addAllAttributes(attributes);
        mainService.handleHtmxRequests(model, hxRequest);
        return "error";
    }
}

