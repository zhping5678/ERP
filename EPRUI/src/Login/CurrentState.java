package Login;

import vo.uservo.Position;

public class CurrentState {
    private static String loginID;
    private static String name;
    private static String loginPassword;
    private static Position p;
    private static boolean right;

    public static String getLoginID(){
        return loginID;
    }

    public static  void setLoginID(String id){
        loginID=id;
    }

    public static Position getPosition(){
        return p;
    }

    public static String getName(){
        return name;
    }

    public static void setPosition(Position position){
        p=position;
    }

    public static void setName(String n){
        name=n;
    }

    public static void setRight(boolean r){
        right=r;
    }

    public static boolean getRight(){
        return right;
    }


}
