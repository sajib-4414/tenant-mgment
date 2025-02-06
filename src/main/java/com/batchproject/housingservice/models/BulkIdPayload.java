package com.batchproject.housingservice.models;

import lombok.Data;

import java.util.List;

@Data
public class BulkIdPayload {
    List<Long> ids;
}
