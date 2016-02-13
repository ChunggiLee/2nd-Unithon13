package kr.co.unithon.unithon13;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import kr.co.unithon.unithon13.httpService.BaseResponse;
import kr.co.unithon.unithon13.httpService.PathResponse;
import kr.co.unithon.unithon13.httpService.SeoulClient;

import kr.co.unithon.unithon13.httpService.ServiceGenerator;
import kr.co.unithon.unithon13.httpService.StationListResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    TextView mTvText;
    SeoulClient seoulClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TAG", "REsponse com");

        mTvText = (TextView) findViewById(R.id.tv_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        seoulClient = ServiceGenerator.createService(SeoulClient.class);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                seoulClient = ServiceGenerator.createPathService(SeoulClient.class);
                seoulClient.getShortestPath(0, 5, "소요산", "잠실나루", new Callback<PathResponse>() {
                    @Override
                    public void success(PathResponse pathResponse, Response response) {
                        Log.d("TAG", "Success");
                        Log.d("TAG", pathResponse.pathResult.toString());

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("TAG", "Error : "+ error.getMessage());
                    }
                });
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
