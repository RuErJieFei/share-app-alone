package com.yy.content.domain.dto;

import com.yy.content.domain.enumeration.AuditStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShareAuditDto {
    private Integer id;
    private AuditStatusEnum auditStatusEnum;
    private String reason;
    private Boolean showFlag;
}
