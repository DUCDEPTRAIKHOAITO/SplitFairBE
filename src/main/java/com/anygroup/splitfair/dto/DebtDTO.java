package com.anygroup.splitfair.dto;

import com.anygroup.splitfair.enums.DebtStatus;
import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
public class DebtDTO {
    private UUID id;
    private UUID expenseId;
    private UUID fromUserId;
    private UUID toUserId;
    private BigDecimal amount;
    private DebtStatus status;
    private String groupName;

    private String fromUserName;
    private String fromUserAvatar;
    private String toUserName;
    private String toUserAvatar;

    private String expenseDescription;
    private Instant createdTime;
}