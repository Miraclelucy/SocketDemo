package com.lucy.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Administrator
 *  20170430 lushiqin
 *  基于TCP连接的socket通信(客户端--字符串传递信息)
 */
public class StringClient {
    //1创建Socket对象
	//2通过输出流 向服务器端发送数据
	//3通过输入流 接受服务器端发过来的响应
	//4关闭相关的资源
	public static void main(String[] args) throws IOException {
		Socket socket=new Socket("localhost",9100);
		OutputStream out=socket.getOutputStream();
		PrintWriter p= new PrintWriter(out);
		p.write("我是小花，bilibili");
		p.flush();
		socket.shutdownOutput();//关闭输出流
		
		InputStream in=socket.getInputStream();
		byte[] b=new byte[1024];
		int len=-1;
		while((len = in.read())!=-1){
			in.read(b);
		}
		String str=new String(b);
		System.out.println(str);
		
		in.close();
		p.close();
		out.close();
		socket.close();
	}
}
