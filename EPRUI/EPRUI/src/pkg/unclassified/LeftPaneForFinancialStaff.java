package pkg.unclassified;
import pkg.ui.accountui.AccountButton;
import pkg.ui.clientui.ClientButton;
import pkg.ui.fileui.FileButton;
import pkg.ui.settingui.SettingButton;

public class LeftPaneForFinancialStaff extends LeftPane {
     public LeftPaneForFinancialStaff(){
         super();
         tabButtonArrayList.add(new FileButton());
         tabButtonArrayList.add(new ClientButton());
         tabButtonArrayList.add(new AccountButton());
         tabButtonArrayList.add(new SettingButton());
         this.getChildren().addAll(tabButtonArrayList);
     }
    public static LeftPane getInstance(){
        if (leftPane==null){
            leftPane=new LeftPaneForFinancialStaff();
        }
        return leftPane;
    }


}
