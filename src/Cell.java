/**
 * Keeps track of each cells inside the world
 */
public class Cell {
    public Lifeform life;
    public boolean isEmpty;

    public Cell(Lifeform setLife){
        setLife(setLife);
    }

    /**
     * Sets the life to this cell.
     * 
     * @param setLife represents what lifeform this cell holds 
     */
    public void setLife(Lifeform setLife) {
        life = setLife;
        if (setLife == null) {
            isEmpty = true;
        } else {
            isEmpty = false;
        }
    }
}
