package generator;
import javax.swing.*;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.WeakHashMap;


public class LevelGenerator {
    private ArrayList<Chamber> chambers;
    private ArrayList<Passage> passages;
    private ArrayList<Door> doors;

    public LevelGenerator(){
        chambers = new ArrayList<Chamber>();
        passages = new ArrayList<Passage>();
        doors = new ArrayList<Door>();

        createLevel();
    }

    private void createLevel(){


        Chamber tempChamber1 = new Chamber("chamber Alpha");
        Door tempDoor1 = new Door("door Alpha");
        tempDoor1.setOneSpace(tempChamber1);
        Passage tempPassage1 = new Passage("passage Alpha");
        tempDoor1.setOneSpace(tempPassage1);
        Door tempDoor2 = new Door("door Bravo");
        tempDoor2.setOneSpace(tempPassage1);

        Chamber tempChamber2 = new Chamber("chamber Beta");
        tempDoor2.setOneSpace(tempChamber2);
        Door tempDoor3 = new Door("door Charlie");
        tempDoor3.setOneSpace(tempChamber2);
        Passage tempPassage2 = new Passage("passage Beta");
        tempDoor3.setOneSpace(tempPassage2);

        Door tempDoor4 = new Door("door Delta");
        tempDoor4.setOneSpace(tempPassage2);

        Chamber tempChamber3 = new Chamber("chamber Charlie");
        tempDoor4.setOneSpace(tempChamber3);
        Door tempDoor5 = new Door("door Echo");
        tempDoor5.setOneSpace(tempChamber3);
        Passage tempPassage3 = new Passage("passage Charlie");
        tempDoor5.setOneSpace(tempPassage3);

        Door tempDoor6 = new Door("door Foxtrot");
        tempDoor6.setOneSpace(tempPassage3);

        Chamber tempChamber4 = new Chamber("chamber Delta");
        tempDoor6.setOneSpace(tempChamber4);
        Door tempDoor7 = new Door("door Golf");
        tempDoor7.setOneSpace(tempChamber4);
        Passage tempPassage4 = new Passage("passage Delta");
        tempDoor7.setOneSpace(tempPassage4);

        Door tempDoor8 = new Door("door Hotel");
        tempDoor8.setOneSpace(tempPassage4);

        Chamber tempChamber5 = new Chamber("chamber Echo");
        tempDoor8.setOneSpace(tempChamber5);

        tempDoor1.genConnectTo();
        tempDoor2.genConnectTo();
        tempDoor3.genConnectTo();
        tempDoor4.genConnectTo();
        tempDoor5.genConnectTo();
        tempDoor6.genConnectTo();
        tempDoor7.genConnectTo();
        tempDoor8.genConnectTo();

        chambers.add(tempChamber1);
        chambers.add(tempChamber2);
        chambers.add(tempChamber3);
        chambers.add(tempChamber4);
        chambers.add(tempChamber5);

        passages.add(tempPassage1);
        passages.add(tempPassage2);
        passages.add(tempPassage3);
        passages.add(tempPassage4);

        doors.add(tempDoor1);
        doors.add(tempDoor2);
        doors.add(tempDoor3);
        doors.add(tempDoor4);
        doors.add(tempDoor5);
        doors.add(tempDoor6);
        doors.add(tempDoor7);
        doors.add(tempDoor8);


    }

    public ArrayList<Chamber> getChambers(){
            return chambers;
    }

    public ArrayList<Passage> getPassages(){
        return passages;
    }

    public ArrayList<Door> getDoors(){
        return doors;
    }

}


