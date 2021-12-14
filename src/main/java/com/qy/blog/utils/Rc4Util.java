package com.qy.blog.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Rc4Util {
    public static String toRC4(String aInput,String aKey)
    {
        int[] iS = new int[256];
        byte[] iK = new byte[256];

        for (int i=0;i<256;i++)
            iS[i]=i;

        int j = 1;

        for (short i= 0;i<256;i++)
        {
            iK[i]=(byte)aKey.charAt((i % aKey.length()));
        }

        j=0;

        for (int i=0;i<255;i++)
        {
            j=(j+iS[i]+iK[i]) % 256;
            int temp = iS[i];
            iS[i]=iS[j];
            iS[j]=temp;
        }


        int i=0;
        j=0;
        char[] iInputChar = aInput.toCharArray();
        char[] iOutputChar = new char[iInputChar.length];
        for(short x = 0;x<iInputChar.length;x++)
        {
            i = (i+1) % 256;
            j = (j+iS[i]) % 256;
            int temp = iS[i];
            iS[i]=iS[j];
            iS[j]=temp;
            int t = (iS[i]+(iS[j] % 256)) % 256;
            int iY = iS[t];
            char iCY = (char)iY;
            iOutputChar[x] =(char)( iInputChar[x] ^ iCY) ;
        }

        return new String(iOutputChar);

    }

    // 测试加解密
    public static void main(String[] args) {
        String inputStr = "admin";
        String key = "189100";

        String str = toRC4(inputStr,key);
        String encode = Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
        //打印加密后的字符串
        System.out.println("加密：" + encode);

        byte[] decode = Base64.getDecoder().decode(encode);
        //打印解密后的字符串
        System.out.println("解密：" + toRC4(new String(decode),key));
    }
}
