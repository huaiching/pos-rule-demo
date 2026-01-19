package com.mli.flow.service.variable;

import com.mli.flow.contract.ChangeVariableContract;
import com.mli.flow.entity.PsebEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 核保引擎變數 - P001
 * 核保訊息 P001 的 檢核險種
 */
@Service
public class P001Service implements ChangeVariableContract {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public void execute(Map<String, Object> dataMap) {
        String sql = "SELECT * FROM pseb " +
                     "WHERE pseb_type = 'PS' " +
                     "  AND pseb_code1 = 'change' " +
                     "  AND pseb_desc1 = 'P001' ";
        Map<String, Object> params = new HashMap<>();
        List<PsebEntity> psebList = namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(PsebEntity.class));

        List<String> planCodeList = psebList.stream()
                .map(PsebEntity::getPsebDesc2).collect(Collectors.toList());

        dataMap.put("P001", planCodeList);
    }

}
