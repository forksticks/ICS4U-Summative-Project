/*
 * Author: Stanley Huang
 * Date: June 5, 2023
 * Description: this class inherits stats from the Battler class and adds more stats to battlers
 */
public class TruePotential extends Battler
{
    //declare instance variables
    //these stats will not be affected, so a variable for base stats aren't required
    private int intShred;
    private int intDodge;

    //default constructor
    public TruePotential()
    {
        super();
        this.intShred = 0;
        this.intDodge = 0;
    }

    //constructor to populate the battler's attributes
    public TruePotential(String name, int bHealth, int health, int bAttack, int attack, int bDefense, int defense, int bSpeed, int speed, Moves one, Moves two, Moves three, int shred, int dodge)
    {
        super(name, bHealth, health, bAttack, attack, bDefense, defense, bSpeed, speed, one, two, three);
        this.intShred = shred;
        this.intDodge = dodge;
    }

    /*
     * Author: Stanley Huang
     * Date: June 5, 2023
     * Description: toString method to print out the additional stats
     */
    public String toString()
    {
        return super.toString() + "\nShred: " + this.intShred + "\nDodge: " + this.intDodge;
    }

    /*
     * Author: Stanley Huang
     * Date: June 5, 2023
     * Description: getter method to get the battler's shred stat
     */
    public int getShred()
    {
        return this.intShred;
    }

    /*
     * Author: Stanley Huang
     * Date: June 5, 2023
     * Description: getter method to get the battler's dodge stat
     */
    public int getDodge()
    {
        return this.intDodge;
    }
    
}
