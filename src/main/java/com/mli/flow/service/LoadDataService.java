package com.mli.flow.service;

import com.mli.flow.entity.*;
import com.mli.flow.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 前置資料 讀取相關邏輯
 */

@Service
public class LoadDataService {
    @Autowired
    private PolfRepository polfRepository;
    @Autowired
    private ColfRepository colfRepository;
    @Autowired
    private ClntRepository clntRepository;
    @Autowired
    private AddrRepository addrRepository;
    @Autowired
    private PoclRepository poclRepository;
    @Autowired
    private RspoRepository rspoRepository;
    @Autowired
    private RscoRepository rscoRepository;
    @Autowired
    private RsclRepository rsclRepository;
    @Autowired
    private RsdrRepository rsdrRepository;
    @Autowired
    private ChswRepository chswRepository;
    @Autowired
    private PldfRepository pldfRepository;

    public PolfEntity getPolf() {
        return polfRepository.findAll().get(0);
    }

    public RspoEntity getRslf() {
        return rspoRepository.findAll().get(0);
    }

    public ChswEntity getChsw() {
        return chswRepository.findAll().get(0);
    }

    public List<ColfEntity> getColf() {
        return colfRepository.findAll();
    }

    public List<RscoEntity> getRsco() {
        return rscoRepository.findAll();
    }

    public List<PldfEntity> getPldf() {
        return pldfRepository.findAll();
    }

    public List<ClntEntity> getClnt() {
        return clntRepository.findAll();
    }

    public List<AddrEntity> getAddr() {
        return addrRepository.findAll();
    }

    public List<PoclEntity> getPocl() {
        return poclRepository.findAll();
    }

    public List<RsclEntity> getRscl() {
        return rsclRepository.findAll();
    }

    public List<RsdrEntity> getRsdr() {
        return rsdrRepository.findAll();
    }
}
