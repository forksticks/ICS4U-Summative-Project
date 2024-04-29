/*
 * Author: Stanley Huang
 * Date: June 9, 2023
 * Description: The main class which will run the program
 */

 //importing stuff from java library such as arraylists and scanner
import java.io.*;
import java.util.*;
public class HuangBattler 
{
    /*
     * Author: Stanley Huang
     * Date: June 9, 2023
     * Description: main method which runs the program
     */
    public static void main(String[] args)
    {
        //declare variables
        Player player = null;
        String strTempNameHolder;
        ArrayList<Battler> allyTeam = new ArrayList<Battler>();
        ArrayList<TruePotential> trueAllyTeam = new ArrayList<TruePotential>();
        EnemyTeam enemyTeam;
        boolean bolWinOrLose = true;
        boolean bolPlay = true;
        boolean bolTryCatch = true;
        boolean bolSave;
        int intChoice;
        int intSave;

        //welcome to user to the game
        System.out.println("Welcome to The Battlegrounds");

        //ask the user for their name and then build a new player
        System.out.println("What is your name?");
        strTempNameHolder = new Scanner(System.in).nextLine();
        player = new Player(strTempNameHolder);

        //to let the user build their team
        allyTeam = buildAlliedTeam();
        System.out.println("Your Team: ");
        for (int i = 0; i < 3; i++)
        {
            System.out.println(allyTeam.get(i) + "\n");
        }

        //loop to allow users to keep playing
        do
        {
            //start the battle when the user has less than 5 wins (regular battles, no true potential battlers)
            if(player.getWinStreak() < 5)
            {
                //generate a random enemy team and sort it based on health (lowest to highest health)
                enemyTeam = buildEnemyTeam();
                enemyTeam = sortEnemyTeam(enemyTeam);

                //start the battle
                bolWinOrLose = battle(allyTeam, enemyTeam);

                //keep track of the player's win streak
                player.setWinStreak(bolWinOrLose);

            }
            
            //start the battle when users have more than 5 wins (true potential battles)
            else
            {
                //generate a random enemy team and sort it based on health(lowest to highest health)
                enemyTeam = buildEnemyTeam();
                enemyTeam = sortEnemyTeam(enemyTeam);

                //convert the user's team to a true potential team
                trueAllyTeam = convertAlliedTeam(allyTeam);

                //start the battle
                bolWinOrLose = truePotentialBattle(trueAllyTeam, enemyTeam);

                //keep track of the player's win streak
                player.setWinStreak(bolWinOrLose);
            }

            //output the user's win streak
            System.out.println("Current winstreak: " + player.getWinStreak());
            
            //ask if they want to play again
            System.out.println("Would you like to play another round?\nType 1 for yes and any other number for no.");

            //try and catch for when user does not enter an integer
            do
            {
                try
                {
                    intChoice = new Scanner(System.in).nextInt();
                    if (intChoice == 1)
                    {
                        bolPlay = true;
                        bolTryCatch = false;
                    }
                    else
                    {
                        bolPlay = false;
                        bolTryCatch = false;
                    }
                }
                catch(Exception e)
                {
                    System.out.println("Please enter a number.");
                    bolTryCatch = true;
                }
            }
            while(bolTryCatch);

            //to reset the stats and health of battlers for a new battle
            for (int i = 0; i < allyTeam.size(); i++)
            {
                allyTeam.get(i).revertStats();
            }
        
            //tell the player that true potential is unlocked
            if (player.getWinStreak() == 5)
            {
                System.out.println("Your battlers have grown...");
            }

        }
        while(bolPlay);

        //ask if they want to save their win streak
        //their name and win streak count are saved to a file
        System.out.println("Would you like to save your winstreak?\nType 1 for yes and any other number for no.");

        //loop and try catch for when the user does not 
        do
        {
            try
            {
                intSave = new Scanner(System.in).nextInt();
                if (intSave == 1)
                {
                    writeToFile(player);
                    bolSave = false;
                }
                else
                {
                    bolSave = false;
                }
            }
            catch(Exception e)
            {
                System.out.println("Please enter a number.");
                bolSave = true;
            }
        }
        while(bolSave);

    }

