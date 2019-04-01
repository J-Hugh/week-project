package com.hugh.auto.test.selenium.remote.domain;

import lombok.Data;

/**
 * 节点信息
 */
@Data
public class NodeInfo {

    /**
     * 节点ip
     */
    private String ip;

    /**
     * 端口
     */
    private Integer port;

    public String getRemoteUrl() {
        return String.format("http://%s:%s", this.ip, this.port);
    }

}
