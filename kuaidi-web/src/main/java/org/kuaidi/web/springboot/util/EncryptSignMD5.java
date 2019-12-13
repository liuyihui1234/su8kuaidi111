package org.kuaidi.web.springboot.util;

import org.kuaidi.utils.Md5Util;

public class EncryptSignMD5 {
	
	/// <summary>
    /// 合作商ID
    /// </summary>
    public static String partnerid = "KBSY";

    /// <summary>
    /// 合作秘钥key
    /// </summary>
    public static String secretKey = "QE5voGGFJBUh5EL2";//"85c4f851-5d3f-42d1-9e0f-e7e26c70b7f8";

    /// <summary>
    /// 快递100合作秘钥key
    /// </summary>
    public static String kuaidi100SecretKey = "VPbTy9Vtl2BprtNH";

    /// <summary>
    /// 快8速运合作秘钥key
    /// </summary>
    public static String kuai8SecretKey = "GYGDcwh7LXwDv8FI";
    
    
    public static String GetSignMD5(String str)
    {
    	return Md5Util.encode(str);
    }
    
    
    
}
