package com.glory.bianyitong.util.glidehttps;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Protocol;

import java.security.cert.CertificateException;
import java.util.Arrays;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by lucy on 2017/1/17.
 */
public class MyTrustManager {

    private TrustManagerListener tmListener;

    public MyTrustManager setTrustManagerListener(TrustManagerListener tmListener) {
        this.tmListener = tmListener;
        return this;
    }

    public OkHttpClient getUnsafeOkHttpClient() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            if (tmListener != null) {
                                tmListener.checkClientTrusted(chain, authType);
                            }
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            if (tmListener != null) {
                                tmListener.checkServerTrusted(chain, authType);
                            }
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            if (tmListener != null) {
                                tmListener.getAcceptedIssuers();
                            }
                            return null;
                        }
                    }
            };
            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setSslSocketFactory(sslSocketFactory);
            okHttpClient.setProtocols(Arrays.asList(Protocol.HTTP_1_1));
            okHttpClient.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public interface TrustManagerListener {
        void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType);

        void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType);

        java.security.cert.X509Certificate[] getAcceptedIssuers();
    }

}
