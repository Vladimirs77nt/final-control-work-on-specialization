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
        System.out.println();
        System.out.println("ДОБРО ПОЖАЛОВАТЬ В ПИТОМНИК !");
        load_data();
        Scanner scanner = new Scanner(System.in, "Cp866");

        String stroka = "";

        do {
            main_menu();
            System.out.print("> ");
            stroka = scanner.nextLine();
            if (stroka.equals("0")) break;
            try {
                controller(Integer.parseInt(stroka));}
            catch (Exception ex){
                System.out.println("ошибка ввода...");
            }
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
        System.out.println(" > 3 - Увидеть список команд животного по ID");
        System.out.println(" > 4 - Добавить новые команды для животного по ID");
        System.out.println(" > 5 - Удалить животное по ID");
        System.out.println(" > 6 - Сохранение базы данны в файл");
        System.out.println(" > 0 - завершение работы программы");
        System.out.println();
    }
    
    /**
     * Контроллер выбора команды в меню (терминал)
     * @param choice - номер в меню
     */
    public static void controller(int choice){
        switch (choice) {
            case 1: // список всех животных
                printAnimal();
                break;
            case 2: // новое животное
                inputAnimal();
                break;
            case 3: // список команд животного
                commandAnimal();
                break;
            case 4: // добавление новых команд животного
                addCommandAnimal();;
                break;
            case 5: // удаление животного по ID
                deleteAnimal();
                break;
            case 6: // запись в файл
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
     * Метод вывода на экран ВСЕХ животных в питомнике
     */
    public static void printAnimal (){
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("%-3s %-10s %-16s %-14s %-12s %s\n", "ID", "Животное", "имя", "класс", "возраст", "команды");
        System.out.println("---------------------------------------------------------------------------------------------");

        for (Animal animal : pitomnik_animals) {
            System.out.printf("%-3d %-10s %-10s (%-17s)     %-5d  %-10s \n",
                                animal.getID(), animal.typeAnimal, animal.getName(), animal.getClassName(), animal.getAge(), animal.getCommandsString());
        }
        System.out.println("---------------------------------------------------------------------------------------------");

    }

    /**
     * Метод вывода на экран карточки животного по ID
     */
    public static void printAnimal2 (int id){
        for (Animal animal : pitomnik_animals) {
            if (animal.getID() == id) {
                System.out.println();
                System.err.println("ID: " + animal.getID());
                System.out.println("Класс животного: " + animal.classAnimal + " (" + animal.getClassName() + ")");
                System.out.println("Вид животного: " + animal.typeAnimal);
                System.out.println("Имя: "  + animal.name);
                System.out.println("Возвраст: " + animal.age);
                System.err.println("Команды: " + animal.commands.toString());
                break;
            }
        }
    }

    /**
     * Метод УДАЛЕНИЯ животного по ID
     */
    public static void deleteAnimal (){
        System.out.println();
        System.out.println(" >>> УДАЛЕНИЕ ЖИВОТНОГО ИЗ ПИТОМНИКА");
        System.out.println("Введите ID номер животного (число):");

        boolean flag = true;
        Scanner scanner = new Scanner(System.in, "Cp866");
        String deleteID = "";
        flag = true;
        do {
            deleteID = scanner.nextLine();
            if (isNumeric(deleteID)) flag = false;
            else System.out.println(" ошибка! введите число от 0 и выше");
        } while (flag);

        int id = Integer.parseInt(deleteID);
        flag = false;
        for (Animal animal : pitomnik_animals) {
            if (animal.getID() == id) {
                pitomnik_animals.remove(animal);
                System.out.println(" !! животное с номером ID=" + id + " (" + animal.getTypeAnimal() + " " + animal.getName() + ") удалено !!");
                flag = true;
                break;
            }
        }
        if (!flag)
            System.out.println(" !! животного с таким ID нет !!");
    }

    /**
    * Метод вывода списка команд для животного по ID
    */
    public static void commandAnimal (){
        System.out.println();
        System.out.println(" >>> Вывод списка команд для животного");
        System.out.println("Введите ID номер животного (число):");

        boolean flag = true;
        Scanner scanner = new Scanner(System.in, "Cp866");
        String deleteID = "";
        flag = true;
        do {
            deleteID = scanner.nextLine();
            if (isNumeric(deleteID)) flag = false;
            else System.out.println(" ошибка! введите число от 0 и выше");
        } while (flag);

        int id = Integer.parseInt(deleteID);
        flag = false;
        for (Animal animal : pitomnik_animals) {
            if (animal.getID() == id) {
                pitomnik_animals.remove(animal);
                System.out.println(" >> Список команд для животного с номером ID=" + id + " (" + animal.getTypeAnimal() + " " + animal.getName() + "):");
                System.out.println(animal.getCommandsString());
                flag = true;
                break;
            }
        }
        if (!flag)
            System.out.println(" !! животного с таким ID нет !!");
    }

    /**
    * Метод вывода списка команд для животного по ID
    */
    public static void addCommandAnimal (){
        System.out.println();
        System.out.println(" >>> Добавление новых команд для животного");
        System.out.println("Введите ID номер животного (число):");

        boolean flag = true;
        Scanner scanner = new Scanner(System.in, "Cp866");
        String deleteID = "";
        flag = true;
        do {
            deleteID = scanner.nextLine();
            if (isNumeric(deleteID)) flag = false;
            else System.out.println(" ошибка! введите число от 0 и выше");
        } while (flag);

        int id = Integer.parseInt(deleteID);
        flag = false;
        for (Animal animal : pitomnik_animals) {
            if (animal.getID() == id) {
                System.out.println("\n >> Текущий список команд для животного с номером ID=" + id + " (" + animal.getTypeAnimal() + " " + animal.getName() + "):");
                System.out.println(animal.getCommandsString());
                System.out.println("\nВведите новые команды (через пробел):");
                String newCommandsAnimal = scanner.nextLine();
                String[] strokaArray = newCommandsAnimal.split(" ");
                for (int i = 0; i < strokaArray.length; i++) {
                    if (strokaArray[i] != "")
                        animal.addNewCommand(strokaArray[i]);
                }
                System.out.println("\n >> Обновленный список команд для животного с номером ID=" + id + " (" + animal.getTypeAnimal() + " " + animal.getName() + "):");
                System.out.println(animal.getCommandsString());
                flag = true;
                break;
            }
        }
        if (!flag)
            System.out.println(" !! животного с таким ID нет !!");
    }

    /**
     * Добавление нового животного в базу данных
     * @return
     */
    public static void inputAnimal(){
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
        add_animal(line);
        System.out.println(" !! новое животное добавлено в питомник !!");
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