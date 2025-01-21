/**
 * This program creates a carnivore that eats, moves, and dies.
 * 
 * @author Soomin Jeong
 * @version A2b
 */
public class Carnivore extends Lifeform implements OmnivoreEdible {

    /**
     * Instantiates the x and y coordinates of this lifeform,
     * the hunger and maximum hunger values, and died to false.
     */
    public Carnivore() {
        hunger = 0;
        maxHunger = 5;
    }
    
    public boolean matable(Lifeform checkLife) {
        if (checkLife instanceof Carnivore) {
            return true;
        } else {
            return false;
        }
    }

    public boolean edible(Lifeform checkLife) {
        if (checkLife instanceof CarnivoreEdible) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateReproduction(int mate, int food, int empty) {
        if (mate >= 1 && food >= 2 && empty >= 3) {
            return true;
        } else {
            return false;
        }
    }
}
