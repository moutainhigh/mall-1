package com.yunxin.cb.test;

import com.yunxin.cb.util.RSAUtils;

public class RSAUtilsTest {
    static String publicKey;
    static String privateKey;

//    static {
//        try {
//            Map<String, Object> keyMap = RSAUtils.genKeyPair();
//            publicKey = RSAUtils.getPublicKey(keyMap);
//            privateKey = RSAUtils.getPrivateKey(keyMap);
//            System.err.println("公钥: \n\r" + publicKey);
//            System.err.println("私钥： \n\r" + privateKey);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    static void testJavaRsa() {
//                 String PUBLICKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCcd+0zTY9Gn94iqkQJTlxYnEnCeFsLkk0a7hoAvi2B74VzDVV3xH0ZO9RkXvo1SgCB+uzbEWdrgQkzTqyjfTtgOguu3OnkVxIMJF34ibchTY0LWHGxq1m2gLGuVVqrlu1LtdV0X7xo/5zc8Mr+46veWb86kSpqe6rOAm69WWo5GwIDAQAB";
//                 String PRIVATEKEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJx37TNNj0af3iKqRAlOXFicScJ4WwuSTRruGgC+LYHvhXMNVXfEfRk71GRe+jVKAIH67NsRZ2uBCTNOrKN9O2A6C67c6eRXEgwkXfiJtyFNjQtYcbGrWbaAsa5VWquW7Uu11XRfvGj/nNzwyv7jq95ZvzqRKmp7qs4Cbr1ZajkbAgMBAAECgYAHp349EkA+DjgJrhah9elilFKvZr/dcwy+koNHIgaL4rG+jRpvP3d3MowTVOocjUA1G5dWqCVNBwTyM5kSbl/nIxSCYwdUoDid4r0JbqkXkTTsIq3euHG8eiWr9rr3SDmwDojWoJEc4liVlfme8dQuMfgxe1QKq7wTrJwCKwbeMQJBAPwpknRPRK8W9hefbbtEu8mlbzUy+ER8Puq6dvS+lnWzJ8n2chJcHRYQFwWpjl4+SZuKeEcDmYmuQ7xuqEIayO0CQQCe2YeaxcU4uuDC45RAwCcMaNw1nDJuA+Gi47lXbroBXoeOiNZunViSZVUgDgrV/Ku6V54TaZIzZ21QFjf7mXEnAkEA7dZwMpAJonOvzfwrzbQ4wyrsx2q5zC68UT1qsdGJrJ48azutwC9tp7+pV0fj5nQtjS1/4Ms+aCQb84ET5rXIyQJAM0m45tgEHZT5DPO94kooUXFp6EVOYwcNyzILnZc6p0aGLhcwZPaYqmvdWEQwa3bxW3D+sPXdJou2V61U1f9s8QJALccvYwwWlCTq1iTmegYk9fOoc+isZKH+Z0YW70kFi94AYEO+utYwmXBEAqQ5VC/bywa1O71xdL4/RGCOSxBf2A==";
        String data = "123456";
        data = RSAUtils.encryptedData(data, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCDqkbkAVumh6W2NPgVb5CkV/odJKCJBfGUMBa1P5FydcNYZ7kqXz80plGXDKbwg+MwZC32J6i6p4SSwNuHMDnMcIaR+UuXIaFEv6DoSfcDELcYRvhlJEBC0vFgAdf5rY+W8DLeKAIURVBLDUNe8NLiHfWRTvONlaAczuU0fMIseQIDAQAB");
        System.out.println("data：" + data);
//        System.out.println("privateKey：" + privateKey);
        System.out.println("解密数据：" + RSAUtils.decryptData(data, "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIOqRuQBW6aHpbY0+BVvkKRX+h0koIkF8ZQwFrU/kXJ1w1hnuSpfPzSmUZcMpvCD4zBkLfYnqLqnhJLA24cwOcxwhpH5S5choUS/oOhJ9wMQtxhG+GUkQELS8WAB1/mtj5bwMt4oAhRFUEsNQ17w0uId9ZFO842VoBzO5TR8wix5AgMBAAECgYBdM1CBH3D3/bdQtkgSLGoP23Lh5EZ7GCj8g9nfLQNRKYlA8BNDqi58n2rL6JnXURSl8ZQTVdl7Xw9aLoh3Nq0CQxw10qwndYWef2u34MCa01c+rrAWZa5iCJIqRI+SOwYTat+v+HaIw0VdDbSz7yI4Oq5bn7ewt9BUg/0ktRi8PQJBANKMjMdQWYuvVNkRxpdFTZwu+33DoTC5RHpPC852oKgXr1/10XKfsS99LLrVA0900Y60ZGRMhy4rbnjnu6NF7OMCQQCgFmmRhoy0G5QD1oxIGCLzKPXHAtc+nFWc8bXxSznZxGWeVlGgOtdyCPQLu1iL5+eTXMxmotnL/PWb0LFRLjvzAkAn3bb2Mk+SNFOTelso31VvojG6QWVu7VV69yacJDgNO9bYC5pp4rTQ8hmbEqQ60jqvk5aCqz7ZTh3vxKjxpFbTAkB2fGAiX42geJDyzkcBzSuBAZ33b/jT1G3hPV1GfEETFrOHn6fGigaP1mSakk0VL81MWnDGvl2pB07ZxnqW9TtVAkEAyUvyEnaSq//9ZV50o8Jth07cFxvNyzPisyKf0xI0ogf0Se8vTOD498uzWgjWAtKKKix1ouDjxLY+47jCwGsfOQ=="));
    }

    public static void main(String[] args) throws Exception {
        testJavaRsa();
    }
}
