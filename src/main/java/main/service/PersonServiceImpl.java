package main.service;


import main.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Configurable
@Service
public class PersonServiceImpl implements PersonService {

    @Value("${maxNumResults}")
    private int maxResults;



    // Хранилище клиентов
    private static final Map<Integer, Person> phoneBook = new HashMap<>();

    // Переменная для генерации ID клиента
    private static final AtomicInteger PERSON_ID_HOLDER = new AtomicInteger();

    //Добавление записи о человеке
    @Override
    public void addPerson(Person person) {
        final  int personId = PERSON_ID_HOLDER.incrementAndGet();
        person.setId(personId);
        phoneBook.put(personId,person);
    }

    //Получение списка всех записей
    @Override
    public List<Person> getAllPerson() {
        return new ArrayList<>(phoneBook.values());
    }

    //Получение  записи по id
    @Override
    public Person getIdPerson(int id) {
        return  phoneBook.get(id);
    }

    //Редактирование записи по id
    @Override
    public boolean updatePerson(Person person, int id) {
        if (phoneBook.containsKey(id)){
            person.setId(id);
            phoneBook.put(id, person);
            return true;
        }
        return false;
    }



    //Получение списка всех записей, удовлетворяющих поисковому запросу
    //имя начинается на query
    //телефон заканчивается на query
    @Override
    public List<Person> queryPerson(String query) {
        List<Person> result = new ArrayList<>();
         phoneBook.values().forEach(person -> {
             if (result.size() == maxResults)return;
             boolean nameStarsWits = person.getTitle().startsWith(query);
             boolean phoneEndsWith = person.getPhone().endsWith(query);
             if (nameStarsWits||phoneEndsWith) result.add(person);
         });

        return result;
    }

    //Удаление записи по id
    @Override
    public boolean deletePersonById(int id) {
        return phoneBook.remove(id) !=null;
    }
}










