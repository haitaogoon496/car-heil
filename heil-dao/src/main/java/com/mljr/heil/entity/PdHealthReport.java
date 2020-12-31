package com.mljr.heil.entity;

import com.mljr.heil.base.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class PdHealthReport extends BaseEntity{
    private Integer id;

    private Date reportTime;

    private String reportDetail;
}