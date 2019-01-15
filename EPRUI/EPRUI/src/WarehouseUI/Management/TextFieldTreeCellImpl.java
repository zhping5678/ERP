package WarehouseUI.Management;

import Login.CurrentState;
import MockObject.Warehousebl;
import vo.utilityvo.ResultMessage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vo.warehousevo.CommodityTypeVO;
import vo.warehousevo.CommodityVO;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import static java.lang.Thread.sleep;

public class TextFieldTreeCellImpl extends TreeCell<String> {
    private TextField textField;

    @Override
    public void startEdit() {
        super.startEdit();

        if (textField == null) {
            createTextField();
        }
        setText(null);
        setGraphic(textField);
        textField.selectAll();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(getItem());
        setGraphic(getTreeItem().getGraphic());
    }

    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(getTreeItem().getGraphic());

                //0.判断是否被禁用给字体上色
                WarehouseTreeItem<String> tempBan= (WarehouseTreeItem) getTreeItem();
                boolean isBanned= tempBan.isBanned();
                if(isBanned){
                    setStyle("-fx-text-fill:rgb(178,178,178)");
                }
                else{
                    setStyle("-fx-text-fill:black");
                }

                //1.判断点击选项的孩子是商品还是商品分类
                Warehousebl warehouse=new Warehousebl();
                WarehouseTreeItem<String> temp= (WarehouseTreeItem<String>) getTreeItem();
                String path="";
                if(temp.getParent()==null){
                    path=temp.getValue();
                }
                else{
                    path=temp.getPath();
                }
                boolean hasCommodity=warehouse.hasCommodity(path);
                boolean hasCommodityType=warehouse.hasCommodityType(path);
                boolean isCommodity=warehouse.isCommodity(path);

