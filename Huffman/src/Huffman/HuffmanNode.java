package Huffman;


/*
 *  Jonathan Lai
 *  Ms. Maloney
 *  Data Structures
 *  Project 7
 *  A node class containing a frequency value, a characte value, and a right/left connection.
 *  Implements the comparable interface in order to modify the function of a priorityQueue
 *  when sorting it.
 *
 */
public class HuffmanNode implements Comparable<HuffmanNode> {

    //Number of times a character appears. 0 = never.
    private int frequency;

    // The assigned int value of a character
    private int character;

    //Assign a 0 for left branch
    private HuffmanNode left;

    //Assign a 1 for right branch
    private HuffmanNode right;

    //Branch constructor w/ frequency
    HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {

        this(frequency, -1);
        this.right = right;
        this.left = left;

    }

    //Leaf constructor
    HuffmanNode(int frequency, int character) {

        this.frequency = frequency;
        this.character = character;

    }

    //Branch constructor
    HuffmanNode() {

        this(0, -1);

    }


    //PriorityQueue method overload to within comparable interface.
    // Returns values of 0, less than 0, or more than 0
    public int compareTo(HuffmanNode node) {

        return frequency - node.frequency;

    }

    public int getFrequency() {

        return frequency;

    }

    public int getCharacter() {

        return character;

    }

    public HuffmanNode getLeft() {

        return left;

    }

    public HuffmanNode getRight() {

        return right;

    }

    public void setLeft(HuffmanNode left) {

        this.left = left;

    }

    public void setRight(HuffmanNode right) {

        this.right = right;

    }

    //Checks if node is a leaf node
    public boolean isLeaf() {

        return this.right == null && this.left == null;

    }

}
