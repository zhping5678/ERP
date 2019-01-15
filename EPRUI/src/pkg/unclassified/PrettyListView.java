package pkg.unclassified;

import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Set;
/*************ORIGINAL*************/
public class PrettyListView<T> extends ListView<T> {
    private ScrollBar vBar = new ScrollBar();
    private ScrollBar hBar = new ScrollBar();
    /**************------------------***********/
    /*************ORIGINAL*************/
    public PrettyListView() {
        super();
        skinProperty().addListener(it -> {
            // first bind, then add new scrollbars, otherwise the new bars will be found
            bindScrollBars();
            getChildren().addAll(vBar, hBar);
        });
        /**************------------------***********/
        this.getStylesheets().add("/pkg/stylesheet/PrettyListView.css");
        /*************ORIGINAL*************/
        getStyleClass().add("pretty-list-view");
        vBar.setManaged(false);
        vBar.setOrientation(Orientation.VERTICAL);
        vBar.getStyleClass().add("pretty-scroll-bar");
        vBar.visibleProperty().bind(vBar.visibleAmountProperty().isNotEqualTo(0));
        hBar.setManaged(false);
        hBar.setOrientation(Orientation.HORIZONTAL);
        hBar.getStyleClass().add("pretty-scroll-bar");
        hBar.visibleProperty().bind(hBar.visibleAmountProperty().isNotEqualTo(0));
        /**************------------------***********/
        AnimationTimer timer =new AnimationTimer() {
            @Override
            public void handle(long now) {
                calculate(now);
            }
        };
        timer.start();
        /////////////////////////////////////////////////////////////////////
        //
        //Animation of VBar.{
        vBarDisappear();
        this.setOnScrollStarted(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                vBarAppearAndDisappear();
             //   System.out.println("1");
                vBarAnimationTerminate();
                ((PrettyListView<T>)(event.getSource())).setId("scrolling");
                System.out.println("5scrolling");
            }
        });
        this.setOnScrollFinished(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                vBarDisappear();
             //   System.out.println("2");
                vBarAnimation();
            }
        });
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                vBarAppearAndDisappear();
             //   System.out.println(3);
            }
        });
        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                vBarDisappear();
             //   System.out.println(4);
            }
        });
        this.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
             //   System.out.println(4.5);
            }
        });
        vBar.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                vBarAppearAndDisappear();
             //   System.out.println(5);
            }
        });
        vBar.setOnScrollStarted(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                vBarAppearAndDisappear();
            //    System.out.println(6);
            }
        });
        vBar.setOnScrollFinished(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                vBarDisappear();
            //    System.out.println(7);
            }
        });
        vBar.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                vBarAppear();
            //    System.out.println(8);
            }
        });
        vBar.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                vBarDisappear();
             //   System.out.println(9);
            }
        });
        ///////////////
        // }
    }
    private FadeTransition vBarAppearTransition;
    private SequentialTransition vBarSequentialTransition;
    private void vBarAppear(){
        if (vBarSequentialTransition!=null) vBarSequentialTransition.stop();
        if (vBarAppearTransition!=null) vBarAppearTransition.stop();
        vBarAppearTransition=new FadeTransition(Duration.millis(50),vBar);
        vBarAppearTransition.setFromValue(vBar.getOpacity());
        vBarAppearTransition.setToValue(1.0);
        vBarAppearTransition.playFromStart();
    }
    private void vBarAppearAndDisappear(){
        if (vBarSequentialTransition!=null) vBarSequentialTransition.stop();
        if (vBarAppearTransition!=null) vBarAppearTransition.stop();
        vBarAppearTransition=new FadeTransition(Duration.millis(50),vBar);
        vBarAppearTransition.setFromValue(vBar.getOpacity());
        vBarAppearTransition.setToValue(1.0);
        vBarAppearTransition.playFromStart();
        vBarAppearTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vBarDisappear();
            }
        });
    }
    private void vBarDisappear(){
        if (vBarAppearTransition!=null) vBarAppearTransition.stop();
        if (vBarSequentialTransition!=null) vBarSequentialTransition.stop();
        FadeTransition vBarStayTransition=new FadeTransition(Duration.millis(1000),vBar);
        vBarStayTransition.setFromValue(vBar.getOpacity());
        vBarStayTransition.setToValue(vBar.getOpacity());
        FadeTransition vBarFadeTransition=new FadeTransition(Duration.millis(500),vBar);
        vBarFadeTransition.setFromValue(vBar.getOpacity());
        vBarFadeTransition.setToValue(0.0);
        vBarSequentialTransition=new SequentialTransition();
        vBarSequentialTransition.getChildren().addAll(vBarStayTransition,vBarFadeTransition);
        vBarSequentialTransition.playFromStart();
    }
    double nowTime=-1;
    double lastTime=-1;
    double vBarSpeed=-1;
    double lastVBarPosition=-1;
    double nowVBarPosition=-1;
    Timeline timeline;
    private void calculate(long now){
        nowTime=longToDouble(now);
        nowVBarPosition=vBar.getValue();
        if (lastTime>=0){
            if(lastVBarPosition>=0){
                vBarSpeed=(nowVBarPosition-lastVBarPosition)/(nowTime-lastTime);
           //     System.out.println(nowTime);
                lastVBarPosition=nowVBarPosition;
            }else{
                lastVBarPosition=nowVBarPosition;
            }
            lastTime=nowTime;
        }else{
            lastTime=nowTime;
        }
    }
    ArrayList<KeyFrame> keyFrameArrayList;
    private double longToDouble(long l){
        return (double) (l/1000000.000000000);
    }
    private void setKeyFrameArrayList(){
        keyFrameArrayList=new ArrayList<>();
        double a=-0.0000012;
     //   System.out.println(vBarSpeed/a);
        double currentSpeed=vBarSpeed;
        if (currentSpeed<0) a=-a;
        double startPosition=vBar.getValue();
        double targetPosition=startPosition+((0-currentSpeed*currentSpeed)/(2*a));
        if(targetPosition>1)targetPosition=1;//////
        if (targetPosition<0)targetPosition=0;////////////
        boolean enough=false;
        double currentPosition=startPosition;

        double interval=10;
        while(!enough){
            if(Math.abs(currentPosition-startPosition)+Math.abs(interval*currentSpeed)>=Math.abs(targetPosition-startPosition)){
           //     System.out.println("true");
                enough=true;
                KeyValue keyValue=new KeyValue(vBar.valueProperty(),targetPosition);
                KeyFrame keyFrame=new KeyFrame(Duration.millis((int)(Math.abs((targetPosition-currentPosition)/currentSpeed)+200)),keyValue);
                keyFrameArrayList.add(keyFrame);
            }else{

           //     System.out.println(false);
                double nextPosition=currentPosition+currentSpeed*interval;
                KeyValue keyValue=new KeyValue(vBar.valueProperty(),nextPosition);
                KeyFrame keyFrame=new KeyFrame(Duration.millis(interval),keyValue);
                keyFrameArrayList.add(keyFrame);
                currentPosition=nextPosition;
                currentSpeed=currentSpeed+interval*a;
            }
        }
    }

    private void vBarAnimation(){
        this.setId("scrolling");
        System.out.println("3scrolling");
        setKeyFrameArrayList();
        vBarSingleAnimation();


    }
    int i=-1;
    private void vBarSingleAnimation(){
        i++;
        System.out.println(i+ " "+keyFrameArrayList.size());
        if ((i>=keyFrameArrayList.size())||(i<0)){
            i=-1;
            this.setId(null);
            System.out.println("2null");
            return;
        }
        timeline=new Timeline();
        timeline.getKeyFrames().add(keyFrameArrayList.get(i));
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vBarSingleAnimation();
              //  System.out.println("finished");
            }
        });
        timeline.play();
    }
    private void vBarAnimationTerminate() {
        if (timeline!=null) timeline.stop();
        timeline=null;
        i=-1;
    }
    /*************ORIGINAL*************/
    private void bindScrollBars() {
        final Set<Node> nodes = lookupAll("VirtualScrollBar");
        for (Node node : nodes) {
            if (node instanceof ScrollBar) {
                ScrollBar bar = (ScrollBar) node;
                if (bar.getOrientation().equals(Orientation.VERTICAL)) {
                    bindScrollBars(vBar, bar);
                } else if (bar.getOrientation().equals(Orientation.HORIZONTAL)) {
                    bindScrollBars(hBar, bar);
                }
            }
        }
    }
    private void bindScrollBars(ScrollBar scrollBarA, ScrollBar scrollBarB) {
        scrollBarA.valueProperty().bindBidirectional(scrollBarB.valueProperty());
        scrollBarA.minProperty().bindBidirectional(scrollBarB.minProperty());
        scrollBarA.maxProperty().bindBidirectional(scrollBarB.maxProperty());
        scrollBarA.visibleAmountProperty().bindBidirectional(scrollBarB.visibleAmountProperty());
        scrollBarA.unitIncrementProperty().bindBidirectional(scrollBarB.unitIncrementProperty());
        scrollBarA.blockIncrementProperty().bindBidirectional(scrollBarB.blockIncrementProperty());
    }
    @Override
    protected void layoutChildren() {
        super.layoutChildren();

        Insets insets = getInsets();
        double w = getWidth();
        double h = getHeight();
        final double prefWidth = vBar.prefWidth(-1);
        vBar.resizeRelocate(w - prefWidth - insets.getRight(), insets.getTop(), prefWidth, h - insets.getTop() - insets.getBottom());

        final double prefHeight = hBar.prefHeight(-1);
        hBar.resizeRelocate(insets.getLeft(), h - prefHeight - insets.getBottom(), w - insets.getLeft() - insets.getRight(), prefHeight);
    }
    /**************------------------***********/
}