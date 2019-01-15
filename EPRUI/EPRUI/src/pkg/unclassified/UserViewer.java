package pkg.unclassified;
import javafx.beans.property.BooleanProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserViewer extends Viewer {
    public BooleanProperty editable;
    public FileTitleComponent fileTitleComponent;
    public static UserViewer userViewer;
    public CreationInformationComponent creationInformationComponent;
    public SeparatorComponent separatorComponent;
    public SeparatorWithTitleComponent basicSeparatorWithTitleComponent;
    public SeparatorWithTitleComponent passwordSeparatorWithTitleComponent;
    public TextFieldComponent remarkTextFieldComponent;
    public ButtonsComponent confirmButtonsComponent;
    public SpaceComponent spaceComponent;
    public ChoiceBoxComponent positionChoiceBoxComponent;
    public ChoiceBoxComponent authorityChoiceBoxComponent;
    public PasswordFieldComponent originalPasswordFieldComponent;
    public PasswordFieldComponent newPasswordFieldComponent;
    public PasswordFieldComponent repeatPasswordFieldComponent;






    public UserViewer() {
        positionChoiceBoxComponent =new ChoiceBoxComponent("Position","General Manager","Financial Staff","Warehouse Worker","Salesman");
        authorityChoiceBoxComponent=new ChoiceBoxComponent("Authority","Yes","No");
        positionChoiceBoxComponent.choiceBox.getSelectionModel().select(0);
        //  positionChoiceBoxComponent.setDisable(true);
        fileTitleComponent = new FileTitleComponent("paulwong", "Paul Wong");
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        creationInformationComponent = new CreationInformationComponent("A user");
        separatorComponent = new SeparatorComponent();
        basicSeparatorWithTitleComponent = new SeparatorWithTitleComponent("BASIC");
        passwordSeparatorWithTitleComponent = new SeparatorWithTitleComponent("OTHERS");
        confirmButtonsComponent = new ButtonsComponent("Confirm");
        //   addCommodityButtonsComponent.buttonArrayList.get(0).setDisable(true);
        originalPasswordFieldComponent=new PasswordFieldComponent("Original Password");
        newPasswordFieldComponent=new PasswordFieldComponent("New Password");
        repeatPasswordFieldComponent=new PasswordFieldComponent("Repeat Password");

        spaceComponent = new SpaceComponent();
        remarkTextFieldComponent =new TextFieldComponent("Remark");

        vBox.getChildren().addAll(fileTitleComponent
                , creationInformationComponent
                , separatorComponent
                , basicSeparatorWithTitleComponent
                , positionChoiceBoxComponent
                ,authorityChoiceBoxComponent
                , passwordSeparatorWithTitleComponent
                ,originalPasswordFieldComponent
                ,newPasswordFieldComponent
                ,repeatPasswordFieldComponent
                ,confirmButtonsComponent
                , spaceComponent

        );
    }


    public static UserViewer getInstance() {
        if (userViewer == null) {
            userViewer = new UserViewer();
        }
        return userViewer;
    }
}

