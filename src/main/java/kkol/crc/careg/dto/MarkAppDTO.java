package kkol.crc.careg.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MarkAppDTO {
    public List<String> markList;
}
