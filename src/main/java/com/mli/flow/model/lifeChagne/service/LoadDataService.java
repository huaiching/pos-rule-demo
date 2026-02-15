package com.mli.flow.model.lifeChagne.service;

import com.mli.flow.model.entity.entity.*;
import com.mli.flow.model.entity.repository.*;
import com.mli.flow.model.lifeChagne.dto.LoadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前置資料 讀取相關邏輯
 */

@Service
public class LoadDataService {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public LoadDTO getAllData(String policyNo, String receiveNo) {
        LoadDTO loadDTO = new LoadDTO();
        // 取得資料: 變更前
        loadDTO.setPolf(getPolf(policyNo));
        loadDTO.setColfList(getColf(policyNo));
        loadDTO.setPoclList(getPocl(policyNo));
        List<String> clientIdList = loadDTO.getPoclList().stream()
                .map(PoclEntity::getClientId).distinct().toList();
        loadDTO.setClntList(getClnt(clientIdList));
        loadDTO.setAddrList(getAddr(clientIdList));
        List<PldfEntity> pldfOriList = new ArrayList<>();
        for (ColfEntity colfEntity : loadDTO.getColfList()) {
            PldfEntity pldfEntity = getPldf(colfEntity.getPlanCode(), colfEntity.getRateScale());
            pldfOriList.add(pldfEntity);
        }
        loadDTO.setPldfOriList(pldfOriList);
        // 取得資料: 變更後
        loadDTO.setRspo(getRspo(receiveNo));
        loadDTO.setRscoList(getRsco(receiveNo));
        loadDTO.setRsclList(getRscl(receiveNo));
        loadDTO.setRsdrList(getRsdr(receiveNo));
        List<PldfEntity> pldfNewList = new ArrayList<>();
        for (RscoEntity rscoEntity : loadDTO.getRscoList()) {
            PldfEntity pldfEntity = getPldf(rscoEntity.getPlanCode(), rscoEntity.getRateScale());
            pldfNewList.add(pldfEntity);
        }
        loadDTO.setPldfNewList(pldfNewList);
        // 取得資料: 其他
        loadDTO.setChsw(getChsw(receiveNo));

        return loadDTO;
    }


    public PolfEntity getPolf(String policyNo) {
        String sql = "SELECT * FROM polf " +
                     "WHERE policy_no = :policyNo";

        Map<String, Object> params = new HashMap<>();
        params.put("policyNo", policyNo);

        List<PolfEntity> polfEntityList = namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(PolfEntity.class));

        if (CollectionUtils.isEmpty(polfEntityList)) {
            return null;
        } else  {
            return polfEntityList.getFirst();
        }
    }

    public RspoEntity getRspo(String receiveNo) {
        String sql = "SELECT * FROM rspo " +
                     "WHERE receive_no = :receiveNo";

        Map<String, Object> params = new HashMap<>();
        params.put("receiveNo", receiveNo);

        List<RspoEntity> rspoEntityList = namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(RspoEntity.class));

        if (CollectionUtils.isEmpty(rspoEntityList)) {
            return null;
        } else  {
            return rspoEntityList.getFirst();
        }
    }

    public ChswEntity getChsw(String receiveNo) {
        String sql = "SELECT * FROM chsw " +
                     "WHERE receive_no = :receiveNo";

        Map<String, Object> params = new HashMap<>();
        params.put("receiveNo", receiveNo);

        List<ChswEntity> chswEntityList = namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(ChswEntity.class));

        if (CollectionUtils.isEmpty(chswEntityList)) {
            return null;
        } else  {
            return chswEntityList.getFirst();
        }
    }

    public List<ColfEntity> getColf(String policyNo) {
        String sql = "SELECT * FROM colf " +
                     "WHERE policy_no = :policyNo " +
                     "ORDER BY coverage_no";

        Map<String, Object> params = new HashMap<>();
        params.put("policyNo", policyNo);

        return namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(ColfEntity.class));
    }

    public List<RscoEntity> getRsco(String receiveNo) {
        String sql = "SELECT * FROM rsco " +
                     "WHERE receive_no = :receiveNo " +
                     "ORDER BY coverage_no";

        Map<String, Object> params = new HashMap<>();
        params.put("receiveNo", receiveNo);

        return namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(RscoEntity.class));
    }

    public PldfEntity getPldf(String planCode, String rateScale) {
        String sql =  "SELECT * FROM pldf " +
                      "WHERE plan_code = :planCode " +
                      "  AND rate_scale = :rateScale";

        Map<String, Object> params = new HashMap<>();
        params.put("planCode", planCode);
        params.put("rateScale", rateScale);

        List<PldfEntity> pldfEntityList = namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(PldfEntity.class));

        if (CollectionUtils.isEmpty(pldfEntityList)) {
            return null;
        } else  {
            return pldfEntityList.getFirst();
        }
    }

    public List<ClntEntity> getClnt(List<String> clientIdList) {
        String sql = "SELECT * FROM clnt " +
                     "WHERE client_id IN (:clientIdList)";

        Map<String, Object> params = new HashMap<>();
        params.put("clientIdList", clientIdList);

        return namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(ClntEntity.class));
    }

    public List<AddrEntity> getAddr(List<String> clientIdList) {
        String sql = "SELECT * FROM addr " +
                     "WHERE client_id IN (:clientIdList) " +
                     "ORDER BY client_id, addr_ind";

        Map<String, Object> params = new HashMap<>();
        params.put("clientIdList", clientIdList);

        return namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(AddrEntity.class));
    }

    public List<PoclEntity> getPocl(String policyNo) {
        String sql = "SELECT * FROM pocl " +
                     "WHERE policy_no = :policyNo";

        Map<String, Object> params = new HashMap<>();
        params.put("policyNo", policyNo);

        return namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(PoclEntity.class));
    }

    public List<RsclEntity> getRscl(String receiveNo) {
        String sql = "SELECT * FROM rscl " +
                     "WHERE receive_no = :receiveNo";

        Map<String, Object> params = new HashMap<>();
        params.put("receiveNo", receiveNo);

        return namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(RsclEntity.class));
    }

    public List<RsdrEntity> getRsdr(String receiveNo) {
        String sql = "SELECT * FROM rsdr " +
                     "WHERE receive_no = :receiveNo " +
                     "ORDER BY client_id, addr_ind";

        Map<String, Object> params = new HashMap<>();
        params.put("receiveNo", receiveNo);

        return namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(RsdrEntity.class));
    }
}
