package org.zmsoft.jfp.framework.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
/**
 * FTP读取
 *
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class FileReadHelper {
	public static String readToString(String fileName) {  
        String encoding = "UTF-8";  
        File file = new File(fileName);  
        Long filelength = file.length();  
        byte[] filecontent = new byte[filelength.intValue()];  
        try {  
            FileInputStream in = new FileInputStream(file);  
            in.read(filecontent);  
            in.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        try {  
            return new String(filecontent, encoding);  
        } catch (UnsupportedEncodingException e) {  
            System.err.println("The OS does not support " + encoding);  
            e.printStackTrace();  
            return null;  
        }  
    }  
	
	/** 
     * 以行为单位读取文件，常用于读面向行的格式化文件 
     */  
    public static List<String> readToLines(String fileName) {  
        File file = new File(fileName);  
        BufferedReader reader = null;  
        ArrayList<String> datas = new  ArrayList<String>();
        try {  
            reader = new BufferedReader(new FileReader(file));  
            String tempString = null;  
            int line = 1;  
            // 一次读入一行，直到读入null为文件结束  
            while ((tempString = reader.readLine()) != null) {  
            	datas.add(tempString);
                // 显示行号  
                System.out.println("line " + line + ": " + tempString);  
                line++;  
            }  
            reader.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (reader != null) {  
                try {  
                    reader.close();  
                } catch (IOException e1) {  
                }  
            }  
        } 
        return datas;
    }  
    
    /** 
     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。 
     */  
    public static Integer[] readFileByBytes(String fileName) {  
        File file = new File(fileName);  
        InputStream in = null;
        ArrayList<Integer> datas = new  ArrayList<Integer>();  
        try {  
            System.out.println("以字节为单位读取文件内容，一次读一个字节：");  
            // 一次读一个字节  
            in = new FileInputStream(file);  
            int tempByte;  //0~255
            while ((tempByte = in.read()) != -1) { 
            	datas.add(tempByte);
            }  
            in.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        Integer[] ret = new Integer[datas.size()];;
        ret = datas.toArray(ret);
        return ret;
    }  
  
}
