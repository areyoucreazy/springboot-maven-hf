package com.hf.job01;

import java.util.Base64;

/**
 * 自定义类加载器
 * @author hfzhang
 * @date 2021/1/11
 */
public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) {
        try {
            new HelloClassLoader().findClass("com.messi.sport.Hello").newInstance();//加载并初始化Hello类
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String helloBase64 = "yv66vgAAADQARgoAEAAqCgAPACsJACwALQcALgoABAAqCAAvCgAEADAKAAQAMQoABAAyCgAzADQI" +
                "ADUIADYIADcIADgHADkHADoBAAY8aW5pdD4BAAMoKVYBAARDb2RlAQAPTGluZU51bWJlclRhYmxl" +
                "AQASTG9jYWxWYXJpYWJsZVRhYmxlAQAEdGhpcwEAF0xjb20vbWVzc2kvc3BvcnQvSGVsbG87AQAE" +
                "bWFpbgEAFihbTGphdmEvbGFuZy9TdHJpbmc7KVYBAAFpAQABSQEABGFyZ3MBABNbTGphdmEvbGFu" +
                "Zy9TdHJpbmc7AQABYQEAAWIBAAFjAQAGcmVzdWx0AQANU3RhY2tNYXBUYWJsZQcAHQEAEE1ldGhv" +
                "ZFBhcmFtZXRlcnMBAAdjb21wdXRlAQAGKElJSSlJAQAIPGNsaW5pdD4BAApTb3VyY2VGaWxlAQAK" +
                "SGVsbG8uamF2YQwAEQASDAAlACYHADsMADwAPQEAF2phdmEvbGFuZy9TdHJpbmdCdWlsZGVyAQAP" +
                "6K6h566X57uT5p6c77yaDAA+AD8MAD4AQAwAQQBCBwBDDABEAEUBABPorqHnrpfnu5PmnpzlpKfk" +
                "uo4xAQAG5b6q546vAQAD5qyhAQAP5Yid5aeL5YyWIEhlbGxvAQAVY29tL21lc3NpL3Nwb3J0L0hl" +
                "bGxvAQAQamF2YS9sYW5nL09iamVjdAEAEGphdmEvbGFuZy9TeXN0ZW0BAANvdXQBABVMamF2YS9p" +
                "by9QcmludFN0cmVhbTsBAAZhcHBlbmQBAC0oTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcv" +
                "U3RyaW5nQnVpbGRlcjsBABwoSSlMamF2YS9sYW5nL1N0cmluZ0J1aWxkZXI7AQAIdG9TdHJpbmcB" +
                "ABQoKUxqYXZhL2xhbmcvU3RyaW5nOwEAE2phdmEvaW8vUHJpbnRTdHJlYW0BAAdwcmludGxuAQAV" +
                "KExqYXZhL2xhbmcvU3RyaW5nOylWACEADwAQAAAAAAAEAAEAEQASAAEAEwAAAC8AAQABAAAABSq3" +
                "AAGxAAAAAgAUAAAABgABAAAACgAVAAAADAABAAAABQAWABcAAAAJABgAGQACABMAAAEJAAQABgAA" +
                "AGgEPAU9Bj4bHB24AAI2BLIAA7sABFm3AAUSBrYABxUEtgAItgAJtgAKFQQEpAALsgADEgu2AAoD" +
                "NgUVBRUEogAqsgADuwAEWbcABRIMtgAHFQUEYLYACBINtgAHtgAJtgAKhAUBp//VsQAAAAMAFAAA" +
                "AC4ACwAAABEAAgASAAQAEwAGABUADgAWACgAFwAuABgANgAbAEAAHABhABsAZwAeABUAAAA+AAYA" +
                "OQAuABoAGwAFAAAAaAAcAB0AAAACAGYAHgAbAAEABABkAB8AGwACAAYAYgAgABsAAwAOAFoAIQAb" +
                "AAQAIgAAABcAA/8ANgAFBwAjAQEBAQAA/AACAfoALQAkAAAABQEAHAAAAAoAJQAmAAIAEwAAAFgA" +
                "AwAEAAAADBobHGgFbGAEZD4drAAAAAIAFAAAAAoAAgAAACEACgAiABUAAAAqAAQAAAAMAB4AGwAA" +
                "AAAADAAfABsAAQAAAAwAIAAbAAIACgACACEAGwADACQAAAANAwAeAAAAHwAAACAAAAAIACcAEgAB" +
                "ABMAAAAlAAIAAAAAAAmyAAMSDrYACrEAAAABABQAAAAKAAIAAAANAAgADgABACgAAAACACk=";
        byte[] bytes = decode(helloBase64);
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] decode(String helloBase64) {
        return Base64.getDecoder().decode(helloBase64);
    }

}
