package com.note.mina.client;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>消息处理客户端 <p>
 *
 * @author note
 * @version 1.0.0
 * @date 2016/9/19.
 */
public class MessageClientHandler extends IoHandlerAdapter {

    private final static Logger log = LoggerFactory.getLogger(MessageClientHandler.class);

    private final String values;


    public MessageClientHandler(String values) {
        this.values = values;
    }


    @Override
    public void sessionCreated(IoSession session) throws Exception {
        log.info("client : session is created");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        log.info("client : session is opened");
        session.write(values);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        log.info("client : session is closed");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        log.info("client : session is idle");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        log.info("client : exception is happened",cause);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        log.info("client : message is received: [{}]",message.toString());
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        log.info("client : message is sent: [{}]",message.toString());
    }

    @Override
    public void inputClosed(IoSession session) throws Exception {
        log.info("client : client is created");
    }
}
