package my.example.webui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private HttpSession session;

    @RequestMapping("/set/{name}/{value}")
    @ResponseStatus(HttpStatus.OK)
    public void setAttribute(@PathVariable("name") String name,
                             @PathVariable("value") String value) {
        session.setAttribute(name, value);
    }

    @RequestMapping("/get/{name}")
    public String getAttribute(@PathVariable("name") String name) {
        return (String) session.getAttribute(name);
    }
}
