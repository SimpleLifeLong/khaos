package org.greece.mythology.erebus.uranus;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "URANUS-INTERFACE-NORMAL")
public interface UranusFeignService {

    @GetMapping("/app/user")
    public String getUser();

}
