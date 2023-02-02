package com.ssafy.youandi.dto.response;

import com.ssafy.youandi.entity.mypageinfo.Record;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class RecordResponseDto {
    private List<Record> recordList;

}
