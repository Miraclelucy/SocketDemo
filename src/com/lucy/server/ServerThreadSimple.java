package com.lucy.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThreadSimple  extends Thread{
	Socket s=null;
	public ServerThreadSimple(Socket s){
		this.s=s;
		
	}
	public void run(){
		
		//2 通过accept对象监听端口，在接受到来自客户端的请求时，处于阻塞状态
		//3 通过输入流接受来自客户端的请求
		//4 通过输出流向客户端发送响应信息
		//5 关闭资源信息
		InputStream in=null;
		OutputStream out=null;
		PrintWriter p=null;
		try {
			in = s.getInputStream();
			byte[] b=new byte[1024];
			int len = -1;
			while((len =in.read(b))!=-1)
			{
				in.read(b);
			}
			String str = new String(b);
			System.out.println("来自"+s.getInetAddress()+"的请求："+ str);
			s.shutdownInput();//关闭输入流
			
			out=s.getOutputStream();
			p=new PrintWriter(out);
			p.write("你好，欢迎来到外太空！");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		finally{
		
		try {
			if (p!=null){
				p.close();
			}
            if(out!=null){
            	out.close();
            }
            if(in!=null){
            	in.close();
            }
            if(s!=null){
            	s.close();
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
			
			
		}
		
		
	}
}
