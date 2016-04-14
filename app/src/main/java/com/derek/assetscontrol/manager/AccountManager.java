package com.derek.assetscontrol.manager;

import com.derek.assetscontrol.model.User;
import com.derek.assetscontrol.network.NetConstants;
import com.derek.basemodule.manager.BaseManager;
import com.derek.basemodule.manager.NetObservableImpl;
import com.derek.basemodule.model.NetReq;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by derek on 16/4/11.
 */
public class AccountManager extends BaseManager {
    private static AccountManager instance = new AccountManager();

    private AccountManager() {

    }

    public static AccountManager getInstance() {
        return instance;
    }

    public void login(String account, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("staff_id", account);
        map.put("password", password);
        NetReq netReq = new NetReq(NetConstants.LOGIN_URL, NetReq.METHOD_TYPE.POST, map);
        subscribe(Constants.LOGIN, netReq, User.class, new NetObservableImpl());
    }
}
