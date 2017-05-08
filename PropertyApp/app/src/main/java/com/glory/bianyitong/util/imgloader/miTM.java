package com.glory.bianyitong.util.imgloader;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by lucy on 2016/12/30.
 */
public class miTM implements TrustManager,X509TrustManager{ //https
    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
        return null;
    }
    public boolean isServerTrusted(
            java.security.cert.X509Certificate[] certs) {
        return true;
    }
    public boolean isClientTrusted(
            java.security.cert.X509Certificate[] certs) {
        return true;
    }
    public void checkServerTrusted(
            java.security.cert.X509Certificate[] certs, String authType)
            throws java.security.cert.CertificateException {
        return;
    }
    public void checkClientTrusted(
            java.security.cert.X509Certificate[] certs, String authType)
            throws java.security.cert.CertificateException {
        return;
    }
}
