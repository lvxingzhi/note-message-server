package com.note.mina.server;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Date;

/**
 * Created by Administrator on 2016/9/14.
 */
public class TimeServer {

    static Logger logger = LoggerFactory.getLogger(TimeServer.class);

    public static void main(String[] args) {
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.setHandler(new IoHandler(){
            @Override
            public void sessionCreated(IoSession ioSession) throws Exception {
                //session创建
            }

            @Override
            public void sessionOpened(IoSession ioSession) throws Exception {
                //session打开
                ioSession.write(new Date());
            }

            @Override
            public void sessionClosed(IoSession ioSession) throws Exception {
                //session关闭
            }

            @Override
            public void sessionIdle(IoSession ioSession, IdleStatus idleStatus) throws Exception {

            }

            @Override
            public void exceptionCaught(IoSession ioSession, Throwable throwable) throws Exception {

            }

            @Override
            public void messageReceived(IoSession ioSession, Object o) throws Exception {

            }

            @Override
            public void messageSent(IoSession ioSession, Object o) throws Exception {
                ioSession.closeOnFlush();
            }

            @Override
            public void inputClosed(IoSession ioSession) throws Exception {

            }

        });
        acceptor.getFilterChain().addLast("logging",new LoggingFilter());
        acceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new TextLineCodecFactory()));



        try{
            acceptor.bind(new InetSocketAddress(8150));
        }catch (final IOException e){
            logger.error("Bind error",e);
        }




    }
}
