import java.util.ArrayList;

public class Animal {

    private static int count;   // счетчик

    public int classAnimal;     // класс животного (домашнее - 1 и вьючное - 2, прочее - 3)
    public String typeAnimal;   // вид животного (кошка, собака, лошадь...)
    public String name;         // имя животного
    public int age;             // возраст животного
    public ArrayList<String> commands;   // - список команд животного

    public Animal (int classAnimal, String typeAnimal, String name, int age, ArrayList<String> commands) {
        this.classAnimal = classAnimal;
        this.typeAnimal = typeAnimal;
        this.name = name;
        this.age = age;
        this.commands = commands;
        count++;
    }

    public int getClassAnimal(){
        return classAnimal;
    }

    public String getClassName(){
        return "прочие животные";
    }

    public String getTypeAnimal(){
        return typeAnimal;
    }

    public String getName(){
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCommandsString() {
        int count = commands.size();
        if (count == 0) return "";
        String line = "";
        for (String comm1 : commands) {
            line += comm1 + " ";
        }
        line = line.substring(0, line.length() - 1);
        return line;
    }

    public static int getCount() {
        return count;
    }
}