    /*
     * Author: Stanley Huang
     * Date: June 9, 2023
     * Description: this method will let the user build their team
     */
    public static ArrayList<Battler> buildAlliedTeam()
    {
        //declare variables
        int intTempChoice = 0;
        boolean bolTryCatch = true;
        ArrayList<Battler> listOfBattlers = new ArrayList<Battler>();
        listOfBattlers = BattleBase.BattlerList(BattleBase.MovesList());
        ArrayList<Battler> alliedTeam = new ArrayList<Battler>();
        Battler tempBattler;

        //to iterate through the arraylist and display all of the battlers
        System.out.println("Here are the available battlers: ");
        for (int i = 0; i < listOfBattlers.size(); i++)
        {
            System.out.println("Battler " + (i+1) + "\n" + listOfBattlers.get(i) + "\n");
        }

        //let the user select their team
        System.out.println("Please choose your battlers (you can choose 3)");
        for (int i = 0; i < 3; i++)
        {

            //try and catch when user doesn't enter an integer
            do
            {
                try
                {
                    intTempChoice = new Scanner(System.in).nextInt();
                    if (intTempChoice > listOfBattlers.size() || intTempChoice < 1)
                    {
                        System.out.println("Please enter a number that corresponds to one of the battlers.");
                        bolTryCatch = true;
                    }
                    else
                    {
                        bolTryCatch = false;
                    }
                }
                catch(Exception e)
                {
                    System.out.println("Invalid option. Please enter a digit.");
                }
            }
            while(bolTryCatch);
            
            //temporary hold a battler
            tempBattler = listOfBattlers.get(intTempChoice-1);

            //generate a new battler which copies the attributes from the temporary battler, since adding them directly, doesn't allow you to have duplicates
            alliedTeam.add(new Battler(tempBattler.getName(), tempBattler.getBaseHealth(), tempBattler.getHealth(), tempBattler.getBaseAttack(), tempBattler.getAttack(), tempBattler.getBaseDefense(), tempBattler.getDefense(), tempBattler.getBaseSpeed(), tempBattler.getSpeed(), tempBattler.getMoveOne(), tempBattler.getMoveTwo(), tempBattler.getMoveThree()));
        }

        return alliedTeam;
    }

    /*
     * Author: Stanley Huang
     * Date: June 13, 2023
     * Description: method to convert a normal battler team to a true potential team
     */
    public static ArrayList<TruePotential> convertAlliedTeam(ArrayList<Battler> team)
    {
        //declare variables
        ArrayList<TruePotential> listOfBattlers = new ArrayList<TruePotential>();
        listOfBattlers = BattleBase.TruePotentialBattlerList(BattleBase.MovesList());
        ArrayList<TruePotential> trueTeam = new ArrayList<TruePotential>();
        Battler tempHolder;
        TruePotential tempBattler = null;

        //iterate through the list of allied battlers
        for (int i = 0; i < team.size(); i++)
        {
            //temporarily hold a battler which will be copied
            tempHolder = team.get(i);

            //nested for loop to check if the temporary battler is the same as the battler in the true potential list
            for (int j = 0; j < listOfBattlers.size(); j++)
            {
                //if a match is found, the battler will be copied into the new arraylist of the allied team
                if (tempHolder.getName().equals(listOfBattlers.get(j).getName()))
                {
                    tempBattler = listOfBattlers.get(j);
                    trueTeam.add(new TruePotential(tempBattler.getName(), tempBattler.getBaseHealth(), tempBattler.getHealth(), tempBattler.getBaseAttack(), tempBattler.getAttack(), tempBattler.getBaseDefense(), tempBattler.getDefense(), tempBattler.getBaseSpeed(), tempBattler.getSpeed(), tempBattler.getMoveOne(), tempBattler.getMoveTwo(), tempBattler.getMoveThree(), tempBattler.getShred(), tempBattler.getDodge()));
                }
                
            }
        }

        return trueTeam;
    }

    /*
     * Author: Stanley Huang
     * Date: June 9, 2023
     * Description: method to build the enemy's team
     */
    public static EnemyTeam buildEnemyTeam()
    {
        //declaring variables
        ArrayList<Battler> listOfBattlers = new ArrayList<Battler>();
        listOfBattlers = BattleBase.BattlerList(BattleBase.MovesList());
        EnemyTeam enemyTeam = new EnemyTeam();
        Battler battler1;
        Battler battler2;
        Battler battler3;

        //these are also temporary battlers which will get copied
        //using math.random to generate a random team
        battler1 = listOfBattlers.get((int)(10*Math.random()));
        battler2 = listOfBattlers.get((int)(10*Math.random()));
        battler3 = listOfBattlers.get((int)(10*Math.random()));

        //generate the enemy team by copying the temporary battlers
        enemyTeam.addNode(1, new Battler(battler1.getName(), battler1.getBaseHealth(), battler1.getHealth(), battler1.getBaseAttack(), battler1.getAttack(), battler1.getBaseDefense(), battler1.getDefense(), battler1.getBaseSpeed(), battler1.getSpeed(), battler1.getMoveOne(), battler1.getMoveTwo(), battler1.getMoveThree()));
        enemyTeam.addNode(2, new Battler(battler2.getName(), battler2.getBaseHealth(), battler2.getHealth(), battler2.getBaseAttack(), battler2.getAttack(), battler2.getBaseDefense(), battler2.getDefense(), battler2.getBaseSpeed(), battler2.getSpeed(), battler2.getMoveOne(), battler2.getMoveTwo(), battler2.getMoveThree()));
        enemyTeam.addNode(3, new Battler(battler3.getName(), battler3.getBaseHealth(), battler3.getHealth(), battler3.getBaseAttack(), battler3.getAttack(), battler3.getBaseDefense(), battler3.getDefense(), battler3.getBaseSpeed(), battler3.getSpeed(), battler3.getMoveOne(), battler3.getMoveTwo(), battler3.getMoveThree()));

        return enemyTeam;
    }

