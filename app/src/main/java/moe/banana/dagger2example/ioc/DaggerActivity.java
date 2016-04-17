package moe.banana.dagger2example.ioc;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

@SuppressLint("MissingSuperCall")
public abstract class DaggerActivity<D extends ActivityDelegate> extends AppCompatActivity {

    private ActivityDelegate mActivityDelegate = new ActivityDelegate(new InvocationAgent());

    protected final void performDelegateCreate(@Nullable Bundle savedInstanceState) {
        mActivityDelegate = createDelegate();
        mActivityDelegate.setInvocationAgent(new InvocationAgent());
        mActivityDelegate.onCreate(savedInstanceState);
    }

    public abstract D createDelegate();

    // Proxy-pass activity events to delegate

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mActivityDelegate.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onStart() {
        mActivityDelegate.onStart();
    }

    @Override
    protected void onResume() {
        mActivityDelegate.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mActivityDelegate.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        mActivityDelegate.onPause();
    }

    @Override
    protected void onStop() {
        mActivityDelegate.onStop();
    }

    @Override
    protected void onRestart() {
        mActivityDelegate.onRestart();
    }

    @Override
    protected void onDestroy() {
        mActivityDelegate.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return mActivityDelegate.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mActivityDelegate.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        mActivityDelegate.onBackPressed();
    }

    // more...

    /**
     * Access methods (public, protected) in Activity from ActivityDelegate
     *
     * (This class should contains a full set of methods in an Activity)
     */
    public class InvocationAgent {
        public DaggerActivity getActivity() {
            return DaggerActivity.this;
        }

        public void onCreate(@Nullable Bundle savedInstanceState) {
            // called after DaggerActivity.performDelegateCreate(Bundle)
        }

        protected void onRestoreInstanceState(Bundle savedInstanceState) {
            DaggerActivity.super.onRestoreInstanceState(savedInstanceState);
        }

        protected void onStart() {
            DaggerActivity.super.onStart();
        }

        protected void onResume() {
            DaggerActivity.super.onResume();
        }

        protected void onSaveInstanceState(Bundle outState) {
            DaggerActivity.super.onSaveInstanceState(outState);
        }

        protected void onPause() {
            DaggerActivity.super.onPause();
        }

        protected void onStop() {
            DaggerActivity.super.onStop();
        }

        protected void onRestart() {
            DaggerActivity.super.onRestart();
        }

        protected void onDestroy() {
            DaggerActivity.super.onDestroy();
        }

        public boolean onCreateOptionsMenu(Menu menu) {
            return DaggerActivity.super.onCreateOptionsMenu(menu);
        }

        public boolean onOptionsItemSelected(MenuItem item) {
            return DaggerActivity.super.onOptionsItemSelected(item);
        }

        public void onBackPressed() {
            DaggerActivity.super.onBackPressed();
        }

        public void setSupportActionBar(Toolbar toolbar) {
            DaggerActivity.this.setSupportActionBar(toolbar);
        }

        public MenuInflater getMenuInflater() {
            return DaggerActivity.this.getMenuInflater();
        }
    }

    private InvocationAgent mInvocationAgent = new InvocationAgent();

    public InvocationAgent daggerProvideInvocationAgent() {
        return mInvocationAgent;
    }

}
