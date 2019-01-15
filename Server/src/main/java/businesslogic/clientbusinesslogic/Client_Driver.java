package businesslogic.clientbusinesslogic;
/*
 *@Name:
 *@Description: 测试点：
 *                      1.增加客户（增加成功/客户已存在/增加失败）
 *                      2.删除客户（彻底删除成功/删除成功/在服务中/没有权限）
 *                      3.禁用客户（禁用成功/在服务中/没有权限/失败）
 *                      4.还原客户（还原成功/失败）
 *                      5.修改客户信息（太复杂了明天再说把）
 *@Author: Jane
 @Date: 2017/12/15 2:40
 */

import businesslogicservice.clientbusinesslogicservice.ClientBusinessLogicService;
import vo.clientvo.ClientIdentity;
import vo.clientvo.ClientVO;

import java.rmi.RemoteException;

public class Client_Driver {
    public static void main(String args[]) throws RemoteException {
        ClientBusinessLogicService client=new ClientBusinessLogicController();
        //ClientBusinessLogicControllerAccess c=new ClientBusinessLogicController();
        //ClientVO vo1=new ClientVO("Jane", "jane",ClientIdentity.Seller,1,"NJU","8829","7797@qq.com","214400","Ping","null",0.0,10000.0,0.0,10000.0,false);
        ClientVO vo2=new ClientVO("Liu", "刘",ClientIdentity.Supplier,1,"NJU","8829","7797@qq.com","214400","Ping","null",0.0,10000.0,10000.0,10000.0,false);
        //ClientVO vo3=new ClientVO("Ping", "张萍",ClientIdentity.SellerAndSupplier,1,"NJU","8829","7797@qq.com","214400","Ping","null",500.0,300.0,0.0,60000.0,false);
        System.out.println(client.addClient("zhangping",vo2));
        //System.out.println(client.addClient("zhangping",vo1));
//        c.modifyClientCanDel("Paul",false);
//        System.out.println("销售商");
//        for(String s:client.showSellerList()){
//            System.out.println(s);
//        }
//
//        System.out.println("进货商");
//        for(String s:client.showSupplierList()){
//            System.out.println(s);
//        }
//        System.out.println(client.deleteClient("zhangping","Jane"));
//        System.out.println(client.modifyClient("zhangping","Jane",new ClientVO("Ping", "张萍",ClientIdentity.SellerAndSupplier,1,"NJU","8829","7797@qq.com","214400","Ping","null",500.0,300.0,0.0,60000.0,false)));
//        System.out.println(client.modifyClient("zhangping","Jane",new ClientVO("Jane", "zhongjie",ClientIdentity.SellerAndSupplier,1,"QHD","8829","7797@qq.com","214400","Ping","null",500.0,300.0,0.0,60000.0,false)));
    }
}