    /*
     * Author: Stanley Huang
     * Date: June 14, 2023
     * Description: Selection sort method to sort the enemy's team based on their health points
     */
    public static EnemyTeam sortEnemyTeam(EnemyTeam enemy)
    {
        //declare variables
        int currentMinIndex;
        Battler temp;
        
        //to loop through the linked list
        for(int i = 1; i <= enemy.length; i++)
        {
            //set the current lowest index
            currentMinIndex = i;

            //iterate through the linked list and compare the health of each battlers
            for(int j = i+1; j <= enemy.length; j++)
            {
                //if a lower health battler is found, update the lowest index
                if(enemy.findNode(j).cargo.getHealth() < enemy.findNode(currentMinIndex).cargo.getHealth())
                {
                    currentMinIndex = j;
                }
            }
            
            //swap number if needed
            if(i != currentMinIndex)
            {
                temp = enemy.findNode(currentMinIndex).cargo;
                enemy.findNode(currentMinIndex).cargo = enemy.findNode(i).cargo;
                enemy.findNode(i).cargo = temp;
            }
        }
        
       return enemy;
            
    }

    /*
     * Author: Stanley Huang
     * Date: June 12, 2023
     * Description: method for starting battles
     */
    public static boolean battle(ArrayList<Battler> team, EnemyTeam enemy)
    {
        //declare variables
        Battler currentAllyBattler;
        Battler currentEnemyBattler;
        boolean bolBattle = true;
        boolean bolCurrentBattle = true;
        boolean bolTryCatch = true;
        boolean bolWinLose = true;
        int intSelection = 0;
        int intEnemySelection = 0;
        int intBattler = 1;
        Moves tempMove = null;
        Moves enemyTempMove = null;
        
        //tell the user the battle started
        System.out.println("Battle start. Which battler would you like to send out?");
        currentAllyBattler = selectBattler(team);
        currentEnemyBattler = selectEnemyBattler(enemy, intBattler);

        //to loop the battle until all of the enemy's team or ally team faints
        do
        {
            //to loop the ongoing 1 vs 1 battle occurring
            do
            {
                //show the user the health of the battlers on the field and display move choices that the user can select
                System.out.println("\nAlly's Health: " + currentAllyBattler.getHealth());
                System.out.println("Enemy's Health: " + currentEnemyBattler.getHealth());
                System.out.println("\nWhich move would you like to use?");
                System.out.println("1: " + currentAllyBattler.getMoveOne().getName());
                System.out.println("2: " + currentAllyBattler.getMoveTwo().getName());
                System.out.println("3: " + currentAllyBattler.getMoveThree().getName());

                //try catch to make sure an integer is selected
                do
                {
                    try
                    {
                        //make sure they choose a number that corresponds to one of the moves
                        intSelection = new Scanner(System.in).nextInt();
                        if (intSelection < 1 || intSelection > 3)
                        {
                            System.out.println("Please choose a valid move.");
                            bolTryCatch = true;
                        }
                        else
                        {
                            bolTryCatch = false;
                        }
                    }
                    catch(Exception e)
                    {
                        System.out.println("Please enter a digit that corresponds to a move.");
                        bolTryCatch = true;
                    }
                }
                while(bolTryCatch);

                //grab the corresponding move based on the selected number
                if (intSelection == 1)
                {
                    tempMove = currentAllyBattler.getMoveOne();
                }
                else if (intSelection == 2)
                {
                    tempMove = currentAllyBattler.getMoveTwo();
                }
                else
                {
                    tempMove = currentAllyBattler.getMoveThree();
                }

                //enemy's move selection
                intEnemySelection = (int)(3*Math.random());

                //grab the corresponding move based on the selected number
                if (intEnemySelection == 0)
                {
                    enemyTempMove = currentEnemyBattler.getMoveOne();
                }
                else if (intEnemySelection == 1)
                {
                    enemyTempMove = currentEnemyBattler.getMoveTwo();
                }
                else
                {
                    enemyTempMove = currentEnemyBattler.getMoveThree();
                }

                //check which battler has the higher speed
                if (currentAllyBattler.getSpeed() >= currentEnemyBattler.getSpeed())
                {
                    //implement the allied move using a method
                    implementAllyMove(tempMove, currentAllyBattler, currentEnemyBattler);
                    
                    //calculate damage dealt to ally
                    currentEnemyBattler.setHealth((int)(currentEnemyBattler.getHealth() - calcAllyDamage(tempMove, currentAllyBattler, currentEnemyBattler)));                    

                    //if the enemy battler's health reaches less than 0, they faint, and it ends the current battle
                    if (currentEnemyBattler.getHealth() <= 0)
                    {
                        System.out.println("Enemy " + currentEnemyBattler.getName() + " fainted.");
                        bolCurrentBattle = false;
                    }

                    //otherwise, it switches to the other battler's turn
                    else
                    {
                        //implement the enemy's move using a method
                        implementEnemyMove(enemyTempMove, currentAllyBattler, currentEnemyBattler);

                        //calculate damage dealt to ally
                        currentAllyBattler.setHealth((int)(currentAllyBattler.getHealth() - calcEnemyDamage(enemyTempMove, currentAllyBattler, currentEnemyBattler)));

                        //check if ally battler faints, if they do the current battle ends, otherwise the battle loops
                        if (currentAllyBattler.getHealth() <= 0)
                        {
                            System.out.println("Ally " + currentAllyBattler.getName() + " fainted.");
                            bolCurrentBattle = false;
                        }

                        else
                        {
                            bolCurrentBattle = true;
                        }
                    }
                }
                
                //when the enemy battler is faster than the ally battler
                else
                {
                    //implement the enemy's move using a method
                    implementEnemyMove(enemyTempMove, currentAllyBattler, currentEnemyBattler);

                    //calculate damage dealt to ally
                    currentAllyBattler.setHealth((int)(currentAllyBattler.getHealth() - calcEnemyDamage(enemyTempMove, currentAllyBattler, currentEnemyBattler)));

                    //check if ally faints, if not, move to the ally's turn of action
                    if (currentAllyBattler.getHealth() <= 0)
                    {
                        System.out.println("Ally " + currentAllyBattler.getName() + " fainted.");
                        bolCurrentBattle = false;
                    }
                    else
                    {
                        //implement the ally's move using a method
                        implementAllyMove(tempMove, currentAllyBattler, currentEnemyBattler);

                        //calculate damage dealt to enemy
                        currentEnemyBattler.setHealth((int)(currentEnemyBattler.getHealth() - calcAllyDamage(tempMove, currentAllyBattler, currentEnemyBattler)));

                        //check if enemy faints, if not, loop the current battle
                        if (currentEnemyBattler.getHealth() <= 0)
                        {
                            System.out.println("Enemy " + currentEnemyBattler.getName() + " fainted.");
                            bolCurrentBattle = false;
                        }

                        else
                        {
                            bolCurrentBattle = true;
                        }
                    }
                }

            }
            while(bolCurrentBattle);
            
            //if current battle ends, check to see if any team has 0 battlers left, if they do the entire battle will end
            //otherwise the user or computer will choose their next battler and the battle continues
            if (team.get(0).getHealth() <= 0 && team.get(1).getHealth() <= 0 && team.get(2).getHealth() <= 0)
            {
                System.out.println("\nYou lost.");
                bolBattle = false;
                bolWinLose = false;
            }

            else if (enemy.findNode(1).cargo.getHealth() <= 0 && enemy.findNode(2).cargo.getHealth() <= 0 && enemy.findNode(3).cargo.getHealth() <= 0)
            {
                System.out.println("\nYou won.");
                bolBattle = false;
                bolWinLose = true;
            }

            else if (currentAllyBattler.getHealth() <= 0 && currentEnemyBattler.getHealth() > 0)
            {
                System.out.println("\nSelect your next battler.");
                currentAllyBattler = selectBattler(team);
                bolBattle = true;
            }

            else
            {
                intBattler++;
                currentEnemyBattler = selectEnemyBattler(enemy, intBattler);
                bolBattle = true;
            }
        }
        while(bolBattle);

        return bolWinLose;

    }

