package pkg.unclassified;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ItemText extends javafx.scene.text.TextFlow {
    public String id;
    public Text frontText =new Text("");
    private Text spaceText=new Text(" ");
    public Text backText=new Text("");
    public boolean selected=false;
    public ItemText(String id, String frontText, String backText){
        this.id =id;
        this.frontText.setText(frontText);
        this.backText.setText(backText);
        this.backText.setFill(Color.grayRgb(162));
        this.getChildren().addAll(this.frontText,spaceText,this.backText);
    }
}
