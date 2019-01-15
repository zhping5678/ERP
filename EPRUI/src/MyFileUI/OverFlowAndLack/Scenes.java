package MyFileUI.OverFlowAndLack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.util.ArrayList;

public class Scenes {
    private static ObservableList<Parent> roots = FXCollections.observableArrayList();
    private static ArrayList<FXMLLoader> loaders=new ArrayList<>();
    private static ArrayList<String> location=new ArrayList<>();
    private static int number=0;
    private static String state="";
    private static int amountSum=0;
    private static double moneySum=0;
    public Parent getParent(){
        if(roots.size()==1){
            location.clear();
            return roots.get(0);
        }
        else{
            if(roots.size()==2){
                location.clear();
            }
            Parent root= roots.get(roots.size()-2);
            roots.remove(roots.size()-1);
            return root;
        }
    }

    public void addParent(Parent root){
        roots.add(root);
    }

    public FXMLLoader getLoader(){
        if(loaders.size()==1){
            return loaders.get(0);
        }
        else{
            FXMLLoader loader=loaders.get(loaders.size()-1);
            loaders.remove(loaders.size()-1);
            return loader;
        }
    }

    public void addLoader(FXMLLoader loader){
        loaders.add(loader);
    }

    public String getLocation(){
        String result=location.get(0);
        for(int i=1;i<location.size();i++){
            result=result+"/"+location.get(i);
        }
        return result;
    }

    public void addLocation(String locate){
        location.add(locate);
    }

    public void removeLocation(){
        if(location.size()!=1){
            location.remove(location.size()-1);
        }
    }

    public void pruductChosen(){
        location.clear();
        Parent root=roots.get(0);
        roots.clear();
        roots.add(root);
        FXMLLoader loader=loaders.get(0);
        loaders.clear();
        loaders.add(loader);
    }

    public int getNumber(){
        return number;
    }

    public void setNumber(){
        number++;
    }

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state=state;
    }

    public void initialNumber(int number){
        this.number=number;
    }

    public void clear(){
        roots.clear();
        loaders.clear();
        location.clear();
        amountSum=0;
        moneySum=0;
    }

    public void updateAmountSum(int amount){
        amountSum+=amount;
    }

    public void updateMoneySum(double money){
        moneySum+=money;
    }
}
