package com.koreait.exam.batch_ex_24_04.app_batch_ex_24_04;


import org.junit.jupiter.api.DisplayName;
import com.koreait.exam.batch_ex_24_04.util.Util;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

public class UtilTests {
    @Test
    @DisplayName("특정 월의 마지막 날을 계산 1")
    void t1() {
        int endDayOf_2023_02 = Util.date.getEndDayOf(2023, 2);

        assertThat(endDayOf_2023_02).isEqualTo(28);
    }

    @Test
    @DisplayName("특정 월의 마지막 날을 계산 2")
    void t2() {
        int endDayOf_2024_02 = Util.date.getEndDayOf(2024, 2);

        assertThat(endDayOf_2024_02).isEqualTo(29);
    }

    @Test
    @DisplayName("특정 월의 마지막 날을 계산 3")
    void t3() {
        int endDayOf_2024_04 = Util.date.getEndDayOf(2024, 4);

        assertThat(endDayOf_2024_04).isEqualTo(31);
    }

    @Test
    @DisplayName("문자열로 LocalDateTime 객체 만들기 1")
    void t4() {
        LocalDateTime localDateTime1 = Util.date.parse("yyyy-MM-dd HH:mm:ss.SSSSSS","2024-04-24 23:59:59.999999");

        assertThat(localDateTime1.toString().length()).isEqualTo(26);
    }

    @Test
    @DisplayName("문자열로 LocalDateTime 객체 만들기 2")
    void t5() {
        LocalDateTime localDateTime1 = Util.date.parse("yyyy-MM-dd HH:mm:ss","2024-04-24 23:59:59");

        assertThat(localDateTime1.toString().length()).isEqualTo(19);
    }

}