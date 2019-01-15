package pkg.unclassified;

import javafx.beans.property.SimpleStringProperty;

public class LogsTableComponentItem extends InformationItemInTable{
    SimpleStringProperty id =new SimpleStringProperty();
    SimpleStringProperty time =new SimpleStringProperty();
    SimpleStringProperty event =new SimpleStringProperty();

    public LogsTableComponentItem(String id, String time,  String event){
        this.id.set(id);
        this.time.set(time);


        this.event.set(event);
    }

    public String getId() {
        return id.get();
    }
    public String getTime() {
        return time.get();
    }

    public String getEvent() {
        return event.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public void setTime(String time) {
        this.time.set(time);
    }


    public void setEvent(String remark) {
        this.event.set(remark);
    }

}
