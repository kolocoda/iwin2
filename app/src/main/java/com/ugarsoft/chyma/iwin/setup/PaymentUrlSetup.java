package com.ugarsoft.chyma.iwin.setup;

import com.ugarsoft.chyma.iwin.models.PaymentUrl;
import com.ugarsoft.chyma.iwin.models.Tariff;

import java.util.ArrayList;

/**
 * Created by Chyma on 5/16/2016.
 */
public class PaymentUrlSetup {

    public static ArrayList<PaymentUrl> getAllEPayUrls() {
        ArrayList<PaymentUrl> payChannels = new ArrayList<>();

        PaymentUrl u = new PaymentUrl();
        u.setCode(Tariff.aedc);
        u.setTitle("Pay Using LightUp");
        u.setUrl("https://lightup.com.ng/");

        PaymentUrl u1 = new PaymentUrl();
        u1.setCode(Tariff.aedc);
        u1.setTitle("Pay Using BuyPower");
        u1.setUrl("https://buypower.ng/auth/login");

        PaymentUrl u3 = new PaymentUrl();
        u3.setCode(Tariff.bedcS);
        u3.setTitle("Pay Using Quickteller (Postpaid)");
        u3.setUrl("https://www.quickteller.com/bedcpostpaid?option=");

        PaymentUrl u2 = new PaymentUrl();
        u2.setCode(Tariff.bedcS);
        u2.setTitle("Pay Using Quickteller");
        u2.setUrl("https://www.quickteller.com/bedcprepaid");

        PaymentUrl u4 = new PaymentUrl();
        u4.setCode(Tariff.eedc);
        u4.setTitle("Pay Using Quickteller");
        u4.setUrl("https://www.quickteller.com/eedcprepaid?option=");

        PaymentUrl u5 = new PaymentUrl();
        u5.setCode(Tariff.eedc);
        u5.setTitle("Pay Using Quickteller (Postpaid)");
        u5.setUrl("https://www.quickteller.com/phcnpostpaidenugu?option=");

        PaymentUrl u6 = new PaymentUrl();
        u6.setCode(Tariff.ekedcS);
        u6.setTitle("Pay Using eTransact");
        u6.setUrl("https://www.payoutlet.net/");

        PaymentUrl u7 = new PaymentUrl();
        u7.setCode(Tariff.ekedcS);
        u7.setTitle("Pay Using oneCard");
        u7.setUrl("http://www.mytopupnigeria.com/topUp_page.do?abbr=EKEDC");

        PaymentUrl u8 = new PaymentUrl();
        u8.setCode(Tariff.ekedcS);
        u8.setTitle("Pay Using Quickteller");
        u8.setUrl("https://www.quickteller.com/ekedpprepaid?option=");

        PaymentUrl u9 = new PaymentUrl();
        u9.setCode(Tariff.ekedcS);
        u9.setTitle("Pay Using Quickteller (Postpaid)");
        u9.setUrl("https://www.quickteller.com/ekedppostpaid?option=");

        PaymentUrl u10 = new PaymentUrl();
        u10.setCode(Tariff.ibedc);
        u10.setTitle("Pay Using Quickteller (Postpaid)");
        u10.setUrl("https://www.quickteller.com/ibedc?option=");

        PaymentUrl u11 = new PaymentUrl();
        u11.setCode(Tariff.ikedcS);
        u11.setTitle("Pay Using Dashboard");
        u11.setUrl("http://www.dashboardng.com/Payments/PayBills.aspx");

        PaymentUrl u12 = new PaymentUrl();
        u12.setCode(Tariff.ikedcS);
        u12.setTitle("Pay Using Quickteller");
        u12.setUrl("https://www.quickteller.com/ikejaelectricprepaid?option=");

        PaymentUrl u13 = new PaymentUrl();
        u13.setCode(Tariff.ikedcS);
        u13.setTitle("Pay Using Quickteller (Postpaid");
        u13.setUrl("https://www.quickteller.com/ikejaelectric?option=");

        PaymentUrl u14 = new PaymentUrl();
        u14.setCode(Tariff.kedcA);
        u14.setTitle("Pay Using Quickteller");
        u14.setUrl("https://www.quickteller.com/kedcoprepaid?option=");

        PaymentUrl u15 = new PaymentUrl();
        u15.setCode(Tariff.kedcA);
        u15.setTitle("Pay Using Quickteller (Postpaid)");
        u15.setUrl("https://www.quickteller.com/kedcopostpd?option=");

        PaymentUrl u16 = new PaymentUrl();
        u16.setCode(Tariff.phedc);
        u16.setTitle("Pay Using Quickteller (Postpaid)");
        u16.setUrl("https://www.quickteller.com/phedpostpaid");

        PaymentUrl u17 = new PaymentUrl();
        u17.setCode(Tariff.yedcS);
        u17.setTitle("Pay Using Quickteller (Postpaid)");
        u17.setUrl("https://www.quickteller.com/yedc?option=");

        payChannels.add(u);
        payChannels.add(u1);
        payChannels.add(u2);
        payChannels.add(u3);
        payChannels.add(u4);
        payChannels.add(u5);
        payChannels.add(u6);
        payChannels.add(u7);
        payChannels.add(u8);
        payChannels.add(u9);
        payChannels.add(u10);
        payChannels.add(u11);
        payChannels.add(u12);
        payChannels.add(u13);
        payChannels.add(u14);
        payChannels.add(u15);
        payChannels.add(u16);
        payChannels.add(u17);


        return payChannels;
    }
}
