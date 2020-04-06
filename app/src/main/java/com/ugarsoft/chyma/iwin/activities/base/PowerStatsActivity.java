package com.ugarsoft.chyma.iwin.activities.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.models.PowerChart;
import com.ugarsoft.chyma.iwin.models.PowerStats;
import com.ugarsoft.chyma.iwin.models.ResponseObject;
import com.ugarsoft.chyma.iwin.utils.Constant;
import com.ugarsoft.chyma.iwin.utils.DBService;
import com.ugarsoft.chyma.iwin.utils.Pref;
import com.ugarsoft.chyma.iwin.utils.WebServiceImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PowerStatsActivity extends BaseActivity {

    private TextView date1;
    private TextView date2;
    private TextView avGen1;
    private TextView avGen2;
    private TextView peakGen1;
    private TextView peakGen2;
    private TextView avrGen1;
    private TextView avrGen2;
    private TextView gasCon1;
    private TextView gasCon2;
    private TextView waterCon1;
    private TextView waterCon2;
    private TextView loadCon1;
    private TextView loadCon2;
    private TextView transCon1;
    private TextView transCon2;
    private TextView totalCon1;
    private TextView totalCon2;

    private ImageView avGenImg;
    private ImageView peakGenImg;
    private ImageView avrGenImg;
    private ImageView gasConImg;
    private ImageView waterConImg;
    private ImageView loadConImg;
    private ImageView transConImg;
    private ImageView totalConImg;

    private FloatingActionButton fab;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward, fab2_open, fab2_close;
    private boolean isFabOpen = false;
    private ProgressBar progressBarTop;

    private List<PowerStats> powerStatsList = new ArrayList();
    private DBService dbService;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM. dd");

    private String[] mMonth = new String[]{
            "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_stats);
        setUpNavigationBar();
        setupViews();
        loadData();
    }

    private void setupViews() {
        date1 = (TextView) findViewById(R.id.date1);
        date2 = (TextView) findViewById(R.id.date2);
        avGen1 = (TextView) findViewById(R.id.avGenMW1);
        avGen2 = (TextView) findViewById(R.id.avGenMW2);
        avrGen1 = (TextView) findViewById(R.id.avrGen1);
        avrGen2 = (TextView) findViewById(R.id.avrGen2);
        peakGen1 = (TextView) findViewById(R.id.peakGenMW1);
        peakGen2 = (TextView) findViewById(R.id.peakGenMW2);
        gasCon1 = (TextView) findViewById(R.id.gasConMW1);
        gasCon2 = (TextView) findViewById(R.id.gasCon2);
        waterCon1 = (TextView) findViewById(R.id.waterCon1);
        waterCon2 = (TextView) findViewById(R.id.waterCon2);
        loadCon1 = (TextView) findViewById(R.id.loadCon1);
        loadCon2 = (TextView) findViewById(R.id.loadCon2);
        transCon1 = (TextView) findViewById(R.id.transCon1);
        transCon2 = (TextView) findViewById(R.id.transCon2);
        totalCon1 = (TextView) findViewById(R.id.totalCon1);
        totalCon2 = (TextView) findViewById(R.id.totalCon2);

        avGenImg = (ImageView) findViewById(R.id.avGenMWImg);
        peakGenImg = (ImageView) findViewById(R.id.peakGenImg);
        avrGenImg = (ImageView) findViewById(R.id.avrGenImg);
        gasConImg = (ImageView) findViewById(R.id.gasConImg);
        waterConImg = (ImageView) findViewById(R.id.waterConImg);
        loadConImg = (ImageView) findViewById(R.id.loadConImg);
        transConImg = (ImageView) findViewById(R.id.transConImg);
        totalConImg = (ImageView) findViewById(R.id.totalConImg);

        fab = (FloatingActionButton) findViewById(R.id.chartView);
        fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        fab2_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab2_open);
        fab2_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab2_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);

        progressBarTop = (ProgressBar) findViewById(R.id.progressBarTop);
        progressBarTop.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFAB();
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hideFAB();
                isFabOpen = false;
                openOwnershipChart();
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideFAB();
                isFabOpen = false;
                openPowerChart();
            }
        });
        dbService = DBService.getInstance(this);

    }

    public void animateFAB(){

        if(!isFabOpen){
            expandFab();
            isFabOpen = true;
        }
         else {
            //Close FAB menu
            hideFAB();
            isFabOpen = false;
        }
    }

    private void expandFab() {
       fab1.setVisibility(View.VISIBLE);
        fab1.setClickable(true);

        fab2.setVisibility(View.VISIBLE);
        fab2.setClickable(true);
    }

    private void hideFAB() {

        //Floating Action Button 1
        fab1.setVisibility(View.GONE);
        fab1.setClickable(true);

        fab2.setVisibility(View.GONE);
        fab2.setClickable(true);
    }
    private void loadData() {
        if (!powerStatsList.isEmpty()) {
            powerStatsList.clear();
        }
        try {
            Dao<PowerStats, Long> dao = dbService.getDao(PowerStats.class);
            QueryBuilder<PowerStats, Long> qb = dao.queryBuilder();
            qb.orderBy("reportId", false);
            qb.limit((long) 2);
            powerStatsList.addAll(qb.query());
            if (!powerStatsList.isEmpty()) {
                bindDataToViews(powerStatsList);
            }
            else {
                new ReportLoader(null, null).execute();
                new ChartLoader(null,null).execute();
            }
            Log.i("PowerStatsActivity", "Statistics objects = " + powerStatsList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadNewData() {
        try {
            Dao<PowerStats, Long> dao = dbService.getDao(PowerStats.class);
            QueryBuilder<PowerStats, Long> qb = dao.queryBuilder();
            qb.orderBy("reportId", false);
            qb.limit((long) 2);

            if(qb.query().size()>0) {
                PowerStats stat = qb.query().get(0);
                if (stat != null) {
                    Long latestReportId = stat.getReportId();
                    new ReportLoader(null, latestReportId).execute();
                    Log.i("PowerStatsActivity", "Load New Report Method");
                }
            } else{
                new ReportLoader(null, null).execute();
                Log.i("PowerStatsActivity", "Load New Report Method getting Report");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Dao<PowerChart, Long> dao = dbService.getDao(PowerChart.class);
            QueryBuilder<PowerChart, Long> qb = dao.queryBuilder();
            qb.orderBy("date", false);
            qb.limit((long) 2);

            if(qb.query().size()>0) {
                PowerChart chart = qb.query().get(0);
                if (chart != null) {
                    Long latestReportId = chart.getChartId();
                    new ChartLoader(null, latestReportId).execute();
                    Log.i("PowerStatsActivity", "Load New Report Method");
                }
            } else{
                new ChartLoader(null, null).execute();
                Log.i("PowerStatsActivity", "Load New Report Method getting Report");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void bindDataToViews(List<PowerStats> list) {
        PowerStats stat1 = null;
        PowerStats stat2 = null;
        if (!list.isEmpty() && list.size() > 1) {
            stat1 = list.get(0);
            stat2 = list.get(1);
        }
        if (stat1 != null && stat2 != null) {
            bindData(stat2, stat1);
        }

    }

    private void bindData(PowerStats stat1, PowerStats stat2) {

        date1.setText(dateFormat.format(stat1.getDate()));
        avGen1.setText(String.valueOf(stat1.getAvailableCapMW()));
        peakGen1.setText(String.valueOf(stat1.getPeakGenMW()));
        avrGen1.setText(String.valueOf(stat1.getAverageGenMW()));
        gasCon1.setText(String.valueOf(stat1.getGasConstraintMW()));
        waterCon1.setText(String.valueOf(stat1.getWaterConstraintMW()));
        loadCon1.setText(String.valueOf(stat1.getLoadRejectionMW()));
        transCon1.setText(String.valueOf(stat1.getTotalMW()));
        totalCon1.setText(String.valueOf(stat1.getTransmissionConstraintMW()));

        date2.setText(dateFormat.format(stat2.getDate()));
        avGen2.setText(String.valueOf(stat2.getAvailableCapMW()));
        peakGen2.setText(String.valueOf(stat2.getPeakGenMW()));
        avrGen2.setText(String.valueOf(stat2.getAverageGenMW()));
        gasCon2.setText(String.valueOf(stat2.getGasConstraintMW()));
        waterCon2.setText(String.valueOf(stat2.getWaterConstraintMW()));
        loadCon2.setText(String.valueOf(stat2.getLoadRejectionMW()));
        transCon2.setText(String.valueOf(stat2.getTotalMW()));
        totalCon2.setText(String.valueOf(stat2.getTransmissionConstraintMW()));

        if (stat1.getAvailableCapMW() > stat2.getAvailableCapMW()) {
            avGenImg.setVisibility(View.VISIBLE);
            avGenImg.setImageResource(R.drawable.ic_arrow_down);
        } else  if (stat1.getAvailableCapMW() == stat2.getAvailableCapMW()) {
            avGenImg.setVisibility(View.GONE);
        }else {
            avGenImg.setVisibility(View.VISIBLE);
            avGenImg.setImageResource(R.drawable.ic_arrow_up);
        }
        if (stat1.getPeakGenMW() > stat2.getPeakGenMW()) {
            peakGenImg.setVisibility(View.VISIBLE);
            peakGenImg.setImageResource(R.drawable.ic_arrow_down);
        } else  if (stat1.getPeakGenMW() == stat2.getPeakGenMW()) {
            peakGenImg.setVisibility(View.GONE);
        } else {
            peakGenImg.setVisibility(View.VISIBLE);
            peakGenImg.setImageResource(R.drawable.ic_arrow_up);
        }
        if (stat1.getAverageGenMW() > stat2.getAverageGenMW()) {
            avrGenImg.setVisibility(View.VISIBLE);
            avrGenImg.setImageResource(R.drawable.ic_arrow_down);
        } else if (stat1.getAverageGenMW() == stat2.getAverageGenMW()) {
            avrGenImg.setVisibility(View.GONE);
        } else {
            avrGenImg.setVisibility(View.VISIBLE);
            avrGenImg.setImageResource(R.drawable.ic_arrow_up);
        }
        if (stat1.getGasConstraintMW() < stat2.getGasConstraintMW()) {
            gasConImg.setVisibility(View.VISIBLE);
            gasConImg.setImageResource(R.drawable.ic_arrow_down);
        } else if (stat1.getGasConstraintMW() == stat2.getGasConstraintMW()) {
            gasConImg.setVisibility(View.GONE);
        } else {
            gasConImg.setVisibility(View.VISIBLE);
            gasConImg.setImageResource(R.drawable.ic_arrow_up);
        }
        if (stat1.getWaterConstraintMW() < stat2.getWaterConstraintMW()) {
            waterConImg.setVisibility(View.VISIBLE);
            waterConImg.setImageResource(R.drawable.ic_arrow_down);
        } else if (stat1.getWaterConstraintMW() == stat2.getWaterConstraintMW()) {
            waterConImg.setVisibility(View.GONE);
        } else {
            waterConImg.setVisibility(View.VISIBLE);
            waterConImg.setImageResource(R.drawable.ic_arrow_up);
        }
        if (stat1.getLoadRejectionMW() < stat2.getLoadRejectionMW()) {
            loadConImg.setVisibility(View.VISIBLE);
            loadConImg.setImageResource(R.drawable.ic_arrow_down);
        } else if (stat1.getLoadRejectionMW() == stat2.getLoadRejectionMW()) {
            loadConImg.setVisibility(View.GONE);
        } else {
            loadConImg.setVisibility(View.VISIBLE);
            loadConImg.setImageResource(R.drawable.ic_arrow_up);
        }
        if (stat1.getTotalMW() < stat2.getTotalMW()) {
                transConImg.setVisibility(View.VISIBLE);
            transConImg.setImageResource(R.drawable.ic_arrow_down);
        } if (stat1.getTotalMW() == stat2.getTotalMW()) {
            transConImg.setVisibility(View.GONE);
        } else {
            transConImg.setVisibility(View.VISIBLE);
            transConImg.setImageResource(R.drawable.ic_arrow_up);
        }
        if (stat1.getTransmissionConstraintMW() < stat2.getTransmissionConstraintMW()) {
            totalConImg.setVisibility(View.VISIBLE);
            totalConImg.setImageResource(R.drawable.ic_arrow_down);
        } else if (stat1.getTransmissionConstraintMW() == stat2.getTransmissionConstraintMW()) {
            totalConImg.setVisibility(View.GONE);
        }else {
            totalConImg.setVisibility(View.VISIBLE);
            totalConImg.setImageResource(R.drawable.ic_arrow_up);
        }
    }

    private void openOwnershipChart() {

        // Pie Chart Section Names
        PowerChart chart = getChartItems();
        String[] code = chart.getOwnerShipNames() == null ? new String[]{
                "GENCOs 35.8%", "NIPP 35.0%", "IPP 29.2%"} : chart.getOwnerShipNames();

        // Pie Chart Section Value
        double[] defaultValues = {35.8, 35.0, 29.2};
        double[] distribution = chart.getOwnerShipDistribution() == null ? defaultValues : chart.getOwnerShipDistribution();

        // Color of each Pie Chart Sections
        int[] colors = {Color.GREEN, Color.RED, Color.BLUE};

        // Instantiating CategorySeries to plot Pie Chart
        CategorySeries distributionSeries = new CategorySeries("GENERATION DISTRIBUTION BY OWNERSHIP");
        for (int i = 0; i < distribution.length; i++) {
            // Adding a slice with its values and name to the Pie Chart
            distributionSeries.add(code[i], distribution[i]);
        }

        // Instantiating a renderer for the Pie Chart
        DefaultRenderer defaultRenderer = new DefaultRenderer();
        for (int i = 0; i < distribution.length; i++) {
            SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
            seriesRenderer.setColor(colors[i]);
            seriesRenderer.setDisplayChartValues(true);
            // Adding a renderer for a slice
            defaultRenderer.addSeriesRenderer(seriesRenderer);
        }

        defaultRenderer.setChartTitle("GENERATION CONTRIBUTION BY OWNERSHIP");
        defaultRenderer.setChartTitleTextSize(20);
        defaultRenderer.setLabelsColor(R.color.darkText);
        defaultRenderer.setLabelsTextSize(25.0F);
        defaultRenderer.setLegendTextSize(25.0F);
        defaultRenderer.setBackgroundColor(R.color.white);
        defaultRenderer.setApplyBackgroundColor(true);
        defaultRenderer.setZoomButtonsVisible(true);

        // Creating an intent to plot PIE chart using dataset and multipleRenderer
        Intent intent = ChartFactory.getPieChartIntent(getBaseContext(), distributionSeries, defaultRenderer, "AChartEnginePieChartDemo");

        // Start Activity
        startActivity(intent);

    }

    private void openPowerChart() {

        // Pie Chart Section Names
        PowerChart chart = getChartItems();
        String[] code = chart.getPowerTypeNames() == null ? new String[]{
                "Hydro 20%", "Thermal 80%"} : chart.getPowerTypeNames();

        // Pie Chart Section Value
        double[] defaultValues = {20.0, 80.0};
        double[] distribution = chart.getPowerTypeDistribution() == null ? defaultValues : chart.getPowerTypeDistribution();

        // Color of each Pie Chart Sections
        int[] colors = {Color.GREEN, Color.RED};

        // Instantiating CategorySeries to plot Pie Chart
        CategorySeries distributionSeries = new CategorySeries("GENERATION DISTRIBUTION BY ENERGY");
        for (int i = 0; i < distribution.length; i++) {
            // Adding a slice with its values and name to the Pie Chart
            distributionSeries.add(code[i], distribution[i]);
        }

        // Instantiating a renderer for the Pie Chart
        DefaultRenderer defaultRenderer = new DefaultRenderer();
        for (int i = 0; i < distribution.length; i++) {
            SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
            seriesRenderer.setColor(colors[i]);
            seriesRenderer.setDisplayChartValues(true);
            // Adding a renderer for a slice
            defaultRenderer.addSeriesRenderer(seriesRenderer);
        }

        defaultRenderer.setChartTitle("GENERATION DISTRIBUTION BY ENERGY");
        defaultRenderer.setChartTitleTextSize(20);
        defaultRenderer.setLabelsColor(R.color.darkText);
        defaultRenderer.setLabelsTextSize(25.0F);
        defaultRenderer.setLegendTextSize(25.0F);
        defaultRenderer.setBackgroundColor(R.color.badge);
        defaultRenderer.setApplyBackgroundColor(true);
        defaultRenderer.setZoomButtonsVisible(true);

        // Creating an intent to plot PIE chart using dataset and multipleRenderer
        Intent intent = ChartFactory.getPieChartIntent(getBaseContext(), distributionSeries, defaultRenderer, "AChartEnginePieChartDemo");

        // Start Activity
        startActivity(intent);

    }

    private PowerChart getChartItems(){
        List<PowerChart> chartList = new ArrayList<>();
        PowerChart chart = new PowerChart();
        try {
            Dao<PowerChart, Long> dao = dbService.getDao(PowerChart.class);
            QueryBuilder<PowerChart, Long> queryBuilder = dao.queryBuilder();
            queryBuilder.orderBy("chartId",false);
            chartList = queryBuilder.query();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!chartList.isEmpty()){
            String[] titles = new String[3];
            double[] data = new double[3];

            String[] powerTypeTitles = new String[2];
            double[] powerTypeData = new double[2];
            for (int i=0; i<1; i++){
                titles[i] = "GENCOs " + chartList.get(0).getGenco()+ "%";
                titles[i+1] = "NIPP " + chartList.get(0).getNipp()+ "%";
                titles[i+2] = "IPP " + chartList.get(0).getIpp()+ "%";

                data[i] = chartList.get(0).getGenco();
                data[i+1] = chartList.get(0).getNipp();
                data[i+2] = chartList.get(0).getIpp();

                powerTypeTitles[i] = "Hydro " + chartList.get(0).getHydro()+ "%";
                powerTypeTitles[i+1] = "Thermal " + chartList.get(0).getThermal()+ "%";

                powerTypeData[i] = chartList.get(0).getHydro();
                powerTypeData[i+1] = chartList.get(0).getThermal();

            }
            chart.setOwnerShipDistribution(data);
            chart.setOwnerShipNames(titles);
            chart.setPowerTypeNames(powerTypeTitles);
            chart.setPowerTypeDistribution(powerTypeData);
        }
        return  chart;
    }

    /* private void openChart(){
         int[] x = { 1,2,3,4,5,6,7};
         double[] availableGen = { 6000,6500,6700,7000,6800,7500,7700,6800};
         double[] totalCon = {4200, 4700, 4900, 4800, 3600, 4000, 4300, 3400 };
         double[] peakGen = {3200, 3700, 3900, 3800, 3800, 3800, 3700, 3400 };
         double[] averageGen = {2800, 2700, 2900, 3000, 3600, 3300, 3630, 2600 };

         // Creating an  XYSeries for Income
         XYSeries avGenSeries = new XYSeries("Available Generation");
         // Creating an  XYSeries for Expense
         XYSeries peakGenSeries = new XYSeries("Peak Generation");
         // Adding data to Income and Expense Series
         XYSeries avrGenSeries = new XYSeries("Average Generation");
         // Adding data to Income and Expense Series
         XYSeries totalLossSeries = new XYSeries("Total Constrained Generation");
         // Adding data to Income and Expense Series
         for(int i=0;i<x.length;i++){
             avGenSeries.add(x[i], availableGen[i]);
             totalLossSeries.add(x[i],totalCon[i]);
             peakGenSeries.add(x[i], peakGen[i]);
             avrGenSeries.add(x[i], averageGen[i]);
         }

         // Creating a dataset to hold each series
         XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
         // Adding Avaailable Generation Series to the dataset
         dataset.addSeries(avGenSeries);
         // Adding Total Loss Series to dataset
         dataset.addSeries(totalLossSeries);
         //Adding Peak Generation to dataset
         dataset.addSeries(avrGenSeries);
         //Adding Peak Generation to dataset
         dataset.addSeries(peakGenSeries);

         // Creating XYSeriesRenderer to customize avGenSeries
         XYSeriesRenderer avGenRenderer = new XYSeriesRenderer();
         avGenRenderer.setColor(Color.BLUE);
         avGenRenderer.setPointStyle(PointStyle.DIAMOND);
         avGenRenderer.setFillPoints(true);
         avGenRenderer.setLineWidth(4);
         avGenRenderer.setDisplayChartValues(true);

         // Creating XYSeriesRenderer to customize totalLossSeries
         XYSeriesRenderer totalLossRenderer = new XYSeriesRenderer();
         totalLossRenderer.setColor(Color.RED);
         totalLossRenderer.setPointStyle(PointStyle.SQUARE);
         totalLossRenderer.setFillPoints(true);
         totalLossRenderer.setLineWidth(4);
         totalLossRenderer.setDisplayChartValues(true);

         // Creating XYSeriesRenderer to customize peakGenSeries
         XYSeriesRenderer peakGenRenderer = new XYSeriesRenderer();
         peakGenRenderer.setColor(Color.GREEN);
         peakGenRenderer.setPointStyle(PointStyle.SQUARE);
         peakGenRenderer.setFillPoints(true);
         peakGenRenderer.setLineWidth(4);
         peakGenRenderer.setDisplayChartValues(true);

         // Creating XYSeriesRenderer to customize averageGenSeries
         XYSeriesRenderer avrGenRenderer = new XYSeriesRenderer();
         avrGenRenderer.setColor(Color.YELLOW);
         avrGenRenderer.setPointStyle(PointStyle.SQUARE);
         avrGenRenderer.setFillPoints(true);
         avrGenRenderer.setLineWidth(4);
         avrGenRenderer.setDisplayChartValues(true);

         // Creating a XYMultipleSeriesRenderer to customize the whole chart
         XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
         multiRenderer.setXLabels(0);
         multiRenderer.setChartTitle("Power Generation Chart");
         multiRenderer.setXTitle("Days");
         multiRenderer.setYTitle("MegaWatts (MW)");
         multiRenderer.setChartTitleTextSize(32.0F);
         multiRenderer.setLegendTextSize(24.0F);
         multiRenderer.setLabelsTextSize(20.0F);
         multiRenderer.setAxisTitleTextSize(22.0F);
         multiRenderer.setChartValuesTextSize(30.0F);
         multiRenderer.setMarginsColor(R.color.darkText);
         multiRenderer.setShowAxes(true);
         multiRenderer.setGridColor(R.color.darkText);
         multiRenderer.setBackgroundColor(R.color.lightGrey);
         multiRenderer.setZoomButtonsVisible(true);
         multiRenderer.setShowCustomTextGrid(true);
         multiRenderer.setShowGridX(true);
         multiRenderer.setShowGridY(true);
         multiRenderer.setZoomEnabled(true);

         for(int i=0;i<x.length;i++){
             multiRenderer.addXTextLabel(i+1, mMonth[i]);
         }

         // Adding Renderers to multipleRenderer
         // Note: The order of adding dataseries to dataset and renderers to multipleRenderer
         // should be same
         multiRenderer.addSeriesRenderer(avGenRenderer);
         multiRenderer.addSeriesRenderer(totalLossRenderer);
         multiRenderer.addSeriesRenderer(avrGenRenderer);
         multiRenderer.addSeriesRenderer(peakGenRenderer);

         // Creating an intent to plot line chart using dataset and multipleRenderer
         Intent intent = ChartFactory.getLineChartIntent(getBaseContext(), dataset, multiRenderer);

         // Start Activity
         startActivity(intent);
     }

     */
    /*private PowerStats getStatisticsData(List<PowerStats> list) {
        double[] gen = new double[7];
        double[] average = new double[7];
        double[] peak = new double[7];
        double[] loss = new double[7];

        int size = list.size() >= 7 ? 7 : list.size();

        for (int i = 0; i < size; i++) {
            gen[i] = list.get(i).getAvailableCapMW();
            average[i] = list.get(i).getAverageGenMW();
            peak[i] = list.get(i).getPeakGenMW();
            loss[i] = list.get(i).getTotalMW();
        }
        PowerStats stats = new PowerStats();
        stats.setAvailableGenerationData(gen);
        stats.setAverageGenerationData(average);
        stats.setPeakGenerationData(peak);
        stats.setTotalLossData(loss);

        return stats;

    }*/

    @Override
    public int getMenuItemType() {
        return NAVIGATION_MENU_ITEM_POWER_STATS;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_stats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            if(WebServiceImpl.deviceIsConnected(PowerStatsActivity.this)) {
                loadNewData();
            } else {
                Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private class ReportLoader extends AsyncTask<Void, Void, Boolean> {

        Long idAfter;
        Long idBefore;
        DBService dbService = DBService.getInstance(PowerStatsActivity.this);

        public ReportLoader(Long dateBefore, Long dateAfter) {
            this.idBefore = dateBefore;
            this.idAfter = dateAfter;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBarTop.setVisibility(View.VISIBLE);
            Pref.saveToSharedPreference(PowerStatsActivity.this, Constant.LOADING_DATA.name(), true);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            ResponseObject responseObject;
            if (idAfter == null && idBefore == null) {
                responseObject = WebServiceImpl.getReports();
                Log.i("PowerStatActivity", "Getting Reports");
                if (responseObject != null && responseObject.getStatusCode() == 200) {
                    List<PowerStats> statsList = responseObject.getPowerStatsList();
                    for (PowerStats news : statsList) {
                        try {
                            Dao<PowerStats, Long> dao = dbService.getDao(PowerStats.class);
                            dao.create(news);
                        } catch (Exception e) {
                            Log.i("PowerStatActivity", e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    for (PowerStats stat : statsList) {
                        powerStatsList.add(stat);
                    }
                    return true;
                }
            } else if (idAfter != null && idBefore == null) {
                responseObject = WebServiceImpl.getReportAfter(idAfter);
                Log.i("PowerStatActivity", "Getting Report After");
                if (responseObject != null && responseObject.getStatusCode() == 200) {

                        List<PowerStats> statsList = responseObject.getPowerStatsList();
                        for (PowerStats news : statsList) {
                            try {
                                Dao<PowerStats, Long> dao = dbService.getDao(PowerStats.class);
                                dao.create(news);
                            } catch (Exception e) {
                                Log.i("PowerStatActivity", e.getMessage());
                                e.printStackTrace();
                            }
                        }
                        for (PowerStats stat : statsList) {
                            powerStatsList.add(stat);
                        }
                        return true;
                    }

            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean == Boolean.TRUE) {
                loadData();
            }
            progressBarTop.setVisibility(View.GONE);
            Pref.saveToSharedPreference(PowerStatsActivity.this, Constant.LOADING_DATA.name(), false);
        }
    }

    private class ChartLoader extends AsyncTask<Void, Void, Boolean> {

        Long idAfter;
        Long idBefore;
        DBService dbService = DBService.getInstance(PowerStatsActivity.this);

        public ChartLoader(Long dateBefore, Long dateAfter) {
            this.idBefore = dateBefore;
            this.idAfter = dateAfter;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBarTop.setVisibility(View.VISIBLE);
            Pref.saveToSharedPreference(PowerStatsActivity.this, Constant.LOADING_DATA.name(), true);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            ResponseObject responseObject;
            if (idAfter == null && idBefore == null) {
                Log.i("PowerStatActivity", "Getting Chart");
                responseObject = WebServiceImpl.getChart();
                if (responseObject != null && responseObject.getStatusCode() == 200) {
                    List<PowerChart> powerChartList = responseObject.getPowerChartList();
                    for (PowerChart chart : powerChartList) {
                        try {
                            Dao<PowerChart, Long> dao = dbService.getDao(PowerChart.class);
                            dao.create(chart);
                        } catch (Exception e) {
                            Log.i("PowerStatActivity", e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    return true;
                }
            } else if (idAfter != null && idBefore == null) {
                Log.i("PowerStatActivity", "Getting Chart After");
                responseObject = WebServiceImpl.getChartAfter(idAfter);
                if (responseObject != null && responseObject.getStatusCode() == 200) {
                    List<PowerChart> powerChartList = responseObject.getPowerChartList();
                    for (PowerChart chart : powerChartList) {
                        try {
                            Dao<PowerChart, Long> dao = dbService.getDao(PowerChart.class);
                            dao.create(chart);
                        } catch (Exception e) {
                            Log.i("PowerStatActivity", e.getMessage());
                            e.printStackTrace();
                        }

                    }
                    return true;
                }
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean == Boolean.TRUE) {
                loadData();
            }
            progressBarTop.setVisibility(View.GONE);
            Pref.saveToSharedPreference(PowerStatsActivity.this, Constant.LOADING_DATA.name(), false);
        }
    }
}

