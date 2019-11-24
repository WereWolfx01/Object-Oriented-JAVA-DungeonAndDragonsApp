package gui;

import dnd.models.Monster;
import dnd.models.Treasure;
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
            myGui.updateDoors(((level.getPassages()).get(index-5)).getDoors());
//            System.out.println(((level.getPassages()).get(index-5)).getDoors().size());
            return (((level.getPassages()).get(index-5)).getDescription());
        }
        else {
            myGui.updateDoors(((level.getChambers()).get(index)).getDoors());
//            System.out.println(((level.getChambers()).get(index)).getDoors().size());
            return (((level.getChambers()).get(index)).getDescription());
        }

    }

    public String getDoorDescription(String name){
        for(Door d: this.getDoors()){
            if( d.getName().equals(name) ){
                return d.getDescription();
            }
        }
        return null;
    }

    public Door getDoorFromString(String name){
        for(Door d: this.getDoors()){
            if( d.getName().equals(name) ){
                return d;
            }
        }
        return null;
    }

    public ArrayList<Monster> getMonsters(int index){
        if( index > 4 ){
            return (((level.getPassages()).get(index-5)).getMonsters());
        }
        else {
            return (((level.getChambers()).get(index)).getMonsters());
        }
    }

    public ArrayList<Treasure> getTreasure(int index){
        if( index > 4 ){
            return (((level.getPassages()).get(index-5)).getTrasures());
        }
        else {
            return (((level.getChambers()).get(index)).getTreasures());
        }
    }


}
