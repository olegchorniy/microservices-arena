package my.example.standalone;

import my.example.standalone.conversion.AgeEntity;
import my.example.standalone.conversion.SourceEntity;
import my.example.standalone.service.AgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
}
