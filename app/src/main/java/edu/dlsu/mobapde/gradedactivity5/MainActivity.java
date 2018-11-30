package edu.dlsu.mobapde.gradedactivity5;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
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

    ImageView image_r, image_l;
    private static final String CHANNEL_ID = "ph.edu.dlsu.a09a_notifications.NOTIFICATION";
    private int notificationID = 146;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image_r = findViewById(R.id.image_r);

        image_l = findViewById(R.id.image_l);

        Toast.makeText(this, "Subtract the painter’s age(30) from the answer", Toast.LENGTH_LONG).show();
        if (getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT) {
            BottomNavigationView navigation = findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {

                        @Override
                        public boolean onNavigationItemSelected(MenuItem item) {
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
                            }
                            return false;
                        }
                    });
            navigation.setSelectedItemId(R.id.nav_p1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
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
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void answer() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("What is the total of the painter's life?");

        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.answersheet, null));

        final AlertDialog dialog = builder.create();
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText editText = dialog.findViewById(R.id.answerField);
                if (editText.getText().toString().equals("143")) {
                    createNotificationChannel();
                    createBasicNotification();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Not what the painter had in mind.", Toast.LENGTH_LONG).show();
                }
            }
        });

        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("Cancel", "led");
            }
        });
        dialog.show();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Winner", importance);
            channel.setDescription("Some Winning Channel");

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }
    private void createBasicNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);

        builder.setSmallIcon(R.drawable.notification_icon);
        builder.setContentTitle("Winner!");
        builder.setContentText("The painter rests easy now!");
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);

        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.notification_icon_big));
        builder.setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(notificationID, builder.build());
        notificationID++;
    }
}
