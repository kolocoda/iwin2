package com.ugarsoft.chyma.iwin.setup;

import com.ugarsoft.chyma.iwin.models.Contact;
import com.ugarsoft.chyma.iwin.models.Tariff;

import java.util.ArrayList;

/**
 * Created by Chyma on 5/11/2016.
 */
public class ContactSetup {

    public static ArrayList<Contact> getAllContacts(){
        ArrayList<Contact> allContacts = new ArrayList<>();

        Contact c = new Contact();
        c.setName("Abuja Electricity Distribution");
        c.setDesc("FCT, Nassarawa, Niger, Kogi");
        c.setCode(Tariff.aedc);
        c.setContent("<p>The Abuja Electricity Distribution Company (Operated by Kann Power Consortium) has a central control unit for customers. Customers benefiting from this DISCO will be patched to their respective area units when they call these numbers.</p>\n" +
                "\t\t\t\t<p><b>Line 1:</b> 08150181818</p>\n" +
                "\t\t\t\t<p><b>Line 2:</b> 08150191919</p>");

        Contact c1 = new Contact();
        c1.setName("Benin Electricity Distribution");
        c1.setDesc("Ondo, Edo, Delta");
        c1.setCode(Tariff.bedcS);
        c1.setContent("<p>Business Units of the Benin Distribution Company (operated by Vigeo Holdings):</p>\n" +
                "            \t<table data-role=\"table\" data-mode=\"columntoggle\" class=\"ui-responsive\" id=\"myTable\">\n" +
                "            \t\t<thead>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<th data-priority=\"2\">ID</th>\n" +
                "            \t\t\t\t<th>Business Unit</th>\n" +
                "            \t\t\t\t<th data-priority=\"1\">Phone number</th>\n" +
                "            \t\t\t\t<th data-priority=\"3\">Email</th>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t</thead>\n" +
                "            \t\t<tbody>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>1</td>\n" +
                "            \t\t\t\t<td>Headquarters</td>\n" +
                "            \t\t\t\t<td>08023544517,</br> 09038857404</td>\n" +
                "            \t\t\t\t<td>ccoheadoffice@gmail.com</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>2</td>\n" +
                "            \t\t\t\t<td>Control Room</td>\n" +
                "            \t\t\t\t<td>09038857357</td>\n" +
                "            \t\t\t\t<td>customercompliants@bedcpower.com</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>3</td>\n" +
                "            \t\t\t\t<td>Auchi</td>\n" +
                "            \t\t\t\t<td>08036955344,</br> 08027783669</td>\n" +
                "            \t\t\t\t<td>ccoauchi@gmail.com</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>4</td>\n" +
                "            \t\t\t\t<td>Ekete</td>\n" +
                "            \t\t\t\t<td>07033999922, </br>08027783665</td>\n" +
                "            \t\t\t\t<td>ccoekete@gmail.com</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>5</td>\n" +
                "            \t\t\t\t<td>Evbuotubu</td>\n" +
                "            \t\t\t\t<td>08039236850, </br>08027783652</td>\n" +
                "            \t\t\t\t<td>ccoevbuotubu@gmail.com</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>6</td>\n" +
                "            \t\t\t\t<td>Ikpoba Hall</td>\n" +
                "            \t\t\t\t<td>08061179143, </br>08027783572</td>\n" +
                "            \t\t\t\t<td>ccoikpobahill@gmail.com</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>7</td>\n" +
                "            \t\t\t\t<td>Sokponba</td>\n" +
                "            \t\t\t\t<td>08023511648, </br> 08062481899, </br>08027783559</td>\n" +
                "            \t\t\t\t<td>ccosokponba@gmail.com</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>8</td>\n" +
                "            \t\t\t\t<td>Ugbowo</td>\n" +
                "            \t\t\t\t<td>08032795126,</br> 08127520829, </br>08027783549</td>\n" +
                "            \t\t\t\t<td>ccougbowo@gmail.com</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>9</td>\n" +
                "            \t\t\t\t<td>Evboriaria</td>\n" +
                "            \t\t\t\t<td>07065398216, </br> 08056600185, </br>08027783547</td>\n" +
                "            \t\t\t\t<td>ccoevboriaria@gmail.com</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>10</td>\n" +
                "            \t\t\t\t<td>Uromi</td>\n" +
                "            \t\t\t\t<td>07067143292, </br>08027783523</td>\n" +
                "            \t\t\t\t<td>ccouromi@gmail.com</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>11</td>\n" +
                "            \t\t\t\t<td>Okada</td>\n" +
                "            \t\t\t\t<td>08067179202, </br> 08074426874, </br>08027783512</td>\n" +
                "            \t\t\t\t<td>ccookada@gmail.com</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>12</td>\n" +
                "            \t\t\t\t<td>Ado Ekiti</td>\n" +
                "            \t\t\t\t<td>07030079413, </br>08054209349, </br>08027783466</td>\n" +
                "            \t\t\t\t<td>ccoadoekiti@gmail.com</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>13</td>\n" +
                "            \t\t\t\t<td>Ido Ekiti</td>\n" +
                "            \t\t\t\t<td>07039090826, </br>08077304900,</br> 08027783918</td>\n" +
                "            \t\t\t\t<td>ccoidoekiti@gmail.com</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>14</td>\n" +
                "            \t\t\t\t<td>Agbor</td>\n" +
                "            \t\t\t\t<td>08058244529, </br> 08039611800, </br> 08027783917</td>\n" +
                "            \t\t\t\t<td>ccoagbor@gmail.com</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>15</td>\n" +
                "            \t\t\t\t<td>Asaba</td>\n" +
                "            \t\t\t\t<td>08039273896, </br>08027783872</td>\n" +
                "            \t\t\t\t<td>ccoasaba@gmail.com</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>16</td>\n" +
                "            \t\t\t\t<td>Warri</td>\n" +
                "            \t\t\t\t<td>08022220597, </br>08065593338, </br> 08027783816</td>\n" +
                "            \t\t\t\t<td>ccowarri@gmail.com</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>17</td>\n" +
                "            \t\t\t\t<td>Sapele</td>\n" +
                "            \t\t\t\t<td>08033926629, </br>08056638308, </br> 08027783814</td>\n" +
                "            \t\t\t\t<td>ccosapele@gmail.com</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>18</td>\n" +
                "            \t\t\t\t<td>Effurun</td>\n" +
                "            \t\t\t\t<td>08064250621, </br>0818346391, </br>08027783796</td>\n" +
                "            \t\t\t\t<td>ccoeffurun@gmail.com</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>19</td>\n" +
                "            \t\t\t\t<td>Obiaruku</td>\n" +
                "            \t\t\t\t<td>08063814155, </br> 08027783792</td>\n" +
                "            \t\t\t\t<td>ccoobiaruku@gmail.com</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>20</td>\n" +
                "            \t\t\t\t<td>Ughelli</td>\n" +
                "            \t\t\t\t<td>08068365605, </br>08071023071, </br>08027783783</td>\n" +
                "            \t\t\t\t<td>ccoughelli@gmail.com</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>21</td>\n" +
                "            \t\t\t\t<td>Ondo</td>\n" +
                "            \t\t\t\t<td>08060283251, </br>08077304900,</br> 08027784257</td>\n" +
                "            \t\t\t\t<td>ccoondo1@gmail.com</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>22</td>\n" +
                "            \t\t\t\t<td>Owo</td>\n" +
                "            \t\t\t\t<td>08038271361, </br>08098679895, </br> 08027784246</td>\n" +
                "            \t\t\t\t<td>ccoowo@gmail.com</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>23</td>\n" +
                "            \t\t\t\t<td>Akure</td>\n" +
                "            \t\t\t\t<td>07062166463, </br>08027784228</td>\n" +
                "            \t\t\t\t<td>ccoakure@gmail.com</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>24</td>\n" +
                "            \t\t\t\t<td>Igbara Oke</td>\n" +
                "            \t\t\t\t<td>08037494833, </br>08038630562,</br>08027784224</td>\n" +
                "            \t\t\t\t<td>ccoigbaraoke@gmail.com</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t</tbody>\n" +
                "            \t</table>\n" +
                "\t\t\t");

        Contact c2 = new Contact();
        c2.setName("Enugu Electricity Distribution");
        c2.setDesc("Anambra, Enugu, Ebonyi, Abia, Imo");
        c2.setCode(Tariff.eedc);
        c2.setContent("<p>Business Units of the Enugu Distribution Company (operated by Interstate Electrics):</p>\n" +
                "            \t<table data-role=\"table\" data-mode=\"columntoggle\" class=\"ui-responsive\" id=\"myTable\">\n" +
                "            \t\t<thead>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<th data-priority=\"2\">ID</th>\n" +
                "            \t\t\t\t<th>Business Unit</th>\n" +
                "            \t\t\t\t<th data-priority=\"1\">Phone number</th>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t</thead>\n" +
                "            \t\t<tbody>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>1</td>\n" +
                "            \t\t\t\t<td>Ogui</td>\n" +
                "            \t\t\t\t<td>09021001335</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>2</td>\n" +
                "            \t\t\t\t<td>Awkunanaw</td>\n" +
                "            \t\t\t\t<td>09021001336</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>3</td>\n" +
                "            \t\t\t\t<td>Abakpa</td>\n" +
                "            \t\t\t\t<td>09021001337</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>4</td>\n" +
                "            \t\t\t\t<td>Nsukka</td>\n" +
                "            \t\t\t\t<td>09021001338</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>5</td>\n" +
                "            \t\t\t\t<td>Owerri</td>\n" +
                "            \t\t\t\t<td>09021001339</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>6</td>\n" +
                "            \t\t\t\t<td>New Owerri</td>\n" +
                "            \t\t\t\t<td>09021001340</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>7</td>\n" +
                "            \t\t\t\t<td>Orlu</td>\n" +
                "            \t\t\t\t<td>09021001341</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>8</td>\n" +
                "            \t\t\t\t<td>Mbaise</td>\n" +
                "            \t\t\t\t<td>09021001342</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>9</td>\n" +
                "            \t\t\t\t<td>Umuahia</td>\n" +
                "            \t\t\t\t<td>09021001345</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>10</td>\n" +
                "            \t\t\t\t<td>Aba</td>\n" +
                "            \t\t\t\t<td>09021001346</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>11</td>\n" +
                "            \t\t\t\t<td>Ariaria</td>\n" +
                "            \t\t\t\t<td>09021001347</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>12</td>\n" +
                "            \t\t\t\t<td>Onitsha</td>\n" +
                "            \t\t\t\t<td>09021001348</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>13</td>\n" +
                "            \t\t\t\t<td>Ogbaru</td>\n" +
                "            \t\t\t\t<td>09021001349</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>14</td>\n" +
                "            \t\t\t\t<td>Ogidi</td>\n" +
                "            \t\t\t\t<td>09021001350</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>15</td>\n" +
                "            \t\t\t\t<td>Nnewi</td>\n" +
                "            \t\t\t\t<td>09021001351</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>16</td>\n" +
                "            \t\t\t\t<td>Ekwulobia</td>\n" +
                "            \t\t\t\t<td>09021001352</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>17</td>\n" +
                "            \t\t\t\t<td>Awka</td>\n" +
                "            \t\t\t\t<td>09021001353</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>18</td>\n" +
                "            \t\t\t\t<td>Abakaliki</td>\n" +
                "            \t\t\t\t<td>09021001354</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>19</td>\n" +
                "            \t\t\t\t<td>Headquaters</td>\n" +
                "            \t\t\t\t<td>08121020423</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t</tbody>\n" +
                "            \t</table>\n" +
                "\t\t\t");

        Contact c3 = new Contact();
        c3.setName("Eko Electricity Distribution");
        c3.setDesc("Lagos, Ogun");
        c3.setCode(Tariff.ekedcS);
        c3.setContent("<p>Business Units of the Eko Distribution Company:</p>\n" +
                "            \t<table data-role=\"table\" data-mode=\"columntoggle\" class=\"ui-responsive\" id=\"myTable\">\n" +
                "            \t\t<thead>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<th data-priority=\"2\">ID</th>\n" +
                "            \t\t\t\t<th>Business Unit</th>\n" +
                "            \t\t\t\t<th data-priority=\"1\">Phone number</th>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t</thead>\n" +
                "            \t\t<tbody>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>1</td>\n" +
                "            \t\t\t\t<td>Headquarters</td>\n" +
                "            \t\t\t\t<td>01-4537850</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>2</td>\n" +
                "            \t\t\t\t<td>Ajele</td>\n" +
                "            \t\t\t\t<td>01-3427556</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>3</td>\n" +
                "            \t\t\t\t<td>Agbara</td>\n" +
                "            \t\t\t\t<td>01-4537852</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>4</td>\n" +
                "            \t\t\t\t<td>Festac</td>\n" +
                "            \t\t\t\t<td>08164057278</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>5</td>\n" +
                "            \t\t\t\t<td>Ojo</td>\n" +
                "            \t\t\t\t<td>01-4537851</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>6</td>\n" +
                "            \t\t\t\t<td>Ijora</td>\n" +
                "            \t\t\t\t<td>01-3426058, </br>01-3426059</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>7</td>\n" +
                "            \t\t\t\t<td>Apapa</td>\n" +
                "            \t\t\t\t<td>08074108457</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>8</td>\n" +
                "            \t\t\t\t<td>Ibeju</td>\n" +
                "            \t\t\t\t<td>01-3427514</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>9</td>\n" +
                "            \t\t\t\t<td>Islands</td>\n" +
                "            \t\t\t\t<td>01-4532757</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>10</td>\n" +
                "            \t\t\t\t<td>Lekki</td>\n" +
                "            \t\t\t\t<td>01-4537853</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>11</td>\n" +
                "            \t\t\t\t<td>Mushin</td>\n" +
                "            \t\t\t\t<td>09030054664</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>12</td>\n" +
                "            \t\t\t\t<td>Orile</td>\n" +
                "            \t\t\t\t<td>01-4533332</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t</tbody>\n" +
                "            \t</table>           \t\n" +
                "\t\t\t");

        Contact c4 = new Contact();
        c4.setName("Ibadan Electricity Distribution");
        c4.setDesc("Oyo, Osun, Kwara, Kogi, Ogun");
        c4.setCode(Tariff.ibedc);
        c4.setContent("<table data-role=\"table\" data-mode=\"columntoggle\" class=\"ui-responsive\" id=\"myTable\">\n" +
                "            \t\t<thead>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<th data-priority=\"2\">ID</th>\n" +
                "            \t\t\t\t<th>Business Unit</th>\n" +
                "            \t\t\t\t<th data-priority=\"1\">Phone number</th>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t</thead>\n" +
                "            \t\t<tbody>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>1</td>\n" +
                "            \t\t\t\t<td>Headquaters</td>\n" +
                "            \t\t\t\t<td>09053698850</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>2</td>\n" +
                "            \t\t\t\t<td>Head</td>\n" +
                "            \t\t\t\t<td>09053698851</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>3</td>\n" +
                "            \t\t\t\t<td>Apata</td>\n" +
                "            \t\t\t\t<td>09053698852</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>4</td>\n" +
                "            \t\t\t\t<td>Akanran</td>\n" +
                "            \t\t\t\t<td>09053698853</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>5</td>\n" +
                "            \t\t\t\t<td>Baboko</td>\n" +
                "            \t\t\t\t<td>09053698854</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>6</td>\n" +
                "            \t\t\t\t<td>Challenge</td>\n" +
                "            \t\t\t\t<td>09053698855</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>7</td>\n" +
                "            \t\t\t\t<td>Dugbe</td>\n" +
                "            \t\t\t\t<td>09053698856</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>8</td>\n" +
                "            \t\t\t\t<td>Ede</td>\n" +
                "            \t\t\t\t<td>09053698857</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>9</td>\n" +
                "            \t\t\t\t<td>Ijebu-ode</td>\n" +
                "            \t\t\t\t<td>09053698858</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>10</td>\n" +
                "            \t\t\t\t<td>Ijeun</td>\n" +
                "            \t\t\t\t<td>09053698859</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>11</td>\n" +
                "            \t\t\t\t<td>Ikirun</td>\n" +
                "            \t\t\t\t<td>09053698860</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>12</td>\n" +
                "            \t\t\t\t<td>Ile-ife</td>\n" +
                "            \t\t\t\t<td>09053698861</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>13</td>\n" +
                "            \t\t\t\t<td>Ilesha</td>\n" +
                "            \t\t\t\t<td>09053698862</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>14</td>\n" +
                "            \t\t\t\t<td>Jebba</td>\n" +
                "            \t\t\t\t<td>09053698863</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>15</td>\n" +
                "            \t\t\t\t<td>Molete</td>\n" +
                "            \t\t\t\t<td>09053698864</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>16</td>\n" +
                "            \t\t\t\t<td>Monatan</td>\n" +
                "            \t\t\t\t<td>09053698865</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>17</td>\n" +
                "            \t\t\t\t<td>Oyo</td>\n" +
                "            \t\t\t\t<td>09053698866</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>18</td>\n" +
                "            \t\t\t\t<td>Ojoo</td>\n" +
                "            \t\t\t\t<td>09053698867</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>19</td>\n" +
                "            \t\t\t\t<td>Olumo</td>\n" +
                "            \t\t\t\t<td>09053698868</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>20</td>\n" +
                "            \t\t\t\t<td>Oshogbo</td>\n" +
                "            \t\t\t\t<td>09053698869</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>21</td>\n" +
                "            \t\t\t\t<td>Omuaran</td>\n" +
                "            \t\t\t\t<td>09053698870</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>22</td>\n" +
                "            \t\t\t\t<td>Ota</td>\n" +
                "            \t\t\t\t<td>09053698871</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>23</td>\n" +
                "            \t\t\t\t<td>Ogbomosho</td>\n" +
                "            \t\t\t\t<td>09053698872</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>24</td>\n" +
                "            \t\t\t\t<td>Sagamu</td>\n" +
                "            \t\t\t\t<td>09053698873</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t</tbody>\n" +
                "            \t</table>\n" +
                "\t\t\t");

        Contact c9 = new Contact();
        c9.setName("Ikeja Electricity Distribution");
        c9.setDesc("Lagos, Ogun");
        c9.setCode(Tariff.ikedcS);
        c9.setContent("<p>The Ikeja Electricity Distribution Company has a central control unit for customers. Customers benefiting from this DISCO will be patched to their respective area units when they call these numbers.</p>\n" +
                "\t\t\t\t<p><b>Line 1:</b> 01-4483900</p>\n" +
                "\t\t\t\t<p><b>Line 2:</b> 070002255453</p>");

        Contact c5 = new Contact();
        c5.setName("Jos Electricity Distribution");
        c5.setDesc("Plateau, Bauchi, Gombe, Benue");
        c5.setCode(Tariff.jedc);
        c5.setContent("<div class=\"ui-content\">\n" +
                "\t<p>Business Units of the Jos Electricity Distribution Company:</p>\n" +
                "\t<table data-role=\"table\" data-mode=\"columntoggle\" class=\"ui-responsive\" id=\"myTable\">\n" +
                "\t\t<thead>\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<th data-priority=\"2\">ID</th>\n" +
                "\t\t\t\t<th>Business Unit</th>\n" +
                "\t\t\t\t<th data-priority=\"1\">Phone number</th>\n" +
                "\t\t\t\t<th data-priority=\"3\">Email</th>\n" +
                "\t\t\t</tr>\n" +
                "\t\t</thead>\n" +
                "\t\t<tbody>\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<td>1</td>\n" +
                "\t\t\t\t<td>Anglo Jos</td>\n" +
                "\t\t\t\t<td> 08065782460</td>\n" +
                "\t\t\t\t<td>emmanuel.ameh@jedplc.com </td>\n" +
                "\t\t\t</tr>\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<td>2</td>\n" +
                "\t\t\t\t<td>Azare</td>\n" +
                "\t\t\t\t<td>08024136452 </td>\n" +
                "\t\t\t\t<td>abubakar.saad@jedplc.com</td>\n" +
                "\t\t\t</tr>\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<td>3</td>\n" +
                "\t\t\t\t<td>Bauchi </td>\n" +
                "\t\t\t\t<td> 08031188855</td>\n" +
                "\t\t\t\t<td>mohammed.aminu@jedplc.com </td>\n" +
                "\t\t\t</tr>\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<td>4</td>\n" +
                "\t\t\t\t<td>Bukuru</td>\n" +
                "\t\t\t\t<td>07031625373 </td>\n" +
                "\t\t\t\t<td>bitrus.samben@jedplc.com </td>\n" +
                "\t\t\t</tr>\n" +
                "\t\t\t\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<td>5</td>\n" +
                "\t\t\t\t<td>Gboko </td>\n" +
                "\t\t\t\t<td>08167046187</td>\n" +
                "\t\t\t\t<td>denen.aondoakaa@jedplc.com</td>\n" +
                "\t\t\t</tr>\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<td>6</td>\n" +
                "\t\t\t\t<tdGombe</td>\n" +
                "\t\t\t\t<td>08036147972</td>\n" +
                "\t\t\t\t<td>saad.abba@jedplc.com</td>\n" +
                "\t\t\t</tr>\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<td>7</td>\n" +
                "\t\t\t\t<td>Jos</td>\n" +
                "\t\t\t\t<td>08035962596</td>\n" +
                "\t\t\t\t<td>michael.igbang@jedplc.com </td>\n" +
                "\t\t\t</tr>\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<td>8</td>\n" +
                "\t\t\t\t<td>Makurdi</td>\n" +
                "\t\t\t\t<td>08033102195</td>\n" +
                "\t\t\t\t<td>john.emeruwa@jedplc.com</td>\n" +
                "\t\t\t</tr>\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<td>9</td>\n" +
                "\t\t\t\t<td>Otukpo</td>\n" +
                "\t\t\t\t<td>07032661155 </td>\n" +
                "\t\t\t\t<td>abdu.ocholi@jedplc.com </td>\n" +
                "\t\t\t</tr>      \n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<td>10</td>\n" +
                "\t\t\t\t<td>Pankshin</td>\n" +
                "\t\t\t\t<td>08023202539</td>\n" +
                "\t\t\t\t<td>gyang.tanko@jedplc.com  </td>\n" +
                "\t\t\t</tr>\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<td>11</td>\n" +
                "\t\t\t\t<td>Tudun Wada</td>\n" +
                "\t\t\t\t<td> 07032126510</td>\n" +
                "\t\t\t\t<td>zakariya.wada@jedplc.com </td>\n" +
                "\t\t\t</tr>          \t\t\t\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<td>12</td>\n" +
                "\t\t\t\t<td>Yelwa</td>\n" +
                "\t\t\t\t<td>08035555567</td>\n" +
                "\t\t\t\t<td>babajada.auwalu@jedplc.com</td>\n" +
                "\t\t\t</tr>\n" +
                "\t\t</tbody>\n" +
                "\t</table>\n" +
                "</div>\n");

        Contact c6 = new Contact();
        c6.setName("Kaduna Electricity Distribution");
        c6.setDesc("Kaduna, Zamfara, Sokoto, Kebbi");
        c6.setCode(Tariff.kaedcS);
        c6.setContent("<p>Business Units of the Kaduna Distribution Company:</p>\n" +
                "            \t<table data-role=\"table\" data-mode=\"columntoggle\" class=\"ui-responsive\" id=\"myTable\">\n" +
                "            \t\t<thead>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<th data-priority=\"2\">ID</th>\n" +
                "            \t\t\t\t<th>Business Unit</th>\n" +
                "            \t\t\t\t<th data-priority=\"1\">Phone number</th>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t</thead>\n" +
                "            \t\t<tbody>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>1</td>\n" +
                "            \t\t\t\t<td>Kawo</td>\n" +
                "            \t\t\t\t<td>08032607794</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>2</td>\n" +
                "            \t\t\t\t<td>Sabon Tasha</td>\n" +
                "            \t\t\t\t<td>07085619259</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>3</td>\n" +
                "            \t\t\t\t<td>Sabon Gari</td>\n" +
                "            \t\t\t\t<td>08097163747</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>4</td>\n" +
                "            \t\t\t\t<td>Doka</td>\n" +
                "            \t\t\t\t<td>07037700184</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>5</td>\n" +
                "            \t\t\t\t<td>Tudun Wada</td>\n" +
                "            \t\t\t\t<td>08034521698</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>6</td>\n" +
                "            \t\t\t\t<td>Makera</td>\n" +
                "            \t\t\t\t<td>08035933499</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>7</td>\n" +
                "            \t\t\t\t<td>Jere</td>\n" +
                "            \t\t\t\t<td>08035283744</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>8</td>\n" +
                "            \t\t\t\t<td>Kachia</td>\n" +
                "            \t\t\t\t<td>08025711055</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>9</td>\n" +
                "            \t\t\t\t<td>Samaru</td>\n" +
                "            \t\t\t\t<td>08093682413</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>10</td>\n" +
                "            \t\t\t\t<td>Kafanchan</td>\n" +
                "            \t\t\t\t<td>07036984148</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>11</td>\n" +
                "            \t\t\t\t<td>Saminaka</td>\n" +
                "            \t\t\t\t<td>07036663971</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>12</td>\n" +
                "            \t\t\t\t<td>Rigasa</td>\n" +
                "            \t\t\t\t<td>08036520190</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>13</td>\n" +
                "            \t\t\t\t<td>Ungwa Rimi</td>\n" +
                "            \t\t\t\t<td>07033457893</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>14</td>\n" +
                "            \t\t\t\t<td>Zaria City</td>\n" +
                "            \t\t\t\t<td>08023786909</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>15</td>\n" +
                "            \t\t\t\t<td>Jaji</td>\n" +
                "            \t\t\t\t<td>08036052641</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>16</td>\n" +
                "            \t\t\t\t<td>Birnin Gearin</td>\n" +
                "            \t\t\t\t<td>08037326227</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>17</td>\n" +
                "            \t\t\t\t<td>Kaura Namoda</td>\n" +
                "            \t\t\t\t<td>08027210257</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>18</td>\n" +
                "            \t\t\t\t<td>Gusau</td>\n" +
                "            \t\t\t\t<td>08037875200</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>19</td>\n" +
                "            \t\t\t\t<td>Talata Mafara</td>\n" +
                "            \t\t\t\t<td>08037380454</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>20</td>\n" +
                "            \t\t\t\t<td>Gwiwa</td>\n" +
                "            \t\t\t\t<td>080389973000</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>21</td>\n" +
                "            \t\t\t\t<td>Marina</td>\n" +
                "            \t\t\t\t<td>08163689537</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>22</td>\n" +
                "            \t\t\t\t<td>Ungwa Rogo</td>\n" +
                "            \t\t\t\t<td>08035474422</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>23</td>\n" +
                "            \t\t\t\t<td>Birnin Kebbi</td>\n" +
                "            \t\t\t\t<td>08065818930</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>24</td>\n" +
                "            \t\t\t\t<td>Zuru</td>\n" +
                "            \t\t\t\t<td>08033910949</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>25</td>\n" +
                "            \t\t\t\t<td>Argungu</td>\n" +
                "            \t\t\t\t<td>09026120948</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>26</td>\n" +
                "            \t\t\t\t<td>Jega</td>\n" +
                "            \t\t\t\t<td>08103536011</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t</tbody>\n" +
                "            \t</table>\n" +
                "\t\t\t");

        Contact c7 = new Contact();
        c7.setName("Kano Electricity Distribution");
        c7.setDesc("Kano, Jigawa, Katsina");
        c7.setCode(Tariff.kedcA);
        c7.setContent("<p>Business Units of the Kano Distribution Company:</p>\n" +
                "            \t<table data-role=\"table\" data-mode=\"columntoggle\" class=\"ui-responsive\" id=\"myTable\">\n" +
                "            \t\t<thead>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<th data-priority=\"2\">ID</th>\n" +
                "            \t\t\t\t<th>Business Unit</th>\n" +
                "            \t\t\t\t<th data-priority=\"1\">Phone number</th>\n" +
                "            \t\t\t\t<th data-priority=\"3\">Email</th>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t</thead>\n" +
                "            \t\t<tbody>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>1</td>\n" +
                "            \t\t\t\t<td>Headquarters</td>\n" +
                "            \t\t\t\t<td>070055551111</td>\n" +
                "            \t\t\t\t<td></td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>2</td>\n" +
                "            \t\t\t\t<td>Dala</td>\n" +
                "            \t\t\t\t<td>08023667350</td>\n" +
                "            \t\t\t\t<td>adamu.sani@kedco.ng</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>3</td>\n" +
                "            \t\t\t\t<td>Dakata</td>\n" +
                "            \t\t\t\t<td>08033297570</td>\n" +
                "            \t\t\t\t<td>hashimu.mera@kedco.ng</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>4</td>\n" +
                "            \t\t\t\t<td>Dutse</td>\n" +
                "            \t\t\t\t<td>08071717717</td>\n" +
                "            \t\t\t\t<td>aminu.ibrahim@kedco.ng</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>5</td>\n" +
                "            \t\t\t\t<td>Funtua</td>\n" +
                "            \t\t\t\t<td>08137508618</td>\n" +
                "            \t\t\t\t<td>sanusi.yahaya@kedco.ng</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>6</td>\n" +
                "            \t\t\t\t<td>Hadejia</td>\n" +
                "            \t\t\t\t<td>08036061912</td>\n" +
                "            \t\t\t\t<td>isa.maidoya@kedco.ng</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>7</td>\n" +
                "            \t\t\t\t<td>Kabuga</td>\n" +
                "            \t\t\t\t<td>08098154105</td>\n" +
                "            \t\t\t\t<td>martins.galadima@kedco.ng</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>8</td>\n" +
                "            \t\t\t\t<td>Katsina</td>\n" +
                "            \t\t\t\t<td>07034859346</td>\n" +
                "            \t\t\t\t<td>ismail.jaafar@kedco.ng</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>9</td>\n" +
                "            \t\t\t\t<td>Kumbotso</td>\n" +
                "            \t\t\t\t<td>08034284014</td>\n" +
                "            \t\t\t\t<td>ahmed.iliasu@kedco.ng</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>10</td>\n" +
                "            \t\t\t\t<td>Mariri</td>\n" +
                "            \t\t\t\t<td>08033288526</td>\n" +
                "            \t\t\t\t<td>olabode.akinyimika@kedco.ng</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>11</td>\n" +
                "            \t\t\t\t<td>Nassarawa</td>\n" +
                "            \t\t\t\t<td>08037031647</td>\n" +
                "            \t\t\t\t<td>ahmad.elnafaty@kedco.ng</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>12</td>\n" +
                "            \t\t\t\t<td>Sabon Gari</td>\n" +
                "            \t\t\t\t<td>08035805210</td>\n" +
                "            \t\t\t\t<td>kabiru.galadima@kedco.ng</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>13</td>\n" +
                "            \t\t\t\t<td>Sharada</td>\n" +
                "            \t\t\t\t<td>08061311449</td>\n" +
                "            \t\t\t\t<td>binta.abubakar@kedco.ng</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t</tbody>\n" +
                "            \t</table>\n" +
                "\t\t\t");

        Contact c8 = new Contact();
        c8.setName("Portharcourt Electricity Distribution");
        c8.setDesc("Akwa Ibom, Bayelsa, Cross River, Rivers");
        c8.setCode(Tariff.phedc);
        c8.setContent("<p>Business Units of the Portharcourt Distribution Company:</p>\n" +
                "            \t<table data-role=\"table\" data-mode=\"columntoggle\" class=\"ui-responsive\" id=\"myTable\">\n" +
                "            \t\t<thead>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<th data-priority=\"2\">ID</th>\n" +
                "            \t\t\t\t<th>Business Unit</th>\n" +
                "            \t\t\t\t<th data-priority=\"1\">Phone number</th>\n" +
                "            \t\t\t\t<th data-priority=\"3\">Email</th>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t</thead>\n" +
                "            \t\t<tbody>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>1</td>\n" +
                "            \t\t\t\t<td>Headquarters</td>\n" +
                "            \t\t\t\t<td>084303451,</br> 084303452,</br> 08139834000,</br> 08139834001</td>\n" +
                "            \t\t\t\t<td>customercare@phed.com.ng</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>2</td>\n" +
                "            \t\t\t\t<td>Garden City Central</td>\n" +
                "            \t\t\t\t<td>084303448,</br> 07063211185</td>\n" +
                "            \t\t\t\t<td>customercare.rumuola@phed.com.ng</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>3</td>\n" +
                "            \t\t\t\t<td>Garden City East</td>\n" +
                "            \t\t\t\t<td>084303208, 08033020432</td>\n" +
                "            \t\t\t\t<td>customercare.diobu@phed.com.ng</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>4</td>\n" +
                "            \t\t\t\t<td>Garden City Industrial</td>\n" +
                "            \t\t\t\t<td>084303209, </br>08167046187</td>\n" +
                "            \t\t\t\t<td>customercare.transamadi@phed.com.ng</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>5</td>\n" +
                "            \t\t\t\t<td>Garden City Main</td>\n" +
                "            \t\t\t\t<td>084303207, </br>08094695034</td>\n" +
                "            \t\t\t\t<td>customercare.borokiri@phed.com.ng</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>6</td>\n" +
                "            \t\t\t\t<td>Garden City New</td>\n" +
                "            \t\t\t\t<td>084303448, </br>08033129007</td>\n" +
                "            \t\t\t\t<td>customercare.rumuodomaya@phed.com.ng</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>7</td>\n" +
                "            \t\t\t\t<td>Glory City Main</td>\n" +
                "            \t\t\t\t<td>084303469, </br>08062581585</td>\n" +
                "            \t\t\t\t<td>customercare.yenagoa@phed.com.ng</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>8</td>\n" +
                "            \t\t\t\t<td>Paradise City Main</td>\n" +
                "            \t\t\t\t<td>087291156, </br>08099165035</td>\n" +
                "            \t\t\t\t<td>customercare.calabar@phed.com.ng</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>9</td>\n" +
                "            \t\t\t\t<td>Paradise City North</td>\n" +
                "            \t\t\t\t<td>08029196675, </br>08035523869</td>\n" +
                "            \t\t\t\t<td>customercare.ikom@phed.com.ng</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>10</td>\n" +
                "            \t\t\t\t<td>Promise City Main</td>\n" +
                "            \t\t\t\t<td>085293213, </br>08066081400</td>\n" +
                "            \t\t\t\t<td>customercare.uyo@phed.com.ng</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>11</td>\n" +
                "            \t\t\t\t<td>Promise City South</td>\n" +
                "            \t\t\t\t<td>085293215, </br>08034720529</td>\n" +
                "            \t\t\t\t<td>customercare.eket@phed.com.ng</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t</tbody>\n" +
                "            \t</table>\n" +
                "\t\t\t");

        Contact c10 = new Contact();
        c10.setName("Yola Distribution");
        c10.setDesc("Adamawa, Borno, Taraba, Yobe");
        c10.setCode(Tariff.yedcS);
        c10.setContent("<table data-role=\"table\" data-mode=\"columntoggle\" class=\"ui-responsive\" id=\"myTable\">\n" +
                "            \t\t<thead>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<th data-priority=\"2\">ID</th>\n" +
                "            \t\t\t\t<th>Business Unit</th>\n" +
                "            \t\t\t\t<th data-priority=\"1\">Phone number</th>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t</thead>\n" +
                "            \t\t<tbody>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>1</td>\n" +
                "            \t\t\t\t<td>BIU</td>\n" +
                "            \t\t\t\t<td>08065159484</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>2</td>\n" +
                "            \t\t\t\t<td>Bulumkutu</td>\n" +
                "            \t\t\t\t<td>08067654212</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>3</td>\n" +
                "            \t\t\t\t<td>Damaturu</td>\n" +
                "            \t\t\t\t<td>08036071690</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>4</td>\n" +
                "            \t\t\t\t<td>Gombe</td>\n" +
                "            \t\t\t\t<td>08123293663</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>5</td>\n" +
                "            \t\t\t\t<td>Jalingo</td>\n" +
                "            \t\t\t\t<td>08039650396</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>6</td>\n" +
                "            \t\t\t\t<td>Jimeta</td>\n" +
                "            \t\t\t\t<td>08065928533</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>7</td>\n" +
                "            \t\t\t\t<td>Nguru</td>\n" +
                "            \t\t\t\t<td>09038853294</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>8</td>\n" +
                "            \t\t\t\t<td>Numan</td>\n" +
                "            \t\t\t\t<td>07039107008</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>9</td>\n" +
                "            \t\t\t\t<td>Mubi</td>\n" +
                "            \t\t\t\t<td>08036182848</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>10</td>\n" +
                "            \t\t\t\t<td>Potiskum</td>\n" +
                "            \t\t\t\t<td>08034595624</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>11</td>\n" +
                "            \t\t\t\t<td>Wukari</td>\n" +
                "            \t\t\t\t<td>08036240051</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>12</td>\n" +
                "            \t\t\t\t<td>Yelwa Yola</td>\n" +
                "            \t\t\t\t<td>08034959389</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t\t<tr>\n" +
                "            \t\t\t\t<td>13</td>\n" +
                "            \t\t\t\t<td>Yerwa</td>\n" +
                "            \t\t\t\t<td>08069574900</td>\n" +
                "            \t\t\t</tr>\n" +
                "            \t\t</tbody>\n" +
                "            \t</table>\n" +
                "\t\t\t");


        allContacts.add(c);
        allContacts.add(c1);
        allContacts.add(c2);
        allContacts.add(c3);
        allContacts.add(c4);
        allContacts.add(c9);
        allContacts.add(c5);
        allContacts.add(c6);
        allContacts.add(c7);
        allContacts.add(c8);
        allContacts.add(c10);

        return allContacts;
    }
}
