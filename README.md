Итоговая контрольная работа по блоку специализация  
Необходимо организовать систему учета для питомника в котором живут домашние и вьючные животные.  

Задание:  

***1. Используя команду cat в терминале операционной системы Linux, создать два файла Домашние животные (заполнив файл собаками, кошками, хомяками) и Вьючные животными (заполнив файл Лошадьми, верблюдами и ослы), а затем объединить их. Просмотреть cодержимое созданного файла. Переименовать файл, дав ему новое имя (Друзья человека).***  

Решение:  

1.1. Создаем файл командой: cat>"Домашние животные" и вводим названия животных:   
migel@Linux-5:~/final-control-work$ cat>"Домашние животные"  
Кошки  
Собаки  
Хомяки  
Коровы  
Козы  
Крысы  

1.2. Созадем второй файл командой: cat>"Вьючные животные" и вводим названия животных:   
migel@Linux-5:~/final-control-work$ cat>"Вьючные животные"  
Лошади  
Верблюды  
Ослы  
Мулы  

1.3. Объединяем два файла в один с другим названием командой: cat "Домашние животные" "Вьючные животные" > "Друзья человека"  

1.4. Смотрим содержимое полученного файла командой: cat "Друзья человека"  
результат:  
Кошки  
Собаки  
Хомяки  
Коровы  
Козы  
Крысы  
Лошади  
Верблюды  
Ослы  
Мулы  

***2. Создать директорию, переместить файл туда.***  

Решение:  

2.1. Создаем новую папку командой: mkdir "База питомника"  

2.2. Перемещаем объединенный файл в новую папку командой: mv "Друзья человека" "База питомника"/  

2.3. Смотрим содержимое текущей папки командрй: ls  
Видим что этого файла больше нет:  
'База питомника'  'Вьючные животные'  'Домашние животные'  

2.4. Переходим в новую папку командой: cd База\ питомника/  

2.5. Смотрим содержимое новой папки командой: ls  
Видим что этот файл находится здесь:  
'Друзья человека'  

***3. Подключить дополнительный репозиторий MySQL. Установить любой пакет из этого репозитория.***  

3.1. Для начала на сайте https://dev.mysql.com/downloads/repo/apt/ - смотрю последнюю доступную версию  
см. скриншот Task 3.1.  

3.2. Командой: wget https://dev.mysql.com/get/mysql-apt-config_0.8.26-1_all.deb - скачиваю файл deb с репозитория  
Командой: sudo dpkg -i mysql-apt-config_0.8.26-1_all.deb - устанавливаю deb пакет mysql  
Командой: sudo apt update - обновляем информацию о пакетах
см. скриншот Task 3.2.  

***4. Установить и удалить deb-пакет с помощью dpkg.***  
Устанавливаем Google Chrome  
4.1. Скачиваем:  
wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
4.2. Устанавливаем: sudo dpkg -i --force-depends google-chrome-stable_current_amd64.deb  
4.3. Запускаем: google-chrome  
4.4. Удаляем: sudo dpkg -r google-chrome-stable  

***5. Выложить историю команд в терминале ubuntu***  
cat>"Домашние животные"  
Кошки  
Собаки  
Хомяки  
Коровы  
Козы  
Крысы  

cat>"Вьючные животные"  
Лошади  
Верблюды  
Ослы  
Мулы  

cat "Домашние животные" "Вьючные животные" > "Друзья человека"  
cat "Друзья человека"  

mkdir "База питомника"  
mv "Друзья человека" "База питомника"/  
ls  
cd "База питомника"/  
ls  

wget https://dev.mysql.com/get/mysql-apt-config_0.8.26-1_all.deb  
sudo dpkg -i mysql-apt-config_0.8.26-1_all.deb  
sudo apt update  

wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb  
sudo dpkg -i --force-depends google-chrome-stable_current_amd64.deb  
google-chrome  
sudo dpkg -r google-chrome-stable  

***6. Нарисовать диаграмму, в которой есть класс родительский класс, домашние животные и вьючные животные, в составы которых в случае домашних животных войдут классы: собаки, кошки, хомяки, а в класс вьючные животные войдут: Лошади, верблюды и ослы.***  



***БЛОК ЗАДАНИЙ по MySQL - смотрите файл Друзья_человека.sql***   

    7. В подключенном MySQL репозитории создать базу данных “Друзья человека   
    8. Создать таблицы с иерархией из диаграммы в БД   
    9. Заполнить низкоуровневые таблицы именами(животных), командами которые они выполняют и датами рождения   
    10. Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу.  
    11. Создать новую таблицу “молодые животные” в которую попадут все животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью до месяца подсчитать возраст животных в новой таблице  
    12. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на прошлую принадлежность к старым таблицам.  


13. Создать класс с Инкапсуляцией методов и наследованием по диаграмме.  



***ВНИМАНИЕ! реализация программы была сделана так, как я его понял, дополняя его своими идеями и предположениями***
***прошу отнестись с пониманием...***

14. Написать программу, имитирующую работу реестра домашних животных.  
    В программе должен быть реализован следующий функционал:  
        14.1. Завести новое животное  
        14.2. определять животное в правильный класс  
        14.3. увидеть список команд, которое выполняет животное  
        14.4. обучить животное новым командам  
        14.5. Реализовать навигацию по меню  

    Программа написана в программе JAVA.  
    Выделены три класса:  
    Класс Animal - базовый класс животных  
    Класс Pets - класс "Домашних животных" на основе класса Animal  
    Класс PackAnimal - класс "Вьючных животных" на основе класса Animal  
    Животные при добавлении вручную определяюся в какой либой класс  
    Если вдруг животное не относится к Домашним или Вьючныым животным - то оно определяется в базоый класс Animal  
    "База данных" хранится в файле "database.txt"  
    При запуске программы автоматически произодится считывание данных из файла и формирование ArrayList с объектами животных по соответствующим классам.  

15. Создайте класс Счетчик, у которого есть метод add(), увеличивающий̆  значение внутренней̆int переменной̆на 1 при нажатие “Завести новое животное” Сделайте так, чтобы с объектом такого типа можно было работать в блоке try-with-resources. Нужно бросить исключение, если работа с объектом типа счетчик была не в ресурсном try и/или ресурс остался открыт. Значение
считать в ресурсе try, если при заведения животного заполнены все поля.  

    Счетчик "встроил" внутри класса Animal  
    Отдельный класс Счетчик (Counter) подготовил но привязать/реализовать его уже не успеваю...  


Студент: Семячков Владимир Сергеевич  
группа: Программирование | 9 | 3701 | 10.12.2022  