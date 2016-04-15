package moe.banana.dagger2example;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import moe.banana.dagger2example.ioc.DaggerActivity;

@Module(includes = Application.class)
public class MainActivity extends DaggerActivity<MainActivityDelegate> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Will call `createDelegate`.
        // Make sure that all dependencies can be resolved correctly before calling this method.
        super.performDelegateCreate(savedInstanceState);
    }

    @Provides
    @Named("drawer")
    public DrawerLayout provideDrawerLayout() {
        return (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    @Provides
    @Named("navigation")
    public NavigationView provideNavigationView() {
        return (NavigationView) findViewById(R.id.nav_view);
    }

    @Provides
    @Named("toolbar")
    public Toolbar provideToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    @Provides
    @Named("fab")
    public FloatingActionButton provideFloatingActionButton() {
        return (FloatingActionButton) findViewById(R.id.fab);
    }

    @Override
    public MainActivityDelegate createDelegate() {
        return DaggerMainActivityComponent.builder()
                .application(((Application) getApplication()))
                .mainActivity(this).build().delegate();
    }

    // Dagger2 does not allow an overridden method annotated `@Provides`
    @Provides
    public DaggerActivity.InvocationAgent provideInvocationAgent() {
        return daggerProvideInvocationAgent();
    }

}
