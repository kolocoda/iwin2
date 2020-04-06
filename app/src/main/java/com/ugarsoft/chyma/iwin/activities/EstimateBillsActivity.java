package com.ugarsoft.chyma.iwin.activities;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.models.AllMonth;
import com.ugarsoft.chyma.iwin.models.Bill;
import com.ugarsoft.chyma.iwin.utils.CompatUtil;
import com.ugarsoft.chyma.iwin.utils.Constant;
import com.ugarsoft.chyma.iwin.utils.DBQuery;
import com.ugarsoft.chyma.iwin.utils.EnergyCalculator;
import com.ugarsoft.chyma.iwin.utils.KeyboardUtil;
import com.ugarsoft.chyma.iwin.utils.MessageUtil;
import com.ugarsoft.chyma.iwin.utils.Pref;

import org.apache.commons.lang.WordUtils;

import java.util.List;

public class EstimateBillsActivity extends AppCompatActivity {

    private EditText noOfOccupants;
    private EditText hoursPerDay;
    private CardView selectMonth;
    private Spinner discoSpinner;
    private Button step2Button;
    private Button step21Button;
    private Button step23Button;
    private Button step32Button;
    private Button step34Button;
    private Button step43Button;
    private Button submitButton;

    private View step1View;
    private View step2View;
    private View step3View;
    private View step4View;

    private EditText incLight;
    private EditText lite;
    private  EditText energyLight;
    private EditText securityLight;
    private EditText fluorescentLight;
    private EditText fans;
    private EditText humidifier;
    private EditText fridge;
    private EditText freezer;
    private EditText acOneHp;
    private EditText acOneHalfHp;
    private EditText acTwoHp;
    private EditText iron;
    private EditText toaster;
    private EditText waterHeater;
    private EditText electricStove;
    private EditText washingMachine;
    private EditText pumpMotor;
    private EditText blender;
    private EditText homeTheatre;
    private EditText microwave;
    private EditText plasmaTv;
    private EditText lcdTv;
    private EditText ledTv;
    private EditText printer;
    private EditText hairDryer;
    private EditText vacuumCleaner;
    private EditText gameConsole;
    private EditText phonecharger;
    private EditText coffeeMaker;
    private EditText waterDispenser;
    private EditText fishAquarium;
    private EditText electricKettle;
    private EditText deepFrier;
    private TextView selectMonthText;
    private CardView selectMonthCard;
    private  View estimator;
    private boolean back1, back2, back3, back4;

    private AllMonth selectedMonth;


