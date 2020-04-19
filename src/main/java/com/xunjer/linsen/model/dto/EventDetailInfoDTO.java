package com.xunjer.linsen.model.dto;

import com.xunjer.linsen.model.entity.EventInfo;
import com.xunjer.linsen.model.entity.ModuleDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author linsen
 * @date 2020/3/24 15:42
 * @tips 明日复明日 明日何其多
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDetailInfoDTO extends EventInfo {

    private String typeName;

    private String tagName;

    private List<ModuleDetail> detail;

}
