package kr.co.unithon.unithon13.httpService;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import kr.co.unithon.unithon13.model.PathResult;
import kr.co.unithon.unithon13.model.SwResult;

/**
 * Created by daehyun on 16. 2. 13..
 */
@Root(name="shortestRoute")
public class PathResponse {
    @Element(name="RESULT")
    public SwResult result;

    @Element(name="row")
    public PathResult pathResult;
}
