package kr.co.unithon.unithon13;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import kr.co.unithon.unithon13.model.PathResult;
import kr.co.unithon.unithon13.model.Station;

public class ResultActivity extends AppCompatActivity {
    private static long  MIN_MILLISECONDS =60000;
    private Toolbar toolbar;
    private Station srcStation;
    private Station dstStation;
    private PathResult mPathResult;

    private ImageView srcStnPin;
    private ImageView dstStnPin;

    private TextView mTravelCntLabel;
    private TextView mTravelTimeLabel;

    private NumberPicker mDaynightpicker;
    private NumberPicker mHourPicker;
    private NumberPicker mMinPicker;

    private TextView arrivalDaynightLabel;
    private TextView arrivalTimeLabel;
    private Date currentTime;
    private Date spinnerTime;
    private Date arrivalTime;
    Calendar calendar;
    Calendar arCalendar;
    private static Map<String,Integer> pinMap = new HashMap<>();
    static {
        pinMap.put("1",R.drawable.pin_line_1);
        pinMap.put("2",R.drawable.pin_line_2);
        pinMap.put("3",R.drawable.pin_line_3);
        pinMap.put("4",R.drawable.pin_line_4);
        pinMap.put("5",R.drawable.pin_line_5);
        pinMap.put("6",R.drawable.pin_line_6);
        pinMap.put("7",R.drawable.pin_line_7);
        pinMap.put("8",R.drawable.pin_line_8);
        pinMap.put("9",R.drawable.pin_line_9);
        pinMap.put("i",R.drawable.pin_line_i);
        pinMap.put("a",R.drawable.pin_line_a);
        pinMap.put("b",R.drawable.pin_line_b);
        pinMap.put("e",R.drawable.pin_line_e);
        pinMap.put("g",R.drawable.pin_line_g);
        pinMap.put("i",R.drawable.pin_line_i);
        pinMap.put("k",R.drawable.pin_line_k);
        pinMap.put("s",R.drawable.pin_line_s);
        pinMap.put("su",R.drawable.pin_line_su);
        pinMap.put("u",R.drawable.pin_line_u);
    }
//    private static final String daynight = ["",""];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Bundle bundle =getIntent().getExtras();
        srcStation = bundle.getParcelable("src_stn");
        dstStation = bundle.getParcelable("dst_stn");
        mPathResult = bundle.getParcelable("path_result");

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        setupActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        srcStnPin = (ImageView)findViewById(R.id.src_stn_pin);
        dstStnPin = (ImageView)findViewById(R.id.dst_stn_pin);

        mTravelCntLabel =(TextView)findViewById(R.id.travel_cnt_label);
        mTravelTimeLabel = (TextView)findViewById(R.id.travel_time_label);
        arrivalDaynightLabel = (TextView)findViewById(R.id.arrival_daynight_label);
        arrivalTimeLabel = (TextView)findViewById(R.id.arrival_time_label);


        //init pin section
        int srcPinResId = pinMap.get(srcStation.getLineNum().toLowerCase());
        int dstPinResId = pinMap.get(dstStation.getLineNum().toLowerCase());

        srcStnPin.setImageResource(srcPinResId);
        dstStnPin.setImageResource(dstPinResId);

        mTravelCntLabel.setText(mPathResult.getMinStatnCnt());
        mTravelTimeLabel.setText(mPathResult.getMinTravelTm()+"");


        ((TextView)findViewById(R.id.src_stn_label)).setText(srcStation.getName());
        ((TextView)findViewById(R.id.dst_stn_label)).setText(dstStation.getName());
//        ((TextView)findViewById(R.id.tv_time)).setText(mPathResult.toString());

        mDaynightpicker = (NumberPicker) findViewById(R.id.picker_daynight);
        mHourPicker = (NumberPicker) findViewById(R.id.picker_hour);
        mMinPicker = (NumberPicker) findViewById(R.id.picker_minute);

        mHourPicker.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);
        mHourPicker.setMinValue(1);
        mHourPicker.setMaxValue(12);
        List<String> hourList = getHoursStrings();
        mHourPicker.setDisplayedValues(hourList.toArray(new String[hourList.size()]));

        mMinPicker.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);
        mMinPicker.setMinValue(0);
        mMinPicker.setMaxValue(59);
        List<String> minList = getMinuteStrings();
        mMinPicker.setDisplayedValues(minList.toArray(new String[minList.size()]));
//        mMinPicker.setDisplayedValues(getMinuteStrings());

        mDaynightpicker.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);
        mDaynightpicker.setMinValue(0);
        mDaynightpicker.setMaxValue(1);
        mDaynightpicker.setDisplayedValues(new String[]{"오전", "오후"});


        // create a GregorianCalendar with the Pacific Daylight time zone
        // and the current date and time
        TimeZone timezone = TimeZone.getTimeZone("Asia/Seoul");

        calendar = new GregorianCalendar(timezone, Locale.KOREA);

        Date trialTime = new Date();

        calendar.setTime(trialTime);
        currentTime = calendar.getTime();
        spinnerTime = calendar.getTime();

        int daynight = calendar.get(Calendar.AM_PM);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        Log.d("Result AM_PM", calendar.get(Calendar.AM_PM) + "");
        Log.d("Result HOUR", calendar.get(Calendar.HOUR)+"");
        Log.d("Result MINUTE", calendar.get(Calendar.MINUTE)+"");

        mDaynightpicker.setValue(daynight);
        mHourPicker.setValue(hour);
        mMinPicker.setValue(minute);

        updateArrivalTimeUI();


        mDaynightpicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                updateArrivalTimeUI();
            }
        });
        mHourPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                spinnerTime.setHours(newVal);
                updateArrivalTimeUI();
            }
        });
        mMinPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                spinnerTime.setMinutes(newVal);
                updateArrivalTimeUI();
            }
        });
    }

    private void updateArrivalTimeUI() {
        arrivalTime = new Date(spinnerTime.getTime() + mPathResult.getMinTravelTm()*MIN_MILLISECONDS);
        calendar.setTime(arrivalTime);
        if(calendar.get(Calendar.AM_PM) == 0) {
            arrivalDaynightLabel.setText("오전");
        } else {
            arrivalDaynightLabel.setText("오후");

        }
        String ar_str = String.format("%02d", calendar.get(Calendar.HOUR))+ ":" + calendar.get(Calendar.MINUTE);
        arrivalTimeLabel.setText(ar_str);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }

    private List<String> getMinuteStrings() {
//        String[] minutes = new String[60];

        List<String> minutes = new ArrayList<>();
        for(int i = 0;  i < 60 ; i++){
//            minutes[i] = String.format("%2d",i);
            minutes.add(String.format("%02d",i));
        }
        return minutes;
    }
    private List<String> getHoursStrings() {
//        String[] hours = new String[12];
        List<String> hours = new ArrayList<>();
        for(int i = 1;  i <= 12 ; i++){
//            hours[i-1] = String.format("%2d",i);
            hours.add(String.format("%02d", i));
        }
        return hours;
    }
}
