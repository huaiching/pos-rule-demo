package com.mli.flow.entity;

import com.mli.flow.uniquekey.AddrKey;
import com.mli.flow.uniquekey.ColfKey;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "addr")
@IdClass(AddrKey.class)
@Schema(description = "客戶地址檔")
public class AddrEntity {
    @Id
    @Schema(description = "客戶證號")
    @Column(name = "client_id")
    private String clientId;

    @Id
    @Schema(description = "地址指示")
    @Column(name = "addr_ind")
    private String addrInd;

    @Schema(description = "地址")
    @Column(name = "address")
    private String address;

    @Schema(description = "電話")
    @Column(name = "tel_1")
    private String tel1;

    public AddrEntity() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AddrEntity that = (AddrEntity) o;
        return Objects.equals(clientId, that.clientId) && Objects.equals(addrInd, that.addrInd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, addrInd);
    }
}
