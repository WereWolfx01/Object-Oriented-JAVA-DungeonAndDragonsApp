/**Aeman Abdulmuhssen
student id #1003984
email: aabdulmu@uoguelph.ca*/
package generator;

import dnd.models.ChamberContents;
import dnd.models.ChamberShape;
import dnd.models.DnDElement;
import dnd.models.Monster;
import dnd.models.Treasure;
import dnd.models.RegularChamberShape;
import java.util.ArrayList;
import java.lang.StringBuilder;
import dnd.models.Exit;
import dnd.die.*;

public class Chamber extends Space implements java.io.Serializable {

	private ChamberContents myContents;
	private ChamberShape mySize;
	private Monster userMonster;
	private Treasure userTreasure;
	private ArrayList<Door> myDoors;
	private ArrayList<Monster> myMonsters;
	private ArrayList<Treasure> myTreasures;
	private ArrayList<Exit> myExits;
	private StringBuilder details = new StringBuilder();
	private String name;





	/******************************
	 Required Methods for that we will test during grading
	*******************************/
	/* note:  Some of these methods would normally be protected or private, but because we
	don't want to dictate how you set up your packages we need them to be public
	for the purposes of running an automated test suite (junit) on your code.  */

/*
 *
 */
	public Chamber() {
		myDoors = new ArrayList<Door>();
		myExits = new ArrayList<Exit>();
		myMonsters = new ArrayList<Monster>();
		myTreasures = new ArrayList<Treasure>();
		myContents = new ChamberContents();
		myContents.chooseContents(8);
		mySize = ChamberShape.selectChamberShape(7);
		mySize.selectChamberShape(1);
		setExits("random");
		setDoors();
		genChamber();
	}

	public Chamber(String someName) {
		D20 roll = new D20();

		myDoors = new ArrayList<Door>();
		myExits = new ArrayList<Exit>();
		myMonsters = new ArrayList<Monster>();
		myTreasures = new ArrayList<Treasure>();
		myContents = new ChamberContents();
		myContents.chooseContents(roll.roll());

		mySize = ChamberShape.selectChamberShape(roll.roll());
//		mySize.selectChamberShape(1);
		setExits("random");
		setDoors();
		genChamber();
		name = someName;
	}

/*
 *
 */
public Chamber(ChamberShape theShape, ChamberContents theContents) {
	mySize = theShape;
	myContents = theContents;
	myMonsters = new ArrayList<Monster>();
	myTreasures = new ArrayList<Treasure>();
	myExits = new ArrayList<Exit>();
	setExits("");
	setDoors();
	genChamber();
}

/*
 *
 */
public void setShape(ChamberShape theShape) {
	mySize = theShape;
	setExits("");
	setDoors();
}

/*
 *
 */
public ArrayList<Door> getDoors() {

return myDoors;
}

/*
 *
 */
public void addMonster(Monster theMonster) {
	myMonsters.add(theMonster);
}

/*
 *
 */
public ArrayList<Monster> getMonsters() {
return myMonsters;
}

/*
 *
 */
public void addTreasure(Treasure theTreasure) {
	myTreasures.add(theTreasure);
}

/*
 *
 */
public ArrayList<Treasure> getTreasures() {

return myTreasures;
}


/*
 *
 */
@Override
public String getDescription() {
	this.setDescription();

return details.toString();
}

private void setDescription(){
	Exit e;
	int i = 1;
	for(Door d: myDoors){

		if (d.getExit() != null) {
			e = d.getExit();
			details.append("Door " + i + ": " + e.getDirection() + ", " + e.getLocation() + "\n");
			i++;
		}

	}
}


/*
 *
 */
@Override
public void setDoor(Door newDoor) {
	//should add a door connection to this room
	myDoors.add(newDoor);

}


/***********
You can write your own methods too, you aren't limited to the required ones
*************/

/*
 *
 */
public void setDoors() {
	myDoors = new ArrayList<Door>();
	for(Exit e: myExits) {
		Door d = new Door(e);
		d.setOneSpace(this);
	}
}



/*
 *
 */
public int getExits() {
	return (mySize.getNumExits());
}

/*
 *
 */
public void setExits(String choice) {
	if (choice == "random") {
		mySize.setNumExits();
	}

	details.append("Number of exits: " + mySize.getNumExits() + "\n");
	//myExits = mySize.getExits();

}

private void genChamber() {
	String description;
	D20 roll = new D20();
	int i = 0;
	int state = 1;

	description = myContents.getDescription();
	details.append("Shape of chamber: ");
	details.append(mySize.getShape());
	details.append('\n');
	details.append("Contents of chamber generated: ");

	while (state != 0) {
		switch (description) {
			case "empty":
				state = 0;
				details.append("Empty \n");
			break;

			case "monster only":
				state = 0;
				userMonster = new Monster();
				userMonster.setType(roll.roll());
				details.append("Monster only \n");
				details.append("Monster: ");
				details.append(userMonster.getDescription());
				details.append('\n');
				myMonsters.add(userMonster);
			break;

			case "monster and treasure":
				state = 0;
				userMonster = new Monster();

				userMonster.setType(roll.roll());
				userTreasure = new Treasure();
				;
				userTreasure.chooseTreasure(roll.roll());
					details.append("Monster and treasure \n");
					details.append("Monster: ");
					details.append(userMonster.getDescription());
					details.append("\nTreasure: ");
					details.append(userTreasure.getDescription());
					details.append('\n');
					myMonsters.add(userMonster);
					myTreasures.add(userTreasure);
			break;

			case "treasure":
				state = 0;
				userTreasure = new Treasure();
				userTreasure.chooseTreasure(roll.roll());
				details.append("Treasure only: \n");
				details.append("Treasure: ");
				details.append(userTreasure.getDescription());
				details.append('\n');
				myTreasures.add(userTreasure);
			break;

			default:
				state = 1;
				myContents.chooseContents(5);
				description = myContents.getDescription();
			break;
		}
	}
}

public String getName(){
	return name;
}

public void addToDescription(String toAdd){
	details.append(toAdd);
}

public void removeFromDescription(String toRemove){
	details = new StringBuilder(details.toString().replaceFirst(toRemove, ""));
}

}
