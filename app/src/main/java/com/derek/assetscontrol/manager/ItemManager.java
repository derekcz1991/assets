package com.derek.assetscontrol.manager;

import android.util.Log;
import com.derek.assetscontrol.model.Item;
import com.derek.assetscontrol.model.OrderItem;
import com.derek.assetscontrol.network.NetConstants;
import com.derek.basemodule.manager.BaseManager;
import com.derek.basemodule.manager.NetObservableImpl;
import com.derek.basemodule.model.NetReq;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by derek on 16/4/9.
 */
public class ItemManager extends BaseManager {

    private String TAG = this.getClass().getSimpleName();

    private static ItemManager instance = new ItemManager();

    private ItemManager() {

    }

    public static ItemManager getInstance() {
        return instance;
    }

    public void getItemByNfcId(String nfcId) {
        Map<String, String> params = new HashMap<>();
        params.put("nfc_id", nfcId);
        Log.d(TAG, params.toString());
        NetReq netReq = new NetReq(NetConstants.GET_ITEM, NetReq.METHOD_TYPE.POST, params);
        subscribe(Constants.GET_ITEM, netReq, Item.class, new NetObservableImpl());
    }

    public void uploadItem(String staffId, String nfcId, String startPointId, String endPointId) {
        Map<String, String> params = new HashMap<>();
        params.put("staff_id", staffId);
        params.put("nfc_id", nfcId);
        params.put("current_location", startPointId);
        params.put("target_location", endPointId);
        Log.d(TAG, params.toString());
        NetReq netReq = new NetReq(NetConstants.UPLOAD_ITEM, NetReq.METHOD_TYPE.POST, params);
        subscribe(Constants.UPLOAD_ITEM, netReq, null, new NetObservableImpl());
    }

    public void getOrderList(String nfcId) {
        Map<String, String> params = new HashMap<>();
        params.put("staff_id", nfcId);
        Log.d(TAG, params.toString());
        NetReq netReq = new NetReq(NetConstants.GET_ORDER_LIST, NetReq.METHOD_TYPE.POST, params);
        subscribe(Constants.GET_ORDER_LIST, netReq, OrderItem.class, new NetObservableImpl());
    }

    public void checkItem(String staffId, String nfcId, String startPoint, String endPoint, String trackId) {
        Map<String, String> params = new HashMap<>();
        params.put("staff_id", staffId);
        params.put("nfc_id", nfcId);
        params.put("current_location", startPoint);
        params.put("target_location", endPoint);
        params.put("track_id", trackId);
        Log.d(TAG, params.toString());
        NetReq netReq = new NetReq(NetConstants.CHECK_ITEM, NetReq.METHOD_TYPE.POST, params);
        subscribe(Constants.CHECK_ITEM, netReq, null, new NetObservableImpl());
    }

    public void confirmItem(String staffId, String nfcId, String startPoint, String trackId) {
        Map<String, String> params = new HashMap<>();
        params.put("staff_id", staffId);
        params.put("nfc_id", nfcId);
        params.put("current_location", startPoint);
        params.put("track_id", trackId);
        NetReq netReq = new NetReq(NetConstants.CONFIRM_ITEM, NetReq.METHOD_TYPE.POST, params);
        subscribe(Constants.CONFIRM_ITEM, netReq, null, new NetObservableImpl());
    }
}
