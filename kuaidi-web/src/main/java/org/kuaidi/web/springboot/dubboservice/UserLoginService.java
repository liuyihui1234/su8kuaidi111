package org.kuaidi.web.springboot.dubboservice;

import java.util.Date;
import java.util.List;
import com.aliyuncs.exceptions.ClientException;
import org.apache.commons.lang.StringUtils;
import org.kuaidi.bean.Config;
import org.kuaidi.bean.domain.*;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.BankPayInfoService;
import org.kuaidi.iservice.IDictionaryService;
import org.kuaidi.iservice.IEforcesIncmentService;
import org.kuaidi.iservice.IEforcesPaydedaiService;
import org.kuaidi.iservice.NetSignInfo;
import org.kuaidi.iservice.UserService;
import org.kuaidi.utils.Md5Util;
import org.kuaidi.utils.SendPhoneCode;
import org.kuaidi.utils.SubStrUtil;
import org.kuaidi.utils.UUIDUtil;
import org.kuaidi.web.springboot.util.AliyunOSS.AppReplaceOSSUtil;
import org.kuaidi.web.springboot.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Reference;
import net.sf.json.JSONObject;


@Component
public class UserLoginService {
	//验证码
	SendPhoneCode phoneCode = new SendPhoneCode();

	@Reference(version = "1.0.0")
	private UserService userService;

	@Reference(version = "1.0.0")
	private NetSignInfo netSignService;

	@Reference(version = "1.0.0")
	private BankPayInfoService bankPayService;

	@Reference(version = "1.0.0")
	private IEforcesPaydedaiService payDetailService;

	@Reference(version = "1.0.0")
	private IDictionaryService inetsignService;
	
	@Reference(version = "1.0.0")
	private IEforcesIncmentService  incentService; 
	
	@Autowired
	private RedisUtil redisUtil;

