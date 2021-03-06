# PhoneBookТелефонный справочник

Необходимо реализовать справочник телефонов на RESTFul API с возможностью поиска по первым буквам имени и последним цифрам телефона

Возможные действия:

Метод	Endpoint	Пояснение	Описание
POST	/person	В запросе передается body в формате JSON:
{
   “title”: “string”,
   “phone”: “string”,
   “address”: “string”
}

В ответе ожидается новая запись о человеке в формате JSON:
{
     “id”: number,
     “title”: “string”,
     “phone”: “string”,
     “address”: “string”
}
	Добавление записи о человеке с возможностью указания имени (title) и телефона (phone)
GET	/person	В ответе ожидается полный список записей в формате JSON:
[
    {
        “id”: number,
        “title”: “string”,
        “phone”: “string”,
        “address”: “string”
    }
]	Получение списка всех записей.

Количество возвращаемых записей должно быть ограничено N - число, задаваемое в конфигурационном файле











GET	/person/${id}	ID необходимой записи передается как path-параметр: например: /person/1

В ответе ожидается конкретная запись в формате JSON:
{
     “id”: number,
     “title”: “string”,
     “phone”: “string”,
     “address”: “string”
}




	Получение  записи по ее идентификатору
PUT	/person/${id}	ID записи, которую необходимо отредактировать, передается как path-параметр: например: /person/1

Редактируемые параметры передаются в body запроса в формате JSON:

{
     “title”: “string”,
     “phone”: “string”,
     “address”: “string”
}	Редактирование записи по ее идентификатору

Для редактирования доступны: имя, телефон, адрес
GET	/person?query=string	В ответе ожидается список записей, удовлетворяющих поисковому запросу в формате JSON:
[
  {
     “id”: number,
     “title”: “string”,
     “phone”: “string”,
     “address”: “string”
  }
]	Получение списка всех записей, удовлетворяющих поисковому запросу: имя человека начинается с указанного сочетания букв или телефон оканчивается на это сочетание

Количество возвращаемых записей должно быть ограничено N - число, задаваемое в конфигурационном файле
DELETE	/person/${id}	ID записи, которую необходимо удалить, передается как path-параметр, например: /person/1	Удаление записи по ее идентификатору




При доступных и адекватных запросах код ответа должен быть равен 200, 201 или 202

При недоступных или неадекватных запросах сервис должен возвращать соответствующие сообщения об ошибках, код ответа должен отличаться от 200, 201 или 202.

Задачу нужно решить наиболее оптимальным образом. Можно использовать любые opensource библиотеки, но механизм хранения/поиска записей нужно реализовать самостоятельно, т.е. нельзя использовать базы данных.

Проверка будет производиться автоматизированным тестирующим ПО.



