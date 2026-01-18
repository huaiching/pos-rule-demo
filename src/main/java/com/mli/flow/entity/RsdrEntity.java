package com.mli.flow.entity;

import com.mli.flow.uniquekey.AddrKey;
import com.mli.flow.uniquekey.RsdrKey;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rsdr")
@IdClass(RsdrKey.class)
@Schema(description = "客戶地址檔")
public class RsdrEntity {
    @Id
    @Schema(description = "客戶證號")
    @Column(name = "client_id")
    private String clientId;

    @Id
    @Schema(description = "地址指示")
    @Column(name = "addr_ind")
    private String addrInd;

    @Schema(description = "受理號碼")
    @Column(name = "receive_no")
    private String receiveNo;

    @Schema(description = "功能碼")
    @Column(name = "function_ind")
    private String functionInd;

    @Schema(description = "地址")
    @Column(name = "address")
    private String address;

    @Schema(description = "電話")
    @Column(name = "tel_1")
    private Integer tel1;

    public RsdrEntity() {
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

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public String getFunctionInd() {
        return functionInd;
    }

    public void setFunctionInd(String functionInd) {
        this.functionInd = functionInd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTel1() {
        return tel1;
    }

    public void setTel1(Integer tel1) {
        this.tel1 = tel1;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RsdrEntity that = (RsdrEntity) o;
        return Objects.equals(clientId, that.clientId) && Objects.equals(addrInd, that.addrInd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, addrInd);
    }
}
