package com.orlandovald.twitter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ovaldez
 */
@ConfigurationProperties(prefix = "twitter")
public class TwitterProperties {

    /**
     * Consumer Key (API Key)
     */
    private String consumerKey;

    /**
     * Consumer Secret (API Secret)
     */
    private String consumerSecret;

    /**
     * Access Token
     */
    private String token;

    /**
     * Access Token Secret
     */
    private String secret;

    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
