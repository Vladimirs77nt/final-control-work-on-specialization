public class pitomnik {

    public static void main(String[] args) {
        System.out.println("ДОБРО ПОЖАЛОВАТЬ В ПИТОМНИК !");

        do {
            menu();
            
        } while (true);

    }

    public static void menu(){
        System.out.println();
        System.out.println("База данных питомника");
        System.out.println();
        System.out.println(" > 1 - Вывести список животных питомника");
        System.out.println(" > 2 - Завести новое животное");
        System.out.println(" > 3 - Выбрать животное для работы с ним");
        System.out.println(" > 0 - завершение работы программы");
    }
}