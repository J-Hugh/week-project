package com.hugh.auto.test.selenium.remote;

import com.hugh.auto.test.selenium.remote.domain.NodeInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RemoteService {

    /**
     * 获取所有节点信息
     * @return
     */
    public List<NodeInfo> findAllNode() {
        return new ArrayList<>();
    }



}
