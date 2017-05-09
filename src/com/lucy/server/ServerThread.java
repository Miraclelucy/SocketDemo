package com.lucy.server;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import com.lucy.entity.TfFile;
import com.lucy.entity.TfUser;
import com.lucy.util.CommonTransfer;

public class ServerThread  extends Thread{
	Socket s=null;
	private ObjectInputStream inputstream =null;
	private ObjectOutputStream outputstream=null;
	public ServerThread(Socket s){
		this.s=s;
		
	}
	public void run(){
		try {
			inputstream=new ObjectInputStream(s.getInputStream());
			outputstream=new ObjectOutputStream(s.getOutputStream());
			//服务端接受请求，读取对象
			CommonTransfer tran=(CommonTransfer)inputstream.readObject();
			
			tran=execute(tran);
			//发送响应信息
			outputstream.writeObject(tran);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public CommonTransfer execute(CommonTransfer tran){
		if("register".equals(tran.getCmd()))
		{
			TfUser u=(TfUser)tran.getData();
			System.out.println("====收到来自"+u.getName()+"的注册请求======");
			if("admin".equals(u.getName())){
				tran.setFlag(true);
				tran.setResult(u.getName()+"，注册成功！");
			}
			
		}else if("login".equals(tran.getCmd()))
		{
			TfUser u=(TfUser)tran.getData();
			System.out.println("====收到来自"+u.getName()+"的登录请求======");
			if("admin".equals(u.getName())&&"2017".equals(u.getPwd())){
				tran.setFlag(true);
				tran.setResult(u.getName()+"，登录成功！");
			}
			else{
				tran.setFlag(true);
				tran.setResult(u.getName()+"，用户名不允许登录！或者密码错误");
			}
			
		}else if("uploadFile".equals(tran.getCmd()))
		{
			TfFile tffile=(TfFile)tran.getData();
			//创建文件
			File file=new File("d:/"+tffile.getFilename());
			System.out.println("====收到文件==="+tffile.getFilename()+"");
			System.out.println("====文件流==="+tffile.getFilebyte()+"");

			FileOutputStream fos=null;
			BufferedOutputStream  bos=null;
			try {
				 fos = new FileOutputStream(file);  
				 bos = new BufferedOutputStream(fos); // 缓冲输出流
		         bos.write(tffile.getFilebyte());
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			} 
			finally{
				try {
					if(bos!=null)
					{
						bos.close();
					}
					if(fos!=null){
						fos.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
			//将文件存储
			
		}
		return tran;
	}
}
