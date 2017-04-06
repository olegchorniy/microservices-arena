package my.example.mongodb.spring;

import my.example.mongodb.spring.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class MongoWebController {

    private final MongoTemplate mongoTemplate;
    private final PersonRepository personRepository;

    @Autowired
    public MongoWebController(MongoTemplate mongoTemplate, PersonRepository personRepository) {
        this.mongoTemplate = mongoTemplate;
        this.personRepository = personRepository;
    }

    @RequestMapping("/getAll")
    public List<Person> getAllPersons() {
        return mongoTemplate.findAll(Person.class);
    }

    @RequestMapping("/getByName/{name}")
    public List<Person> getByName(@PathVariable("name") String name) {
        return personRepository.findByName(name);
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public Person save(@RequestBody Person person) {
        return personRepository.save(person);
    }
}
