package gui;

import generator.Chamber;
import hydra.Battle;
import hydra.Hydra;
import java.util.ArrayList;
import generator.*;


public class Controller {
    private GuiDemo myGui;
    private Battle hydraBattle;
    private static int NUM_HYDRAS = 5;
    private LevelGenerator level;

    public Controller(GuiDemo theGui){
        myGui = theGui;
        hydraBattle = new Battle();
        hydraBattle.createBattleLineup(NUM_HYDRAS);
        level = new LevelGenerator();
    }

    private String getNameList(){
        String nameList = new String();
        ArrayList<Hydra> hydras = hydraBattle.getBattleLineup();
        for(Hydra h: hydras){
            nameList = nameList  + h.toString()+ "\n";
        }
        return nameList;
    }

    public void reactToButton(){
        System.out.println("Thanks for clicking!");
    }

    public String getNewDescription(){
        //return "this would normally be a description pulled from the model of the Dungeon level.";
        return getNameList();
    }

    private LevelGenerator getLevel(){
        return level;
    }

    public ArrayList<Chamber> getChambers(){
        ArrayList<Chamber> temp;
        temp = (this.getLevel()).getChambers();
        return temp;
    }

    public ArrayList<Passage> getPassages(){
        ArrayList<Passage> temp;
        temp = (this.getLevel()).getPassages();
        return temp;
    }

    public ArrayList<Door> getDoors(){
        ArrayList<Door> temp;
        temp = (this.getLevel()).getDoors();
        return temp;
    }

    public String getDescription(int index){
        if( index > 4 ){
            return (((level.getPassages()).get(index-5)).getDescription());
        }
        else {
            return (((level.getChambers()).get(index)).getDescription());
        }

    }


}
