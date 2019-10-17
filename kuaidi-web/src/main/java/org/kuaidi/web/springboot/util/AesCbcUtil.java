package org.kuaidi.web.springboot.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.management.openmbean.InvalidKeyException;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;

/**
 * <p>User: qrn
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 * ����: ����
 */
public class AesCbcUtil {



    /**
     * AES����
     *
     * @param encryptedData  ���������������ڵ������û���Ϣ�ļ������ݣ�
     * @param key    ��Կ
     * @param iv     �����㷨�ĳ�ʼ������
     * @param encodingFormat ���ܺ�Ľ����Ҫ���еı���
     * @return String
     * @see  Exception
     */
    public static String decrypt(String encryptedData,String key, String iv, String encodingFormat) throws Exception {
//        initialize();

        //�����ܵ�����
        byte[] dataByte = Base64.decodeBase64(encryptedData);
        //������Կ
        byte[] keyByte = Base64.decodeBase64(key);
        //ƫ����
        byte[] ivByte = Base64.decodeBase64(iv);


        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");

            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// ��ʼ��
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, encodingFormat);
                return result;
            }
            return null;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidParameterSpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
