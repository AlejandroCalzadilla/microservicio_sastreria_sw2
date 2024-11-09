package com.accounts.utils;

import java.util.List;

public class PaginatedResponse<R> {
    private List<R> items;
    private int total;

    public PaginatedResponse(List<R> items, int total) {
        this.items = items;
        this.total = total;
    }

    public List<R> getItems() {
        return items;
    }

    public int getTotal() {
        return total;
    }
}
