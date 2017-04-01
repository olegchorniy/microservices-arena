package my.example.standalone;

import my.example.standalone.conversion.SourceEntity;
import my.example.standalone.conversion.TargetEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @Autowired
    private ConversionService conversionService;

    @RequestMapping("/converter")
    public String testConverter() {
        return "Can convert: " + conversionService.canConvert(SourceEntity.class, TargetEntity.class);
    }
}