    /*
     * Author: Stanley Huang
     * Date: June 13, 2023
     * Description: method for starting battles with true potential
     * this method is basically a duplicate of the battle method, difference is that ally battlers are type TruePotential instead of Battler
     * when calculating damage, this battle method uses the overloaded method instead of the original
     */
    public static boolean truePotentialBattle(ArrayList<TruePotential> team, EnemyTeam enemy)
    {
        //declare variables
        TruePotential currentAllyBattler;
        Battler currentEnemyBattler;
        boolean bolBattle = true;
        boolean bolCurrentBattle = true;
        boolean bolTryCatch = true;
        boolean bolWinLose = true;
        int intSelection = 0;
        int intEnemySelection = 0;
        int intBattler = 1;
        Moves tempMove = null;
        Moves enemyTempMove = null;
        
        //indicate that the battle has started and ask the player which battler they would like to send out
        System.out.println("Battle start. Which battler would you like to send out?");

        //player selects their battler and computer selects their battler
        currentAllyBattler = selectTruePotentialBattler(team);
        currentEnemyBattler = selectEnemyBattler(enemy, intBattler);

        //loop until the battle ends
        do
        {
            //loop the current ongoing 1 vs 1 battle until it ends
            do
            {
                //show the user the HP of each battler and ask which move they would like to use
                System.out.println("\nAlly's Health: " + currentAllyBattler.getHealth());
                System.out.println("Enemy's Health: " + currentEnemyBattler.getHealth());
                System.out.println("\nWhich move would you like to use?");
                System.out.println("1: " + currentAllyBattler.getMoveOne().getName());
                System.out.println("2: " + currentAllyBattler.getMoveTwo().getName());
                System.out.println("3: " + currentAllyBattler.getMoveThree().getName());

                //try catch to make sure an integer is entered
                do
                {
                    try
                    {
                        //make sure they choose a number that corresponds to one of the moves
                        intSelection = new Scanner(System.in).nextInt();
                        if (intSelection < 1 || intSelection > 3)
                        {
                            System.out.println("Please choose a valid move.");
                            bolTryCatch = true;
                        }
                        else
                        {
                            bolTryCatch = false;
                        }
                    }
                    catch(Exception e)
                    {
                        System.out.println("Please enter a digit that corresponds to a move.");
                        bolTryCatch = true;
                    }
                }
                while(bolTryCatch);

                //select the move which corresponds with the number that the player chose
                if (intSelection == 1)
                {
                    tempMove = currentAllyBattler.getMoveOne();
                }
                else if (intSelection == 2)
                {
                    tempMove = currentAllyBattler.getMoveTwo();
                }
                else
                {
                    tempMove = currentAllyBattler.getMoveThree();
                }

                //computer randomly selects a move
                intEnemySelection = (int)(3*Math.random());

                //select the corresponding move based on the number generated randomly
                if (intEnemySelection == 0)
                {
                    enemyTempMove = currentEnemyBattler.getMoveOne();
                }
                else if (intEnemySelection == 1)
                {
                    enemyTempMove = currentEnemyBattler.getMoveTwo();
                }
                else
                {
                    enemyTempMove = currentEnemyBattler.getMoveThree();
                }

                //if the ally battler is faster, they get to use their move first
                if (currentAllyBattler.getSpeed() >= currentEnemyBattler.getSpeed())
                {
                    //implement the chosen move using a method
                    implementAllyMove(tempMove, currentAllyBattler, currentEnemyBattler);

                    //calculate damage dealt by ally
                    currentEnemyBattler.setHealth((int)(currentEnemyBattler.getHealth() - calcAllyDamage(tempMove, currentAllyBattler, currentEnemyBattler)));

                    //check if enemy faints, if not, the enemy get to use their move
                    if (currentEnemyBattler.getHealth() <= 0)
                    {
                        System.out.println("Enemy " + currentEnemyBattler.getName() + " fainted.");
                        bolCurrentBattle = false;
                    }
                    else
                    {
                        //implement the chosen move using a method
                        implementEnemyMove(enemyTempMove, currentAllyBattler, currentEnemyBattler);

                        //calculate damage dealt by enemy
                        currentAllyBattler.setHealth((int)(currentAllyBattler.getHealth() - calcEnemyDamage(enemyTempMove, currentAllyBattler, currentEnemyBattler)));

                        //check if ally faints, if not, the current battle loops, otherwise current battle ends
                        if (currentAllyBattler.getHealth() <= 0)
                        {
                            System.out.println("Ally " + currentAllyBattler.getName() + " fainted.");
                            bolCurrentBattle = false;
                        }

                        else
                        {
                            bolCurrentBattle = true;
                        }
                    }
                }
                
                //if enemy battler is faster, they get to use their move first
                else
                {
                    //implement the chosen move using a method
                    implementEnemyMove(enemyTempMove, currentAllyBattler, currentEnemyBattler);

                    //calculate damage dealt by enemy
                    currentAllyBattler.setHealth((int)(currentAllyBattler.getHealth() - calcEnemyDamage(enemyTempMove, currentAllyBattler, currentEnemyBattler)));

                    //check if ally faints, if not, ally gets to use their move
                    if (currentAllyBattler.getHealth() <= 0)
                    {
                        System.out.println("Ally " + currentAllyBattler.getName() + " fainted.");
                        bolCurrentBattle = false;
                    }
                    else
                    {
                        //implement the chosen move using a method
                        implementAllyMove(tempMove, currentAllyBattler, currentEnemyBattler);

                        //calculate damadge dealt by ally
                        currentEnemyBattler.setHealth((int)(currentEnemyBattler.getHealth() - calcAllyDamage(tempMove, currentAllyBattler, currentEnemyBattler)));
                        
                        //check if enemy faints, if not, the current battle loops, otherwise current battle ends
                        if (currentEnemyBattler.getHealth() <= 0)
                        {
                            System.out.println("Enemy " + currentEnemyBattler.getName() + " fainted.");
                            bolCurrentBattle = false;
                        }

                        else
                        {
                            bolCurrentBattle = true;
                        }
                    }
                }

            }
            while(bolCurrentBattle);
            
            //check to see if either team has no more available battlers to determine winner of battle and end the loop
            //if both teams still have battlers, they will be prompted to select their next battler, and the battle loops
            if (team.get(0).getHealth() <= 0 && team.get(1).getHealth() <= 0 && team.get(2).getHealth() <= 0)
            {
                System.out.println("\nYou lost.");
                bolBattle = false;
                bolWinLose = false;
            }

            else if (enemy.findNode(1).cargo.getHealth() <= 0 && enemy.findNode(2).cargo.getHealth() <= 0 && enemy.findNode(3).cargo.getHealth() <= 0)
            {
                System.out.println("\nYou won.");
                bolBattle = false;
                bolWinLose = true;
            }

            else if (currentAllyBattler.getHealth() <= 0 && currentEnemyBattler.getHealth() > 0)
            {
                System.out.println("\nSelect your next battler.");
                currentAllyBattler = selectTruePotentialBattler(team);
                bolBattle = true;
            }

            else
            {
                intBattler++;
                currentEnemyBattler = selectEnemyBattler(enemy, intBattler);
                bolBattle = true;
            }
        }
        while(bolBattle);

        return bolWinLose;

    }

