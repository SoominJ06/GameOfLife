/**
 * This program creates a lifeform that reproduces, and dies.
 * 
 * @author Soomin Jeong
 * @version A2b
 */
public class Plant extends Lifeform implements HerbivoreEdible, OmnivoreEdible {

    /**
     * Takes care of the move for this plant;
     * move meaning the herbivore can either reproduce, or don't do anything.
     * 
     * @param world the world this plant resides in
     */
    @Override
    public void move(World world, int y, int x) {
        Cell newCell = world.newGrid[y][x];
        
        // If the cell is not occupied with any other lifeforms, check if it can reproduce
        int move = validateAll(world, y, x);
        if (move == 1) {
            reproduce(world, y, x);
        } else {
            // If it couldn't reproduce, don't do anything but to set the new cell to itself
            newCell.setLife(this);
            die(world.grid[y][x]);
        }
    }

    /**
     * A method that validates if this lifeform can reproduce.
     * For a plant, if there are at least 2 neighboring lifeform of its own kind, 
     * and at least 3 empty cells, they are able to reproduce.
     *  
     * @param world represents the world this lifeform lives on
     * @return true if it can reproduce, false otherwise
     */
    @Override
    public int validateAll(World world, int y, int x) {
        surroundingCells = checkSurrounding(world, y, x);
        int mate = 0;
        int empty = 0;
        
        for (int i = 0; i < 8; i++) {
            if (surroundingCells[i] == 0) {
                empty++;
            } else if (surroundingCells[i] == 1) {
                mate++;
            }
        }
        
        if (validateReproduction(mate, 0, empty)) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean validateReproduction(int mate, int food, int empty) {
        if (mate >= 2 && empty >= 3) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean matable(Lifeform checkLife) {
        if (checkLife instanceof Plant) {
            return true;
        } else {
            return false;
        }    
    }

    @Override
    public boolean edible(Lifeform checkLife) {
        return false;
    }

    /**
     * Plants don't eat; just return.
     */
    public void eat(World world, int y, int x) {
        return;
    }

    /**
     * Plants don't move around cells; just return.
     */
    @Override
    public void migrate(World world, int y, int x) {
        return;
    }

    public boolean reachedMaxHunger() {
        return false;
    }

    public void incrementHunger() {
        return;
    }

    public void setHunger() {
        return;
    }
}
