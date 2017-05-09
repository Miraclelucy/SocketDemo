package com.lucy.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * @author Administrator
 * 20170430 lushiqin 
 * 基于TCP连接的socket通信(服务端)
 */
public class StringServer {
	//1 创建ServerSocket对象
    public static void main(String[] args) throws IOException {
		ServerSocket socket =new ServerSocket(9100);
		System.out.println("我是服务端，正在等待");
		int count=0;
		while(true){
			Socket s=socket.accept();
			ServerThreadSimple thread=new ServerThreadSimple(s);
			thread.start();
			count++;
			System.out.println("我是第"+count+"位客户");
		}
		
	}
}
