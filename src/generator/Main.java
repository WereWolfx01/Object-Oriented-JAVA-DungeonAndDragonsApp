/**Aeman Abdulmuhssen
student id #1003984
email: aabdulmu@uoguelph.ca*/
package generator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Main {
  public static void main(String[] args) {


    /*
    * previous generation algorithm for a2
    *
    *
    *
    */


    /*ArrayList<Door> doors;
    ArrayList<PassageSection> sections;
    String lastSection;
    int chamberCount = 0;

    Space chamber, passage;
    Passage p;
    Chamber c = null;
    Door myDoor = new Door();
    myDoor.setArchway(true);
    p = new Passage();
    passage = p;
    myDoor.setOneSpace(passage);

    while (chamberCount <= 5) {
      sections = p.getSections();
      lastSection = sections.get(sections.size() - 1).getDescription();
      System.out.println(p.getDescription());
      if (lastSection.contains("Dead")) { //if reached a dead end, generate another passsage, archway connects two passages
        System.out.println("  *Reached a dead end, new archway is added to a passage!*\n");
        myDoor = new Door();
        myDoor.setArchway(true);
        myDoor.setOneSpace(passage);
        p = new Passage();
        passage = p;
        myDoor.setOneSpace(passage);
      } else {
          c = new Chamber();
          chamberCount++;
          System.out.println(c.getDescription());
          chamber = c;
          doors = p.getDoors();
          myDoor = doors.get(doors.size() - 1);
          myDoor.setSpaces(passage, chamber);
          if (chamberCount == 5) { //return after connecting the last chamber #5 to Door
            System.out.println("**5 chambers have been generated**");
            return;
          }
          doors = c.getDoors();
          myDoor = doors.get(doors.size() - 1);
          p = new Passage();
          passage = p;
          myDoor.setOneSpace(passage);

        }
    }
    */

    /*

    A3 generation algorithm (not complete)

    */

    //HashMap<Door, ArrayList<Chamber>> hmp = new HashMap<Door, ArrayList<Chamber>>();
    //ArrayList<Door> list;
    int i;

    Chamber c1 = new Chamber();
    while( c1.getExits()>4 ){
      c1 = new Chamber();
    }

    Chamber c2 = new Chamber();
    while( c2.getExits()>4 ){
      c2 = new Chamber();
    }

    Chamber c3 = new Chamber();
    while( c3.getExits()>4 ){
      c3 = new Chamber();
    }

    Chamber c4 = new Chamber();
    while( c4.getExits()>4 ){
      c4 = new Chamber();
    }

    Chamber c5 = new Chamber();
    while( c5.getExits()>4 ){
      c5 = new Chamber();
    }

    System.out.println( "ch1 exits : "  + c1.getExits() );
    System.out.println( "ch2 exits : "  + c2.getExits() );
    System.out.println( "ch3 exits : "  + c3.getExits() );
    System.out.println( "ch4 exits : "  + c4.getExits() );
    System.out.println( "ch5 exits : "  + c5.getExits() );


    return;
  }
}
