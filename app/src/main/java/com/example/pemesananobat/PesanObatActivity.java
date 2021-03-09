package com.example.pemesananobat;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import static android.R.string.no;
import static android.os.Build.VERSION_CODES.N;
/**
 * This app displays an order form to order coffee.
 */

public class PesanObatActivity extends AppCompatActivity {
    int quantity=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_obat);
    }
    public void increment(View view){//perintah tombol tambah
        if(quantity==100){
            Toast.makeText(this,"pesanan maximal 100",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity+1 ;
        display(quantity);
    }
    public void decrement(View view){//perintah tombol tambah
        if (quantity==1){
            Toast.makeText(this,"pesanan minimal 1",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity -1;
        display(quantity);
    }
    public void Submitorder(View view) {
        EditText nameEditText=(EditText)findViewById(R.id.edt_name);
        String name=nameEditText.getText().toString();
        Log.v("MainActivity","Nama:"+name);

        CheckBox SiCepatChekBox= (CheckBox) findViewById(R.id.SiCepat_checkbox);
        boolean hasSiCepat=SiCepatChekBox.isChecked();//mengidentifikasi check
        Log.v("MainActivity","has SiCepat:"+hasSiCepat);

        CheckBox JneChekBox= (CheckBox) findViewById(R.id.Jne_checkbox);
        boolean hasJne=JneChekBox.isChecked();//mengidentifikasi check
        Log.v("MainActivity","has siCepat:"+hasJne);

        int price=calculateprice(hasSiCepat,hasJne);//memanggil method jumlah harga
        String pricemessage=createOrderSummary(price,name,hasSiCepat,hasSiCepat);
        displayMessage(pricemessage);
    }
    private int calculateprice(boolean addSiCepat,boolean addJne){//jumlah pesanan * harga
        int harga=5000;
        if(addSiCepat){
            harga=harga+1000;//harga tambahan pengiriman
        }
        if (addJne){
            harga=harga+2000;
        }
        return quantity * harga;
    }
    private String createOrderSummary(int price, String name, boolean addSiCepat, boolean addJne) {//hasil pemesanan
        String pricemessage=" Nama = "+name;
        pricemessage+="\n Jasa Ekspedisi SiCepat =" +addSiCepat;
        pricemessage+="\n Jasa Ekspedisi Jne =" +addJne;
        pricemessage+="\n Jumlah Pemesanan =" +quantity;
        pricemessage+="\n Total = Rp " +price;
        pricemessage+="\n Thank You for order";
        return  pricemessage;
    }
    //method ini untuk mencetak hasil perintah yang di tampilkan dengan inisial quantity_textview di textview 0
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_textview);
        priceTextView.setText(message);
    }
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_textview);
        quantityTextView.setText("" + number);
    }
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_textview);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
}