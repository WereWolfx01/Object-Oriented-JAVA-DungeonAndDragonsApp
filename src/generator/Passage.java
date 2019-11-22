/**Aeman Abdulmuhssen
student id #1003984
email: aabdulmu@uoguelph.ca*/
package generator;

import dnd.models.Monster;
import dnd.models.Treasure;
import java.util.ArrayList;
import java.util.HashMap;

/*
A passage begins at a door and ends at a door.  It may have many other doors along
the way

You will need to keep track of which door is the "beginning" of the passage
so that you know how to
*/

public class Passage extends Space {
	//these instance variables are suggestions only
	//you can change them if you wish.

	//private ArrayList<PassageSection> thePassage;
	//private HashMap<Door,PassageSection> doorMap;
	private ArrayList<Door> doorList;
	private ArrayList<PassageSection> passageSectionList;
	private PassageSection newSection;
	private StringBuilder description;
	private String name;

	/*
	 *
	 */
	public Passage() {
		doorList = new ArrayList<Door>();
		passageSectionList = new ArrayList<PassageSection>();
		description = new StringBuilder();
		genPassage();
	}

	public Passage(String someName) {
		doorList = new ArrayList<Door>();
		passageSectionList = new ArrayList<PassageSection>();
		description = new StringBuilder();
		genPassage();
		name = someName;
	}

	/******************************
	 Required Methods for that we will test during grading
	*******************************/
	/* note:  Some of these methods would normally be protected or private, but because we
	don't want to dictate how you set up your packages we need them to be public
	for the purposes of running an automated test suite (junit) on your code.  */

	/*
	 *
	 */
public ArrayList<Door> getDoors() {
//gets all of the doors in the entire passage
return doorList;
}

/*
 *
 */
public Door getDoor(int i) {
	//returns the door in section 'i'. If there is no door, returns null
	if (passageSectionList.get(i) != null) {
		Door toReturn;
		toReturn = (passageSectionList.get(i)).getDoor();
		return toReturn;
	} else {
		return null;
	}
}

/*
 *
 */
public void addMonster(Monster theMonster, int i) {
	// adds a monster to section 'i' of the passage
	PassageSection psgSection;
	psgSection = passageSectionList.get(i);
	psgSection.setMonster(theMonster);
	passageSectionList.set(i, psgSection);
}

/*
 *
 */
public Monster getMonster(int i) {
	//returns Monster door in section 'i'. If there is no Monster, returns null
	if (passageSectionList.get(i) != null) {
		Monster toReturn;
		toReturn = (passageSectionList.get(i)).getMonster();
		return toReturn;
	} else {
		return null;
	}
}


/*
 *
 */
public void addPassageSection(PassageSection toAdd) {
	//adds the passage section to the passageway
	passageSectionList.add(toAdd);
}


/*
 *
 */
@Override
public void setDoor(Door newDoor) {
	//should add a door connection to the current Passage Section
	doorList.add(newDoor);
}

/*
 *
 */
@Override
public String getDescription() {

return description.toString();
}
/***********
You can write your own methods too, you aren't limited to the required ones
*************/

/*
 *
 */
public ArrayList<PassageSection> getSections() {
	return passageSectionList;
}

/*
 *
 */
private void genPassage() {
	String section;
	description.append("**Passage**\n");
	newSection = new PassageSection();
	passageSectionList.add(newSection);
	section = newSection.getDescription();
	description.append(section);
	while (section.contains("10 ft")) {
		newSection = new PassageSection();
		addPassageSection(newSection);
		section = newSection.getDescription();
		description.append(section);
	}

	if (section.contains("chamber")) {
		Door chamberDoor = new Door();
		if (section.contains("archway")) {
			chamberDoor.setArchway(true);
		}
		chamberDoor.setOneSpace(this);
	}


}

public String getName(){
	return name;
}

}
