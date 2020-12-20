package com.msauchanka.craft.demo.utility;

import org.apache.http.client.utils.URIBuilder;

import javax.annotation.PostConstruct;

public class URITestUtils {

    private String defaultScheme;
    private String defaultHost;
    private String defaultUri;

    private URIBuilder defaultBuilder;

    public URITestUtils(String defaultScheme, String defaultHost, String defaultUri) {
        this.defaultScheme = defaultScheme;
        this.defaultHost = defaultHost;
        this.defaultUri = defaultUri;
    }

    @PostConstruct
    private void defaultBuilderInitialize() {
        defaultBuilder = new URIBuilder();
        defaultBuilder.setScheme(defaultScheme);
        defaultBuilder.setHost(defaultHost);
        defaultBuilder.setPath(defaultUri);
    }

    public String getDefaultUrl() {
        return defaultBuilder.toString();
    }
}
