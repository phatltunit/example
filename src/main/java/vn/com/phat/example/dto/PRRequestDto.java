package vn.com.phat.example.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PRRequestDto {
    private List<PRRequestBiDataDto> biData;
    private String prNo;

}
