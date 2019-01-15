package pkg.ui.fileui;
import pkg.unclassified.*;


public class FileAccordion extends BetterAccordion {

    private static FileAccordion fileAccordion;

    public AllFileTitledWithList allFileTitledWithList=AllFileTitledWithList.getInstance();
    public BusinessHistoryTitledWithList businessHistoryTitledWithList=BusinessHistoryTitledWithList.getInstance();
    public BusinessConditionsTitledWithList businessConditionsTitledWithList=BusinessConditionsTitledWithList.getInstance();
    public SalesDetailsTitledWithList salesDetailsTitledWithList=SalesDetailsTitledWithList.getInstance();



    private FileAccordion(){
        this.getPanes().addAll(allFileTitledWithList,businessHistoryTitledWithList,businessConditionsTitledWithList,salesDetailsTitledWithList);
        this.setExpandedPane(allFileTitledWithList);





    }

    public static FileAccordion getInstance(){
        if (fileAccordion ==null){
            fileAccordion =new FileAccordion();
        }
        return fileAccordion;
    }



}
