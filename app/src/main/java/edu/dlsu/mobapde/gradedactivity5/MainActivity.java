package edu.dlsu.mobapde.gradedactivity5;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class MainActivity extends AppCompatActivity {

    int selectedItemId = R.id.nav_p1;
    ImageView image_r, image_l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image_r = findViewById(R.id.image_r);

        image_l = findViewById(R.id.image_l);

        Toast.makeText(this, "Subtract age of farmer from the answer", Toast.LENGTH_LONG).show();
        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            BottomNavigationView navigation = findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {

                        @Override
                        public boolean onNavigationItemSelected(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.nav_p1:
                                    image_l.setImageResource(R.drawable.puzzle1_l);
                                    return true;
                                case R.id.nav_p2:
                                    image_l.setImageResource(R.drawable.puzzle2_l);
                                    return true;
                                case R.id.nav_p3:
                                    image_l.setImageResource(R.drawable.puzzle3_l);
                                    return true;
                                case R.id.nav_ans:
                                    answer();
                                    return true;
                            }
                            return false;
                        }
                    });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.navigation, menu);

            return true;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_p1:
                image_r.setImageResource(R.drawable.puzzle1_p);
                return true;
            case R.id.nav_p2:
                image_r.setImageResource(R.drawable.puzzle2_p);
                return true;
            case R.id.nav_p3:
                image_r.setImageResource(R.drawable.puzzle3_p);
                return true;
            case R.id.nav_ans:
                answer();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void answer() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("What is the total of the painter's life?");

        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.answersheet, null));

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Log.d("Answer is","OKed");
                EditText editText = findViewById(R.id.answerField);
                if (editText.getText().toString().equals("143")) {

                }
                else {
                    Toast.makeText(getApplicationContext(), "Not what the painter had in mind.", Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Log.d("Answer is","Cancelled");
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
