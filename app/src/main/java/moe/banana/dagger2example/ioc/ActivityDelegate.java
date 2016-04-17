package moe.banana.dagger2example.ioc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Base class for all ActivityDelegate
 *
 * (This class should proxy a full set of methods in Activity through InvocationAgent to act as an Activity)
 */
public class ActivityDelegate {

    public DaggerActivity.InvocationAgent mInvocationAgent;

    public ActivityDelegate() { }

    public ActivityDelegate(DaggerActivity.InvocationAgent invocationAgent) {
        setInvocationAgent(invocationAgent);
    }

    public void setInvocationAgent(DaggerActivity.InvocationAgent agent) {
        mInvocationAgent = agent;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        mInvocationAgent.onCreate(savedInstanceState);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mInvocationAgent.onRestoreInstanceState(savedInstanceState);
    }

    protected void onStart() {
        mInvocationAgent.onStart();
    }

    protected void onResume() {
        mInvocationAgent.onResume();
    }

    protected void onSaveInstanceState(Bundle outState) {
        mInvocationAgent.onSaveInstanceState(outState);
    }

    protected void onPause() {
        mInvocationAgent.onPause();
    }

    protected void onStop() {
        mInvocationAgent.onStop();
    }

    protected void onRestart() {
        mInvocationAgent.onRestart();
    }

    protected void onDestroy() {
        mInvocationAgent.onDestroy();
    }

    public void onBackPressed() {
        mInvocationAgent.onBackPressed();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return mInvocationAgent.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return mInvocationAgent.onOptionsItemSelected(item);
    }

    public DaggerActivity getActivity() {
        return mInvocationAgent.getActivity();
    }

    public void setSupportActionBar(Toolbar toolbar) {
        mInvocationAgent.setSupportActionBar(toolbar);
    }

    public MenuInflater getMenuInflater() {
        return mInvocationAgent.getMenuInflater();
    }

}
