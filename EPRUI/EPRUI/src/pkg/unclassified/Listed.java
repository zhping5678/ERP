package pkg.unclassified;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class Listed extends PrettyListView<ItemText> {
    ObservableList<ItemText> observableList;
    public Listed(){
        /*
        this.getStylesheets().add("/pkg.unclassified/Listed.css");
        this.setOnScrollFinished(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                System.out.println(event.getTotalDeltaY());
            }
        });
        this.setOnScrollStarted(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                ((Listed)(event.getSource())).setId("scrolling");
                System.out.println("scorlling");
            }
        });
        this.setOnScrollFinished(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {

                ((Listed)(event.getSource())).setId("");
            }
        });
        */

        this.setCellFactory(new Callback<ListView<ItemText>, ListCell<ItemText>>() {
            @Override
            public ListCell<ItemText> call(ListView<ItemText> param) {
                return new ListedCell();
            }
        });
    }

    public class ListedCell extends ListCell<ItemText>{
        private AnchorPane anchorPane=new AnchorPane();

        private ItemText itemText=null;
        public ListedCell(){


            anchorPane.prefHeightProperty().bind(this.heightProperty());
            anchorPane.setPrefWidth(Parameters.MIDDLE_PREF_WIDTH-40);
          //  this.getChildren().add(anchorPane);
            anchorPane.setStyle(anchorPane.getStyle()
                    +"-fx-background-color:transparent;"
            );
        }
        @Override
        public void updateItem(ItemText item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                itemText=item;
                anchorPane.getChildren().clear();
                anchorPane.getChildren().add(itemText);
                AnchorPane.setLeftAnchor(itemText,10.0);






              //  setText(item.frontText.getText());
                setGraphic(anchorPane);

            }
        }

    }
}