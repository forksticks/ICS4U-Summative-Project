/*
 * Author: Stanley Huang
 * Date: June 2, 2023
 * Description: this class contains information about what a move does
 * NOTE: moves have an attribute for applying status effects, but I scrapped that from my code due to time constraints
 */
public class Moves 
{
    //declare the instance variables
    private String strMoveName;
    private int intDamage;
    private int intHeal;
    private int intRaiseAttack;
    private int intLowerAttack;
    private int intRaiseDefense;
    private int intLowerDefense;
    private int intRaiseSpeed;
    private int intLowerSpeed;

    //default constructor
    public Moves()
    {
        this.strMoveName = "";
        this.intDamage = 0;
        this.intHeal = 0;
        this.intRaiseAttack = 0;
        this.intLowerAttack = 0;
        this.intRaiseDefense = 0;
        this.intLowerDefense = 0;
        this.intRaiseSpeed = 0;
        this.intLowerSpeed = 0;
    }

    //constructor to populate a move's attributes
    public Moves(String name, int damage, int heal, int raiseA, int lowerA, int raiseD, int lowerD, int raiseS, int lowerS)
    {
        this.strMoveName = name;
        this.intDamage = damage;
        this.intHeal = heal;
        this.intRaiseAttack = raiseA;
        this.intLowerAttack = lowerA;
        this.intRaiseDefense = raiseD;
        this.intLowerDefense = lowerD;
        this.intRaiseSpeed = raiseS;
        this.intLowerSpeed = lowerS;
    }

    /*
     * Author: Stanley Huang
     * Date: June 5, 2023
     * Description: toString to print out a move's attributes
     */
    public String toString()
    {
        return "Name: " + this.strMoveName + "\nDamage: " + this.intDamage + "\nHealing: " + this.intHeal + "\nRaises attack: " + this.intRaiseAttack
        + "\nLowers attack: " + this.intLowerAttack + "\nRaises defense: " + this.intRaiseDefense + "\nLowers defense: " + this.intLowerDefense
        + "\nRaises speed: " + this.intRaiseSpeed + "\nLowers speed: " + this.intLowerSpeed;
    }

    /*
     * Author: Stanley Huang
     * Date: June 6, 2023
     * Description: getter method to get a move's name
     */
    public String getName()
    {
        return this.strMoveName;
    }
    /*
     * Author: Stanley Huang
     * Date: June 5, 2023
     * Description: getter method to get a move's damage
     */
    public int getDamage()
    {
        return this.intDamage;
    }

    /*
     * Author: Stanley Huang
     * Date: June 5, 2023
     * Description: getter method to get a move's healing
     */
    public int getHeal()
    {
        return this.intHeal;
    }

    /*
     * Author: Stanley Huang
     * Date: June 5, 2023
     * Description: getter method to get a move's raise attack attribute
     */
    public int getRaiseAttack()
    {
        return this.intRaiseAttack;
    }

    /*
     * Author: Stanley Huang
     * Date: June 5, 2023
     * Description: getter method to get a move's lower attack attribute
     */
    public int getLowerAttack()
    {
        return this.intLowerAttack;
    }

    /*
     * Author: Stanley Huang
     * Date: June 5, 2023
     * Description: getter method to get a move's raise defense attribute
     */
    public int getRaiseDefense()
    {
        return this.intRaiseDefense;
    }

    /*
     * Author: Stanley Huang
     * Date: June 5, 2023
     * Description: getter method to get a move's lower defense attribute
     */
    public int getLowerDefense()
    {
        return this.intLowerDefense;
    }

    /*
     * Author: Stanley Huang
     * Date: June 5, 2023
     * Description: getter method to get a move's raise speed attribute
     */
    public int getRaiseSpeed()
    {
        return this.intRaiseSpeed;
    }

    /*
     * Author: Stanley Huang
     * Date: June 5, 2023
     * Description: getter method to get a move's lower speed attribute
     */
    public int getLowerSpeed()
    {
        return this.intLowerSpeed;
    }
}
