package com.example.paygateway

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject

class MainActivity : AppCompatActivity(), PaymentResultListener {
    private lateinit var pay:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pay=findViewById(R.id.pay)
        pay.setOnClickListener{
            makePayment()
        }
    }

    private fun makePayment() {
        val co = Checkout()

        try {
            val options = JSONObject()
            options.put("name","Sarfraz1k")
            options.put("description","Owner")
            //You can omit the image option to fetch the image from the dashboard
            options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg")
            options.put("theme.color", "#40480");
            options.put("currency","INR");
            //options.put("order_id", "order_DBJOWzybf0sJbb");
            options.put("amount","10100")//pass amount in currency subunits

          //  val retryObj = JSONObject();
          //  retryObj.put("enabled", true);
          //  retryObj.put("max_count", 4);
          //  options.put("retry", retryObj);

            val prefill = JSONObject()
            prefill.put("email","")
            prefill.put("contact","")

            options.put("prefill",prefill)
            co.open(this,options)
        }catch (e: Exception){
            Toast.makeText(this,"Error in payment: "+ e.message, Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    override fun onPaymentSuccess(p0: String?) {
      Toast.makeText(this,"Payment Success",Toast.LENGTH_LONG).show()
        pay=findViewById(R.id.pay)
        pay.visibility=View.VISIBLE

    }

    override fun onPaymentError(p0: Int, p1: String?) {
       Toast.makeText(this,"Something went wrong!",Toast.LENGTH_LONG).show()
        pay=findViewById(R.id.pay)
        pay.visibility=View.VISIBLE

    }

}