/*
 * Author: Stanley Huang
 * Date: June 6, 2023
 * Description: this class contains arraylists with moves and battlers
 */
//import array lists
import java.util.ArrayList;
public class BattleBase 
{
    ArrayList<Moves> listOfMoves = new ArrayList<Moves>();

    /*
     * Author: Stanley Huang
     * Date: June 6, 2023
     * Description: method with a list of moves
     */
    public static ArrayList<Moves> MovesList()
    {
        ArrayList<Moves> list = new ArrayList<Moves>();
        list.add(new Moves("Swift Strike", 60, 0, 0, 0, 0, 0, 10, 0)); //0
        list.add(new Moves("Gradiation", 90, 0, 0, 0, 0, 0, 0, 0)); //1
        list.add(new Moves("Pressure Point", 50, 0, 0, 0, 0, 10, 0, 0)); //2
        list.add(new Moves("Body Slam", 75, 0, 0, 0, 0, 0, 0, 0)); //3
        list.add(new Moves("Force Claw", 70, 0, 5, 0, 0, 0, 0, 0)); //4
        list.add(new Moves("Life Siphon", 60, 10, 0, 0, 0, 0, 0, 0)); //5
        list.add(new Moves("Fearful", 0, 0, 0, 10, 0, 0, 0, 0)); //6
        list.add(new Moves("Reckless Rampage", 120, 0, 0, 0, 0, 0, 0, 0)); //7,
        list.add(new Moves("Dislocate", 40, 0, 0, 0, 0, 0, 0, 0)); //8
        list.add(new Moves("Deep Driver", 80, 0, 0, 0, 0, 5, 0, 0)); //9
        list.add(new Moves("Tribe Dancer", 50, 0, 10, 0, 0, 0, 10, 0)); //10
        list.add(new Moves("Sticky Claw", 50, 0, 0, 0, 0, 0, 0, 10)); //11
        list.add(new Moves("Great Defender", 0, 0, 0, 0, 20, 0, 0, 0)); //12
        list.add(new Moves("Agile Step", 0, 0, 0, 0, 0, 0, 25, 0)); //13
        list.add(new Moves("Drop Kick", 100, 0, 0, 5, 0, 0, 0, 0)); //14
        list.add(new Moves("Recover", 0, 50, 0, 0, 0, 0, 0, 0)); //15
        list.add(new Moves("Power Up", 0, 0, 15, 0, 0, 0, 0, 0)); //16
        list.add(new Moves("GIGACHAD", 0, 0, 20, 0, 20, 0, 20, 0)); //17
        list.add(new Moves("DISRESPECT", 0, 0, 0, 20, 0, 20, 0, 20)); //18
        return list;
    }

    /*
     * Author: Stanley Huang
     * Date: June 7, 2023
     * Description: method with a list of battlers
     */
    public static ArrayList<Battler> BattlerList(ArrayList<Moves> moveList)
    {
        ArrayList<Battler> list = new ArrayList<Battler>();
        list.add(new Battler("Koreon", 224, 224, 123, 123, 56, 56, 110, 110, moveList.get(0), moveList.get(1), moveList.get(2))); //0
        list.add(new Battler("Morphis", 400, 400, 76, 76, 140, 140, 60, 60, moveList.get(3), moveList.get(12), moveList.get(4))); //1
        list.add(new Battler("Talue", 800, 800, 70, 70, 87, 87, 51, 51, moveList.get(7), moveList.get(15), moveList.get(6))); //2
        list.add(new Battler("Crypton", 300, 300, 75, 75, 75, 75, 75, 75, moveList.get(1), moveList.get(13), moveList.get(16))); //3
        list.add(new Battler("Meraphan", 380, 380, 92, 92, 65, 65, 93, 93, moveList.get(11), moveList.get(9), moveList.get(14))); //4
        list.add(new Battler("Orangus", 200, 200, 75, 75, 100, 100, 100, 100, moveList.get(10), moveList.get(5), moveList.get(8))); //5
        list.add(new Battler("Valius", 360, 360, 100, 100, 70, 70, 82, 82, moveList.get(16), moveList.get(1), moveList.get(15))); //6
        list.add(new Battler("Doomile", 320, 320, 90, 90, 60, 60, 80, 80, moveList.get(5), moveList.get(8), moveList.get(9))); //7
        list.add(new Battler("Corvicune", 260, 260, 110, 110, 60, 60, 120, 120, moveList.get(0), moveList.get(4), moveList.get(15))); //8
        list.add(new Battler("Graze", 440, 440, 70, 70, 100, 100, 51, 51, moveList.get(1), moveList.get(12), moveList.get(15))); //9
        list.add(new Battler("TESTER", 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, moveList.get(17), moveList.get(18), moveList.get(7))); //10
        return list;
    }

    /*
     * Author: Stanley Huang
     * Date: June 13, 2023
     * Description: method with a list of true potential battlers
     */
    public static ArrayList<TruePotential> TruePotentialBattlerList(ArrayList<Moves> moveList)
    {
        ArrayList<TruePotential> list = new ArrayList<TruePotential>();
        list.add(new TruePotential("Koreon", 224, 224, 123, 123, 56, 56, 110, 110, moveList.get(0), moveList.get(1), moveList.get(2), 5, 10)); //0
        list.add(new TruePotential("Morphis", 400, 400, 76, 76, 140, 140, 60, 60, moveList.get(3), moveList.get(12), moveList.get(4), 1, 1)); //1
        list.add(new TruePotential("Talue", 800, 800, 70, 70, 87, 87, 51, 51, moveList.get(7), moveList.get(15), moveList.get(6), 1, 1)); //2
        list.add(new TruePotential("Crypton", 300, 300, 75, 75, 75, 75, 75, 75, moveList.get(1), moveList.get(13), moveList.get(16), 3, 3)); //3
        list.add(new TruePotential("Meraphan", 380, 380, 92, 92, 65, 65, 93, 93, moveList.get(11), moveList.get(9), moveList.get(14), 3, 7)); //4
        list.add(new TruePotential("Orangus", 200, 200, 75, 75, 100, 100, 100, 100, moveList.get(10), moveList.get(5), moveList.get(8), 3, 3)); //5
        list.add(new TruePotential("Valius", 360, 360, 100, 100, 70, 70, 82, 82, moveList.get(16), moveList.get(1), moveList.get(15), 4, 5)); //6
        list.add(new TruePotential("Doomile", 320, 320, 90, 90, 60, 60, 80, 80, moveList.get(5), moveList.get(8), moveList.get(9), 3, 1)); //7
        list.add(new TruePotential("Corvicune", 260, 260, 110, 110, 60, 60, 120, 120, moveList.get(0), moveList.get(4), moveList.get(15), 1, 8)); //8
        list.add(new TruePotential("Graze", 440, 440, 70, 70, 100, 100, 51, 51, moveList.get(1), moveList.get(12), moveList.get(15), 2, 2)); //9
        list.add(new TruePotential("TESTER", 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, moveList.get(17), moveList.get(18), moveList.get(7), 0, 100)); //10
        return list;
    }
}
