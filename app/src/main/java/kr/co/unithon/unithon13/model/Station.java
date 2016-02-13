package kr.co.unithon.unithon13.model;

import org.simpleframework.xml.Element;

/**
 * Created by daehyun on 16. 2. 13..
 */
public class Station {
//    <STATION_CD>0413</STATION_CD>
//    <STATION_NM>쌍문</STATION_NM>
//    <LINE_NUM>4</LINE_NUM>
//    <FR_CODE>413</FR_CODE>
    @Element(name="STATION_CD")
    private String stationCode;
    @Element(name="STATION_NM")
    private String name;
    @Element(name="LINE_NUM")
    private String lineNum;
    @Element(name="FR_CODE")
    private String frCode;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("STATION_CODE:");
        builder.append(stationCode);
        builder.append("STATION_NAME:");
        builder.append(name);
        builder.append("LINE_NUM:");
        builder.append(lineNum);
        builder.append("fr CODE");
        builder.append(frCode);

        return builder.toString();
    }
}