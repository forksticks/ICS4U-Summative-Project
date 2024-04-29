/*
 * Author: Stanley Huang
 * Date: June 6, 2023
 * Description: This class contains all of the constructors and methods neccessary for building a linkedlist and printing it
 */
public class EnemyTeam
{
    //instance variables; head is the variable that holds the first node in the linked list while the length keeps count of how long the linked list is
    Node head;
    int length;

    //default constructor
    public EnemyTeam()
    {
        this.head = null;
        this.length = 0;
    }

    //constructor to populate the head and the length of the linked list
    public EnemyTeam(Node n)
    {
        this.head = n;
        this.length = 0;
    }

    /*
     * Author: Stanley Huang
     * Date: June 6, 2023
     * Description: method to add more nodes to the linked list
     */
    public void addNode(int index, Battler cargo)
    {
        //adding to the first node in the linked list is a special case, since there isn't any other node yet, the node cannot point to the next one
        if (index == 1)
        {
            this.head = new Node(cargo, null);

            this.length++;
        }
        //when adding to the last node, it will not have anything to point at
        else if (index == this.length+1)
        {
            findNode(index-1).next = new Node(cargo, null);

            this.length++;
        }
        //make sure that the node they add is able to connect to existing nodes
        else if(index > this.length + 1)
        {
            System.out.println("Could not add a node at this postion");
        }
        //adding nodes to the linked list
        //also make sure that the previous node points to the one that you add
        else
        {
            findNode(index-1).next = new Node(cargo, findNode(index));
            this.length++;
        }
    }

    /*
     * Author: Stanley Huang
     * Date: June 6, 2023
     * Description: This method will allow us to grab a certain node from the linked list
     */
    public Node findNode(int index)
    {
        //temporary variable, set it to the head so that we can iterate through the linked list
        Node n = this.head;

        //iterating through the linked list
        for (int i = 1; i <= this.length; i++)
        {
            //if the node is found, return it
            if (i == index)
            {
                return n;
            }
            //if the node is not found, set n to the next node to check
            else
            {
                n = n.next;
            }
        }
        //when the linked list doesn't have a specified node number, null is returned
        return null;
    }

    /*
     * Author: Stanley Huang
     * Date: June 6, 2023
     * Description: This method will allow us to remove nodes
     */
    public void removeNode(int index)
    {
        //temporary variable for iteration purposes
        Node n = this.head;

        //if the first node of the list is being removed, then the second node in the list will become the head
        if (index == 1)
        {
            this.head = this.head.next;

            this.length--;
        }
        //to remove the last node
        else if(index == this.length)
        {
            //finds the node that comes before the node you are trying to remove
            n = findNode(index-1);
            //since the node from the line above is the new last node, it will not have another node to link to
            n.next = null;

            this.length--;
        }
        //make sure that the node they are trying to remove actually exists
        else if (index > this.length)
        {
            System.out.println("Node does not exist at this location");
        }
        //for when the node isn't the first one
        else
        {
            //this will get the node that comes before the one that you are trying to remove
            n = findNode(index-1);
            //will take the previous node and make it point towards the node the comes after the one you remove
            n.next = n.next.next;

            this.length--;
        }
    }

    /*
     * Author: Stanley Huang
     * Date: June 6, 2023
     * Description: toString() method to print out the linked list as a String
     */
    public String toString()
    {
        Node temporary = this.head;
        String strOutput = "";
        while (temporary != null)
        {
            strOutput += temporary + "\n\n";
            temporary = temporary.next;
        }
        return strOutput;
    }

    /*
     * Author: Stanley Huang
     * Date: June 6, 2023
     * Description: Getter method to get the first node of the linked list
     */
    public Node getHead()
    {
        return this.head;
    }

    /*
     * Author: Stanley Huang
     * Date: June 6, 2023
     * Description: getter method to see how long the linked list is (mainly to test if remove or add methods are properly keeping
     * track of the linked list length)
     */
    public int getLength()
    {
        return this.length;
    }
}