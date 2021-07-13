package com.doutiaotech.apollo.infrastructure.clickhouse.model.trade;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostAddrBean {
    private ProvinceBean province;
    private CityBean city;
    private TownBean town;
    private StreetBean street;
    private String detail;

    @NoArgsConstructor
    @Data
    public static class ProvinceBean {
        private String name;
        private String id;
    }

    @NoArgsConstructor
    @Data
    public static class CityBean {
        private String name;
        private String id;
    }

    @NoArgsConstructor
    @Data
    public static class TownBean {
        private String name;
        private String id;
    }

    @NoArgsConstructor
    @Data
    public static class StreetBean {
        private String name;
        private String id;
    }
}