    /*
     * Author: Stanley Huang
     * Date: June 14, 2023
     * Description: method to implement what a move does during a battle
     */
    public static void implementAllyMove(Moves move, Battler ally, Battler enemy)
    {
        System.out.println("\nAlly " + ally.getName() + " used: " + move.getName());
        
        //the entire block of code is for implementing the effects of the ally's move
        ally.setAttack(ally.getAttack() + raiseAttack(move));
        if (ally.getAttack() > (ally.getBaseAttack() + 50))
        {
            ally.setAttack(ally.getBaseAttack() + 50);
            System.out.println("Could not raise attack any further.");
        }
        else
        {
            System.out.println("Ally attack was raised by: " + move.getRaiseAttack());
        }

        ally.setDefense(ally.getDefense() + raiseDefense(move));

        if (ally.getDefense() > (ally.getBaseDefense() + 50))
        {
            ally.setDefense(ally.getBaseDefense() + 50);
            System.out.println("Could not raise defense any further.");
        }
        else
        {
            System.out.println("Ally defense was raised by: " + move.getRaiseDefense());
        }

        ally.setSpeed(ally.getSpeed() + raiseSpeed(move));

        if (ally.getSpeed() > (ally.getBaseSpeed() + 50))
        {
            ally.setSpeed(ally.getBaseSpeed() + 50);
            System.out.println("Could not raise speed any further.");
        }
        else
        {
            System.out.println("Ally speed was raised by: " + move.getRaiseSpeed());
        }

        enemy.setAttack(enemy.getAttack() - lowerAttack(move));
        if (enemy.getAttack() < (enemy.getBaseAttack() - 50))
        {
            enemy.setAttack(enemy.getBaseAttack() - 50);
            System.out.println("Could not lower attack any further.");
        }
        else
        {
            System.out.println("Enemy attack was lowered by: " + move.getLowerAttack());
        }

        enemy.setDefense(enemy.getDefense() - lowerDefense(move));
        if (enemy.getDefense() < (enemy.getBaseDefense() - 50))
        {
            enemy.setDefense(enemy.getBaseDefense() - 50);
            System.out.println("Could not lower defense any further.");
        }
        else
        {
            System.out.println("Enemy defense was lowered by: " + move.getLowerDefense());
        }

        enemy.setSpeed(enemy.getSpeed() - lowerSpeed(move));
        if (enemy.getSpeed() < (enemy.getBaseSpeed() - 50))
        {
            enemy.setSpeed(enemy.getBaseSpeed() - 50);
            System.out.println("Could not lower speed any further.");
        }
        else
        {
            System.out.println("Enemy speed was lowered by: " + move.getLowerSpeed());
        }

        ally.setHealth(ally.getHealth() + calcHeal(move));
        if (ally.getHealth() > ally.getBaseHealth())
        {
            ally.setHealth(ally.getBaseHealth());
            System.out.println("Max HP");
        }
        else
        {
            System.out.println("Ally healed for: " + move.getHeal());
        }
                   
    }

