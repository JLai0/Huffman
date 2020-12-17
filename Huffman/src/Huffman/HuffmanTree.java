package Huffman;


import java.util.*;
import java.io.PrintStream;

/*
 *  Jonathan Lai
 *  Ms. Maloney
 *  Data Structures
 *  Project 7
 *  HuffmanTree is a means to construct binary trees of Huffman code in order to encode
 *  and decode them.
 *
 */

public class HuffmanTree {

    private HuffmanNode overallRoot; //Main tree node located at the very beginning of the binary tree

    //Pre: Takes in an int array of frequencies where each index value corresponds to a different character
    //Post: Set overallRoot to the root node of the HuffmanTree consisting of all character nodes starting with
    // smallest frequencies towards the bottom and largest first. This HuffmanTree includes an eof value at the very end.
    HuffmanTree(int[] count) {

        Queue<HuffmanNode> nodeQueue = new PriorityQueue<>(); //priority queue sorts HuffmanNodes in order of smallest frequencies first and lowest last

        for (int i = 0; i < count.length; i++) {

            if (count[i] > 0) {

                nodeQueue.add(new HuffmanNode(count[i], i)); //HuffmanNode (frequency,character)

            }

        }

        nodeQueue.add(new HuffmanNode(1, count.length)); //add eof value tp end

        while (nodeQueue.size() > 1) { //loop through until one node is connecting all of the character nodes

            HuffmanNode node1 = nodeQueue.remove();
            HuffmanNode node2 = nodeQueue.remove();
            nodeQueue.add(new HuffmanNode(node1.getFrequency() + node2.getFrequency(), node1, node2));

        }

        overallRoot = nodeQueue.remove(); //remove the last node from nodeQueue making it the main node

    }

    //Pre: Takes in scanner linked to a code file
    //Post: Creates a HuffmanTree using code file information
    HuffmanTree(Scanner input) {

        while (input.hasNext()) {

            int character = Integer.parseInt(input.nextLine());
            String position = input.nextLine();
            overallRoot = createTree(overallRoot, position, character);

        }

    }

    //Private pair method with the Huffman(Scanner input) method
    private HuffmanNode createTree(HuffmanNode node, String position, int character) {

        if (node == null) {

            node = new HuffmanNode();

        }

        if (position.length() == 1) {

            if (position.charAt(0) == '0') {

                node.setLeft(new HuffmanNode(0, character));

            } else {

                node.setRight(new HuffmanNode(0, character));

            }

        } else {

            if (position.charAt(0) == '0') {

                node.setLeft(createTree(node.getLeft(), position.substring(1), character));

            } else {

                node.setRight(createTree(node.getRight(), position.substring(1), character));

            }


        }

        return node;

    }

    //Pre: Takes in legal encoding characters and reads bits from the input stream
    //Post: Stops at eof value and upon and will translate entire BitInputStream into characters
    public void decode(BitInputStream input, PrintStream output, int eof) {

        HuffmanNode current = overallRoot;

        while (current.getCharacter() < eof) {

            if (current.isLeaf()) {

                output.write(current.getCharacter());
                current = overallRoot;


            } else {

                if (input.readBit() == 0) {

                    current = current.getLeft();

                } else {

                    current = current.getRight();

                }

            }

        }

    }


    //Pre: Takes in a PrintStream that is linked to an external file
    //Post: Writes HuffmanTree to a text file
    public void write(PrintStream output) {

        writeQueue(overallRoot, "", output);

    }

    //Private method paired with write(PrintStream output)
    private void writeQueue(HuffmanNode node, String position, PrintStream output) {

        if (node.isLeaf()) {

            output.println(node.getCharacter());
            output.println(position);

        } else {

            writeQueue(node.getLeft(), position + "0", output);
            writeQueue(node.getRight(), position + "1", output);

        }

    }

}





