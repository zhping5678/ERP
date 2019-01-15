package pkg.unclassified;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import pkg.ui.utilityui.UtilityUIControllerAccess;

public abstract   class TitledWithList extends Titled {
    public Listed listed;
    public TitledWithList(String titleName){
        this(titleName,true);


    }
    public TitledWithList(String titleName,boolean withList){
        super();
        if (withList){
            listed=new Listed();
            listed.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ItemText>() {
                @Override
                public void changed(ObservableValue<? extends ItemText> observable, ItemText oldValue, ItemText newValue) {
                    if( newValue!=null){
                        UtilityUIControllerAccess.utilityUIControllerAccess.switchToFileViewer(newValue.id);
                    }


                }
            });
            this.setText(titleName);
            this.setContent(listed);
        }else{
            this.setText(titleName);

        }



    }
}
