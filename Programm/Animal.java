import java.util.ArrayList;

import org.w3c.dom.css.Counter;

public class Animal {

    protected static int count = 0;   // счетчик

    protected int id;              // id номер
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
        this.id = count;
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

    public int getID() {
        return id;
    }

    public void addNewCommand(String newCommand) {
        commands.add(newCommand);
    }
}