    /*
     * Author: Stanley Huang
     * Date: June 14, 2023
     * Description: method to implement the enemy's moves
     */
    public static void implementEnemyMove(Moves move, Battler ally, Battler enemy)
    {
        System.out.println("\nEnemy " + enemy.getName() + " used: " + move.getName());

        //the entire block of code implements the effects of the enemy's move
        enemy.setAttack(enemy.getAttack() + raiseAttack(move));
        if (enemy.getAttack() > (enemy.getBaseAttack() + 50))
        {
            enemy.setAttack(enemy.getBaseAttack() + 50);
            System.out.println("Could not raise attack any further.");
        }
        else
        {
            System.out.println("Enemy attack was raised by: " + move.getRaiseAttack());
        }

        enemy.setDefense(enemy.getDefense() + raiseDefense(move));
        if (enemy.getDefense() > (enemy.getBaseDefense() + 50))
        {
            enemy.setDefense(enemy.getBaseDefense() + 50);
            System.out.println("Could not raise defense any further.");
        }
        else
        {
            System.out.println("Enemy defense was raised by: " + move.getRaiseDefense());
        }

        enemy.setSpeed(enemy.getSpeed() + raiseSpeed(move));
        if (enemy.getSpeed() > (enemy.getBaseSpeed() + 50))
        {
            enemy.setSpeed(enemy.getBaseSpeed() + 50);
            System.out.println("Could not raise speed any further.");
        }
        else
        {
            System.out.println("Enemy speed was raised by: " + move.getRaiseSpeed());
        }

        ally.setAttack(ally.getAttack() - lowerAttack(move));
        if (ally.getAttack() < (ally.getBaseAttack() - 50))
        {
            ally.setAttack(ally.getBaseAttack() - 50);
            System.out.println("Could not lower attack any further.");
        }
        else
        {
            System.out.println("Ally attack was lowered by: " + move.getLowerAttack());
        }

        ally.setDefense(ally.getDefense() - lowerDefense(move));
        if (ally.getDefense() < (ally.getBaseDefense() - 50))
        {
            ally.setDefense(ally.getBaseDefense() - 50);
            System.out.println("Could not lower defense any further.");
        }
        else
        {
            System.out.println("Ally defense was lowered by: " + move.getLowerDefense());
        }

        ally.setSpeed(ally.getSpeed() - lowerSpeed(move));
        if (ally.getSpeed() < (ally.getBaseSpeed() - 50))
        {
            ally.setSpeed(ally.getBaseSpeed() - 50);
            System.out.println("Could not lower speed any further.");
        }
        else
        {
            System.out.println("Ally speed was lowered by: " + move.getLowerSpeed());
        }

        enemy.setHealth(enemy.getHealth() + calcHeal(move));
        if (enemy.getHealth() > enemy.getBaseHealth())
        {
            enemy.setHealth(enemy.getBaseHealth());
            System.out.println("Max HP");
        }
        else
        {
            System.out.println("Enemy healed for: " + move.getHeal());
        }
    }

