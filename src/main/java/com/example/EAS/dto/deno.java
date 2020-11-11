package com.example.EAS.dto;

import java.time.LocalDateTime;

public class deno {

    public static void main(String[] args) {
        final LocalDateTime now = LocalDateTime.now();
        final int month = now.getMonthValue();
        final int year = now.getYear();
        System.out.println(year+"   "+month);

    }



}
