package pkg.ui.myfileui;
import pkg.unclassified.*;


public class MyFileAccordion extends BetterAccordion {

    private static MyFileAccordion myFileAccordion;

    public DraftTitledWithList draftTitledWithList=DraftTitledWithList.getInstance();
    public PendingTitledWithList pendingTitledWithList=PendingTitledWithList.getInstance();
    public ArchiveTitledWithList archiveTitledWithList=ArchiveTitledWithList.getInstance();
    public TrashTitledWithList trashTitledWithList=TrashTitledWithList.getInstance();


    private MyFileAccordion(){
        this.getPanes().addAll(draftTitledWithList,pendingTitledWithList,archiveTitledWithList,trashTitledWithList);
        this.setExpandedPane(draftTitledWithList);

    }

    public static MyFileAccordion getInstance(){
        if (myFileAccordion==null){
            myFileAccordion=new MyFileAccordion();
        }
        return myFileAccordion;
    }



}
