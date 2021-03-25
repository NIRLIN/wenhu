package org.wenhu.common.util;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/18
 */
public class TencentSendSms {
    public static String sendSmsUtil(String phoneNumber, String verifyCode) {
        try {

            Credential cred = new Credential("AKIDdLxOgjuDermSs8dveARpz0oPlodDsByY", "VlqhQBhxykMgnfWpslP4kBNhv1B8rOfB");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            SmsClient client = new SmsClient(cred, "", clientProfile);

            SendSmsRequest req = new SendSmsRequest();
            String[] phoneNumberSet1 = {"+86" + phoneNumber + ""};
            req.setPhoneNumberSet(phoneNumberSet1);

            req.setTemplateID("896631");
            req.setSign("闻乎知识记录");

            String[] templateParamSet1 = {verifyCode};
            req.setTemplateParamSet(templateParamSet1);

            req.setSmsSdkAppid("1400497877");

            SendSmsResponse resp = client.SendSms(req);

            System.out.println(SendSmsResponse.toJsonString(resp));
            return SendSmsResponse.toJsonString(resp);

        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
        return "succeed";
    }

    public static void main(String[] args) {
        TencentSendSms.sendSmsUtil("18798189064", "123446");
    }
}