	public ResultVo UserdoLogin(EforcesUser user ,Integer type) {
		try {
			String password = Md5Util.encode(user.getPassword()); //把客服端的密码传过来加密
			EforcesUser user1 = userService.selectUser(user.getNumber());
			if (user1 == null) {
				List<EforcesUser> userList = userService.selectUserByPhone(user.getNumber());
				if (userList != null && userList.size() > 0) {
					user1 = userList.get(0);
				}
			}
			if (user1 == null) {
				return ResultUtil.exec(false, "账户不存在！", null);
			} else {
				if (user1.getPassword() != null && StringUtils.equals(user1.getPassword(), password)) { //判断密码不等于空 并且库里密码与加密后一致
					String token = UUIDUtil.getUUID();
					user1.setToken(token);
					JSONObject userInfo = JSONObject.fromObject(user1);
					EforcesIncment incment =  incentService.selectByNumber(user1.getIncnumber());
					if((type != null && type == 2) && (incment != null && incment.getLevel() != 4) ) {
						return ResultUtil.exec(false, "不是快递员账号，请确定！", null);
					}
					JSONObject data = new JSONObject();
					data.put("userInfo", userInfo);
					JSONObject incInfo = JSONObject.fromObject(incment);
					data.put("incInfo", incInfo);
					redisUtil.set(Config.REDISAPPLOGINPREX +  token, data.toString(), 20*60);
					return ResultUtil.exec(true, "登录成功！", user1);
				} else {
					return ResultUtil.exec(false, "密码错误！", null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "登录异常！", null);
		}
	}

	/**
	 * 根据手机号获取验证码登录
	 * @param mobile
	 * @return
	 */
	public ResultVo smsCodeLogin(String mobile) throws ClientException {

		String s = redisUtil.get(Config.redisPhonelimt + mobile);
		if(StringUtils.isNotEmpty(s)){
			return ResultUtil.exec(false,"验证码一分钟之内只能发一次",null);
		}
		List<EforcesUser> listCDate = userService.selectUserByPhone(mobile);
		if(listCDate  != null && listCDate.size()!=0 || !listCDate.isEmpty()){
				String verifyCode = phoneCode.sendCode(mobile);
				redisUtil.set(Config.redisPhonePrex+mobile,verifyCode,300);
				redisUtil.set(Config.redisPhonelimt+mobile,1,60);
				return ResultUtil.exec(true,"登录成功",verifyCode);
		}
		return ResultUtil.exec(false,"登录失败手机号或验证码有误",null);
	}

	/***
	 * @param netSignId
	 * @param userName
	 * @param password
	 * @return
	 */
	public ResultVo addUserInfo(Integer netSignId, String userName, String password) {

		try {
			EforcesNetsign netSignInfo = netSignService.getNetSignById(netSignId);
			if (netSignInfo == null) {
				return ResultUtil.exec(false, "网签信息不存在！", null);
			}
			EforcesUser userInfo = userService.selectUser(userName);
			if (userInfo != null) {
				return ResultUtil.exec(false, "账号已经存在！", null);
			}
			userInfo = new EforcesUser();
			userInfo.setNumber(userName);
			password = Md5Util.encode(password);
			userInfo.setPassword(password);
			Integer userId = userService.insertUserInfo(userInfo);
			if (userId == 0) {
				return ResultUtil.exec(false, "保存用户信息失败！", null);
			}
			netSignInfo.setUserid(userId);
			netSignService.updateNetSignInfo(netSignInfo);
			return ResultUtil.exec(true, "保存用户信息成功！", null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "保存用户信息失败！", null);
		}
	}

	/***
	 * @param
	 * @param userName
	 * @param password
	 * @return
	 */
	public ResultVo updateUserInfo(Integer userId, String userName, String password) {
		try {
			EforcesUser userInfo = userService.selectUserById(userId);
			userInfo.setNumber(userName);
			password = Md5Util.encode(password);
			userInfo.setPassword(password);
			if (userInfo == null) {
				return ResultUtil.exec(false, "参数错误，用户信息不存在！", null);
			}
			Integer rst = userService.updateUserInfo(userInfo);
			if (rst > 0) {
				return ResultUtil.exec(true, "更新用户信息成功！", null);
			} else {
				return ResultUtil.exec(false, "更新用户信息失败！", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "更新用户信息失败！", null);
		}
	}

	/*
	 * 在选择省市县之前，保存用户的信息。
	 */
	public ResultVo addUserInfo(String realName, String phoneNum, String address) {
		try {
			/*
			 * 先查询对应的用户手机号是否已经注册过了。
			 */
			List<EforcesUser> list = userService.selectUserByPhone(phoneNum);
			if (list == null || list.size() == 0) {
				EforcesUser userInfo = new EforcesUser();
				userInfo.setName(realName);
				userInfo.setMobile(phoneNum);
				userInfo.setAddress(address);
				userInfo.setCreattime(new Date());
				Integer userId = userService.insertUserInfo(userInfo);
				JSONObject data = new JSONObject();
				data.put("userId", userId + "");
				data.put("payMoney", 0 + "");
				data.put("netSignId", "0");
				if (userId == 0) {
					return ResultUtil.exec(false, "保存用户信息失败！", null);
				}
				return ResultUtil.exec(true, "保存用户信息成功！", data);
				//
			} else {
				// 判断是否有网签记录。
				EforcesUser userInfo = list.get(0);
				JSONObject data = new JSONObject();
				data.put("userId", userInfo.getId() + "");
				data.put("payMoney", 0 + "");
				data.put("netSignId", "0");
				if (userInfo != null && userInfo.getId() > 0) {
					List<EforcesNetsign> netSignList =
							netSignService.getNetSignByUserId(userInfo.getId());
					if (netSignList != null && netSignList.size() > 0) {
						EforcesNetsign netSignInfo = netSignList.get(0);
						if (netSignInfo != null) {
							data.put("netSignId", netSignInfo.getId() + "");
						}
						if (netSignInfo != null && netSignInfo.getStatus() == 1) {
							ResultVo resultVo = new ResultVo();
							resultVo.setCode(4);
							resultVo.setMsg("账户已经开通！");
							resultVo.setData(data);
							return resultVo;
						} else if (netSignInfo != null && netSignInfo.getStatus() == 0) {
							//查找是否支付成功或者上传支付图片。
							List<EforcesBankPayInfo> bankPayList = bankPayService.selectBankPayInfoByNetSignId(netSignInfo.getId());
							if (bankPayList != null && bankPayList.size() > 0) {
								ResultVo resultVo = new ResultVo();
								resultVo.setCode(3);
								resultVo.setData(data);
								resultVo.setMsg("已经支付成功！");
								return resultVo;
							}
							EforcesIncDefaultPrice defaultPrice = getNetSignPayMoney(netSignInfo);
							if (defaultPrice != null) {
								data.put("payMoney", defaultPrice.getJoinprice() + "");
							}
							EforcesPaydetai record = new EforcesPaydetai();
							record.setStatus(2);
							record.setNetsignid(netSignInfo.getId());
							List<EforcesPaydetai> payDetailList = payDetailService.selectBySort(record);
							if (payDetailList != null && payDetailList.size() > 0) {
								ResultVo resultVo = new ResultVo();
								resultVo.setCode(3);
								resultVo.setMsg("已经支付成功！");
								resultVo.setData(data);
								return resultVo;
							}
							ResultVo resultVo = new ResultVo();
							resultVo.setCode(2);
							resultVo.setData(data);
							resultVo.setMsg("注册完成，尚未支付！");
							return resultVo;
						}
					} else {
						userInfo.setName(realName);
						userInfo.setAddress(address);
						userService.updateUserInfo(userInfo);
						return ResultUtil.exec(true, "保存用户信息成功！", data);
					}
				}
			}
			return ResultUtil.exec(false, "保存用户信息失败！", null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "保存用户信息失败！", null);
		}
	}

	private EforcesIncDefaultPrice getNetSignPayMoney(EforcesNetsign netSignInfo) {
		Integer priceId = 0;
		if (StringUtils.isNotEmpty(netSignInfo.getNetwork())) {
			priceId = 4;
		}
		if (priceId == 0 && StringUtils.isNotEmpty(netSignInfo.getCounty())) {
			priceId = 3;
		}
		if (priceId == 0 && StringUtils.isNotEmpty(netSignInfo.getCity())) {
			priceId = 2;
		}
		if (priceId == 0 && StringUtils.isNotEmpty(netSignInfo.getProvince())) {
			priceId = 1;
		}
		EforcesIncDefaultPrice defaultPrice = inetsignService.getDefaultPriceById(priceId);
		return defaultPrice;
	}

	/*
	 * 在选择省市县之前，保存用户的信息。
	 */
	public ResultVo crtUserNUmber(Integer userId) {
		if (userId == null || userId < 0) {
			return ResultUtil.exec(false, "参数错误！", null);
		}
		try {
			EforcesUser userInfo = userService.selectUserById(userId);
			if (userInfo == null) {
				return ResultUtil.exec(false, "参数错误！", null);
			}
			String incId = userInfo.getIncid();
			String nextUserNum = "";
			if (incId != null && incId.length() > 0) {
				String currentNum = userService.getNextNumber(incId);
				if (currentNum == null || currentNum.length() < incId.length()) {
					nextUserNum = incId + "001";
				} else {
					nextUserNum = SubStrUtil.getSubStrNext(currentNum, incId, 3);
					nextUserNum = incId + nextUserNum;
				}
			}
			//根据incId
			
			EforcesIncment incment =  incentService.selectByNumber(incId);
			
			String netName = incment.getMnemonic() == null ? "" : incment.getMnemonic().trim();
			
			JSONObject data = new JSONObject();
			data.put("userNum", nextUserNum);
			data.put("netName", netName);
			if (userId == 0) {
				return ResultUtil.exec(false, "保存用户信息失败！", null);
			}
			return ResultUtil.exec(true, "保存用户信息成功！", data);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "保存用户信息失败！", null);
		}
	}

	/**
	 * 开通小号
	 * @param userId
	 * @param mobile
	 * @param password
	 * @return
	 */
	public ResultVo openTrumpet(Integer userId, String mobile, String password,String smsCode) {
		//先判断验证码是否正确。
		//从Reids取出key如果为空则没有
		String redisPhone = redisUtil.get(Config.redisPhonePrex+mobile);
		if(redisPhone==null ||redisPhone.length()==0){
			return ResultUtil.exec(false,"输入的手机号或验证码有误！",null);
		}

		if (userId == null || userId < 0) {
			return ResultUtil.exec(false, "参数错误", null);
		}
		try {
			EforcesUser userData = userService.selectUserById(userId);
			if (userData == null) {
				return ResultUtil.exec(false, "参数错误", null);
			}
			String incNumber = userData.getIncnumber();

			String maxUserNum = "";
			if(incNumber.length() >0 && incNumber !=null){
				String createdNumber = userService.getNextNumber(incNumber);
				if(createdNumber == null || createdNumber.length() < incNumber.length()){
					maxUserNum = incNumber + "001";
				} else {
					maxUserNum = SubStrUtil.getSubStrNext(createdNumber,incNumber,3);
					maxUserNum = incNumber + maxUserNum;
				}
			}
			userData.setId(null);
			userData.setMobile(mobile);
			userData.setPassword(Md5Util.encode(password));
			userData.setNumber(maxUserNum);
			userService.insertUserInfo(userData);
			return ResultUtil.exec(true,"添加成功",userData);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "保存小号信息失败！", null);
		}
	}

	/**
	 * 开通小号获取验证码，判断手机号是否已经被注册，如果是则不能再注册、并把验证码和手机号存到Reids里面
	 * @param mobile
	 * @return
	 */
	public int openTrumpetsmsCode(String mobile) throws ClientException {
		//获取短信验证码
		String verifyCode = phoneCode.sendCode(mobile);
		List<EforcesUser> listResult = userService.selectUserByPhone(mobile);
		//如果用户输入的手机号在user表内没数据就说明这个手机号没开通过小号然后 执行业务
		if(listResult.size()==0 || listResult.isEmpty()){
			//将验证码存Redis
			redisUtil.set(Config.redisPhonePrex+mobile,verifyCode,2000);
			return 1 ;
		}
		return 0;
	}

	public ResultVo findUserById(Integer id){
		EforcesUser eforcesUser = userService.selectUserById(id);
		if(eforcesUser!=null){
			return ResultUtil.exec(true,"ok",eforcesUser);
		}
		return ResultUtil.exec(false,"false",null);
	}

	/**
	 * 修改用户名、手机号
	 * @param id 条件
	 * @param Name 用户名
	 * @param Mobile 手机号
	 * @return
	 */
	public ResultVo updateMsg(int id,String Name,String Mobile, String portraitValue){
		try {
			EforcesUser userInfo =   userService.selectUserById(id);
			if(userInfo == null ) {
				return ResultUtil.exec(true,"参数错误，用户不存在！",null);
			}
			if(StringUtils.isNotEmpty(Name)) {
				userInfo.setName(Name);
			}
			if(StringUtils.isNotEmpty(Mobile)) {
				userInfo.setMobile(Mobile);
			}
			if(StringUtils.isNotEmpty(portraitValue)) {
				String portraitPath = AppReplaceOSSUtil.string2Image(portraitValue);
				if(StringUtils.isNotEmpty(portraitPath)) {
					userInfo.setPortraitpath(Config.oosUrlPath + portraitPath);
				}
			}
			int result = userService.updateUserInfo(userInfo);
			return ResultUtil.exec(true,"修改信息成功",userInfo);
		} catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(false,"修改信息失败",null);
		}
	}

