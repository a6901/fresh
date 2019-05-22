package com.atmyteam.fresh.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.events.SessionCreatedEvent;
import org.springframework.session.events.SessionDeletedEvent;
import org.springframework.session.events.SessionExpiredEvent;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
public class RedisSessionConfig {

    /**
     * Redis内session过期事件监听
     * @param sessionExpiredEvent
     */

    @EventListener
    public void onSessionExpired(SessionExpiredEvent sessionExpiredEvent){
        String sessionId = sessionExpiredEvent.getSessionId();
        System.out.println("sessoion:"+sessionId+"已过期");
    }

    /**
     * Redis内session删除事件监听
     * @param sessionDeletedEvent
     */

    @EventListener
    public void onSessionDeleted(SessionDeletedEvent sessionDeletedEvent){

        String sessionId = sessionDeletedEvent.getSessionId();
        System.out.println("sessoion:"+sessionId+"已删除");

    }

    /**
     * Redis内session保存事件监听
     * @param sessionCreatedEvent
     */

    @EventListener
    public void onSessionCreated(SessionCreatedEvent sessionCreatedEvent){
        String sessionId = sessionCreatedEvent.getSessionId();
        System.out.println("sessoion:"+sessionId+"已创建");
    }
}