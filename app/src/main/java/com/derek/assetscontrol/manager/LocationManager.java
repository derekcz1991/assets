package com.derek.assetscontrol.manager;

import com.derek.assetscontrol.model.Location;
import com.derek.assetscontrol.network.NetConstants;
import com.derek.basemodule.manager.BaseManager;
import com.derek.basemodule.manager.NetObservableImpl;
import com.derek.basemodule.model.NetReq;

/**
 * Created by derek on 16/4/9.
 */
public class LocationManager extends BaseManager{

    private static LocationManager instance = new LocationManager();

    private LocationManager() {

    }

    public static LocationManager getInstance() {
        return instance;
    }

    public void getLocationList() {
        NetReq netReq = new NetReq(NetConstants.LOCATION_LIST, NetReq.METHOD_TYPE.GET, null);
        subscribe(Constants.LOCATION_LIST, netReq, Location.class, new NetObservableImpl());
    }
}
