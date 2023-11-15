/*Morse Code Lab
Programmers: Jason, Kaitylyn, Hari, Hemang
Honors Data Structures and Programming
*/

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; //imports the scanner class

class Main {
  public static void main (String[] args) throws IOException, FileNotFoundException {

    //Clears the console
    System.out.print("\033[H\033[2J");
    System.out.flush();

    //initialize variables/objects
    BinTree morse = new BinTree();
    morse.InsertItem(' ', " ");
    Scanner file = new Scanner(new File("Morse.txt"));
    Scanner otherFile = new Scanner(new File("Message.txt"));

    //set delimeter to spaces
    otherFile.useDelimiter(" ");

    //Uses a while loop to continuously add each letter to the morse tree
    while (file.hasNext()) {
      String fileName = file.nextLine();
      char letter = fileName.charAt(0);
      String code = fileName.substring(4);
      morse.InsertItem(letter, code);
    }
    //morse.InOrder();

    //loop through the entire file
    while (otherFile.hasNext()) {
      String curCode = otherFile.next();
      
      //add a space if there's a slash using if/else loop
      if (curCode.equals("/")){
        System.out.print(" ");
      }
      else if (curCode.equals("*")){ //add a new line if there's an asterisk
        System.out.println("");
      }
      else{ //translate the current letter from morse
        System.out.print(morse.translateCode(curCode));
      }
    }
  }
}

/*Test Cases:

(original message, not printed) .--- .- ... --- -. * .... . -- .- -. --. * .... .- .-. .. * -.- .- .. - .-.. -.-- -. * -.. .- - .- / ... - .-. ..- -.-. - ..- .-. . ... * .--. . .-. .. --- -.. / ... .. -..- *

JASON
HEMANG
HARI
KAITLYN
DATA STRUCTURES
PERIOD SIX

*/
