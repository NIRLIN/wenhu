//package org.wenhu.common.util;
//
//import com.aliyuncs.CommonRequest;
//import com.aliyuncs.CommonResponse;
//import com.aliyuncs.DefaultAcsClient;
//import com.aliyuncs.IAcsClient;
//import com.aliyuncs.exceptions.ClientException;
//import com.aliyuncs.http.MethodType;
//import com.aliyuncs.profile.DefaultProfile;
//
///**
// * @author NIRLIN
// * @version 1.0
// * @date 2021/3/13
// */
//public class AliyunSMS {
//    public static void main(String[] args) {
//        try {
//            System.out.println(sendSmsUtil("18798189064"));
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static String sendSmsUtil(String phone) throws ClientException {
//        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FdK7L9uMCj8NCGXZ2tY179", "stmK3DkKzUP16zfhD8B6G4IEKLqEnZ179");
//        IAcsClient client = new DefaultAcsClient(profile);
//        CommonRequest request = new CommonRequest();
//        request.setMethod(MethodType.POST);//注意这四行，与官网所展示的方法不同
//        request.setDomain("dysmsapi.aliyuncs.com");
//        request.setVersion("2017-05-25");
//        request.setAction("SendSms");
//        request.putQueryParameter("RegionId", "cn-hangzhou");
//        request.putQueryParameter("PhoneNumbers", phone);
//        request.putQueryParameter("SignName", "时装邮购系统");
//        request.putQueryParameter("TemplateCode", "SMS_179215578");
//        request.putQueryParameter("TemplateParam", "{\"code\": 123456 }");
//        try {
//            CommonResponse response = client.getCommonResponse(request);
//            System.out.println(response.getData());
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
//        return "ok";
//    }
//
//}
