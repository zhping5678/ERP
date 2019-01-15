package pkg.unclassified;

import javafx.beans.property.SimpleStringProperty;

public class ReturnReceiptsTableComponentItem extends InformationItemInTable{
    SimpleStringProperty Id =new SimpleStringProperty();
    SimpleStringProperty time =new SimpleStringProperty();
    SimpleStringProperty remark=new SimpleStringProperty();
    SimpleStringProperty result=new SimpleStringProperty();
    public ReturnReceiptsTableComponentItem(String Id, String name, String remark,String result){
        this.Id.set(Id);
        this.time.set(name);
        this.remark.set(remark);
        this.result.set(result);
    }
    public String getId() {
        return Id.get();
    }
    public String getTime() {
        return time.get();
    }
    public String getRemark() {
        return remark.get();
    }
    public String getResult(){ return result.get(); }
    public void setId(String id) {
        this.Id.set(id);
    }
    public void setTime(String time) {
        this.time.set(time);
    }
    public void setRemark(String remark) {
        this.remark.set(remark);
    }
    public void setResult(String result){
        this.result.set(result);

    }
}
