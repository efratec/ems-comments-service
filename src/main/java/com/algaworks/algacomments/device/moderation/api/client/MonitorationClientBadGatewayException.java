package com.algaworks.algacomments.device.moderation.api.client;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class MonitorationClientBadGatewayException extends RuntimeException {

}
