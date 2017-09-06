package my.example.standalone;

import my.example.standalone.conversion.AgeEntity;
import my.example.standalone.conversion.SourceEntity;
import my.example.standalone.service.AgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@RestController
@CrossOrigin
public class WebController {

    private final ConversionService conversionService;

    private final AgeService ageService;

    @Autowired
    public WebController(ConversionService conversionService, AgeService ageService) {
        this.conversionService = conversionService;
        this.ageService = ageService;
    }

    @RequestMapping("/converter")
    public String testConverter() {
        return "Can convert: " + conversionService.canConvert(SourceEntity.class, AgeEntity.class);
    }

    @GetMapping("/age/{name}")
    public AgeEntity getAge(@PathVariable("name") String name) {
        return ageService.getAge(name);
    }

    /**
     * Receives requests sent by:
     * <pre>
     * $.ajax({
     *     url: 'https://localhost:8090/html',
     *     method: 'POST',
     *     data: document.body.innerHTML,
     *     contentType: 'text/plain'
     *  })
     *  .done(function(res) {
     *      console.log(res);
     *  });
     * </pre>
     */
    @PostMapping("/html")
    public Object html(@RequestBody String content) throws IOException {
        Files.write(Paths.get("C:/files/ytb.html"), Arrays.asList(content));

        return "OK";
    }
}
