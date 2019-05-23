package org.greece.mythology.tartarus.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class LocalPageRequest {

    private int pageNumber;
    private int pageSize;

    public int getPageNumber() {
        return pageNumber - 1;
    }
}
