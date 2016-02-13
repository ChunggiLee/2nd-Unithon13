package kr.co.unithon.unithon13.httpService;

import android.support.v4.media.session.MediaSessionCompat;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

import kr.co.unithon.unithon13.model.Result;
import kr.co.unithon.unithon13.model.Station;

/**
 * Created by daehyun on 16. 2. 13..
 */
@Root(name="SearchSTNBySubwayLineService")
public class StationListResponse {
    @Element(name="list_total_count")
    public int listCount;

    @ElementList(entry="row", inline=true)
    public List<Station> stations;

}
