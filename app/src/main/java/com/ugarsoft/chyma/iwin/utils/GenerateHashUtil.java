package com.ugarsoft.chyma.iwin.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Chyma
 * <p/>
 * on 5/7/2016.
 */
public class GenerateHashUtil {
    public static String getHash(File file, String hashType) throws NoSuchAlgorithmException, IOException {

        MessageDigest md = MessageDigest.getInstance(hashType);
        FileInputStream fis = new FileInputStream(file);
        byte[] dataBytes = new byte[1024];

        int nread = 0;

        while ((nread = fis.read(dataBytes)) != -1) {
            md.update(dataBytes, 0, nread);
        }

        byte[] mdbytes = md.digest();

        // convert the byte to hex format
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < mdbytes.length; i++) {
            sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        // close the fileInputStream
        fis.close();

        return sb.toString();
    }

    public static String getHash(String jsonString, String hashType) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md = MessageDigest.getInstance(hashType);
        md.update(jsonString.getBytes("UTF-8"));

        byte[] mdbytes = md.digest();
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < mdbytes.length; i++) {
            sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static enum HashType {
        MD5("MD5"), SHA1("SHA-1"), SHA256("SHA-256");
        String algoName;

         HashType(String name) {
            algoName = name;

        }

        public String getAlgoName() {
            return algoName;
        }
    }
}
