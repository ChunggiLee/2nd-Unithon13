package kr.co.unithon.unithon13.httpService;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Element;

import java.util.List;

import kr.co.unithon.unithon13.model.Result;
import kr.co.unithon.unithon13.model.Station;

/**
 * Created by daehyun on 16. 2. 13..
 */
public class BaseResponse {
    @Element(name="RESULT")
    public Result result;
}
