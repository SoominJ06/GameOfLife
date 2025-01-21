/**
 * This program creates a omnivore that eats, moves, and dies.
 * 
 * @author Soomin Jeong
 * @version A2b
 */
public class Omnivore extends Lifeform implements CarnivoreEdible {

    /**
     * Instantiates the x and y coordinates of this lifeform,
     * the hunger and maximum hunger values, and died to false.
     */
    public Omnivore() {
        hunger = 0;
        maxHunger = 5;
    }

    public boolean matable(Lifeform checkLife) {
        if (checkLife instanceof Omnivore) {
            return true;
        } else {
            return false;
        }
    }

    public boolean edible(Lifeform checkLife) {
        if (checkLife instanceof OmnivoreEdible) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateReproduction(int mate, int food, int empty) {
        if (mate >= 1 && food >= 1 && empty >= 3) {
            return true;
        } else {
            return false;
        }
    }

}
