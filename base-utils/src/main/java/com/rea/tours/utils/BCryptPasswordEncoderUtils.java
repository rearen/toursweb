package com.rea.tours.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils
{
    private static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
    public static String encodePassword(String password)
    {
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args)
    {
        String password="123"; //$2a$10$k13eBHyBX1QWLrcst0xXROVkZf9IWwxtb1kIKsqeJZVqHk0iqxVkW
        String pwd=encodePassword(password);
        System.out.println(pwd);
        System.out.println(pwd.length());
    }
}
