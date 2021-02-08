package com.demo.springrabbitmq.bean;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;


/**
 * @author cui
 */
@Data
@Builder
@ToString
public class DemoMessage implements Serializable {

    private static final long serialVersionUID = 1L;


    private String msgId;
    private String dateTime;
    private String data;
}
