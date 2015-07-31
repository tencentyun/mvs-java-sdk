# tencentyun-video-java-sdk
java sdk for [腾讯云微视频服务]

## 安装（直接下载源码集成）

### 直接下载源码集成
从github下载源码装入到您的程序中
调用请参考示例

## 修改配置
修改Demo.java内的appid等信息为您的配置

## 上传、查询、删除程序示例
```java
package com.qcloud.video;
import com.qcloud.video.api.*;

public class Demo {
	//通过控制台获取AppId,SecretId,SecretKey
	public static final int APP_ID = 1000000;
	public static final String SECRET_ID = "SECRET_ID";
	public static final String SECRET_KEY = "SECRET_KEY";
	
	public static void main(String[] args) {
		VideoCloud video = new VideoCloud(APP_ID, SECRET_ID, SECRET_KEY);
		try{			
			String result = "";
			String bucketName = "abcde";
            long start = System.currentTimeMillis();
			//创建目录
			result = video.createFolder(bucketName, "/sdk/","");
			System.out.println("=======createFolder========\n"+result);
			//上传视频
			result = video.uploadFile(bucketName, "/sdk/test.mp4", "D:\\test.mp4");
			System.out.println("=======uploadFile========\n"+result);
			//result = video.uploadFile(bucketName, "/sdk/test.mp4", "D:\\test.mp4", "test file attr", "test title","test desc");
			//更新视频属性、标题、描述等信息
			result = video.updateFile(bucketName, "/sdk/test.mp4", "test file attr", "test title","test desc");
			System.out.println("=======updateFile========\n"+result);
			//获取目录下视频文件信息
            result = video.getFolderList(bucketName, "/", 20, "", 0, VideoCloud.FolderPattern.Both);
            System.out.println("=======getFolderList========\n"+result);
            //获取视频信息  
            result = video.getFileStat(bucketName, "/sdk/test.mp4");
            System.out.println("=======getFileStat========\n"+result);
			//更新目录属性
            result = video.updateFolder(bucketName, "/sdk/", "test folder");
            System.out.println("=======updateFolder========\n"+result);
			//获取目录属性
            result = video.getFolderStat(bucketName, "/sdk/");
            System.out.println("=======getFolderStat========\n"+result);
			//删除视频文件
            result = video.deleteFile(bucketName, "/sdk/test.mp4");
            System.out.println("=======deleteFile========\n"+result);
			//删除目录，目录必须为空
            result = video.deleteFolder(bucketName, "/sdk/");
            System.out.println("=======deleteFolder========\n"+result);
			//分片上传文件
            //result = video.sliceUploadFile(bucketName, "/sdk/big.mp4", "F:\\big.mp4");
			//result = video.sliceUploadFile(bucketName, "/sdk/big.mp4", "F:\\big.mp4", "test file attr", "test title","test desc",512 * 1024);
            long end = System.currentTimeMillis();
            System.out.println("总用时：" + (end - start) + "毫秒");
			System.out.println("The End!");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}

```
