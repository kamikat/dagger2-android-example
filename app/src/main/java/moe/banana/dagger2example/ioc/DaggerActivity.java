package moe.banana.dagger2example.ioc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public abstract class DaggerActivity<D extends ActivityDelegate> extends AppCompatActivity {

    private D mActivityDelegate;

    protected final void performDelegateCreate(@Nullable Bundle savedInstanceState) {
        mActivityDelegate = createDelegate();
        mActivityDelegate.onCreate(savedInstanceState);
    }

    public abstract D createDelegate();

    // Proxy-pass activity events to delegate

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
