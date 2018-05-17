package com.example.toti.myopenssl;

public class OpenSSLCertificate {

    static {
        System.loadLibrary("native-crypto");
    }

        private final long mContext;

    public OpenSSLCertificate(long ctx) {
        mContext = ctx;
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            if (mContext != 0) {
                X509_free(mContext);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static native void X509_free(long x509ctx);
}
