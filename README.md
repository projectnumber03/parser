# Парсер excel файла

## системные требования
+ jre 11
+ gradle 7.4.1
## используемые технологии
+ springboot
+ spring-jpa
+ spring-aop
+ h2-database
+ apache-poi
+ guava
+ apache-commons
+ lombok
## описание
Парсинг происходит в класс `mei.testtask.parser.model.dto.ParsedData`

Расчетные тоталы, сгруппированные по дате, доступны по методу `getTotals()`

Запись значений происходит в таблицу `PARSED_DATA`

Для запуска в качестве готового решения необходимо выполнить команду:
```$xslt
java -jar parser-0.0.1.jar %fileName%
```
Где `%fileName%` - путь к excel-файлу
## параметры
`header.level` - глубина заголовка

`sheet.number` - номер листа, с которого осуществляется парсинг

`model.package` - пакет хранения dto-классов

`date.pattern` - дата, на протяжении месяца которой будут случайным образом формироваться даты записей
## автор
Dmitry Shilov

email: [dunderflute@yandex.ru](mailto:dunderflute@yandex.ru)
