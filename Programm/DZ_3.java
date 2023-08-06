// Домашнее задание к семинару №3 (Соколов Никита)

// Продвинутая работа с исключениями в Java (Промежуточная аттестация)

// Напишите приложение, которое будет запрашивать у пользователя следующие данные, разделенные
// пробелом: Фамилия Имя Отчество Номер_телефона
// Форматы данных: фамилия, имя, отчество - строки, номер_телефона - целое беззнаковое число без
// форматирования. Ввод всех элементов через пробел. Приложение должно проверить введенные данные
// по количеству. Если количество не совпадает с требуемым, вернуть код ошибки, обработать его и
// показать пользователю сообщение, что он ввел меньше и больше данных, чем требуется.
// Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры.
// Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы.
// Можно использовать встроенные типы java и создать свои.
// Исключение должно быть корректно обработано, пользователю выведено сообщение с информацией, что
// именно неверно. Если всё введено и обработано верно, должен создаться файл с названием, равным
// фамилии, в него в одну строку должны записаться полученные данные,вида:
// <Фамилия> <Имя> <Отчество> <номер_телефона>
// Однофамильцы должны записаться в один и тот же файл, в отдельные строки. Не забудьте закрыть
// соединение с файлом. При возникновении проблемы с чтением-записью в файл, исключение должно быть
// корректно обработано, пользователь должен увидеть стектрейс ошибки.

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DZ_3 {
    public static void main(String[] args) {
        System.out.println();
        System.out.println("Создание и запись базы данных.");
        Scanner scanner = new Scanner(System.in, "Cp866");
        String stroka = "";
        inputStroka();

        do {
            System.out.print("> ");
            stroka = scanner.nextLine();
            if (stroka.equals("exit")) break;
            try {
                process(stroka);
            } catch (DataException e) {
                System.out.println(e.getMessage());
                inputStroka();
            } catch (Exception ex) {
                System.out.println("Прочие ошибки: " + ex.getMessage());
            }
        } while (true);

        System.out.println("\n << Программа завершена >>>\n");
        scanner.close();
    }

    public static void process(String stroka) throws DataException {
        saveData(parserStroka(stroka));
    }

    public static void inputStroka(){
        System.out.println();
        System.out.println("> Введите данные формата <Фамилия> <Имя> <Отчеcтво> <Номер_телефона>");
        System.out.println("   разделение через пробел (для выхода наберите exit): ");
    }

    public static String[] parserStroka(String stroka) throws DataException {
        String[] strokaArray = stroka.split(" ");
        if (stroka == "")
            throw new DataException("  >>> Ошибка! Введена пустая строка"); 
        else if (strokaArray.length < 4)
            throw new DataException("  >>> Ошибка! Кол-во введенных данных МЕНЬШЕ заданного формата");
        else if (strokaArray.length > 4)
            throw new DataException("  >>> Ошибка! Кол-во введенных данных БОЛЬШЕ заданного формата");
        else if (!(strokaArray[0].matches("[a-zA-ZА-Яа-я]+")))
            throw new DataException("  >>> Ошибка! В <имени> допустимы только буквы !");
        else if (!(strokaArray[1].matches("[a-zA-ZА-Яа-я]+")))
            throw new DataException("  >>> Ошибка! В <отчестве> допустимы только буквы !");
        else if (!(strokaArray[2].matches("[a-zA-ZА-Яа-я]+")))
            throw new DataException("  >>> Ошибка! В <фамилии> допустимы только буквы !");
        else if (!(strokaArray[3].matches("[\\d]+")))
            throw new DataException("  >>> Ошибка! В <телефоне> допустимы только цифры !");
        return strokaArray;
    }

    public static void saveData(String[] dataArray) {
        String filename = dataArray[0] + ".txt";
        String strokaWrite = dataArray[0] + " " + dataArray[1] + " " + dataArray[2] + " " + dataArray[3] + "\n";
        try (FileWriter fileWriter = new FileWriter(filename, true)) {
            fileWriter.write(strokaWrite);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Прочие ошибки: " + ex.getMessage());
        }
    }
}

class DataException extends Exception {
    public DataException(String message) {
        super(message);
    }
}