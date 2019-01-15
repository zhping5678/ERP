package pkg.unclassified;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CommoditiesTableComponentItem extends InformationItemInTable{
    SimpleStringProperty id =new SimpleStringProperty();
    SimpleStringProperty name=new SimpleStringProperty();
    SimpleIntegerProperty quantity=new SimpleIntegerProperty();
    SimpleDoubleProperty unitPrice=new SimpleDoubleProperty();
    SimpleDoubleProperty totalPrice=new SimpleDoubleProperty(0.00);
    SimpleStringProperty remark=new SimpleStringProperty();

    public CommoditiesTableComponentItem(String Id, String name, int quantity, double unitPrice, String remark){
        this.id.set(Id);
        this.name.set(name);
        this.quantity.set(quantity);
        this.unitPrice.set(unitPrice);
        this.totalPrice.bind(this.unitPrice.multiply(this.quantity));
        this.remark.set(remark);
    }

    public String getId() {
        return id.get();
    }
    public String getName() {
        return name.get();
    }
    public int getQuantity() {
        return quantity.get();
    }
    public double getUnitPrice() {
        return unitPrice.get();
    }
    public double getTotalPrice() {
       return totalPrice.get();
    }
    public String getRemark() {
        return remark.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity); }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice.set(unitPrice);
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice.set(totalPrice);
    }

    public void setRemark(String remark) {
        this.remark.set(remark);
    }

}
