package main.controller;

import main.model.Person;
import main.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Controller
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //Добавление записи о человеке
    @PostMapping(value = "/person")
    public ResponseEntity<?> addPerson(@RequestBody Person person){
        personService.addPerson(person);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    //Получение  записи по id
    @GetMapping(value = "/person/show/{id}")
    public ResponseEntity<Person> getIdPerson (@PathVariable int id) {
        Person person = personService.getIdPerson(id);
        return new ResponseEntity<>(person, HttpStatus.OK);

    }

    //Редактирование записи по id
    @PutMapping(value ="/person/edit/{id}")
    public ResponseEntity<Boolean> editPerson(@PathVariable(name = "id") int id, @RequestBody Person person) {
        boolean edit = personService.updatePerson(person, id);
        return new ResponseEntity<>(edit, HttpStatus.OK);


    }

    //Получение списка всех записей, удовлетворяющих поисковому запросу
    @GetMapping("/person")
    public ResponseEntity<?> searchPerson(@RequestParam(required = false) String query) {
        if (query == null) { // выводим все
            List<Person> persons = personService.getAllPerson();
            return new ResponseEntity<>(persons, HttpStatus.OK);
        }
        else if (query.matches("\\d+")) {// поиск по последним цифрам телефона
            List<Person> persons = personService.queryPerson(query).stream()
                    .filter(p -> p.getPhone().endsWith(query))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(persons, HttpStatus.OK);
        } else  if (query.matches("[A-Za-zА-Яа-яЁё]{1,3}")) {//по первым буквам имени
            List<Person> persons = personService.queryPerson(query).stream()
                      .filter(p -> p.getTitle().startsWith(query))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(persons, HttpStatus.OK);
        } else
        {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    //Удаление записи по id
    @DeleteMapping("/person/delete/{id}")
    public ResponseEntity<Boolean>  deletePersonById(@PathVariable int id) {
         boolean delete = personService.deletePersonById(id);

        return new ResponseEntity<>(delete, HttpStatus.OK);

    }
}
