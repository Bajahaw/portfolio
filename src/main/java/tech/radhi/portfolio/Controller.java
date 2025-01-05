package tech.radhi.portfolio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.stereotype.Controller
public class Controller {
    @ResponseBody
    @GetMapping("/ai")
    public String response(){
        return """
                Hi there! Radhi is a software
                        developer who loves diving into web development and backend projects, especially with Java. When he is not
                        coding, you’ll probably find me strategizing over a game of chess. he is also a computer science major and a
                        proud Arch Linux user, always up for exploring new ideas and technologies . . .
                   \s""";
    }
}
