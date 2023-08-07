# 7. В подключенном MySQL репозитории создать базу данных “Друзья человека”

DROP schema `Друзья_человека`;
CREATE schema `Друзья_человека`;
USE `Друзья_человека`;


# 8. Создать таблицы с иерархией из диаграммы в БД

CREATE table humanFriend(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(45),
    Command VARCHAR(45),
    Birthday Date
    );

CREATE table pet (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(45),
    Command VARCHAR(45),
    Birthday DATE
);

CREATE table cat (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(45),
    Command VARCHAR(45),
    Birthday DATE
);


CREATE table dog (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(45),
    Command VARCHAR(45),
    Birthday DATE
);


CREATE table hamster (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(45),
    Command VARCHAR(45),
    Birthday DATE
);

CREATE table packAnimal (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    liftWeight INT,
    Name VARCHAR(45),
    Command VARCHAR(45),
    Birthday DATE
);

CREATE table horse (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	liftWeight INT,
    Name VARCHAR(45),
    Command VARCHAR(45),
	Birthday DATE
);

CREATE table donkey (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	liftWeight INT,
    Name VARCHAR(45),
    Command VARCHAR(45),
	Birthday DATE
);

CREATE table camel (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	liftWeight INT,
    Name VARCHAR(45),
    Command VARCHAR(45),
	Birthday DATE
);

    
# 9. Заполнить низкоуровневые таблицы именами(животных), командами которые они выполняют и датами рождения    

    INSERT into cat (Name, Command, Birthday) values
    ('Васька', 'Играть', '2020-10-05'),
    ('Коржик', 'Спать', '2019-11-12'),
	('Борька', 'Прыгать', '2022-04-22'),
    ('Оливия', 'Лежать', '2020-12-01');
    
    SELECT * FROM cat;

    INSERT into dog (Name, Command, Birthday) values
    ('Бобик', 'Играть', '2020-10-11'),
    ('Гром', 'Сторожить', '2017-06-06'),
	('Буба', 'Лежать', '2021-11-02'),
    ('Джон', 'Поймать', '2019-02-21');
	
    SELECT * FROM dog;
 
	INSERT into hamster (Name, Command, Birthday) values
    ('Пепель', 'Стоять', '2020-03-09'),
    ('Сток', 'Играть', '2019-08-14'),
	('Шустик', 'Кушать', '2021-02-21'),
    ('Рыжий', 'Спать', '2020-01-11');
	
    SELECT * FROM hamster;
     
	INSERT into camel (camelName, camelCommand, camelLiftWeight, camelBirthday) values
    ('Мустафа', 'Идти', 150, '2020-02-03'),
    ('Крафа', 'Идти', 180, '2019-01-16'),
	('Бабай', 'Сидеть', 300, '2020-03-15'),
    ('Колючка', 'Бежать', 120, '2022-12-01');
	
    SELECT * FROM camel;
     
	INSERT into horse (Name, Command, liftWeight, Birthday) values
    ('Жиган', 'Прыгать', 200, '2021-02-08'),
    ('Черняка', 'Бегать', 120, '2010-04-18'),
	('Ботан', 'Прыгать', 135, '2015-02-11'),
    ('Лентяй', 'Прыгать', 115, '2017-08-28');
	
    SELECT * FROM horse;    
     
	INSERT into donkey (Name, Command, liftWeight, Birthday) values
    ('Алладин', 'Бежать', 55, '2018-03-31'),
    ('Гамар', 'Лежать', 15, '2015-02-21'),
	('Оссирис', 'Идти', 28, '2020-03-09'),
    ('Ятарак', 'Идти', 90, '2020-01-11');
	
    SELECT * FROM donkey;  	
 

# 10. Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой питомник на зимовку.
#     Объединить таблицы лошади, и ослы в одну таблицу.

DELETE FROM camel where id > 0;
SELECT * FROM camel;
 

# 11.Создать новую таблицу “молодые животные” в которую попадут все животные старше 1 года, но младше
#     3 лет и в отдельном столбце с точностью до месяца подсчитать возраст животных в новой таблице

CREATE table youngAnimal (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY)
SELECT  Name, 
        Command, 
        Birthday
FROM horse union 
SELECT  Name, 
        Command, 
        Birthday
FROM donkey;

SELECT * FROM youngAnimal;

## Создание таблицы, в которой все животные в возрасте от 1 до 3 лет.
INSERT into pet (Name, Command, Birthday)
SELECT  Name, 
        Command, 
        Birthday
FROM cat union 
SELECT  Name, 
        Command, 
        Birthday
FROM dog union
SELECT  Name, 
        Command, 
        Birthday
FROM hamster;
SELECT * FROM pet;

INSERT into humanFriend (Name, Command, Birthday)
SELECT  Name, 
        Command, 
        Birthday
FROM pet union 
SELECT  Name, 
        Command, 
        Birthday
FROM youngAnimal;
SELECT * FROM humanFriend;

CREATE table youngAnimals (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY)
SELECT Name, 
        Command, 
        Birthday,
        Round((year(current_date()) - year(Birthday)) + (month(current_date() - month(Birthday)))/10, 2) as age
FROM humanFriend
where Round((year(current_date()) - year(Birthday)) + (month(current_date() - month(Birthday)))/10, 2) > 1 
	and Round((year(current_date()) - year(Birthday)) + (month(current_date() - month(Birthday)))/10, 2) < 3;
SELECT * FROM youngAnimals;


# 12. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на прошлую принадлежность к старым таблицам.

CREATE table newhumanFriend (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY)
SELECT  Name, 
        Command,
        Birthday,
        'cat' as oldTable
FROM cat union 
SELECT  Name, 
        Command, 
        Birthday,
        'dog' as oldTable
FROM dog union
SELECT  Name, 
        Command, 
        Birthday,
        'hamster' as oldTable
FROM hamster union 
SELECT  Name, 
        Command, 
        Birthday,
        'horse' as oldTable
FROM horse union 
SELECT  Name, 
        Command, 
        Birthday,
        'donkey' as oldTable
FROM donkey;

SELECT * FROM newhumanFriend;