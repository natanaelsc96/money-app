package br.com.moneyapi.events;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class EventResource extends ApplicationEvent {

    private static final long serialVersionUID = 1L;
    
    private HttpServletResponse response;
    private Long code;
    
    public EventResource(Object source, HttpServletResponse response, Long code) {
        super(source);
        this.response = response;
        this.code = code;
    }
    
    public HttpServletResponse getResponse() { return response; }
    
    public Long getCode() { return code; }
    
}
