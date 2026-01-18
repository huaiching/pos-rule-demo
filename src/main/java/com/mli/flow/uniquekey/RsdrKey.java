package com.mli.flow.uniquekey;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RsdrKey implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(description = "客戶證號")
    private String clientId;

    @Schema(description = "地址指示")
    private String addrInd;

    public RsdrKey() {
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAddrInd() {
        return addrInd;
    }

    public void setAddrInd(String addrInd) {
        this.addrInd = addrInd;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RsdrKey addrKey = (RsdrKey) o;
        return Objects.equals(clientId, addrKey.clientId) && Objects.equals(addrInd, addrKey.addrInd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, addrInd);
    }
}
