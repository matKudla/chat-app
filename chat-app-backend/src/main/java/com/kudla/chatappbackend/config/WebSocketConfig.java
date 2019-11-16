package com.kudla.chatappbackend.config;

import com.kudla.chatappbackend.user.service.AppUserDetailsService;
import com.kudla.chatappbackend.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.Optional;

@Configuration
@EnableWebSocketMessageBroker
@AllArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private static final String TOKEN = "token";
    private final AppUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;


    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {

                String username;
                String jwt;

                Optional<StompHeaderAccessor> accessor =
                        Optional.ofNullable(MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class));

                if (accessor.isPresent() && StompCommand.CONNECT.equals(accessor.get().getCommand())) {
                    jwt = accessor.get().getFirstNativeHeader(TOKEN);
                    username = jwtUtil.extractUsername(jwt);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    jwtUtil.validateToken(jwt, userDetails);
                    Authentication authentication
                            = new UsernamePasswordAuthenticationToken(username, null, null);
                    accessor.get().setUser(authentication);
                }
                return message;
            }
        });
    }


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }


}
