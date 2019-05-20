package org.greece.mythology.erebus.pontus;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "PONTUS-INTERFACE-NORMAL")
public interface PontusFeignService {
}
