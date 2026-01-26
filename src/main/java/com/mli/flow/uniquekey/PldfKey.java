package com.mli.flow.uniquekey;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PldfKey implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(description = "險種代碼")
    private String planCode;

    @Schema(description = "險種版數")
    private String rateScale;

    public PldfKey() {
    }
}
