package tech.radhi.portfolio;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MainController {
    @ResponseBody
    @GetMapping("/overview")
    public String overview(){
        return """
            Hi there! Radhi is a software
                    developer who loves diving into web development and backend projects, especially with Java. When he is not
                    coding, you’ll probably find me strategizing over a game of chess. he is also a computer science major and a
                    proud Arch Linux user, always up for exploring new ideas and technologies . . .
               \s""";
    }

    @ResponseBody
    @GetMapping("/about-me-brief")
    public String aboutMeBrief(){
        return """
            Radhi currently is a computer science
                    engineering student at the University of Dunaujvaros - Hungary. He is passionate about technology and loves to
                    explore new ideas and concepts. Radhi is a quick learner and enjoys working on challenging projects that push him
                    to think outside the box. He is always looking for new opportunities to grow and develop his skills . . .
               \s""";
    }

    @ResponseBody
    @GetMapping("/social-overview")
    public String socialOverview(){
        return """
            When Radhi's not coding, he's a dynamic online presence, sparking conversations and connections.
                    With a knack for fostering meaningful relationships, he's a magnet for like-minded individuals who
                    share his passion for innovation. Radhi's online persona is a reflection of his curious and
                    adventurous spirit, always seeking out new perspectives and ideas.
               \s""";
    }

    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("questions",
                List.of(
                        new QaItem("first", "who am I?"),
                        new QaItem("second", "What is my career goal?"),
                        new QaItem("third", "What tech stack I use?"),
                        new QaItem("forth", "What projects did I do?")
                ));
        return "about";
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }
}

