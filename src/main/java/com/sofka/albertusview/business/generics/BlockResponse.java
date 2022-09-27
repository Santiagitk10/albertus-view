package com.sofka.albertusview.business.generics;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class BlockResponse {

    private Map<String, Object> data;

    public BlockResponse(Map<String, Object> data) {
        this.data = data;
    }

    public Map<String, Object> getData() {
        return data;
    }
}
