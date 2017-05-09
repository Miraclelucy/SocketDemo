package com.lucy.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import com.lucy.entity.TfFile;
import com.lucy.entity.TfUser;
import com.lucy.util.CommonTransfer;

/**
 * @author Administrator 20170430 lushiqin 基于TCP连接的socket通信(客户端--序列化对象传递信息)
 */
public class Client {
	// 1创建Socket对象
	// 2通过输出流 向服务器端发送数据
	// 3通过输入流 接受服务器端发过来的响应
	// 4关闭相关的资源
	public static void main(String[] args) throws IOException,
			ClassNotFoundException {

		Scanner sc = new Scanner(System.in);
/*
		System.out.println("********用户注册*********");
		System.out.println("输入用户名:");
		String name = sc.next();
		System.out.println("输入密码:");
		String pwd = sc.next();
		// System.out.println(name+"------"+pwd);
		TfUser u = new TfUser();
		u.setName(name);
		u.setPwd(pwd);
		CommonTransfer comt = new CommonTransfer();
		comt.setCmd("register");
		comt.setData(u);

		Socket socket = new Socket("127.0.0.1", 9100);
		ObjectOutputStream out = new ObjectOutputStream(
				socket.getOutputStream());
		ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
		out.writeObject(comt);
		socket.shutdownOutput();
		// 接受来自服务器的响应
		CommonTransfer result = (CommonTransfer) input.readObject();
		String str = result.getResult();
		System.out.println(str);

		System.out.println("********用户登录*********");
		System.out.println("输入用户名:");
		String login_name = sc.next();
		System.out.println("输入密码:");
		String login_pwd = sc.next();
		TfUser u2 = new TfUser();
		u2.setName(login_name);
		u2.setPwd(login_pwd);
		CommonTransfer comt2 = new CommonTransfer();
		comt2.setCmd("login");
		comt2.setData(u2);
		Socket socket2 = new Socket("127.0.0.1", 9100);
		ObjectOutputStream out2 = new ObjectOutputStream(
				socket2.getOutputStream());
		ObjectInputStream input2 = new ObjectInputStream(
				socket2.getInputStream());
		// 通过对象输出流向服务器发送序列化对象
		out2.writeObject(comt2);
		socket2.shutdownOutput();
		// 通过对象输入流接受来自服务器的响应
		CommonTransfer login_result = (CommonTransfer) input2.readObject();
		String login_str = login_result.getResult();
		System.out.println(login_str);
		*/
		
		System.out.println("********文件上传*********");
		System.out.println("输入文件物理路径:");
		String filepath= sc.next();
		File file =new File(filepath);
		FileInputStream fis = new FileInputStream(file); 
		byte[] b=new byte[1024];
		int len;  
        while ((len = fis.read(b)) != -1) {  
        	fis.read(b);  
        }
		TfFile tffile=new TfFile();
		tffile.setFid("1");
		tffile.setFilename(filepath.substring(filepath.lastIndexOf("/")+1));
		System.out.println("------filename----"+filepath.substring(filepath.lastIndexOf("/")+1));
		tffile.setFilebyte(b);
		
		CommonTransfer comt3 = new CommonTransfer();
		comt3.setCmd("uploadFile");
		comt3.setData(tffile);
		Socket socket3 = new Socket("127.0.0.1", 9100);
		ObjectOutputStream out3 = new ObjectOutputStream(
				socket3.getOutputStream());
		ObjectInputStream input3 = new ObjectInputStream(
				socket3.getInputStream());
		// 通过对象输出流向服务器发送序列化对象
		out3.writeObject(comt3);
		socket3.shutdownOutput();
		// 通过对象输入流接受来自服务器的响应
		CommonTransfer file_result = (CommonTransfer) input3.readObject();
		String file_str = file_result.getResult();
		System.out.println(file_str);
		fis.close();
		input3.close();
		out3.close();
		socket3.close();
		/*
		input2.close();
		out2.close();
		socket2.close();
		
		input.close();
		out.close();
		socket.close();
		*/
		

	}
}
