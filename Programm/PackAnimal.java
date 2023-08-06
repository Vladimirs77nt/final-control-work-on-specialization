// класс ВЬЮЧНЫЕ ЖИВОТНЫЕ - лошади, ослы, верблюды...

import java.util.ArrayList;

public class PackAnimal extends Animal {

    public PackAnimal(String typeAnimal, String name, int age, ArrayList<String> commands) {
        super(2, typeAnimal, name, age, commands);
    }

    @Override
    public String getClassName(){
        return "вьючное животное";
    }
}