	/**
	 * 修改用户名、手机号、身份证号、身份证正反两面
	 * @param user
	 * @return
	 */
	public ResultVo updateUseMsg(EforcesUser user1 ,String name ,String portraitpath,String identitynum,
			String identityfontpath, String identitybackpath){
		try {
			/**
			 * 头像
			 */
			if(StringUtils.isNotEmpty(portraitpath)){
				String portraitPath = AppReplaceOSSUtil.string2Image(portraitpath);
				user1.setPortraitpath(Config.oosUrlPath + portraitPath);
			}
			/**
			 * 身份证正面
			 */
			if(StringUtils.isNotEmpty(identityfontpath)){
				String path = AppReplaceOSSUtil.string2Image(identityfontpath);
				user1.setIdentityfontpath(Config.oosUrlPath+path);
			}
			/**
			 * 身份证反面
			 */
			if(StringUtils.isNotEmpty(identitybackpath)){
				String path = AppReplaceOSSUtil.string2Image(identitybackpath);
				user1.setIdentitybackpath(Config.oosUrlPath+path);
			}
			/**
			 * 用户名
			 */
			if(StringUtils.isNotEmpty(name)){
				user1.setName(name);
			}
			/**
			 * 身份证号
			 */
			if(StringUtils.isNotEmpty(identitynum)){
				user1.setIdentitynum(identitynum);
			}
			userService.updateUserInfo(user1);
			return ResultUtil.exec(true,"修改信息成功",user1);
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(false,"修改信息失败",null);
		}
	}
}