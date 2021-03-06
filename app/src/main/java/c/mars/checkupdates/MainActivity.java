package c.mars.checkupdates;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.concurrent.Callable;

import butterknife.ButterKnife;
import butterknife.InjectView;
import timber.log.Timber;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.text)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);
        Timber.plant(new Timber.DebugTree());

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {

//                AndroidMarketApi (needs sign in)
//                PlayStoreApi
                        AndroidQuery.test("com.google.android.gm", new Display() {
                    @Override
                    public void call(final String text) {
                        runOnUiThread(new Runnable() {
                                          @Override
                                          public void run() {
                                              textView.append(text + "\n");
                                          }
                                      }
                        );
                    }
                });

                return null;
            }
        }.execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
