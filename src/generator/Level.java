/**Aeman Abdulmuhssen
student id #1003984
email: aabdulmu@uoguelph.ca*/

package generator;
import dnd.die.D20;
import java.lang.StringBuilder;

public class Level {
  private StringBuilder result = new StringBuilder();
  private D20 die;
  private int roll;
  private Chamber myChamber;

  /*
   *
   */
public Level() {
  setDie();
  setResult(die.roll());
}

/*
 *
 */
public String getResult() {
  return result.toString();
}

/*
 *
 */
public int getDie() {
  return roll;
}

/*
 *
 */
public void setDie() {
  die = new D20();
}

/*
 *
 */
public void setResult(int choice) {
  roll = choice;
  switch (choice) {
    case 1: case 2:
    result.append("passage goes straight for 10 ft\n");
    break;

    case 3: case 4: case 5:
    result.append("passage ends in Door to a Chamber\n");
    break;

    case 6: case 7:
    result.append("archway (door) to right (main passage continues straight for 10 ft)\n");
    break;

    case 8: case 9:
    result.append("archway (door) to left (main passage continues straight for 10 ft)\n");
    break;

    case 10: case 11:
    result.append("passage turns to left and continues for 10 ft\n");
    break;

    case 12: case 13:
    result.append("passage turns to right and continues for 10 ft\n");
    break;

    case 14: case 15: case 16:
    result.append("passage ends in archway (door) to chamber\n");
    break;

    case 17:
    result.append("Stairs, (passage continues straight for 10 ft)\n");
    break;

    case 18: case 19:
    result.append("Archway\n");
    break;

    case 20:
    result.append("Wandering Monster (passage continues straight for 10 ft)\n");
    break;

    default:
    break;
  }

}
}
