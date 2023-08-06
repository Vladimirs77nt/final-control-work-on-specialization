import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Programm {
    public static ArrayList<Animal> pitomnik_animals;
    public static String filename = "database.txt";

    public static void main(String[] args) {
        System.out.println("ДОБРО ПОЖАЛОВАТЬ В ПИТОМНИК !");
        load_data();
        Scanner scanner = new Scanner(System.in, "Cp866");

        String stroka = "";

        do {
            main_menu();
            System.out.print("> ");
            stroka = scanner.nextLine();
            if (stroka.equals("0")) break;
            controller(Integer.parseInt(stroka));
            
        } while (true);

        System.out.println("\n << Программа завершена >>>\n");
        scanner.close();
    }

    /**
     * Вывод на экран меню - список команд для работы с базой данных питомника
     */
    public static void main_menu(){
        System.out.println();
        System.out.println("База данных питомника");
        System.out.println("Меню операций:");
        System.out.println(" > 1 - Вывести список животных питомника");
        System.out.println(" > 2 - Завести новое животное");
        System.out.println(" > 3 - Выбрать животное для работы с ним");
        System.out.println(" > 4 - Сохранение базы данны в файл");
        System.out.println(" > 0 - завершение работы программы");
        System.out.println();
    }
    
    /**
     * Контроллер выбора команды в меню (терминал)
     * @param choice - номер в меню
     */
    public static void controller(int choice){
        System.out.println(" --- запуск контроллера ---");
        System.out.println("Команда = " + choice);
        switch (choice) {
            case 1:
                printAnimal();
                break;
            case 2:
                add_animal(inputAnimal());
                break;
            case 3:
                
                break;
            case 4:
                save_data();
                break;
            default:
                break;
        }
    }
    
    // чтение данных с файла и инициализация базы данных
    public static void load_data () {
        File file = new File(filename);
        pitomnik_animals = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = reader.readLine()) != null) {
                add_animal(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("  !!! База данных прочитана из файла " + filename);
    }

    // ЗАПИСЬ данных в файл
    public static void save_data () {

        Path path = Paths.get(filename);
        try (BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8))
        {
            String line = "";
            for (Animal animal : pitomnik_animals) {
                line = animal.classAnimal + " " + animal.typeAnimal + " " + animal.name + " " + animal.age + " " + animal.getCommandsString() + "\n";
                bw.write(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("  !!! База данных записана в файл " + filename);
    }

    /**
     * Метод вывода на экран всех животных в питомнике
     */
    public static void printAnimal (){
        for (Animal animal : pitomnik_animals) {
            System.out.println();
            System.out.println("Класс животного: " + animal.classAnimal + " (" + animal.getClassName() + ")");
            System.out.println("Вид животного: " + animal.typeAnimal);
            System.out.println("Имя: "  + animal.name);
            System.out.println("Возвраст: " + animal.age);
            System.err.println("Команды: " + animal.commands.toString());
        }
    }

    /**
     * Добавление нового животного в базу данных
     * @return
     */
    public static String inputAnimal(){
        System.out.println();
        System.out.println(" >>> ДОБАВЛЕНИЕ ЖИВОТНОГО В ПИТОМНИКЕ");
        System.out.println("Введите класс животного (число 1-2-3):");
        System.out.println("  1 - Домашнее животное");
        System.out.println("  2 - Вьючное животное");
        System.out.println("  3 - Прочие животные");
        boolean flag = true;
        Scanner scanner = new Scanner(System.in, "Cp866");
        String classAnimal = "";
        do {
            classAnimal = scanner.nextLine();
            if (classAnimal.equals("1") || classAnimal.equals("2") || classAnimal.equals("3"))
                flag = false;
            else System.out.println(" ошибка! введено неверное значение! введите число 1, 2 или 3...");
        } while (flag);

        System.out.println("Введите вид животного:");
        String typeAnimal = scanner.nextLine();

        String nameAnimal = "";
        System.out.println("Введите имя животного:");
        flag = true;
        do {
            nameAnimal = scanner.nextLine();
            if (nameAnimal.length()>2) flag = false;
            else System.out.println(" ошибка! введите имя длинее 2 символов");
        } while (flag);
        
        System.out.println("Введите возраст животного (число):");
        String ageAnimal = "";
        flag = true;
        do {
            ageAnimal = scanner.nextLine();
            if (isNumeric(ageAnimal)) flag = false;
            else System.out.println(" ошибка! введите число от 0 и выше");
        } while (flag);

        System.out.println("Введите команды (через пробел):");
        String commandsAnimal = scanner.nextLine();

        String line = classAnimal + " " + typeAnimal + " " + nameAnimal + " " + ageAnimal + " " + commandsAnimal;
        return line;
    }

    /**
     * Метод добавления в базу данных ArrayList животного
     * @param line - строка с данными разделения через пробел
     * класс + вид + имя + возраст + список команд
     */
    public static void add_animal(String line) {
        String[] strokaArray = line.split(" ");
        if (strokaArray.length<4) return;

        int classAnimal = Integer.parseInt(strokaArray[0]);
        String typeAnimal = strokaArray[1];
        String name = strokaArray[2];
        int age = Integer.parseInt(strokaArray[3]);

        ArrayList<String> command = new ArrayList<String>();
        int count_command = strokaArray.length - 4;
        if (count_command>0) {
            for (int i = 0; i < count_command; i++) {
                command.add(strokaArray[i+4]);
            }
        }

        if (classAnimal == 1) {
            pitomnik_animals.add(new Pets(typeAnimal, name, age, command));
        } else if (classAnimal == 2) {
            pitomnik_animals.add(new PackAnimal(typeAnimal, name, age, command));
        } else {
            pitomnik_animals.add(new Animal(3, typeAnimal, name, age, command));
        }
    }

    public static boolean isNumeric(String s) {
        return s.chars().allMatch(Character::isDigit);
    }

}

class DataException extends Exception {
    public DataException(String message) {
        super(message);
    }
}