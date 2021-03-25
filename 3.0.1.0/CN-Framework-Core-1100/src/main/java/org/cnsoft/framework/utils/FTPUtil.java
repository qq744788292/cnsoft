package org.cnsoft.framework.utils;

import org.cnsoft.framework.constants.ICFrameworkConstants;

/**
 * FTP工具
 *
 * @author CNSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class FTPUtil implements ICFrameworkConstants {
	// protected Logger logger = LoggerFactory.getLogger(this.getClass());
	//
	// /**
	// * 默认图片
	// */
	// private String defaultPic = "";
	//
	// /**
	// * 水印图片
	// */
	// private Resource markPic = null;
	//
	// public String getDefaultPic() {
	// return defaultPic;
	// }
	//
	// public void setDefaultPic(String defaultPic) {
	// this.defaultPic = defaultPic;
	// }
	//
	// public Resource getMarkPic() {
	// return markPic;
	// }
	//
	// public void setMarkPic(Resource markPic) {
	// this.markPic = markPic;
	// }
	//
	// /**
	// * 文件上传
	// *
	// * @param file
	// * @return 图片识别路径
	// * @throws Exception
	// */
	// public String uploadFile(MultipartFile file, String type) throws Exception {
	// String[] filePath = FilePathHelper.makeFilePath(file.getOriginalFilename());
	// try {
	// if (uploadFile(filePath[1], filePath[2], file.getInputStream(), type) == true) {
	// // return filePath[0];
	// if (ZERO.equals(type) || TWO.equals(type)) {
	// if (ZERO.equals(type)) {
	// return serverFileUri + filePath[1] + filePath[2];
	// } else {
	// return serverFileUriSecond + filePath[2];
	// }
	// } else {
	// return filePath[1] + filePath[2];
	// }
	// }
	//
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// return "";
	// }
	//
	// /**
	// * 文件上传
	// *
	// * @param file
	// * @return 图片识别路径
	// * @throws Exception
	// */
	// public String uploadFile(File file, String type) throws Exception {
	// String[] filePath = FilePathHelper.makeFilePath(file.getName());
	// FileInputStream fin = null;
	// try {
	// fin = new FileInputStream(file);
	// if (uploadFile(filePath[1], filePath[2], fin, type) == true)
	// return filePath[0];
	// } catch (IOException e) {
	// e.printStackTrace();
	// } finally {
	// if (fin != null)
	// fin.close();
	// }
	// return "";
	// }
	//
	// /**
	// * 文件上传
	// *
	// * @param fileName
	// * @param input
	// * @return 图片识别路径
	// */
	// public String uploadFile(String fileName, InputStream input, String type) throws Exception {
	// String[] filePath = FilePathHelper.makeFilePath(fileName);
	// try {
	// if (uploadFile(filePath[1], fileName, input, type) == true)
	// return filePath[0];
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return "";
	// }
	//
	// /**
	// * 文件上传到指定位置
	// *
	// * @param file
	// * @return 成功与否
	// * @throws Exception
	// */
	// public boolean uploadFile(String path, String filename, InputStream input, String type) throws Exception {
	// if (ZERO.equals(type)) {
	// return uploadFile(serverIp, serverPort, serverUser, serverUserPasswordFirst, path, filename, input);
	// } else if (TWO.equals(type)) {
	// return uploadFile(serverIp, serverPort, serverUserThird, serverUserPasswordThird, "", filename, input);
	// } else {
	// return uploadFile(serverIp, serverPort, serverUserSecond, serverUserPasswordSecond, path, filename, input);
	// }
	// }
	//
	// /**
	// * Description: 向FTP服务器上传文件
	// *
	// * @param url
	// * FTP服务器hostname
	// * @param port
	// * FTP服务器端口
	// * @param username
	// * FTP登录账号
	// * @param password
	// * FTP登录密码
	// * @param path
	// * FTP服务器保存目录
	// * @param filename
	// * 上传到FTP服务器上的文件名
	// * @param input
	// * 输入流
	// * @return 成功返回true，否则返回falseO
	// * @throws Exception
	// */
	// public boolean uploadFile(String url, int port, String username, String password, String path, String filename, InputStream input) throws Exception {
	// // 初始表示上传失败
	// boolean success = false;
	// // 创建FTPClient对象
	// FTPClient ftp = new FTPClient();
	// try {
	// int reply;
	// // 连接FTP服务器
	// // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
	// ftp.connect(url, port);
	// reply = ftp.getReplyCode();
	// // 登录ftp
	// boolean is = ftp.login(username, password);
	// // 看返回的值是不是230，如果是，表示登陆成功
	// if (is == false)
	// return success;
	// // System.out.println(is);
	// reply = ftp.getReplyCode();
	// // 以2开头的返回值就会为真
	// if (!FTPReply.isPositiveCompletion(reply)) {
	// ftp.disconnect();
	// return success;
	// }
	// ftp.enterLocalPassiveMode();
	// ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
	// // 转到指定上传目录
	// ftp.setBufferSize(2 * 1024);
	//
	// for (String p : path.split(BACKSLASH)) {
	// ftp.makeDirectory(p);
	// ftp.changeWorkingDirectory(p);
	// }
	//
	// ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
	// BufferedInputStream bi = new BufferedInputStream(input);
	// ftp.setDataTimeout(5000);
	//
	// // 将上传文件存储到指定目录
	// boolean b = ftp.storeFile(filename, bi);
	//
	// // 关闭输入流
	// input.close();
	// // 退出ftp
	// ftp.logout();
	// // 表示上传成功
	// success = b;
	// } finally {
	// if (ftp.isConnected()) {
	// try {
	// ftp.disconnect();
	// } catch (IOException ioe) {
	//
	// }
	// }
	// }
	// return success;
	// }
	//
	// ////////// 属性定义/////////////
	// /**
	// * 文件访问前缀地址
	// */
	// // private String fileUri = "F:\\用户目录\\我的文档";
	// // private String fileUri = "//home//uftp//upload//";
	// // private String fileUri = "//data//www//upload//";
	// private String fileUri;
	// /**
	// * FTP服务器IP
	// */
	// // private String serverIp = "127.0.0.1";
	// // private String serverIp = "112.124.111.77";
	// private String serverIp;
	// /**
	// * FTP服务器端口
	// */
	// // private int serverPort = 21;
	// private int serverPort;
	// /**
	// * FTP服务器用户名
	// */
	// // private String serverUser = "user";
	// // private String serverUser = "qftp";
	// // private String serverUser = "imager";
	// private String serverUser;
	// /**
	// * FTP服务器第一个密码
	// */
	// // private String serverUserPassword = "123456";
	// // private String serverUserPassword = "qftp";
	// // private String serverUserPassword = "mkq87298233";
	// private String serverUserPasswordFirst;
	// /**
	// * FTP服务器第二个用户名
	// */
	// private String serverUserSecond;
	// /**
	// * FTP服务器第二个密码
	// */
	// private String serverUserPasswordSecond;
	// /**
	// * FTP服务器第三个用户名
	// */
	// private String serverUserThird;
	// /**
	// * FTP服务器第三个密码
	// */
	// private String serverUserPasswordThird;
	// /**
	// * FTP服务器网址
	// */
	// private String serverFileUri;
	// /**
	// * FTP服务器第二个网址
	// */
	// private String serverFileUriSecond;
	//
	// public String getServerUserPasswordFirst() {
	// return serverUserPasswordFirst;
	// }
	//
	// public void setServerUserPasswordFirst(String serverUserPasswordFirst) {
	// this.serverUserPasswordFirst = serverUserPasswordFirst;
	// }
	//
	// public String getServerUserSecond() {
	// return serverUserSecond;
	// }
	//
	// public void setServerUserSecond(String serverUserSecond) {
	// this.serverUserSecond = serverUserSecond;
	// }
	//
	// public String getServerUserPasswordSecond() {
	// return serverUserPasswordSecond;
	// }
	//
	// public void setServerUserPasswordSecond(String serverUserPasswordSecond) {
	// this.serverUserPasswordSecond = serverUserPasswordSecond;
	// }
	//
	// public String getServerFileUri() {
	// return serverFileUri;
	// }
	//
	// public void setServerFileUri(String serverFileUri) {
	// this.serverFileUri = serverFileUri;
	// }
	//
	// public String getFileUri() {
	// return fileUri;
	// }
	//
	// public String getFileUri(String fileId) {
	// return fileUri + FilePathHelper.getFilePath(fileId, false)[0];
	// }
	//
	// public void setFileUri(String fileUri) {
	// this.fileUri = fileUri;
	// }
	//
	// public String getServerIp() {
	// return serverIp;
	// }
	//
	// public void setServerIp(String serverIp) {
	// this.serverIp = serverIp;
	// }
	//
	// public int getServerPort() {
	// return serverPort;
	// }
	//
	// public void setServerPort(int serverPort) {
	// this.serverPort = serverPort;
	// }
	//
	// public String getServerUser() {
	// return serverUser;
	// }
	//
	// public void setServerUser(String serverUser) {
	// this.serverUser = serverUser;
	// }
	//
	// public String getServerUserThird() {
	// return serverUserThird;
	// }
	//
	// public void setServerUserThird(String serverUserThird) {
	// this.serverUserThird = serverUserThird;
	// }
	//
	// public String getServerUserPasswordThird() {
	// return serverUserPasswordThird;
	// }
	//
	// public void setServerUserPasswordThird(String serverUserPasswordThird) {
	// this.serverUserPasswordThird = serverUserPasswordThird;
	// }
	//
	// public String getServerFileUriSecond() {
	// return serverFileUriSecond;
	// }
	//
	// public void setServerFileUriSecond(String serverFileUriSecond) {
	// this.serverFileUriSecond = serverFileUriSecond;
	// }

}
