package com.trust.chartdemo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

/**
 * @author Talon
 * @date 2018/06/26
 */

public class MyLineChart extends LineChart {

    private String label;

    //LineDataSet可以看做是一条线
    private LineDataSet dataSet;

    private int myVisibleXRangeMaximum = 15;

    public MyLineChart(Context context) {
        this(context, null);
    }

    public MyLineChart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyLineChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initChart();
        initDataSet();
    }

    private void initDataSet() {
        LineData data = new LineData();
        data.setValueTextColor(Color.WHITE);
        setData(data);
    }

    private void initChart() {

        getLegend().setEnabled(false);
        getDescription().setEnabled(false);

        setNoDataText("请设置数据");
        setDrawGridBackground(false);
        setDrawBorders(false);
        setScaleEnabled(true);

        setMarker(new CustomMarkerView(getContext(), R.layout.content_marker_view));
        setHighlightPerTapEnabled(true);
        setDrawMarkers(true);


        YAxis rightAxis = getAxisRight();
        rightAxis.setEnabled(false);
        YAxis leftAxis = getAxisLeft();
        leftAxis.setAxisLineColor(Color.WHITE);
        leftAxis.setDrawZeroLine(false);
        leftAxis.setDrawGridLines(false);
//        leftAxis.setGranularity(2);
//        leftAxis.setMinWidth(40);
        XAxis xAxis = getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(2);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setEnabled(false);

    }

    private void setupData() {
        LineData data = getData();

        if (data != null) {

            ILineDataSet set = data.getDataSetByIndex(0);
            // set.addEntry(...); // can be called as well

            if (set == null) {
                set = createSet();
                data.addDataSet(set);
                set.setValueFormatter(new DefaultValueFormatter(2));
//                set.setValueFormatter(new ValueFormatter() {
//                    @Override
//                    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
//                        return new BigDecimal(value).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue() + "";
//                    }
//                });
            }

            for (int i = 0; i < 10; i++) {
                //data.addXValue(getMinAndSec(calendar, interval * i));
//                data.addXValue(i + "");
                data.addEntry(new Entry(i, 0), 0);
            }

            // let the chart know it's data has changed
            notifyDataSetChanged();
        }
    }


    public void addEntry(float y) {

        LineData data = getData();

        if (data != null) {

            ILineDataSet set = data.getDataSetByIndex(0);
            // set.addEntry(...); // can be called as well

            if (set == null) {
                set = createSet();
                data.addDataSet(set);
                set.setValueFormatter(new DefaultValueFormatter(2));
            }

            data.addEntry(new Entry(set.getEntryCount(), y), 0);
            data.notifyDataChanged();

            // let the chart know it's data has changed
            notifyDataSetChanged();

            // limit the number of visible entries
            setVisibleXRangeMaximum(myVisibleXRangeMaximum);
            // mChart.setVisibleYRange(30, AxisDependency.LEFT);

            // move to the latest entry
            moveViewToX(data.getEntryCount());

            // this automatically refreshes the chart (calls invalidate())
            // mChart.moveViewTo(data.getXValCount()-7, 55f,
            // AxisDependency.LEFT);
        }
    }

    private LineDataSet createSet() {

        LineDataSet set = new LineDataSet(null, label);
        set.setLineWidth(1.5f);
        set.setDrawCircles(false);
        set.setDrawFilled(true);
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.fade_blue);
        set.setFillDrawable(drawable);
        set.setHighlightEnabled(true);
        set.setDrawHighlightIndicators(true);
        set.setHighLightColor(0x00000000);
        set.setDrawValues(false);
        set.setColor(0xff77d6f6);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        return set;

    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getMyVisibleXRangeMaximum() {
        return myVisibleXRangeMaximum;
    }

    public void setMyVisibleXRangeMaximum(int visibleXRangeMaximum) {
        if (visibleXRangeMaximum > 0) {
            this.myVisibleXRangeMaximum = visibleXRangeMaximum;
        }
    }
}
