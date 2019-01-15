package pkg.unclassified;

import javafx.beans.property.SimpleStringProperty;

public class BusinessHistoryTableComponentItem extends InformationItemInTable{
    SimpleStringProperty id =new SimpleStringProperty();
    SimpleStringProperty clerk =new SimpleStringProperty();
    SimpleStringProperty client =new SimpleStringProperty();
    SimpleStringProperty warehouse =new SimpleStringProperty();
    SimpleStringProperty time=new SimpleStringProperty();


    public BusinessHistoryTableComponentItem(String Id, String clerk,String client,String warehouse,String time){
        this.id.set(Id);
        this.clerk.set(clerk);
        this.client.set(client);
        this.warehouse.set(warehouse);
        this.time.set(time);

    }

    public String getId() {
        return id.get();
    }
    public String getClerk() {
        return clerk.get();
    }

    public String getTime() {
        return time.get();
    }

    public String getWarehouse() {
        return warehouse.get();
    }

    public String getClient() {
        return client.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public void setClerk(String clerk) {
        this.clerk.set(clerk);
    }


    public void setClient(String client) {
        this.client.set(client);
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public void setWarehouse(String warehouse) {
        this.warehouse.set(warehouse);
    }
}
