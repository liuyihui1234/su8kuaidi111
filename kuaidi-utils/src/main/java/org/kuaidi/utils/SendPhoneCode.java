package org.kuaidi.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;



public class SendPhoneCode {
	
	
	public String sendCode(String telephone) throws ServerException, ClientException {
		String verCode =ValidateCode.getSMSCode();
		final String product = "Dysmsapi";
		final String domain = "dysmsapi.aliyuncs.com";
		final String accessKeyId = "LTAILDrL2lIxKika";
		final String accessKeySecret = "uKvHgRoe7aiuUuIjpj3MDFIR5JofdO";
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		SendSmsRequest request = new SendSmsRequest();
		request.setMethod(MethodType.POST);
		request.setPhoneNumbers(telephone);//手机号
		request.setSignName("飞之翼");//签名名称
		request.setTemplateCode("SMS_171851426"); //模板code
		request.setTemplateParam("{\"code\":" + verCode + "}");
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

		if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			System.out.println("发送成功~"+sendSmsResponse);
			return verCode;
		} else {
			System.out.println("发送失败~");
			return verCode;
		}
	}

	public static void main(String[] args) throws ClientException {
		SendPhoneCode d = new SendPhoneCode();
		String dada = d.sendCode("13838351484");
		System.out.println(dada);
	}
}