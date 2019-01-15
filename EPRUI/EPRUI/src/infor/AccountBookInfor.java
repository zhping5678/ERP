package infor;

import java.io.Serializable;
import java.util.Set;

public class AccountBookInfor implements Serializable{
    private static final long serialVersionUID=12L;

    private String time;//将账单生成的时间标记为账单的id
    private Set<CommodityIniInfor> commodity;
    private Set<ClientInitInfor> clients;
    private Set<AccountInitInfor> accounts;

    public AccountBookInfor(){}

    public String getTime(){
        return this.time;
    }

    public void setTime(String time){
        this.time=time;
    }

    public Set<CommodityIniInfor> getCommodity() {
        return this.commodity;
    }

    public void setCommodity(Set<CommodityIniInfor> commodity) {
        this.commodity = commodity;
    }

    public Set<ClientInitInfor> getClients() {
        return this.clients;
    }

    public void setClients(Set<ClientInitInfor> clients) {
        this.clients = clients;
    }

    public Set<AccountInitInfor> getAccounts() {
        return this.accounts;
    }

    public void setAccounts(Set<AccountInitInfor> accounts) {
        this.accounts = accounts;
    }

}
