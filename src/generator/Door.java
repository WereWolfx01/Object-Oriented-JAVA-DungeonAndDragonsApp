/**Aeman Abdulmuhssen
student id #1003984
email: aabdulmu@uoguelph.ca*/

package generator;
import dnd.models.Exit;
import dnd.models.Trap;
import java.util.ArrayList;
import java.util.Random;

public class Door {

	private ArrayList<Space> mySpaces;
	private Exit myExit;
	private Trap myTrap;
	private boolean open;
	private boolean locked;
	private boolean archway;
	private boolean isTrapped;
	private StringBuilder doorDescription;

	/*
	 *
	 */
	public Door() {
		//needs to set defaults
		doorDescription = new StringBuilder();
		mySpaces = new ArrayList<Space>();
		myTrap = null;
		myExit = null;
		setIsTrapped();
		setLocked();
		setOpen();
		setArchway();
	}

	/*
	 *
	 */
	public Door(Exit theExit) {
		//sets up the door based on the Exit from the table
		this();
		myExit = theExit;
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
	public void setTrapped(boolean flag, int...roll) {
		// true == trapped.  Trap must be rolled if no integer is given
		if (flag == true) {
			myTrap = new Trap();
			if (roll.length > 0) {
				myTrap.setDescription(roll[0]);
			}
			doorDescription.append("door is trapped\n");
			doorDescription.append("Trap: " + myTrap.getDescription() + "\n");
		} else {
			myTrap = null;
			isTrapped = false;
			doorDescription.append("door is not trapped\n");
		}
	}

	/*
	 *
	 */
	public void setOpen(boolean flag) {
		//true == open
		open = flag;
		if(archway) {
			open = true;
			doorDescription.append("door is open\n");
			return;
		}
		if(flag = false) {
			doorDescription.append("door is closed\n");
		}
	}

	/*
	 *
	 */
	public void setOpen() {
		boolean flag = new Random().nextInt(2) == 0;
		if (archway) {
			open = true;
			return;
		}
		open = flag;
	}

	/*
	 *
	 */
	public void setArchway(boolean flag) {
		//true == is archway
		archway = flag;
		if (flag == true) {
			setOpen(true);
			setLocked(false);
			setTrapped(false);
			doorDescription.append("Door is an archway\n");
		}
	}

	/*
	 *
	 */
	public void setArchway() {
		boolean flag = new Random().nextInt(10) == 0;
		archway = flag;
		if (archway) {
			setArchway(true);
		}
	}

	/*
	 *
	 */
	public boolean isTrapped() {
		if (myTrap != null) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 *
	 */
	public boolean isOpen() {
		return open;
	}

	/*
	 *
	 */
	public boolean isArchway() {
		return archway;
	}

	/*
	 *
	 */
	public String getTrapDescription() {
		if (myTrap == null) {
			return null;
		} else {
			return myTrap.getDescription();
		}
	}

	/*
	 *
	 */
	public ArrayList<Space> getSpaces() {
		//returns the two spaces that are connected by the door
			return mySpaces;
	}

	/*
	 *
	 */
	public void setSpaces(Space spaceOne, Space spaceTwo) {
		//identifies the two spaces with the door
		// this method should also call the addDoor method from Space
		mySpaces.add(spaceOne);
		mySpaces.add(spaceTwo);
		spaceOne.setDoor(this);
		spaceTwo.setDoor(this);
	}

	/*
	 *
	 */
	public void addSpace(Space toAdd) {
		mySpaces.add(toAdd);
	}

	/*
	 *
	 */
	public void setOneSpace(Space toAdd) {
		mySpaces.add(toAdd);
		toAdd.setDoor(this);
	}

	/*
	 *
	 */
	public String getDescription() {
		return doorDescription.toString();
	}
/***********
You can write your own methods too, you aren't limited to the required ones
*************/

/*
 *
 */
	public void setLocked(boolean flag) {
		locked = flag;
		if (flag) {
			doorDescription.append("door is locked\n");
		} else {
			doorDescription.append("door is not locked\n");
		}
	}

	/*
	 *
	 */
	public void setLocked() {
		boolean flag = new Random().nextInt(6)==0;
		locked = flag;
	}

	/*
	 *
	 */
	public boolean getLocked() {
		return locked;
	}

	/*
	 *
	 */
	public void setIsTrapped() {
		boolean flag = new Random().nextInt(20) == 0;
		isTrapped = flag;
		if (isTrapped) {
			setTrapped(true);
		} else {
			setTrapped(false);
		}
	}

	/*
	 *
	 */
	public Exit getExit() {
		return myExit;
	}


}
