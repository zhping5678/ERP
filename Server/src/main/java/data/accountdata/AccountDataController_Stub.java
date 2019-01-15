package data.accountdata;
/**
 *@Name:
 *@Description: 桩程序
 *@Author: Jane
 @Date: 2017/12/12 14:49
 */

import dataservice.accountdataservice.AccountDataService;
import po.accountpo.AccountPO;
/**测试点
 * 1.增加一个账户，当账户不存在时，增加成功；当账户已经存在时，显示已存在，增加失败。
 * 2.删除一个账户，当账户还未产生交易时，彻底删除。当账户已经产生交易，且当账户正处于业务交易中，删除失败，显示使用中。当账户不处于业务交易中，删除成功
 * 3.禁用一个账户，当账户正处于交易中，显示使用中。否则，禁用成功。
 * 4.精确查找一个账户,当该账户对界面是可见的，则返回。
 * 5.模糊查找账户，当账户列表中的账户对界面是可见的，则返回账户的ID。
 * 6.修改账户名.当新帐户名已经存在时，修改失败，显示已存在。
 * 7.修改一个账户是否处于业务交易中的状态
 * 8.修改一个账户是否能被彻底删除的状态
 * 9.修改一个账户的金额
 * 10.显示所以账户的id,其中该账户对界面必须是可见的
 */


import java.util.ArrayList;

public class AccountDataController_Stub implements AccountDataService {
    public void write(AccountPO po){

    }
    public AccountPO read(String ID){
       if(ID=="Ping"){
           AccountPO po=new AccountPO("Ping","张萍",1000.0,false,true,false);
           return po;
       }else if(ID=="Paul"){
           AccountPO po2=new AccountPO("Paul","黄鹏",10000.0,true,true,false);
           return po2;
       }else{
           return null;
       }
    }

    public void remove(String ID){

    }
    public ArrayList<AccountPO> showList(){
        ArrayList<AccountPO> pos=new ArrayList<>();
        AccountPO po1=new AccountPO("Paul","黄鹏",10000.0,true,true,false);
        AccountPO po2=new AccountPO("Wang","王旭",15000.0,true,true,false);
        AccountPO po3=new AccountPO("Ping","张萍",1000.0,false,true,false);
        pos.add(po1);
        pos.add(po2);
        pos.add(po3);
        return pos;
    }
    public ArrayList<AccountPO> findByKeywords(String keywords){
        ArrayList<AccountPO> pos=new ArrayList<>();
        AccountPO po1=new AccountPO("Paul","黄鹏",10000.0,true,true,false);
        AccountPO po2=new AccountPO("Pojo","保罗",15000.0,true,true,false);
        AccountPO po3=new AccountPO("Ping","张萍",1000.0,false,true,false);
        pos.add(po1);
        pos.add(po2);
        pos.add(po3);
        return pos;

    }

    @Override
    public void update(AccountPO po) {

    }

//    @Override
//    public ArrayList<String> test(String a) {
//        return null;
//    }

}
