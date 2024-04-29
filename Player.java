/*
 * Author: Stanley Huang
 * Date: June 2, 2023
 * Description: This class will store the information of the current player
 */
public class Player
{
    //declare instance variables
    private String strName;
    private int intWinStreak;

    //default constructor
    public Player()
    {
        this.strName = "";
        this.intWinStreak = 0;
    }

    //constructor to build an object
    public Player(String name)
    {
        this.strName = name;
        this.intWinStreak = 0;
    }
    
    /*
     * Author: Stanley Huang
     * Date: June 2, 2023
     * Description: method to set the win streak
     */
    public void setWinStreak(boolean win)
    {
        //checking to see if they actually won a battle, if they did, their winstreak increases by one
        //otherwise winstreak goes back to 0
        if (win = true)
        {
            this.intWinStreak++;
        }
        else
        {
            this.intWinStreak = 0;
        }
    }

    /*
     * Author: Stanley Huang
     * Date: June 2, 2023
     * Description: method to get the current win streak of the player
     */
    public int getWinStreak()
    {
        return this.intWinStreak;
    }

    /*
     * Author: Stanley Huang
     * Date: June 2, 2023
     * Description: method to get the name of the player
     */
    public String getName()
    {
        return this.strName;
    }

    /*
     * Author: Stanley Huang
     * Date: June 2, 2023
     * Description: toString method to print out the player as a String
     */
    public String toString()
    {
        return "Player Name: " + this.strName + "\nWin Streak: " + this.intWinStreak;
    }
}