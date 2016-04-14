package com.derek.basemodule.network;

import com.derek.basemodule.model.NetResp;
import com.derek.basemodule.model.UIResult;

/**
 * Created by derek on 16/2/21.
 */
public class NetUtils {

    public static UIResult netResult2UIResult(NetResp netResp) {
        UIResult uiResult;
        if (netResp == null) {
            uiResult = new UIResult(NetConstants.UI_NET_ERROR);
        } else if (netResp.getRet() == 0) {
            uiResult = new UIResult(NetConstants.UI_NEW_DATA);
        } else if (netResp.getData() == null) {
            uiResult = new UIResult(NetConstants.UI_NO_DATA);
        } else {
            uiResult = new UIResult(NetConstants.UI_MSG);
        }
        return uiResult;
    }
}