    /*
     * Author: Stanley Huang
     * Date: June 12, 2023
     * Description: method to select a battler
     */
    public static Battler selectBattler(ArrayList<Battler> team)
    {
        //declare variables
        int intSelection;
        boolean bolTryCatch = true;
        Battler currentAllyBattler = null;

        //display the battlers in the player's team
        for(int i = 0; i < team.size(); i++)
        {
            System.out.println((i + 1) + ": " + team.get(i).getName());
        }

        //try catch to make sure they enter an integer
        //this will select the battler that they want
        do
        {
            try
            {
                //make sure they choose a number that corresponds to one of the team members
                intSelection = new Scanner(System.in).nextInt();
                if (intSelection < 1 || intSelection > 3)
                {
                    System.out.println("Please select a number between 1 and 3.");
                    bolTryCatch = true;
                }
                else
                {
                    //get the battler that they select from the array list
                    currentAllyBattler = team.get((intSelection - 1));

                    //check to make sure that the battler hasn't fainted
                    if (currentAllyBattler.getHealth() <= 0)
                    {
                        //if battler has fainted, code will loop until they choose a battler that is alive
                        System.out.println("Battler has fainted. Please choose another battler.");
                        bolTryCatch = true;
                    }
                    else
                    {
                        System.out.println("You have selected battler: " + currentAllyBattler.getName());
                        bolTryCatch = false;
                    }
                }
            
            }
            catch(Exception e)
            {
                System.out.println("Error. Please enter a digit.");
                bolTryCatch = true;
            }
        }
        while(bolTryCatch);

        return currentAllyBattler;
    }

    /*
     * Author: Stanley Huang
     * Date: June 13, 2023
     * Description: method to select a battler
     */
    public static TruePotential selectTruePotentialBattler(ArrayList<TruePotential> team)
    {
        //declare variables
        int intSelection;
        boolean bolTryCatch = true;
        TruePotential currentAllyBattler = null;

        for(int i = 0; i < team.size(); i++)
        {
            System.out.println((i + 1) + ": " + team.get(i).getName());
        }

        do
        {
            try
            {
                //make sure they choose a number that corresponds to one of the team members
                intSelection = new Scanner(System.in).nextInt();
                if (intSelection < 1 || intSelection > 3)
                {
                    System.out.println("Please select a number between 1 and 3.");
                    bolTryCatch = true;
                }
                else
                {
                    //get the battler that they select from the array list
                    currentAllyBattler = team.get((intSelection - 1));

                    //check to make sure that the battler hasn't fainted
                    if (currentAllyBattler.getHealth() <= 0)
                    {
                        //if battler has fainted, code will loop until they choose a battler that is alive
                        System.out.println("Battler has fainted. Please choose another battler.");
                        bolTryCatch = true;
                    }
                    else
                    {
                        System.out.println("You have selected battler: " + currentAllyBattler.getName());
                        bolTryCatch = false;
                    }
                }
            
            }
            catch(Exception e)
            {
                System.out.println("Error. Please enter a digit.");
                bolTryCatch = true;
            }
        }
        while(bolTryCatch);

        return currentAllyBattler;
    }

    /*
     * Author: Stanley Huang
     * Date: June 12, 2023
     * Description: method to choose the enemy's battler
     */
    public static Battler selectEnemyBattler(EnemyTeam enemy, int selection)
    {
        //declare variables
        Battler currentEnemyBattler;

        //select the enemy's battler
        currentEnemyBattler = enemy.findNode(selection).cargo;
        System.out.println("\nEnemy sent out battler: " + currentEnemyBattler.getName());

        return currentEnemyBattler;
    }

    /*
     * Author: Stanley Huang
     * Date: June 12, 2023
     * Description: method to calculate ally damage
     */
    public static float calcAllyDamage(Moves move, Battler ally, Battler enemy)
    {
        //declare variables
        float fltDamage = 0;

        //damage calculation
        fltDamage = (float)(move.getDamage() * (float)((float)ally.getAttack()/(float)enemy.getDefense()));

        //print out damage dealt
        System.out.println("Damage dealt by ally: " + (int)fltDamage);

        return fltDamage;
    }

    /*
     * Author: Stanley Huang
     * Date: June 13, 2023
     * Description: overloaded method to calculate ally damage when player has unlocked true potential
     */
    public static float calcAllyDamage(Moves move, TruePotential ally, Battler enemy)
    {
        //declare variables
        float fltDamage = 0;

        //damage calculation
        fltDamage = (float)(move.getDamage() * (float)((float)ally.getAttack()/(float)(enemy.getDefense() - ally.getShred())));

        //print out damage dealt
        System.out.println("Damage dealt by ally: " + (int)fltDamage);

        return fltDamage;
    }

