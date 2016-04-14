package com.derek.assetscontrol.activity;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;
import java.io.UnsupportedEncodingException;

/**
 * Created by derek on 15/12/9.
 */
public class ReadNFCActivity extends NFCActivity {
    private final static String TAG = "ReadNFCActivity";

    private String nfcId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void handleNFCIntent(Intent intent) {
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())
            || NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction())) {
            if (processIntent(intent).isEmpty()) {
                isEmptyTag();
            } else {
                //getItemInfo();
                Intent data = new Intent();
                data.putExtra("nfcId", nfcId);
                setResult(100, data);
                finish();
            }
        } else {
            isEmptyTag();
        }
    }

    /**
     * Parses the NDEF Message from the intent
     */
    public String processIntent(Intent intent) {
        // 取出封装在intent中的TAG
        Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

        nfcId = bytesToHexString(tagFromIntent.getId()).toUpperCase();
        String metaInfo = "";
        metaInfo += "卡片ID：" + nfcId + "\n";
        Toast.makeText(this, "找到卡片", Toast.LENGTH_SHORT).show();

        // Tech List
        String prefix = "android.nfc.tech.";
        String[] techList = tagFromIntent.getTechList();

        //分析NFC卡的类型： Mifare Classic/UltraLight Info
        String CardType = "";
        for (int i = 0; i < techList.length; i++) {
            if (techList[i].equals(NfcA.class.getName())) {
                // 读取TAG
                NfcA mfc = NfcA.get(tagFromIntent);
                try {
                    if ("".equals(CardType)) CardType = "MifareClassic卡片类型 \n 不支持NDEF消息 \n";
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (techList[i].equals(MifareUltralight.class.getName())) {
                MifareUltralight mifareUlTag = MifareUltralight.get(tagFromIntent);
                String lightType = "";
                // Type Info
                switch (mifareUlTag.getType()) {
                    case MifareUltralight.TYPE_ULTRALIGHT:
                        lightType = "Ultralight";
                        break;
                    case MifareUltralight.TYPE_ULTRALIGHT_C:
                        lightType = "Ultralight C";
                        break;
                }
                CardType = lightType + "卡片类型\n";

                Ndef ndef = Ndef.get(tagFromIntent);
                CardType += "最大数据尺寸:" + ndef.getMaxSize() + "\n";
            }
        }
        metaInfo += CardType;
        Log.d(TAG, metaInfo);
        return nfcId;
    }

    private String readTag(Intent intent) {
        Parcelable[] rawArray = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        if (rawArray != null) {
            NdefMessage mNdefMsg = (NdefMessage) rawArray[0];
            if (mNdefMsg != null) {
                NdefRecord mNdefRecord = mNdefMsg.getRecords()[0];
                try {
                    if (mNdefRecord != null) {
                        nfcId = new String(mNdefRecord.getPayload(), "UTF-8").substring(3);
                    }
                } catch (UnsupportedEncodingException e) {
                    Log.i(TAG, e.getMessage());
                }
            }
        }
        return nfcId;
    }

    private void isEmptyTag() {
        tvStatus.setText("This is a empty tag");
    }
}
