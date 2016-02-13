package kr.co.unithon.unithon13;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import kr.co.unithon.unithon13.httpService.BaseResponse;
import kr.co.unithon.unithon13.httpService.PathClient;
import kr.co.unithon.unithon13.httpService.PathResponse;
import kr.co.unithon.unithon13.httpService.SeoulClient;

import kr.co.unithon.unithon13.httpService.ServiceGenerator;
import kr.co.unithon.unithon13.httpService.StationListResponse;
import kr.co.unithon.unithon13.model.SWline;
import kr.co.unithon.unithon13.model.Station;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    TextView mTvText;
    SeoulClient seoulClient;
    PathClient pathClient;
    private ArrayList<SWline> mLineList;

    ArrayAdapter<Station> dstStationAdapter;
    ArrayAdapter<Station> srcStationAdapter;

    private ArrayList<Station> mSrcStations;
    private ArrayList<Station> mDstStations;

    private Station srcStation;
    private Station dstStation;
    Spinner srcSpinner;
    Spinner dstSpinner;
    Spinner srcStationSpinner;
    Spinner dstStationSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TAG", "REsponse com");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.logo_icon);
        seoulClient = ServiceGenerator.createService(SeoulClient.class);
        pathClient = ServiceGenerator.createPathService(PathClient.class);

        mLineList = new ArrayList<SWline>();
        mLineList.add(new SWline("1","1호선"));
        mLineList.add(new SWline("2","2호선"));
        mLineList.add(new SWline("3","3호선"));
        mLineList.add(new SWline("4","4호선"));
        mLineList.add(new SWline("5","5호선"));
        mLineList.add(new SWline("6","6호선"));
        mLineList.add(new SWline("7","7호선"));
        mLineList.add(new SWline("8","8호선"));

        // setting default station src dst;
        srcStation = new Station("","선정릉","9","111");
        dstStation = new Station("", "강남","2","333");
//
//        ArrayAdapter<SWline> srcLineadapter = new ArrayAdapter<SWline>(this, android.R.layout.simple_spinner_dropdown_item, mLineList);
//        ArrayAdapter<SWline> dstLineadapter = new ArrayAdapter<SWline>(this, android.R.layout.simple_spinner_dropdown_item, mLineList);
//
//
//        srcSpinner = (Spinner) findViewById(R.id.src_line_spinner);
//        dstSpinner = (Spinner) findViewById(R.id.dst_line_spinner);
//
//        srcSpinner.setAdapter(srcLineadapter);
//        dstSpinner.setAdapter(dstLineadapter);
//
//
//        srcStationSpinner = (Spinner) findViewById(R.id.src_stn_spinner);
//        dstStationSpinner = (Spinner)findViewById(R.id.dst_stn_spinner);
//
//
//        srcStationAdapter = new ArrayAdapter<Station>(this, android.R.layout.simple_spinner_dropdown_item);
//        dstStationAdapter = new ArrayAdapter<Station>(this, android.R.layout.simple_spinner_dropdown_item);
//        srcStationSpinner.setAdapter(srcStationAdapter);
//        dstStationSpinner.setAdapter(dstStationAdapter);
//
//        srcSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                srcStationAdapter.clear();
//                SWline mLine = (SWline) parent.getSelectedItem();
//                seoulClient.getStationsByLinenubmer(1, 200, mLine.getCode(), new Callback<StationListResponse>() {
//                    @Override
//                    public void success(StationListResponse stationListResponse, Response response) {
//                        Log.d("TAG", "Success");
//                        srcStationAdapter.addAll(stationListResponse.stations);
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        Log.d("TAG", "Error : " + error.getMessage());
//                    }
//                });
//
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                srcStationAdapter.clear();
//            }
//        });
//        dstSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                dstStationAdapter.clear();
//                SWline mLine = (SWline)parent.getSelectedItem();
//                seoulClient.getStationsByLinenubmer(1, 200, mLine.getCode(), new Callback<StationListResponse>() {
//                    @Override
//                    public void success(StationListResponse stationListResponse, Response response) {
//                        Log.d("TAG", "Success");
//                        dstStationAdapter.addAll(stationListResponse.stations);
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        Log.d("TAG", "Error : "+ error.getMessage());
//                    }
//                });
//
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                dstStationAdapter.clear();
//            }
//        });
//        srcStationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                srcStation = (Station)parent.getSelectedItem();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                srcStation = null;
//            }
//        });
//        dstStationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                dstStation = (Station)parent.getSelectedItem();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                dstStation = null;
//            }
//        });
        Button findPathButton = (Button) findViewById(R.id.btn_find_path);
        findPathButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pathClient.getShortestPath(0, 5, srcStation.getName(), dstStation.getName(), new Callback<PathResponse>() {
                    @Override
                    public void success(PathResponse pathResponse, Response response) {
                        Log.d("TAG", "Success");
                        Log.d("TAG", pathResponse.pathResult.toString());
                        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("src_stn", srcStation);
                        bundle.putParcelable("dst_stn", dstStation);
                        bundle.putParcelable("path_result", pathResponse.pathResult.get(0));
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("TAG", "Error : " + error.getMessage());
                    }
                });


//                seoulClient.getStationsByLinenubmer(1, 5, "1", new Callback<StationListResponse>() {
//                    @Override
//                    public void success(StationListResponse stationListResponse, Response response) {
//                        Log.d("TAG", "Success");
//
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        Log.d("TAG", "Error : "+ error.getMessage());
//                    }
//                });
//                seoulClient = ServiceGenerator.createPathService(SeoulClient.class);
//                seoulClient.getShortestPath(0, 5, "소요산", "잠실나루", new Callback<PathResponse>() {
//                    @Override
//                    public void success(PathResponse pathResponse, Response response) {
//                        Log.d("TAG", "Success");
//                        Log.d("TAG", pathResponse.pathResult.toString());
//
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        Log.d("TAG", "Error : "+ error.getMessage());
//                    }
//                });
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();e
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
