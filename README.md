# Документация

[Планировании автоматизации тестирования](https://github.com/ElenaGorshenina/AQADiplom/blob/master/docs/Plan.md)

[Отчётные документы по итогам тестирования](https://github.com/ElenaGorshenina/AQADiplom/blob/master/docs/Report.md)

[Отчётные документы по итогам автоматизации](https://github.com/ElenaGorshenina/AQADiplom/blob/master/docs/Summary.md)


# Тестирование веб-сервиса "Путешествие дня"

Тестирование приложения, которое предлагает купить тур по определённой цене с помощью двух способов:

1. Обычная оплата по дебетовой карте;

2. Выдача кредита по данным банковской карты.


## Начало работы

Перед началом работы удостоверьтесь, что у вас есть необходимый набор ПО, описанный в разделе Prerequisites. 
Для получения копии проекта необходимо клонировать репозиторий командой git clone https://github.com/ElenaGorshenina/AQADiplom.git;

### Prerequisites

Для работы вам потребуются следующие ПО:

1. Средство для программирования - Idea;

2. Система контроля версий - Git;

3. Браузер - Google Chrome;

4. Средство для развертывания проектов - Docker-compose.

### Установка и запуск

1. Открыть программу PuTTY;

2. В окне "Host Name (or IP address)" ввести адрес 185.119.57.9, нажать кнопку Open;

3. В появившемся окне ввести логин/пароль (student/netologystudent);

4. Необходимо клонировать репозиторий командой `git clone https://github.com/ElenaGorshenina/AQADiplom.git`;

5. Перейти в папку командой `cd AQADiplom`;

6. Создать контейнеры в скопированном проекте `docker-compose up -d`;

7. Запустить контейнеры командой `docker-compose start`;

8. Запустить jar-файл с базой данных MySQL командой 

`java "-Dspring.datasource.url=jdbc:mysql://185.119.57.9:3306/app" -jar artifacts/aqa-shop.jar`;

9. Открыть IntelliJ IDEA Ultimate;

10. Открыть терминал и запустить тесты командой 

`./gradlew clean test "-Ddb.url=jdbc:mysql://185.119.57.9:3306/app"`;

11. Для запуска jar-файла с базой данных PostgreSQL необходимо перейти в виртуальную машину и воспользоваться командой 

`java "-Dspring.datasource.url=jdbc:postgresql://185.119.57.9:5432/app" -jar artifacts/aqa-shop.jar`;

12. Перейти в IntelliJ IDEA Ultimate;

13. Открыть терминал и запустить тесты командой 

`./gradlew clean test "-Ddb.url=jdbc:postgresql://185.119.57.9:5432/app"`;

14. Сгенерировать отчет с помощью фреймворка Allure

`./gradlew allureReport`

`./gradlew allureServe`;

15. Перейти в виртуальную машину и остановить контейнеры командой `docker-compose stop`;

16. Удалить контейнеры командой `docker-compose down`.