                //2.根据不同的item设置不同是context menu
                ContextMenu menu=new ContextMenu();
                if(isCommodity){
                    menu.getItems().clear();
                    menu.getItems().add(getBan_RecoverCommodity(path));
                    menu.getItems().add(getDeleteCommodity(path));
                }
                else if(hasCommodity){
                    menu.getItems().clear();
                    menu.getItems().add(getNewCommodity(path));
                    menu.getItems().add(getBan_RecoverCommodityType(path));
                    menu.getItems().add(getDeleteCommodityType(path));
                }
                else if(hasCommodityType){
                    menu.getItems().clear();
                    menu.getItems().add(getNewCommodityType(path));
                    menu.getItems().add(getBan_RecoverCommodityType(path));
                    menu.getItems().add(getDeleteCommodityType(path));
                }
                else{
                    menu.getItems().clear();
                    menu.getItems().add(getNewCommodity(path));
                    menu.getItems().add(getNewCommodityType(path));
                    menu.getItems().add(getBan_RecoverCommodityType(path));
                    menu.getItems().add(getDeleteCommodityType(path));
                }
                setContextMenu(menu);
            }
        }

    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setStyle("-fx-border-color:#f2f2f2; -fx-background-color:#ffffff;");
        textField.setOnKeyReleased((KeyEvent t) -> {
            if (t.getCode() == KeyCode.ENTER) {
                commitEdit(textField.getText());
            } else if (t.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
            }
        });
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }

    private MenuItem getNewCommodity(String finalPath){
        MenuItem item=new MenuItem("新增商品");
        item.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /*
                URL url=getClass().getResource("product.fxml");
                FXMLLoader loader=new FXMLLoader(url);
                Parent root=null;
                try {
                    root=loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Map<String, Object> fxmlnamespace=loader.getNamespace();
                */

                URL url=getClass().getResource("SetIDCommodity.fxml");
                FXMLLoader loader=new FXMLLoader(url);
                Parent root=null;
                try {
                    root=loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage=new Stage();
                stage.setScene(new Scene(root));
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.show();

                Map<String,Object> fxmlnamespace=loader.getNamespace();
                Button confirm= (Button) fxmlnamespace.get("confirm");
                Button cancel= (Button) fxmlnamespace.get("cancel");
                TextField id= (TextField) fxmlnamespace.get("id");
                Label attention= (Label) fxmlnamespace.get("attention");
                Label close= (Label) fxmlnamespace.get("close");
                confirm.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if(id.getText().equals("")){
                            attention.setText("商品ID不能为空");
                            attention.setTextFill(Paint.valueOf("red"));
                        }
                        else{
                            Warehousebl warehouse=new Warehousebl();
                            ResultMessage message=warehouse.addNewCommodityID(finalPath+"/"+id.getText());
                            if(message==ResultMessage.addSuccess){
                                CommodityVO vo=new CommodityVO(finalPath+"/"+id.getText(),"","",0,0,0,0,0,0,false);
                                ResultMessage finalmessage=warehouse.newCommodity(CurrentState.getLoginID(),vo);
                                if(finalmessage==ResultMessage.addSuccess){
                                    stage.close();
                                    WarehouseTreeItem<String> commodity=new WarehouseTreeItem<>(id.getText());
                                    getTreeItem().getChildren().add(commodity);
                                }
                                else{
                                    //增加失败
                                }
                            }
                            else if(message==ResultMessage.exist){
                                attention.setText("该路径下已存在该商品ID");
                                attention.setTextFill(Paint.valueOf("red"));
                            }
                            else if(message==ResultMessage.Fail){
                                attention.setText("增加商品失败");
                                attention.setTextFill(Paint.valueOf("red"));
                            }

                            /*
                            if(message==ResultMessage.addSuccess){
                                stage.close();
                                WarehouseTreeItem<String> c=new WarehouseTreeItem<>(id.getText());
                                getTreeItem().getChildren().add(c);
                            }
                            else{
                                attention.setText("该目录下已存在相同ID的商品");
                                attention.setTextFill(Paint.valueOf("red"));
                            }
                            */
                        }
                    }
                });
                cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        stage.close();
                    }
                });
                close.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        stage.close();
                    }
                });

            }
        });

        return item;
    }

    private MenuItem getNewCommodityType(String finalPath){
        MenuItem item=new MenuItem("新增商品分类");
        item.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                URL url=getClass().getResource("SetIDCommodityType.fxml");
                FXMLLoader loader=new FXMLLoader(url);
                Parent root=null;
                try {
                    root=loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage=new Stage();
                stage.setScene(new Scene(root));
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.show();

                Map<String,Object> fxmlnamespace=loader.getNamespace();
                Button confirm= (Button) fxmlnamespace.get("confirm");
                Button cancel= (Button) fxmlnamespace.get("cancel");
                TextField id= (TextField) fxmlnamespace.get("id");
                Label attention= (Label) fxmlnamespace.get("attention");
                Label close= (Label) fxmlnamespace.get("close");
                confirm.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if(id.getText().equals("")){
                            attention.setText("商品分类ID不能为空");
                            attention.setTextFill(Paint.valueOf("red"));
                        }
                        else{
                            Warehousebl warehouse=new Warehousebl();
                            ResultMessage message=warehouse.addNewCommodityTypeID(finalPath+"/"+id.getText());
                            System.out.println(message);
                            if(message==ResultMessage.addSuccess){
                                CommodityTypeVO vo=new CommodityTypeVO(finalPath+"/"+id.getText(),"",false);
                                ResultMessage finalmessage=warehouse.newCommodityType(CurrentState.getLoginID(),vo);
                                System.out.println(finalmessage);
                                if(finalmessage==ResultMessage.addSuccess){
                                    stage.close();
                                    WarehouseTreeItem<String> commoditytype=new WarehouseTreeItem<>(id.getText());
                                    getTreeItem().getChildren().add(commoditytype);
                                }
                                else{
                                    attention.setText("增加商品分类失败，原因不明");
                                    attention.setTextFill(Paint.valueOf("red"));
                                }
                            }
                            else if(message==ResultMessage.exist){
                                attention.setText("该路径下已存在该商品分类ID");
                                attention.setTextFill(Paint.valueOf("red"));
                            }
                            else if(message==ResultMessage.Fail){
                                attention.setText("增加商品分类失败");
                                attention.setTextFill(Paint.valueOf("red"));
                            }

                        }
                    }
                });
                cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        stage.close();
                    }
                });
                close.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        stage.close();
                    }
                });
            }
        });

        return item;
    }

    private MenuItem getBan_RecoverCommodity(String finalPath){
        Warehousebl bl=new Warehousebl();
        boolean isBanned=bl.isBanedCommodity(finalPath);
        MenuItem item=null;
        if(isBanned){
            item=new MenuItem("重启该商品");
        }
        else{
            item=new MenuItem("禁用该商品");
        }

        MenuItem finalItem = item;
        item.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(finalItem.getText().contains("禁用")){
                    if(bl.banCommodity(CurrentState.getLoginID(),finalPath)==ResultMessage.banSuccess){
                        getTreeView().refresh();
                        finalItem.setText("重启该商品");
                    }
                    else{
                        //禁用失败
                        System.out.println("禁用失败");
                    }
                }
                else{
                    if(bl.recoverCommodity(CurrentState.getLoginID(),finalPath)==ResultMessage.recoverSuccess){
                        getTreeView().refresh();
                        finalItem.setText("禁用该商品");
                    }
                    else{
                        //重启失败
                        System.out.println("重启失败");
                    }
                }
            }
        });
        return item;

    }

    private MenuItem getBan_RecoverCommodityType(String finalPath){
        Warehousebl bl=new Warehousebl();
        boolean isBanned=bl.isBanedCommodityType(finalPath);
        MenuItem item=null;
        if(isBanned){
            item=new MenuItem("重启该商品分类");
        }
        else{
            item=new MenuItem("禁用该商品分类");
        }

        MenuItem finalItem = item;
        item.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(finalItem.getText().contains("禁用")){
                    if(bl.banCommodityType(CurrentState.getLoginID(),finalPath)==ResultMessage.banSuccess){
                        finalItem.setText("重启该商品分类");
                        getTreeView().refresh();
                    }
                    else{
                        //禁用失败
                    }
                }
                else{
                    if(bl.recoverCommodityType(CurrentState.getLoginID(),finalPath)==ResultMessage.recoverSuccess){
                        finalItem.setText("禁用该商品分类");
                        getTreeView().refresh();
                    }
                    else{
                        //失败
                    }

                }
            }
        });
        return item;

    }

    private MenuItem getDeleteCommodity(String finalPath){
        MenuItem item=new MenuItem("删除该商品");
        Warehousebl bl=new Warehousebl();
        item.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(bl.deleteCommodity(CurrentState.getLoginID(),finalPath)== ResultMessage.delSuccess){
                    getTreeItem().getParent().getChildren().remove(getTreeItem());
                }
                else{
                    //删除失败
                }
            }
        });
        return item;
    }

    private MenuItem getDeleteCommodityType(String finalPath){
        MenuItem item=new MenuItem("删除该商品分类");
        Warehousebl bl=new Warehousebl();
        item.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(bl.deleteCommodityType(CurrentState.getLoginID(),finalPath)== ResultMessage.delSuccess){
                    getTreeItem().getParent().getChildren().remove(getTreeItem());
                }
                else{
                    //删除失败
                }
            }
        });
        return item;
    }


}
