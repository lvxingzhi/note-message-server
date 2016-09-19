package com.note.mina.client;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Scanner;

/**
 * <p>客户端入口 <p>
 *
 * @author note
 * @version 1.0.0
 * @date 2016/9/19.
 */
public class ClientMain {

    /**
     * Main方法
     * @param args
     */
    public static void main(String[] args) {

        // 创建套接字发送器
        IoConnector connector = new NioSocketConnector();

        // 设置连接超时时间
        connector.setConnectTimeoutMillis(30000);

        // 设置过滤器
        connector.getFilterChain().addLast("codec",new ProtocolCodecFilter(new TextLineCodecFactory(
                Charset.forName("UTF-8"),
                LineDelimiter.WINDOWS.getValue(),
                LineDelimiter.WINDOWS.getValue()
        )));

        connector.setHandler(new MessageClientHandler("你好 \r\n 123"));

        connector.connect(new InetSocketAddress("127.0.0.1",9123));
Scanner sanner = new Scanner(System.in);
        String consoleIn = sanner.nextLine();

        Map<Long,IoSession> ioSessions = connector.getManagedSessions();
        System.out.println( ioSessions);
        System.out.println( ioSessions.toString());
        boolean  b =  ioSessions.containsKey(0);
        System.out.println(b);

    }
}
