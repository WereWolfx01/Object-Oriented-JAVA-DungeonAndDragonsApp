package gui;

import dnd.models.Monster;
import dnd.models.Treasure;
import generator.Chamber;

import java.io.*;
import java.util.ArrayList;

import generator.*;


public class Controller {
    private GuiDemo myGui;
    private LevelGenerator level;

    public Controller(GuiDemo theGui) {
        myGui = theGui;
        level = new LevelGenerator();
    }


    private LevelGenerator getLevel() {
        return level;
    }

    public ArrayList<Chamber> getChambers() {
        ArrayList<Chamber> temp;
        temp = (this.getLevel()).getChambers();
        return temp;
    }

    public ArrayList<Passage> getPassages() {
        ArrayList<Passage> temp;
        temp = (this.getLevel()).getPassages();
        return temp;
    }

    public ArrayList<Door> getDoors() {
        ArrayList<Door> temp;
        temp = (this.getLevel()).getDoors();
        return temp;
    }

    public String getDescription(int index) {
        if (index > 4) {
            myGui.updateDoors(((level.getPassages()).get(index - 5)).getDoors());
//            System.out.println(((level.getPassages()).get(index-5)).getDoors().size());
            return (((level.getPassages()).get(index - 5)).getDescription());
        } else {
            myGui.updateDoors(((level.getChambers()).get(index)).getDoors());
//            System.out.println(((level.getChambers()).get(index)).getDoors().size());
            return (((level.getChambers()).get(index)).getDescription());
        }

    }

    public String getDoorDescription(String name) {
        for (Door d : this.getDoors()) {
            if (d.getName().equals(name)) {
                return d.getDescription();
            }
        }
        return null;
    }

    public Door getDoorFromString(String name) {
        for (Door d : this.getDoors()) {
            if (d.getName().equals(name)) {
                return d;
            }
        }
        return null;
    }

    public ArrayList<Monster> getMonsters(int index) {
        if (index > 4) {
            return (((level.getPassages()).get(index - 5)).getMonsters());
        } else {
            return (((level.getChambers()).get(index)).getMonsters());
        }
    }

    public ArrayList<Treasure> getTreasure(int index) {
        if (index > 4) {
            return (((level.getPassages()).get(index - 5)).getTrasures());
        } else {
            return (((level.getChambers()).get(index)).getTreasures());
        }
    }

    public void addToDescription(String toAdd, int index) {
        if (index > 4) {
            ((level.getPassages()).get(index - 5)).addToDescription(toAdd);
        } else {
            ((level.getChambers()).get(index)).addToDescription(toAdd);
        }
    }

    public void removeFromDescription(String toRemove, int index) {
        if (index > 4) {
            ((level.getPassages()).get(index - 5)).removeFromDescription(toRemove);
        } else {
            ((level.getChambers()).get(index)).removeFromDescription(toRemove);
        }
    }

    public void serialize(String filename) {
        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(level);

            out.close();
            file.close();

            System.out.println("File saved!");

        } catch (IOException ex) {
            System.out.println("Cannot serialize the object(s)");
        }
    }

    public void deserialize(String filename) {
        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            level = (LevelGenerator) in.readObject();

            in.close();
            file.close();

            System.out.println("File loaded!");
        } catch (IOException ex) {
            System.out.println("Error: bad load file type");

        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }

    }

    public void setGui(GuiDemo toAdd) {
        myGui = toAdd;
    }
}