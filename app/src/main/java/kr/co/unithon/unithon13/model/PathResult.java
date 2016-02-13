package kr.co.unithon.unithon13.model;

import lombok.ToString;

/**
 * Created by daehyun on 16. 2. 13..
 *
 *
 //    <row>
 //    <rowNum>1</rowNum>
 //    <selectedCount>1</selectedCount>
 //    <totalCount>1</totalCount>
 //    <statnFid>1001000100</statnFid>
 //    <statnTid>1002000215</statnTid>
 //    <statnFnm>소요산</statnFnm>
 //    <statnTnm>잠실나루</statnTnm>
 //    <shtStatnId>...</shtStatnId>
 //    <shtStatnNm>...</shtStatnNm>
 //    <shtTransferMsg>1시간 20분 걸리고, 2번 환승합니다. (32개역 지나감)</shtTransferMsg>
 //    <shtTravelMsg>1시간 20분 걸리고, 2번 환승합니다. (32개역 지나감)</shtTravelMsg>
 //    <shtStatnCnt>32</shtStatnCnt>
 //    <shtTravelTm>80</shtTravelTm>
 //    <shtTransferCnt>2</shtTransferCnt>
 //    <minStatnId>...</minStatnId>
 //    <minStatnNm>...</minStatnNm>
 //    <minTransferMsg>1시간 22분 걸리고, 1번 환승합니다. (34개역 지나감)</minTransferMsg>
 //    <minTravelMsg>1시간 22분 걸리고, 1번 환승합니다. (34개역 지나감)</minTravelMsg>
 //    <minStatnCnt>34</minStatnCnt>
 //    <minTravelTm>82</minTravelTm>
 //    <minTransferCnt>1</minTransferCnt>
 //    <shtStatnXy>...</shtStatnXy>
 //    <minStatnXy>...</minStatnXy>
 //    </row>
 //    </shortestRoute>
 */


@ToString
public class PathResult {

        public int rowNum;
        public int selectedCount;
        public int totalCount;
        public String statnFid;
        public String statnTid;
        public String statnFnm;
        public String statnTnm;
        public String shtStatnId;
        public String shtStatnNm;
        public String shtTransferMsg;
        public String shtTravelMsg;
        public int shtStatnCnt;
        public int shtTravelTm;
        public int shtTransferCnt;
        public String minStatnId;
        public String minStatnNm;
        public String minTransferMsg;
        public String minTravelMsg;
        public String minStatnCnt;
        public int minTravelTm;
        public int minTransferCnt;
        public String shtStatnXy;
        public String minStatnXy;

}
