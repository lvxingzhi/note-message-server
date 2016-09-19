package com.note.mina.server;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * <p>程序启动入口 <p>
 *
 * @author note
 * @version 1.0.0
 * @date 2016/9/19.
 */
public class MainAccess {

    /**
     * Main方法
     * @param args
     */
    public static void main(String[] args) throws IOException {

        // 创建套接字接收器
        IoAcceptor acceptor = new NioSocketAcceptor();

        // 设置每次读取字节数
        acceptor.getSessionConfig().setReadBufferSize(2048);

        // 设置规定空闲时间
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,10);

        // 添加过滤器-字符串解析
        acceptor.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
                LineDelimiter.WINDOWS.getValue(), LineDelimiter.WINDOWS.getValue())));

        IoHandler ioHandler = new MessageServerHandler();

        // 注册消息处理器
        acceptor.setHandler(ioHandler);

        // 绑定监听端口
        acceptor.bind(new InetSocketAddress(9123));



    }
}
