// класс домашних животных - КОШКИ, СОБАКИ, КОРОВЫ

import java.util.ArrayList;

public class Pets extends Animal{

    public Pets(String typeAnimal, String name, int age, ArrayList<String> commands) {
        super(1, typeAnimal, name, age, commands);
    }

    @Override
    public String getClassName(){
        return "домашнее животное";
    }
}