package com.note.mina.server;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>消息处理服务器 <p>
 *
 * @author note
 * @version 1.0.0
 * @date 2016/9/19.
 */
public class MessageServerHandler extends IoHandlerAdapter{

    private final static Logger log = LoggerFactory.getLogger(MessageServerHandler.class);

    /**
     * session创建时调用
     * @param session
     * @throws Exception
     */
    @Override
    public void sessionCreated(IoSession session) throws Exception {
        log.info("session is created");
    }

    /**
     * session打开时掉用
     * @param session
     * @throws Exception
     */
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        log.info("session is opend");
    }

    /**
     * session关闭时调用
     * @param session
     * @throws Exception
     */
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        log.info("session is closed");
    }

    /**
     * 消息接收
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {

        String messageStr = message.toString();
        log.info("The message reveived is [{}]",messageStr);
        session.write("your message is sended,thinks for your message.");
        if(messageStr.endsWith("quit")){
            session.closeNow();
            return ;
        }


    }

    /**
     * 消息发送
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
        log.info("message is sent {}",message.toString());
    }

    /**
     * session异常调用
     * @param session
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
        log.error("exception is happened",cause);
    }

    /**
     * session达到规定空闲时间调用
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        super.sessionIdle(session, status);
        log.info("session is idle");
    }

    /**
     *
     * @param session
     * @throws Exception
     */
    @Override
    public void inputClosed(IoSession session) throws Exception {
        super.inputClosed(session);
        log.info("client is closeed");
    }
}
