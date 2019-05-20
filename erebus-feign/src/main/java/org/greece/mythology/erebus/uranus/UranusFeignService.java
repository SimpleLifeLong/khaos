package org.greece.mythology.erebus.uranus;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "URANUS-INTERFACE-NORMAL")
public class UranusFeignService {
}
