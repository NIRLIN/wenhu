package org.wenhu.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/16
 */

@FeignClient(name = "report")
public interface Report {
}
