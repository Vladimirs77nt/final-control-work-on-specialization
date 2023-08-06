package Primer_2.Units;

import java.util.ArrayList;

public interface GameInterface {
    void step(ArrayList<BaseHero> teamArray);
    String getInfo();
}