    private boolean shouldAnimate = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate_bills);
        setupViews();
        addItemsToSpinner();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupViews(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (CompatUtil.hasLolipop() && shouldAnimate) {
            toolbar.setAlpha(0f);
            toolbar.animate().translationZBy(40f).alphaBy(1f).setDuration(1500).start();
        }

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.colorPrimary));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        step1View = findViewById(R.id.step1View);
        step2View = findViewById(R.id.step2View);
        step3View = findViewById(R.id.step3View);
        step4View = findViewById(R.id.step4View);
        step2Button = (Button) findViewById(R.id.step2Button);
        step21Button = (Button) findViewById(R.id.step21Button);
        step23Button = (Button) findViewById(R.id.step23Button);
        step32Button = (Button) findViewById(R.id.step32Button);
        step34Button = (Button) findViewById(R.id.step34Button);
        step43Button = (Button) findViewById(R.id.step43Button);
        submitButton = (Button) findViewById(R.id.submitButton);
        selectMonthText = (TextView) findViewById(R.id.selectMonthText);
        selectMonthCard = (CardView) findViewById(R.id.selectMonthCard);

        discoSpinner = (Spinner) findViewById(R.id.discoSpinner);

        lite = (EditText) findViewById(R.id.lightbulb);
        energyLight = (EditText) findViewById(R.id.energy_saver);
        securityLight = (EditText) findViewById(R.id.noOfSecurityLight);
        fluorescentLight = (EditText) findViewById(R.id.noOfFluorescent);
        fans = (EditText) findViewById(R.id.noOfFans);
        humidifier = (EditText) findViewById(R.id.noOfRoomCooler);
        acOneHp = (EditText) findViewById(R.id.noOfAc1hp);
        acOneHalfHp = (EditText) findViewById(R.id.noOfAc15Hp);
        acTwoHp = (EditText) findViewById(R.id.noOfAc2hp);
        iron = (EditText) findViewById(R.id.noOfElectricIron);
        toaster = (EditText) findViewById(R.id.noOfToaster);
        waterHeater = (EditText) findViewById(R.id.noOfWaterHeater);

        electricStove = (EditText) findViewById(R.id.noOfElectricStove);
        washingMachine = (EditText) findViewById(R.id.noOfWashingMashine);
        pumpMotor = (EditText) findViewById(R.id.noOfPump);
        blender = (EditText) findViewById(R.id.noOfBlender);
        homeTheatre = (EditText) findViewById(R.id.noOfHomeTheatre);
        microwave = (EditText) findViewById(R.id.noOfMicrowave);
        plasmaTv = (EditText) findViewById(R.id.noOfPlasmaTV);
        lcdTv = (EditText) findViewById(R.id.noOfLCDTV);
        ledTv = (EditText) findViewById(R.id.noOfLEDTV);
        printer = (EditText) findViewById(R.id.noOfPrinter);
        hairDryer = (EditText) findViewById(R.id.noOfDryer);

        vacuumCleaner = (EditText) findViewById(R.id.noOfVacuumCleaner);
        gameConsole = (EditText) findViewById(R.id.noOfGameConsole);
        phonecharger = (EditText) findViewById(R.id.noOfPhoneCharger);
        coffeeMaker = (EditText) findViewById(R.id.noOfCoffeeMaker);
        waterDispenser = (EditText) findViewById(R.id.noOfWaterDispenser);
        fishAquarium = (EditText) findViewById(R.id.noOfFishSomething);
        electricKettle = (EditText) findViewById(R.id.noOfElectricKettle);
        deepFrier = (EditText) findViewById(R.id.noOfDeepFrier);
        fridge = (EditText) findViewById(R.id.noOfFridge);
        freezer = (EditText) findViewById(R.id.noOfFreezer);

        noOfOccupants = (EditText) findViewById(R.id.noOfOccupants);
        hoursPerDay = (EditText) findViewById(R.id.hoursPerDay);

        showViewI();

        estimator = findViewById(R.id.estimator);

        SetClickListeners();
        KeyboardUtil.setupUI(estimator, EstimateBillsActivity.this);

    }

    private void addItemsToSpinner(){
        List<String> list = DBQuery.getDiscoList(EstimateBillsActivity.this);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        discoSpinner.setAdapter(dataAdapter);
    }


    private void SetClickListeners() {
        step2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showViewII();
            }
        });
        step21Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showViewI();

            }
        });
        step23Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showViewIII();
            }
        });
        step32Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showViewII();

            }
        });
        step34Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showViewIV();
            }
        });
        step43Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showViewIII();
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyItems();
            }
        });
        selectMonthCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMonthChooser();
            }
        });

    }

    private void showViewIV() {
        step1View.setVisibility(View.GONE);
        step2View.setVisibility(View.GONE);
        step3View.setVisibility(View.GONE);
        step4View.setVisibility(View.VISIBLE);
        back1 = false;
        back2= false;
        back3 =false;
        back4 = true;
    }

    @Override
    public void onBackPressed() {
        if (back1) {
            super.onBackPressed();
        } else if(back2){
            showViewI();
        } else if(back3){
            showViewII();
        } else if(back4){
            showViewIII();
        }
    }

    private void showViewIII() {
        step1View.setVisibility(View.GONE);
        step2View.setVisibility(View.GONE);
        step3View.setVisibility(View.VISIBLE);
        step4View.setVisibility(View.GONE);
        back1 = false;
        back2= false;
        back3 =true;
        back4 = false;
    }

    private void showViewII() {
        step1View.setVisibility(View.GONE);
        step2View.setVisibility(View.VISIBLE);
        step3View.setVisibility(View.GONE);
        step4View.setVisibility(View.GONE);
        back1 = false;
        back2= true;
        back3 =false;
        back4 = false;
    }

    private void showViewI() {
        step1View.setVisibility(View.VISIBLE);
        step2View.setVisibility(View.GONE);
        step3View.setVisibility(View.GONE);
        step4View.setVisibility(View.GONE);
        back1 = true;
        back2= false;
        back3 =false;
        back4 = false;
    }


    private void verifyItems(){
        if(selectedMonth == null){
            MessageUtil.showAlert(EstimateBillsActivity.this, "Please select the month");
            showViewI();
        } else if(noOfOccupants.getText().toString().length() == 0){
            MessageUtil.showAlert(EstimateBillsActivity.this, "Please enter the number of house occupants");
            showViewI();
        } else if (hoursPerDay.getText().toString().length() == 0){
            MessageUtil.showAlert(EstimateBillsActivity.this, "Please enter the average number of hours per day");
            showViewI();
        } else {
            evaluateInputs();
        }
    }

    private void evaluateInputs() {

        Double tariff = getTariffFromSpinner();
        int month = selectedMonth.getMonthCode();

        Bill bill = new Bill();

        bill.setCompany(tariff);
        bill.setMonth(month);
        bill.setHoursPerDay(Integer.parseInt(hoursPerDay.getText().toString()));
        bill.setNoOfOccupants(Integer.parseInt(noOfOccupants.getText().toString()));

        bill.setIncandescentLights(lite.getText().length() == 0 ? 0 : Integer.parseInt(lite.getText().toString()));
        bill.setEnergyLights(energyLight.getText().length() == 0 ? 0 : Integer.parseInt(energyLight.getText().toString()));
        bill.setSecurityLights(securityLight.getText().length() == 0 ? 0 : Integer.parseInt(securityLight.getText().toString()));
        bill.setFluorescentLights(fluorescentLight.getText().length() == 0 ? 0 : Integer.parseInt(fluorescentLight.getText().toString()));
        bill.setFans(fans.getText().length() == 0 ? 0 : Integer.parseInt(fans.getText().toString()));
        bill.setHumidifier(humidifier.getText().length() == 0 ? 0 : Integer.parseInt(humidifier.getText().toString()));
        bill.setFridge(fridge.getText().length() == 0 ? 0 : Integer.parseInt(fridge.getText().toString()));
        bill.setFreezer(freezer.getText().length() == 0 ? 0 : Integer.parseInt(freezer.getText().toString()));
        bill.setAcOneHp(acOneHp.getText().length() == 0 ? 0 : Integer.parseInt(acOneHp.getText().toString()));
        bill.setAcOneHalfHp(acOneHalfHp.getText().length() == 0 ? 0 : Integer.parseInt(acOneHalfHp.getText().toString()));
        bill.setAcTwoHp(acTwoHp.getText().length() == 0 ? 0 : Integer.parseInt(acTwoHp.getText().toString()));

        bill.setIron(iron.getText().length() == 0 ? 0 : Integer.parseInt(iron.getText().toString()));
        bill.setToaster(toaster.getText().length() == 0 ? 0 : Integer.parseInt(toaster.getText().toString()));
        bill.setWaterHeater(waterHeater.getText().length() == 0 ? 0 : Integer.parseInt(waterHeater.getText().toString()));
        bill.setElectricStove(electricStove.getText().length() == 0 ? 0 : Integer.parseInt(electricStove.getText().toString()));
        bill.setWashingMachine(washingMachine.getText().length() == 0 ? 0 : Integer.parseInt(washingMachine.getText().toString()));
        bill.setPumpMotor(pumpMotor.getText().length() == 0 ? 0 : Integer.parseInt(pumpMotor.getText().toString()));
        bill.setBlender(blender.getText().length() == 0 ? 0 : Integer.parseInt(blender.getText().toString()));
        bill.setHomeTheatre(homeTheatre.getText().length() == 0 ? 0 : Integer.parseInt(homeTheatre.getText().toString()));
        bill.setMicrowave(microwave.getText().length() == 0 ? 0 : Integer.parseInt(microwave.getText().toString()));
        bill.setPlasmaTv(plasmaTv.getText().length() == 0 ? 0 : Integer.parseInt(plasmaTv.getText().toString()));
        bill.setLcdTv(lcdTv.getText().length() == 0 ? 0 : Integer.parseInt(lcdTv.getText().toString()));

        bill.setLedTv(ledTv.getText().length() == 0 ? 0 : Integer.parseInt(ledTv.getText().toString()));
        bill.setPrinter(printer.getText().length() == 0 ? 0 : Integer.parseInt(printer.getText().toString()));
        bill.setHairDryer(hairDryer.getText().length() == 0 ? 0 : Integer.parseInt(hairDryer.getText().toString()));
        bill.setVacuumCleaner(vacuumCleaner.getText().length() == 0 ? 0 : Integer.parseInt(vacuumCleaner.getText().toString()));
        bill.setGameConsole(gameConsole.getText().length() == 0 ? 0 : Integer.parseInt(gameConsole.getText().toString()));
        bill.setPhoneCharger(phonecharger.getText().length() == 0 ? 0 : Integer.parseInt(phonecharger.getText().toString()));
        bill.setCoffeemaker(coffeeMaker.getText().length() == 0 ? 0 : Integer.parseInt(coffeeMaker.getText().toString()));
        bill.setWaterDispenser(waterDispenser.getText().length() == 0 ? 0 : Integer.parseInt(waterDispenser.getText().toString()));
        bill.setFishAquarium(fishAquarium.getText().length() == 0 ? 0 : Integer.parseInt(fishAquarium.getText().toString()));
        bill.setElectricKettle(electricKettle.getText().length() == 0 ? 0 : Integer.parseInt(electricKettle.getText().toString()));
        bill.setDeepFrier(deepFrier.getText().length() == 0 ? 0 : Integer.parseInt(deepFrier.getText().toString()));

        Log.i("Input ","app1: " + bill.getIncandescentLights());
        Log.i("Input ","app2: " + bill.getEnergyLights());
        Log.i("Input ","app3: " + bill.getSecurityLights());
        Log.i("Input ","app4: " + bill.getFluorescentLights());
        Log.i("Input ","app5: " + bill.getFans());
        Log.i("Input ","app6: " + bill.getHumidifier());
        Log.i("Input ","app7: " + bill.getFridge());
        Log.i("Input ","app8: " + bill.getFreezer());
        Log.i("Input ","app9: " + bill.getAcOneHp());
        Log.i("Input ","app10: " + bill.getAcOneHalfHp());


        String totalKwh =  EnergyCalculator.totalKwh(bill) + "KWh";
        String percentContribution = EnergyCalculator.calculatePercentage(bill);
        String monthlyBill = "NGN " + EnergyCalculator.calculateBillperMonth(bill);



        Pref.saveBillToSharedPref(EstimateBillsActivity.this, bill);
       Pref.saveToSharedPreference(EstimateBillsActivity.this, Constant.TOTAL_KWH.name(),totalKwh);
        Pref.saveToSharedPreference(EstimateBillsActivity.this, Constant.PERCENT_CONTRIBUTION.name(),percentContribution);
        Pref.saveToSharedPreference(EstimateBillsActivity.this, Constant.MONTHLY_BILL.name(),monthlyBill);

        Intent intent = new Intent(EstimateBillsActivity.this, BillResultActivity.class);
        startActivity(intent);
    }

    private Double getTariffFromSpinner() {
        String discoName =  discoSpinner.getSelectedItem().toString();
        return DBQuery.getDiscoTariffByDiscoName(EstimateBillsActivity.this, discoName);
    }

    private void showMonthChooser(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

            final List<AllMonth> months = DBQuery.getAllMonths(EstimateBillsActivity.this);

            CharSequence[] sequences = new CharSequence[months.size()];
            for (int index = 0; index < sequences.length; index++) {
                sequences[index] = WordUtils.capitalize((months.get(index).getMonthName()).toLowerCase());
            }
            builder.setTitle("Select Month");
            builder.setSingleChoiceItems(sequences, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    selectedMonth = months.get(i);
                    selectMonthText.setText("Month: " + WordUtils.capitalize((selectedMonth.getMonthName()).toLowerCase()));

                }
            });
            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            builder.show();


    }
}
