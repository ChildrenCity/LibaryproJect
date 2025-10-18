package com.example.projectilm;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Cart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        package com.example.cartapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

        public class CartActivity extends AppCompatActivity {

            LinearLayout cartLayout;
            TextView totalPrice;
            Button btnClear;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_cart);

                cartLayout = findViewById(R.id.cartLayout);
                totalPrice = findViewById(R.id.totalPrice);
                btnClear = findViewById(R.id.btnClear);

                loadCart();

                btnClear.setOnClickListener(v -> {
                    CartManager.getInstance().clearCart();
                    loadCart();
                });
            }

            private void loadCart() {
                cartLayout.removeAllViews();
                for (Product p : CartManager.getInstance().getCartItems()) {
                    TextView item = new TextView(this);
                    item.setText(p.getName() + " - " + p.getPrice() + " บาท");
                    item.setPadding(20, 10, 20, 10);
                    cartLayout.addView(item);
                }
                totalPrice.setText("ราคารวม: " + CartManager.getInstance().getTotalPrice() + " บาท");
            }
        }

    }
}