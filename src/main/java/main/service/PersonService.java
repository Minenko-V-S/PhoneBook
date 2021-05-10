package main.service;

import main.model.Person;

import java.util.List;

public interface PersonService {

    //Добавление записи о человеке
    void addPerson(Person person);

    //Получение списка всех записей
    List<Person> getAllPerson();

    //Получение  записи по id
    Person getIdPerson(int id);

    //Редактирование записи по id
    boolean updatePerson(Person person, int id);


    //Получение списка всех записей, удовлетворяющих поисковому запросу
    List<Person> queryPerson(String query);

    //Удаление записи по id
    boolean deletePersonById(int id);
}
