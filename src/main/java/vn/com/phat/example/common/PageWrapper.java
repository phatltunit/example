/*
* @author PhatLT
* @date 2025-06-03
* @description Page wrapper for pagination
*/
package vn.com.phat.example.common;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageWrapper<T> {
    private int currentPage;
    private int pageSize;
    private int totalPages;
    private long totalElements;
    private List<T> data;

    public PageWrapper(int currentPage, int pageSize, List<T> data) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.data = data;
    }

    public void setDataAndCount(List<T> data, int count) {
        this.data = data;
        this.totalElements = count;
        this.totalPages = (int) Math.ceil((double) count / pageSize);
    }
}
