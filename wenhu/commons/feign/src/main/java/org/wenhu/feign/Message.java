package org.wenhu.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/16
 */

@Component
@FeignClient(name = "message")
public interface Message {
}
