/*
 * Author: Stanley Huang
 * Date: June 5, 2023
 * Description: This class contains information about battlers
 */
public class Battler 
{
    //declare instance variables
    private String strBattlerName;
    private int intBaseHealth;
    private int intHealth;
    private int intBaseAttack;
    private int intAttack;
    private int intBaseDefense;
    private int intDefense;
    private int intBaseSpeed;
    private int intSpeed;
    private Moves move1;
    private Moves move2;
    private Moves move3;

    //default constructor
    public Battler()
    {
        this.strBattlerName = "";
        this.intBaseHealth = 0;
        this.intHealth = 0;
        this.intBaseAttack = 0;
        this.intAttack = 0;
        this.intBaseDefense = 0;
        this.intDefense = 0;
        this.intBaseSpeed = 0;
        this.intSpeed = 0;
        this.move1 = null;
        this.move2 = null;
        this.move3 = null;
    }

    //constructor to populate the battler's attributes
    public Battler(String name,int bHealth, int health, int bAttack, int attack, int bDefense, int defense, int bSpeed, int speed, Moves one, Moves two, Moves three)
    {
        this.strBattlerName = name;
        this.intBaseHealth = bHealth;
        this.intHealth = health;
        this.intBaseAttack = bAttack;
        this.intAttack = attack;
        this.intBaseDefense = bDefense;
        this.intDefense = defense;
        this.intBaseSpeed = bSpeed;
        this.intSpeed = speed;
        this.move1 = one;
        this.move2 = two;
        this.move3 = three;
    }

    /*
     * Author: Stanley Huang
     * Date: June 5, 2023
     * Description: toString to output a battler's stats
     */
    public String toString()
    {
        //only base stats are returned, because the other variables are used during a battle and constantly change
        return "Name: " + this.strBattlerName + "\nHealth: " + this.intBaseHealth + "\nAttack: " + this.intBaseAttack + "\nDefense: " + this.intBaseDefense + "\nSpeed: " + this.intBaseSpeed;
    }

    /*
     * Author: Stanley Huang
     * Date: June 6, 2023
     * Description: to get the name of the battler
     */
    public String getName()
    {
        return this.strBattlerName;
    }

    /*
     * Author: Stanley Huang
     * Date: June 5, 2023
     * Description: getter method to get the battler's current health
     */
    public int getHealth()
    {
        return this.intHealth;
    }

    /*
     * Author: Stanley Huang
     * Date: June 5, 2023
     * Description: getter method to get the battler's current attack stat
     */
    public int getAttack()
    {
        return this.intAttack;
    }

    /*
     * Author: Stanley Huang
     * Date: June 5, 2023
     * Description: getter method to get the battler's current defense stat
     */
    public int getDefense()
    {
        return this.intDefense;
    }

    /*
     * Author: Stanley Huang
     * Date: June 5, 2023
     * Description: getter method to get the battler's current speed statt
     */
    public int getSpeed()
    {
        return this.intSpeed;
    }

    /*
     * Author: Stanley Huang
     * Date: June 5, 2023
     * Description: getter method to get the battler's first move
     */
    public Moves getMoveOne()
    {
        return this.move1;
    }

    /*
     * Author: Stanley Huang
     * Date: June 5, 2023
     * Description: getter method to get the battler's second move
     */
    public Moves getMoveTwo()
    {
        return this.move2;
    }
    
    /*
     * Author: Stanley Huang
     * Date: June 5, 2023
     * Description: getter method to get the battler's second move
     */
    public Moves getMoveThree()
    {
        return this.move3;
    }

    /*
     * Author: Stanley Huang
     * Date: June 13, 2023
     * Description: getter method to get base health
     */
    public int getBaseHealth()
    {
        return this.intBaseHealth;
    }

    /*
     * Author: Stanley Huang
     * Date: June 12, 2023
     * Description: getter method to get base attack
     */
    public int getBaseAttack()
    {
        return this.intBaseAttack;
    }

    /*
     * Author: Stanley Huang
     * Date: June 12, 2023
     * Description: getter method to get base defense
     */
    public int getBaseDefense()
    {
        return this.intBaseDefense;
    }

    /*
     * Author: Stanley Huang
     * Date: June 12, 2023
     * Description: getter method to get base speed
     */
    public int getBaseSpeed()
    {
        return this.intBaseSpeed;
    }

    /*
     * Author: Stanley Huang
     * Date: June 5, 2023
     * Description: setter method to change the battler's current health
     */
    public void setHealth(int damage)
    {
        this.intHealth = damage;
    }

    /*
     * Author: Stanley Huang
     * Date: June 5, 2023
     * Description: setter method to change the battler's current attack stat
     */
    public void setAttack(int change)
    {
        this.intAttack = change;
    }

    /*
     * Author: Stanley Huang
     * Date: June 5, 2023
     * Description: setter method to change the battler's current defense stat
     */
    public void setDefense(int change)
    {
        this.intDefense = change;
    }

    /*
     * Author: Stanley Huang
     * Date: June 5, 2023
     * Description: setter method to change the battler's current speed stat
     */
    public void setSpeed(int change)
    {
        this.intSpeed = change;
    }

    /*
     * Author: Stanley Huang
     * Date: June 5, 2023
     * Description: this method reverts all of the stats of a battler to its base statsand removes any status effects inflicted on them after the battle ends
     */
    public void revertStats()
    {
        this.intHealth = this.intBaseHealth;
        this.intAttack = this.intBaseAttack;
        this.intDefense = this.intBaseDefense;
        this.intSpeed = this.intBaseSpeed;
    }
}
