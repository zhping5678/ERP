package pkg.unclassified;

import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.input.MouseEvent;

public class IdTableCell extends TableCell<InformationItemInTable,String> {
    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty)  setText(item);
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((IdTableCell)event.getSource()).setStyle(((IdTableCell)event.getSource()).getStyle()+"-fx-text-fill:"+Parameters.CSS_NJU_PURPLE+";");
            }
        });
        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((IdTableCell)event.getSource()).setStyle(((IdTableCell)event.getSource()).getStyle()+"-fx-text-fill:black;");
            }
        });
    }
}

