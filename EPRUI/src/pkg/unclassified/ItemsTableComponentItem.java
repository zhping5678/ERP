package pkg.unclassified;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class ItemsTableComponentItem extends InformationItemInTable {

    SimpleStringProperty remark=new SimpleStringProperty();
    SimpleDoubleProperty value=new SimpleDoubleProperty();

    public ItemsTableComponentItem(String remark, Double value){

        this.remark.set(remark);
        this.value.set(value);
    }

    public String getRemark() {
        return remark.get();
    }

    public Double getValue() {
        return value.get();
    }
    public void setRemark(String remark) {
        this.remark.set(remark);
    }


    public void setValue(Double value){
        this.value.set(value);

    }
}