    /*
     * Author: Stanley Huang
     * Date: June 12, 2023
     * Description: method to calculate enemy damage
     */
    public static float calcEnemyDamage(Moves move, Battler ally, Battler enemy)
    {
        //declare variables
        float fltDamage;

        //damage calculation
        fltDamage = (float)(move.getDamage() * (float)((float)enemy.getAttack()/(float)ally.getDefense()));

        //print out damage dealt
        System.out.println("Damage dealt by enemy: " + (int)fltDamage);

        return fltDamage;
    }

    /*
     * Author: Stanley Huang
     * Date: June 13, 2023
     * Description: overloaded method to calculate enemy damage when player has unlocked true potential
     */
    public static float calcEnemyDamage(Moves move, TruePotential ally, Battler enemy)
    {
        //declare variables
        float fltDamage;
        int intDodge;

        //generate a random number which will be used to check if a battler dodges an attack (1%-100% chance)
        intDodge = (int)(100 * Math.random());

        //check if the battler successfully dodges an attack
        if ((intDodge + 1) <= ally.getDodge())
        {
            fltDamage = 0;
        }
        else
        {
            //damage calculation if attack is not dodged
            fltDamage = (float)(move.getDamage() * (float)((float)enemy.getAttack()/(float)ally.getDefense()));
        }
        
        //print out damage dealt
        System.out.println("Damage dealt by enemy: " + (int)fltDamage);

        return fltDamage;
    }

    /*
     * Author: Stanley Huang
     * Date: June 12, 2023
     * Description: method to calculate healing
     */
    public static int calcHeal(Moves move)
    {
        //declare variables
        int intHeal;

        //get the healing amount
        intHeal = move.getHeal();

        return intHeal;
    }

    /*
     * Author: Stanley Huang
     * Date: June 12, 2023
     * Description: method to calculate attack raises
     */
    public static int raiseAttack(Moves move)
    {
        //declare variables
        int intAttack;

        //get the raise attack amount
        intAttack = move.getRaiseAttack();

        return intAttack;
    }

    /*
     * Author: Stanley Huang
     * Date: June 12, 2023
     * Description: method to calculate attack drops
     */
    public static int lowerAttack(Moves move)
    {
        //declare variables
        int intAttack;

        //get the lower attack amount
        intAttack = move.getLowerAttack();

        return intAttack;
    }

    /*
     * Author: Stanley Huang
     * Date: June 12, 2023
     * Description: method to calculate defense raises
     */
    public static int raiseDefense(Moves move)
    {
        //declare variables
        int intDefense;

        //get the raise defense amount
        intDefense = move.getRaiseDefense();

        return intDefense;
    }

    /*
     * Author: Stanley Huang
     * Date: June 12, 2023
     * Description: method to calculate defense drops
     */
    public static int lowerDefense(Moves move)
    {
        //declare variables
        int intDefense;

        //get the lower defense amount
        intDefense = move.getLowerDefense();

        return intDefense;
    }

    /*
     * Author: Stanley Huang
     * Date: June 12, 2023
     * Description: method to calculate speed raises
     */
    public static int raiseSpeed(Moves move)
    {
        //declare variables
        int intSpeed;

        //get the raise speed amount
        intSpeed = move.getRaiseSpeed();

        return intSpeed;
    }

    /*
     * Author: Stanley Huang
     * Date: June 12, 2023
     * Description: method to calculate speed drops
     */
    public static int lowerSpeed(Moves move)
    {
        //declare variables
        int intSpeed;

        //get the lower speed amount
        intSpeed = move.getLowerSpeed();

        return intSpeed;
    }

    /*
     * Author: Stanley Huang
     * Date: June 13, 2023
     * Description: This method will save the player's winstreak to a file
     */
    public static void writeToFile(Player p)
    {
        //declare variables
        String strName;
        int intWinStreak;

        //initialize variables which will get written to a file
        strName = p.getName();
        intWinStreak = p.getWinStreak();
        //the try and catches to catch errors when trying to write to a file
        try
        {
            FileWriter write = new FileWriter("HallOfFame.txt");
            //write the player to a file and close the file
            write.write("Name: " + strName + "\nWin streak: " + intWinStreak);
            write.close();
            System.out.println("Successfully wrote to file.");
        }
        //when an error occurs this will catch it
        catch (FileNotFoundException e)
        {
            System.out.println("Could not open file for writing.");
        }
        catch (IOException e)
        {
            System.out.println("Cannot write to file");
        }
    }

    /*
     * Author: Stanley Huang
     * Date: June 13, 2023
     * Description: This method will read files that were written to from writeToFile
     */
    public static void readFile()
    {
        //using a scanner to read a file line by line so this variable will hold the information of one line and print it out
        String strInfo;
        try
        {
            //link the file that will be read and create a scanner to read the file
            File read = new File("HallOfFame.txt");
            Scanner sReader = new Scanner(read);
            //loop and print until all of the lines in the file have been read
            while (sReader.hasNextLine())
            {
                strInfo = sReader.nextLine();
                System.out.println(strInfo);
            }
            //close the file
            sReader.close();
        }
        //to catch any errors
        catch(FileNotFoundException e)
        {
            System.out.println("An error has occurred.");
        }
    }
}

