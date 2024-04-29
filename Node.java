/*
 * Author: Stanley Huang
 * Date: June 2, 2023
 * Description: Node class to create nodes which will be stored in a linkedlist 
 */
public class Node 
{
    //declare variables
    Battler cargo;
    Node next;

    //default constructor
    public Node()
    {
        this.cargo = null;
        this.next = null;
    }

    //constructor that sends in cargo and the reference
    public Node(Battler b, Node n)
    {
        this.cargo = b;
        this.next = n;
    }

    /*
     * Author: Stanley Huang
     * Date: May 15, 2023
     * Description: toString to convert cargo into a String
     */
    public String toString()
    {
        return this.cargo + "";
    }
}