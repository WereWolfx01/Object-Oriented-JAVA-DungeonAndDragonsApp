/**Aeman Abdulmuhssen
student id #1003984
email: aabdulmu@uoguelph.ca*/

package generator;

import dnd.models.Monster;


/* Represents a 10 ft section of passageway */

public class PassageSection {
	private Door myDoor;
	private Monster myMonster;
	private StringBuilder section;
	private Level myLevel;

	/******************************
	 Required Methods for that we will test during grading
	*******************************/
	/* note:  Some of these methods would normally be protected or private, but because we
	don't want to dictate how you set up your packages we need them to be public
	for the purposes of running an automated test suite (junit) on your code.  */


	/*
	 *
	 */
public PassageSection() {
	myLevel = new Level();
	section = new StringBuilder();
	setDescription();
	setPassageSection(section.toString());
	//sets up the 10 foot section with default settings
}

/*
 *
 */
public PassageSection(String description) {
	myLevel = new Level();
	section.append(description + "\n");
	setPassageSection(section.toString());
	//sets up a specific passage based on the values sent in from
	//modified table 1
}

/*
 *
 */
public Door getDoor() {
	//returns the door that is in the passage section, if there is one
	if (myDoor != null) {
		return myDoor;
	} else {
		return null;
	}
}

/*
 *
 */
public void setDoor(Door toAdd) {
	myDoor = toAdd;
}

/*
 *
 */
public Monster getMonster() {
	//returns the monster that is in the passage section, if there is one
	if (myMonster != null) {
		return myMonster;
	} else {
		return null;
	}
}

/*
 *
 */
public void setMonster(Monster toAdd) {
	myMonster = toAdd;
}

/*
 *
 */
public String getDescription() {
	return section.toString();
}

/*
 *
 */
public void setDescription() {
	section.append(myLevel.getResult());
}

/*
 *
 */
public void setPassageSection(String description) {
	if (description.contains("door") && description.contains("archway")) {
		myDoor = new Door();
		myDoor.setArchway(true);
	}

	if (description.contains("Monster")) {
		myMonster = new Monster();
		section.append("Monster: " + myMonster.getDescription());
	}


}

}
