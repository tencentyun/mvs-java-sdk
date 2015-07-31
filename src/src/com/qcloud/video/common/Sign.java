package com.qcloud.video.common;

import java.util.Random;

public class Sign {

    private static String appSignBase(int appId, String secretId, String secretKey, long expired, String fileId, String bucketName) {
        if (secretId.isEmpty() || secretKey.isEmpty()) {
            return "-1";
        }

        long now = System.currentTimeMillis() / 1000;    
        int rdm = Math.abs(new Random().nextInt());
        String plainText = "a=" + appId + "&k=" + secretId + "&e=" + expired + "&t=" + now + "&r=" + rdm + "&f=" + fileId + "&b=" + bucketName;       
        
        byte[] bin = hashHmac(plainText, secretKey);

        byte[] all = new byte[bin.length + plainText.getBytes().length];
        System.arraycopy(bin, 0, all, 0, bin.length);
        System.arraycopy(plainText.getBytes(), 0, all, bin.length, plainText.getBytes().length);
        
        return Base64Util.encode(all);
    }
    
    public static String appSign(int appId, String secretId, String secretKey, long expired, String bucketName) {
    	return appSignBase(appId, secretId, secretKey, expired, "", bucketName);
    }
    
    public static String appSignOnce(int appId, String secretId, String secretKey, String remotePath, String bucketName){
    	String fileId = "/"  + appId + "/" + bucketName + remotePath; 
    	return appSignBase(appId, secretId, secretKey, 0, fileId, bucketName);
    }
    
	private static byte[] hashHmac(String plain_text, String accessKey) {
		
		try {
			return HMACSHA1.getSignature(plain_text, accessKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
    
	public static boolean empty(String s){
		return s == null || s.trim().equals("") || s.trim().equals("null");
	}
		
}
