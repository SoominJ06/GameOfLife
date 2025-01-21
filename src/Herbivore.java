/**
 * This program creates a herbivore that eats, moves, and dies.
 * 
 * @author Soomin Jeong
 * @version A2b
 */
public class Herbivore extends Lifeform implements CarnivoreEdible, OmnivoreEdible {

    /**
     * Instantiates the x and y coordinates of this lifeform,
     * the hunger and maximum hunger values, and died to false.
     */
    public Herbivore() {
        // surroundingCells = new int[8];
        hunger = 0;
        maxHunger = 5;
    }

    public boolean matable(Lifeform checkLife) {
        if (checkLife instanceof Herbivore) {
            return true;
        } else {
            return false;
        }
    }

    public boolean edible(Lifeform checkLife) {
        if (checkLife instanceof HerbivoreEdible) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateReproduction(int mate, int food, int empty) {
        if (mate >= 1 && food >= 2 && empty >= 2) {
            return true;
        } else {
            return false;
        }
    }

    // /**
    //  * 
    //  */
    // @Override
    // public void migrate(World world, int y, int x) {
    //     int[] newCoord = chooseCell(world, 0, y, x);
    //     incrementHunger();
    //     world.newGrid[newCoord[0]][newCoord[1]].setLife(this);
    //     world.newGrid[newCoord[0]][newCoord[1]].life.setHunger(hunger);
    //     world.grid[y][x].setLife(null);
    //     world.grid[newCoord[0]][newCoord[1]].setLife(null);        // System.out.print("new guy hunger: " + world.newGrid[newCoord[0]][newCoord[1]].life.hunger);
    //     // System.out.println(", old guy hunger: " + hunger);
    //     // world.newGrid[y][x].setLife(null);
    //     // world.grid[y][x].setLife(null);
    // }
}
